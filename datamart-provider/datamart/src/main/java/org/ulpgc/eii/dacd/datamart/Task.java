package org.ulpgc.eii.dacd.datamart;

import org.ulpgc.eii.dacd.feeder.DateFormatProvider;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Task {
    public void start() {
        new DatabaseFactory().createDirectory();
        new DatabaseFactory().create();
        new DropTable().dropMaxTable();
        new DropTable().dropMinTable();
        new DatabaseTablesFactory().createMaxTemperaturesTable();
        new DatabaseTablesFactory().createMinTemperaturesTable();
        TimerTask task = new TimerTask() {
            public void run() {
                fillYesterday();
                fillToday();
                System.out.println("Database Written.");
            }
        };
        Timer timer = new Timer("Timer");
        long delay  = TimeUnit.HOURS.toMillis(1);
        task.run();
        timer.scheduleAtFixedRate(task, delay, delay);
    }

    private void fillYesterday() {
        new EventWriter().writeMaxTable(new DateFormatProvider().yesterdayDateFormat());
        new EventWriter().writeMinTable(new DateFormatProvider().yesterdayDateFormat());
        new DeleteDuplicated().maxTabledeleter(new DateFormatProvider().yesterdayDateFormat());
        new DeleteDuplicated().minTabledeleter(new DateFormatProvider().yesterdayDateFormat());
    }

    private void fillToday() {
        new EventWriter().writeMaxTable(new DateFormatProvider().todayDateFormat());
        new EventWriter().writeMinTable(new DateFormatProvider().todayDateFormat());
        new DeleteDuplicated().maxTabledeleter(new DateFormatProvider().todayDateFormat());
        new DeleteDuplicated().minTabledeleter(new DateFormatProvider().todayDateFormat());
    }
}
