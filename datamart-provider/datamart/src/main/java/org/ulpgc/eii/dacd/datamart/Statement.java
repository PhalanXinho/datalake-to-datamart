package org.ulpgc.eii.dacd.datamart;

public class Statement {
    private final String date;
    private final String time;
    private final String place;
    private final String station;
    private final double maxValue;
    private final double minValue;

    public Statement(String date, String time, String place, String station, double maxValue, double minValue) {
        this.date = date;
        this.time = time;
        this.place = place;
        this.station = station;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public String getStation() {
        return station;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getMinValue() {
        return minValue;
    }
}
