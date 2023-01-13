package org.ulpgc.eii.dacd.datamart;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseFactory {
    public void create() {
        String url = "jdbc:sqlite:database" + File.separator + "datamart.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createDirectory() {
        File file = new File("database");
        if (file.exists()) {
            System.out.println("Folder existing");
        } else if (file.mkdir()) {
            System.out.println("Created");
        } else {
            System.out.println("Not created");
        }
    }
}