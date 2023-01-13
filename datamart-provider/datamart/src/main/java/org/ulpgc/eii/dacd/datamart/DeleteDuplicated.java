package org.ulpgc.eii.dacd.datamart;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DeleteDuplicated {
    public void maxTabledeleter(String dateFormat) {
        List<Statement> statements = new EventReader().readEvent(dateFormat);
        for (Statement statement : statements) {
            deleteDuplicateRowsInMaxTable(statement.getDate(), statement.getTime(), statement.getPlace(), statement.getStation(), statement.getMaxValue());
        }
    }

    public void minTabledeleter(String dateFormat) {
        List<Statement> statements = new EventReader().readEvent(dateFormat);
        for (Statement statement : statements) {
            deleteDuplicateRowsInMinTable(statement.getDate(), statement.getTime(), statement.getPlace(), statement.getStation(), statement.getMinValue());
        }
    }

    private void deleteDuplicateRowsInMaxTable(String date, String time, String place, String station, double value) {
        String url = "jdbc:sqlite:database" + File.separator + "datamart.db";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM maxTemperatures WHERE DATE = ? AND TIME = ? AND PLACE = ? AND STATION = ? AND VALUE = ? AND rowid NOT IN (SELECT min(rowid) FROM maxTemperatures GROUP BY DATE, TIME, PLACE, STATION, VALUE)")) {
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, time);
            preparedStatement.setString(3, place);
            preparedStatement.setString(4, station);
            preparedStatement.setDouble(5, value);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteDuplicateRowsInMinTable(String date, String time, String place, String station, double value) {
        String url = "jdbc:sqlite:database" + File.separator + "datamart.db";
        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM minTemperatures WHERE DATE = ? AND TIME = ? AND PLACE = ? AND STATION = ? AND VALUE = ? AND rowid NOT IN (SELECT min(rowid) FROM minTemperatures GROUP BY DATE, TIME, PLACE, STATION, VALUE)")) {
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, time);
            preparedStatement.setString(3, place);
            preparedStatement.setString(4, station);
            preparedStatement.setDouble(5, value);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
