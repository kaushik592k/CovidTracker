package org.covid_tracker.middleware.controller;

import org.covid_tracker.middleware.service.AutoSuggestionService;
import org.covid_tracker.middleware.service.AutoSuggestionServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class AutoSuggestionController {


    //
    AutoSuggestionService autoSuggestionService = new AutoSuggestionServiceImpl();

    public AutoSuggestionController() throws IOException {
    }

    @RequestMapping("/search")
    public Set<String> getTop5Names() {
        return autoSuggestionService.getTopK("b");
    }
}
