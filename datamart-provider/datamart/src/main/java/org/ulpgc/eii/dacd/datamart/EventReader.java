package org.ulpgc.eii.dacd.datamart;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.ulpgc.eii.dacd.feeder.EventsFilesFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventReader {
    public List<Statement> readEvent(String dateFormat) {
        List<Statement> statementList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("datalake" + File.separator + dateFormat.replaceAll("-", "") + EventsFilesFactory.extension);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                statementList.add(declareStatement(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return statementList;
    }

    private Statement declareStatement(String line) {
        JsonObject jsonObject = new JsonParser().parse(line).getAsJsonObject();
        String date = jsonObject.get("fint").getAsString().replaceAll("T(.*)", "");
        String time = jsonObject.get("fint").getAsString().replaceAll("(.*)T", "");
        String place = jsonObject.get("ubi").getAsString();
        String station = jsonObject.get("idema").getAsString();
        double maxValue = jsonObject.get("tamax").getAsDouble();
        double minValue = jsonObject.get("tamin").getAsDouble();
        return new Statement(date, time, place, station, maxValue, minValue);
    }
}
