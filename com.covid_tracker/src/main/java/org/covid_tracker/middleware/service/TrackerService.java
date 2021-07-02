package org.covid_tracker.middleware.service;

import org.covid_tracker.middleware.model.Statistic;

import java.io.IOException;
import java.net.MalformedURLException;

public interface TrackerService {
    public Statistic globalStats() throws IOException;
    public Statistic countryStats(String countryName) throws IOException;

}
