package org.ulpgc.eii.dacd.feeder;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AemetWeatherSensor {
    private final static String url = "https://opendata.aemet.es/opendata/api/observacion/convencional/todas";
    Area area = new Area(27.5, 28.4, -16, -15);

    public List<Weather> read() throws IOException {
        String response = ApiKeyProvider.response(url);
        String dataUrl = JsonParser.parseString(response).getAsJsonObject().get("datos").getAsString();
        String aemet = ApiKeyProvider.response(dataUrl);
        JsonArray content = new Gson().fromJson(aemet, JsonArray.class);
        return content.asList().stream()
                .filter(o -> o.getAsJsonObject().get("lat").getAsDouble() > area.getFromLat() && o.getAsJsonObject().get("lat").getAsDouble() < area.getToLat())
                .filter(o -> o.getAsJsonObject().get("lon").getAsDouble() > area.getFromLon() && o.getAsJsonObject().get("lon").getAsDouble() < area.getToLon())
                .map(this::fromJsonToWeather)
                .collect(Collectors.toList());
    }

    private Weather fromJsonToWeather(JsonElement jsonElement) {
        try {
            String date = jsonElement.getAsJsonObject().get("fint").getAsString();
            String station = jsonElement.getAsJsonObject().get("idema").getAsString();
            String location = jsonElement.getAsJsonObject().get("ubi").getAsString();
            double temperature = Double.parseDouble(String.valueOf(jsonElement.getAsJsonObject().get("ta")));
            double maxTemperature = Double.parseDouble(String.valueOf(jsonElement.getAsJsonObject().get("tamax")));
            double minTemperature = Double.parseDouble(String.valueOf(jsonElement.getAsJsonObject().get("tamin")));
            return new Weather(date, station, location, temperature, maxTemperature, minTemperature);
        } catch (NumberFormatException e) {
            System.out.print("");
        }
        return null;
    }
}
