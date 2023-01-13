package org.ulpgc.eii.dacd.feeder;

import com.google.gson.annotations.SerializedName;

public record Weather(@SerializedName("fint") String date,
                      @SerializedName("idema") String station,
                      @SerializedName("ubi") String location,
                      @SerializedName("ta") double temperature,
                      @SerializedName("tamax") double maxTemperature,
                      @SerializedName("tamin") double minTemperature) {

    public Weather(String date, String station, String location, double temperature, double maxTemperature, double minTemperature) {
        this.date = date;
        this.station = station;
        this.location = location;
        this.temperature = temperature;
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    }

    @Override
    public String date() {
        return date;
    }

    @Override
    public String station() {
        return station;
    }

    @Override
    public String location() {
        return location;
    }

    @Override
    public double temperature() {
        return temperature;
    }

    @Override
    public double maxTemperature() {
        return maxTemperature;
    }

    @Override
    public double minTemperature() {
        return minTemperature;
    }
}