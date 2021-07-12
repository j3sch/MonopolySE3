package com.hdm.monopoly.gameplay;

import com.hdm.monopoly.MonopolyApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
public class Countdown {

    private static final Logger log = LogManager.getLogger(Countdown.class);
    int timeInSeconds = 1800;

    public void startCountdown() {
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        log.info("start Countdown");

        final Runnable runnable = () -> {

            timeInSeconds--;
            if (timeInSeconds < 0) {
                log.info("Maximum length of 2 hours reached");
                MonopolyApplication.restart();
                scheduler.shutdown();
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }

    public void resetCountdown() {
        timeInSeconds = 1800;
        log.info("Countdown reset");
    }
}
