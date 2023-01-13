package org.ulpgc.eii.dacd.feeder;

import java.io.File;

public class DirectoryFactory {
    public void create() {
        File file = new File("datalake");
        if (file.exists()) {
            System.out.println("Folder existing");
        } else if (file.mkdir()) {
            System.out.println("Created");
        } else {
            System.out.println("Not created");
        }
    }
}
