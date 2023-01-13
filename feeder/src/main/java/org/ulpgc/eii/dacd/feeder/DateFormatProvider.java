package org.ulpgc.eii.dacd.feeder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateFormatProvider {
    public String todayDateFormat() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    public String yesterdayDateFormat() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return dateFormat.format(calendar.getTime());
    }
}
