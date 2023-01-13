package org.ulpgc.eii.dacd.app;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new org.ulpgc.eii.dacd.feeder.Controller().run();
        sleep(TimeUnit.MINUTES.toMillis(1));
        new org.ulpgc.eii.dacd.datamart.Controller().run();
        new org.ulpgc.eii.dacd.webservice.Controller().run();
    }
}
