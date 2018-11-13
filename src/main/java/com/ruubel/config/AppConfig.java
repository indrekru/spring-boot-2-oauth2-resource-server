package com.ruubel.config;

import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class AppConfig {

    /**
     * Make sure everything is stored in UTC in DB
     */
    public void configureUTC() {
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/UTC"));
    }

    public AppConfig() {
        configureUTC();
    }

}
