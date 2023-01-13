package org.ulpgc.eii.dacd.feeder;

import java.util.List;
import java.util.stream.Collectors;

public class WriterFilter {
    public List<Weather> filterWeatherList(List<Weather> weatherList, String dateFormat) {
        return weatherList.stream()
                .filter(o -> o.date().replaceAll("T(.*)", "").equals(dateFormat))
                .collect(Collectors.toList());
    }
}