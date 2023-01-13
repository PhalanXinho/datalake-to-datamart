package org.ulpgc.eii.dacd.feeder;

import java.io.File;
import java.io.IOException;

public class EventsFilesFactory {
    public static final String extension = ".events";

    public void createFile(String date) {
        try {
            File file = new File("datalake" + File.separator + date.replaceAll("-", "") + extension);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

