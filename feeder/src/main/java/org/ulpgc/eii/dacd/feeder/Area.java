package org.ulpgc.eii.dacd.feeder;

public class Area {
    private double fromLat;
    private double toLat;
    private double fromLon;
    private double toLon;

    public Area(double fromLat, double toLat, double fromLon, double toLon) {
        this.fromLat = fromLat;
        this.toLat = toLat;
        this.fromLon = fromLon;
        this.toLon = toLon;
    }

    public double getFromLat() {
        return fromLat;
    }

    public double getToLat() {
        return toLat;
    }

    public double getFromLon() {
        return fromLon;
    }

    public double getToLon() {
        return toLon;
    }
}
