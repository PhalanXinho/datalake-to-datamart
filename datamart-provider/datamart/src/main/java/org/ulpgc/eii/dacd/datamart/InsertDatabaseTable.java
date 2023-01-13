package org.ulpgc.eii.dacd.datamart;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertDatabaseTable {
    public void insertMaxTemperaturesTable(String date, String time, String place, String station, double value) {
        String sql = "INSERT OR IGNORE INTO maxTemperatures (DATE, TIME, PLACE, STATION, VALUE) VALUES (?,?,?,?,?)";
        try (Connection connection = this.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             preparedStatement.setString(1, date);
             preparedStatement.setString(2, time);
             preparedStatement.setString(3, place);
             preparedStatement.setString(4, station);
             preparedStatement.setDouble(5, value);
             preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertMinTemperaturesTable(String date, String time, String place, String station, double value) {
        String sql = "INSERT OR IGNORE INTO minTemperatures (DATE, TIME, PLACE, STATION, VALUE) VALUES (?,?,?,?,?)";
        try (Connection connection = this.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
             preparedStatement.setString(1, date);
             preparedStatement.setString(2, time);
             preparedStatement.setString(3, place);
             preparedStatement.setString(4, station);
             preparedStatement.setDouble(5, value);
             preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        String url = "jdbc:sqlite:database" + File.separator + "datamart.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
