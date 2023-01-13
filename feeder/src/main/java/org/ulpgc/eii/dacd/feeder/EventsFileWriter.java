package org.ulpgc.eii.dacd.feeder;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EventsFileWriter {
    public void write(String dateFormat) {
        try {
            List<Weather> weatherList = new WriterFilter().filterWeatherList(new AemetWeatherSensor().read(), dateFormat);
            File file = new File("datalake/" + File.separator + dateFormat.replaceAll("-", "") + EventsFilesFactory.extension);
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Weather weather : weatherList) {
                String content = new Gson().toJson(weather);
                bufferedWriter.write(content + "\n");
            }
            bufferedWriter.close();
            System.out.println("File written");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}