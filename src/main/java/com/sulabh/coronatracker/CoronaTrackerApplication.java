package com.sulabh.coronatracker;

import com.sulabh.coronatracker.services.CoronaDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoronaTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoronaTrackerApplication.class, args);

    }

}
