package org.ulpgc.eii.dacd.datamart;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTablesFactory {
    public void createMaxTemperaturesTable() {
        String url = "jdbc:sqlite:database" + File.separator + "datamart.db";
        String sql = "CREATE TABLE IF NOT EXISTS maxTemperatures (DATE text, TIME text, PLACE text, STATION text, VALUE real)";
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
             statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createMinTemperaturesTable() {
        String url = "jdbc:sqlite:database" + File.separator + "datamart.db";
        String sql = "CREATE TABLE IF NOT EXISTS minTemperatures (DATE text, TIME text, PLACE text, STATION text, VALUE real)";
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
             statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}