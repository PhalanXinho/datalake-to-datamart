package org.ulpgc.eii.dacd.datamart;

import java.util.List;

public class EventWriter {
    public void writeMaxTable(String dateFormat) {
        List<Statement> statements = new EventReader().readEvent(dateFormat);
        InsertDatabaseTable insertDatabaseTable = new InsertDatabaseTable();
        for (Statement statement : statements) {
            insertDatabaseTable.insertMaxTemperaturesTable(statement.getDate(), statement.getTime(), statement.getPlace(), statement.getStation(), statement.getMaxValue());
        }
    }

    public void writeMinTable(String dateFormat) {
        List<Statement> statements = new EventReader().readEvent(dateFormat);
        InsertDatabaseTable insertDatabaseTable = new InsertDatabaseTable();
        for (Statement statement : statements) {
            insertDatabaseTable.insertMinTemperaturesTable(statement.getDate(), statement.getTime(), statement.getPlace(), statement.getStation(), statement.getMinValue());
        }
    }
}
