package org.covid_tracker.middleware.controller;

import org.covid_tracker.middleware.service.AutoSuggestionService;
import org.covid_tracker.middleware.service.AutoSuggestionServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class AutoSuggestionController {


    //
    AutoSuggestionService autoSuggestionService = new AutoSuggestionServiceImpl();

    public AutoSuggestionController() throws IOException {
    }

    @RequestMapping("/search")
    public Set<String> getTop5Names(@RequestBody Map<String, String> payload) {
        String[] words = payload.get("term").toLowerCase(Locale.ROOT).split(" ");
        String prefix = "";
        for (String word : words) {
            prefix += word;
        }
        return autoSuggestionService.getTopK(prefix);
    }
}
