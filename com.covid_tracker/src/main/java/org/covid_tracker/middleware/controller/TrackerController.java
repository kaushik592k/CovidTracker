package org.covid_tracker.middleware.controller;

import org.covid_tracker.middleware.model.Statistic;
import org.covid_tracker.middleware.service.TrackerServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class TrackerController {

    TrackerServiceImpl trackerService = new TrackerServiceImpl();

    @RequestMapping("/global")
    public Statistic getGlobalConfirmed() throws IOException {
//        Map<String, Integer> response = new HashMap<>();
        return trackerService.globalStats();
    }


}
