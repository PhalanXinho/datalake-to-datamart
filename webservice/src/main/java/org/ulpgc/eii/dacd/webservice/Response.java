package org.ulpgc.eii.dacd.webservice;

public class Response {
    private final String date;
    private final String time;
    private final String place;
    private final double value;

    public Response(String date, String time, String place, double value) {
        this.date = date;
        this.time = time;
        this.place = place;
        this.value = value;
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

    public double getValue() {
        return value;
    }
}
