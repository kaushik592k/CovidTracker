package org.covid_tracker.middleware.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.covid_tracker.middleware.model.Statistic;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TrackerServiceImpl implements TrackerService {

    Utility util = new Utility();

    @Override
    public Statistic globalStats() throws IOException {



        // CSV file preprocessing
        String confirmedPath = "./confirmed.csv";
        String recoveredPath = "./recovered.csv";
        String deathsPath = "./deaths.csv";

        util.downloadFile("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv",confirmedPath);
        util.downloadFile("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv",recoveredPath);
        util.downloadFile("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv", deathsPath);

        FileReader filereader = new FileReader(confirmedPath);
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();

        List<String[]> allData = csvReader.readAll();
        int confirmed = 0;

        for (String[] row : allData) {
            if(row[0].equals("Province/State")) continue;
            confirmed += Integer.parseInt(row[row.length - 1]);
        }



        filereader = new FileReader(deathsPath);
        parser = new CSVParserBuilder().withSeparator(',').build();
        csvReader = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();

        allData = csvReader.readAll();
        int deaths = 0;

        for (String[] row : allData) {
            if(row[0].equals("Province/State")) continue;
            deaths += Integer.parseInt(row[row.length - 1]);
        }


        filereader = new FileReader(recoveredPath);
        parser = new CSVParserBuilder().withSeparator(',').build();
        csvReader = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();

        allData = csvReader.readAll();
        int recovered = 0;

        for (String[] row : allData) {
            if(row[0].equals("Province/State")) continue;
            recovered += Integer.parseInt(row[row.length - 1]);
        }

        return new Statistic(confirmed,deaths,confirmed - deaths - recovered, recovered);
    }

    @Override
    public Statistic countryStats(String countryName) throws IOException {

        int recovered=0,confirmed=0,deaths=0,active=0;

        String deathsPath = "./deaths.csv";
        String confirmedPath = "./confirmed.csv";
        String recoveredPath = "./recovered.csv";

        util.downloadFile("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv",confirmedPath);
        util.downloadFile("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv",recoveredPath);
        util.downloadFile("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv", deathsPath);

        FileReader filereader = new FileReader(recoveredPath);
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();

        List<String[]>allData = csvReader.readAll();
        int recovered = 0;

        for (String[] row : allData) {
            if(row[0].equals("Province/State")) continue;
            recovered += Integer.parseInt(row[row.length - 1]);
        }
        for(String[] row : allData){
            if(row[0].equals(countryName)){
                recovered += Integer.parseInt(row[row.length - 1]);
            }

        }

        filereader = new FileReader(confirmedPath);
        parser = new CSVParserBuilder().withSeparator(',').build();
        csvReader = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();

        allData = csvReader.readAll();
        for(String[] row : allData){
            if(row[0].equals(countryName)){
                confirmed += Integer.parseInt(row[row.length - 1]);
            }

        }

        filereader = new FileReader(deathsPath);
        parser = new CSVParserBuilder().withSeparator(',').build();
        csvReader = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();
        allData = csvReader.readAll();

        for(String[] row : allData){
            if(row[0].equals(countryName)){
                deaths += Integer.parseInt(row[row.length - 1]);
            }

        }
        active = (confirmed - deaths - recovered);
        return new Statistic(confirmed, deaths, active, recovered);
    }


}
