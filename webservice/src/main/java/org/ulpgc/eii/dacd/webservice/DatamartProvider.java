package org.ulpgc.eii.dacd.webservice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatamartProvider {
    public List<String> getStationIds() {
        List<String> idList = new ArrayList<>();
        String url = "jdbc:sqlite:database\\datamart.db";
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT DISTINCT STATION FROM maxTemperatures")) {
             while (resultSet.next()) {
                idList.add(resultSet.getString(1));
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idList;
    }
}