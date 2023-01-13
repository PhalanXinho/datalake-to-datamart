package org.ulpgc.eii.dacd.datamart;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DropTable {
    public void dropMaxTable() {
        String url = "jdbc:sqlite:database" + File.separator + "datamart.db";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS maxTemperatures")) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropMinTable() {
        String url = "jdbc:sqlite:database" + File.separator + "datamart.db";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS minTemperatures")) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}