package org.ulpgc.eii.dacd.feeder;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Task {
    public void start() {
        new DirectoryFactory().create();
        new EventsFilesFactory().createFile(new DateFormatProvider().yesterdayDateFormat());
        new EventsFileWriter().write(new DateFormatProvider().yesterdayDateFormat());
        TimerTask task = new TimerTask() {
            public void run() {
                new EventsFilesFactory().createFile(new DateFormatProvider().todayDateFormat());
                new EventsFileWriter().write(new DateFormatProvider().todayDateFormat());
            }
        };
        Timer timer = new Timer("Timer");
        long delay  = TimeUnit.HOURS.toMillis(1);
        task.run();
        timer.scheduleAtFixedRate(task, delay, delay);
    }
}
