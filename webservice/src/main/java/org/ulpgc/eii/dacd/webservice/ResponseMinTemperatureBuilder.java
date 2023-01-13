package org.ulpgc.eii.dacd.webservice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResponseMinTemperatureBuilder {
    public List<Response> build() throws SQLException {
        List<String> stationIds = new DatamartProvider().getStationIds();
        List<Response> responseList = new ArrayList<>();
        String url = "jdbc:sqlite:database\\datamart.db";
        String sql = "SELECT DATE, TIME, PLACE, MIN(VALUE) FROM minTemperatures WHERE STATION = ?";
        Connection conn = DriverManager.getConnection(url);
        for (String id : stationIds) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { responseList.add(construct(resultSet)); }
        }
        return responseList;
    }

    public List<Response> buildWithTo(String to) throws SQLException {
        List<String> stationIds = new DatamartProvider().getStationIds();
        List<Response> responseList = new ArrayList<>();
        String url = "jdbc:sqlite:database\\datamart.db";
        String sql = "SELECT DATE, TIME, PLACE, MIN(VALUE) FROM minTemperatures WHERE DATE <= ? AND STATION = ?";
        Connection conn = DriverManager.getConnection(url);
        for (String id : stationIds) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, to);
            preparedStatement.setString(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { responseList.add(construct(resultSet)); }
        }
        return responseList;
    }

    public List<Response> buildWithFrom(String from) throws SQLException {
        List<String> stationIds = new DatamartProvider().getStationIds();
        List<Response> responseList = new ArrayList<>();
        String url = "jdbc:sqlite:database\\datamart.db";
        String sql = "SELECT DATE, TIME, PLACE, MIN(VALUE) FROM minTemperatures WHERE DATE >= ? AND STATION = ?";
        Connection conn = DriverManager.getConnection(url);
        for (String id : stationIds) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, from);
            preparedStatement.setString(2, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { responseList.add(construct(resultSet)); }
        }
        return responseList;
    }

    public List<Response> buildWithBoth(String from, String to) throws SQLException {
        List<String> stationIds = new DatamartProvider().getStationIds();
        List<Response> responseList = new ArrayList<>();
        String url = "jdbc:sqlite:database\\datamart.db";
        String sql = "SELECT DATE, TIME, PLACE, MIN(VALUE) FROM minTemperatures WHERE DATE BETWEEN ? AND ? AND STATION = ?";
        Connection conn = DriverManager.getConnection(url);
        for (String id : stationIds) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, from);
            preparedStatement.setString(2, to);
            preparedStatement.setString(3, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { responseList.add(construct(resultSet)); }
        }
        return responseList;
    }

    private Response construct(ResultSet resultSet) throws SQLException {
        String date = resultSet.getString(1);
        String time = resultSet.getString(2);
        String place = resultSet.getString(3);
        double value = resultSet.getDouble(4);
        return new Response(date, time, place, value);
    }
}
