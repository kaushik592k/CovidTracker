package org.covid_tracker.middleware.service;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.covid_tracker.middleware.model.TrieNode;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AutoSuggestionServiceImpl implements AutoSuggestionService {

    TrieNode root;
    Utility util = new Utility();
    public AutoSuggestionServiceImpl() throws IOException {

        List<String> countryNames = new ArrayList<>();
        util.downloadFile("https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv", "./confirmed.csv");
        FileReader filereader = new FileReader("./confirmed.csv");
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .build();

        List<String[]> allData = csvReader.readAll();
        int confirmed = 0;

        for (String[] row : allData) {
            if(row[0].equals("Province/State")) continue;

            String t = row[1].toLowerCase(Locale.ROOT);
            String name = "";
            for (char str: t.toCharArray()) {
                if (str >= 'a' && str <= 'z')
                    name += str;
            }
            countryNames.add(name);
        }
        this.root = build(countryNames);
    }
    @Override
    public TrieNode build(List<String> countries) {
        TrieNode root = new TrieNode();
        for(String country : countries) {
            char[] s = country.toCharArray();
            int len=s.length;
            TrieNode crawl=root;
            for(int level=0;level<len;level++)
            {
                int id=s[level] - 'a';
                if(crawl.child[id] == null)
                {
                    crawl.child[id]= new TrieNode();
                }
                crawl = crawl.child[id];
            }
            crawl.isEnd=true;
        }
        return root;
    }

    @Override
    public Set<String> getTopK(String prefix) {
        int len = prefix.length();
        char[] s = prefix.toCharArray();
        TrieNode crawl = this.root;
        for(int level=0;level<len;level++)
        {
            if(crawl.child[s[level] - 'a']!=null)
            crawl = crawl.child[s[level] - 'a'];
        }
        if(crawl == this.root)
        {
            return null;
        }
        Set<String> str = getString(crawl, prefix);
        return str;
    }

    public Set<String> getString(TrieNode proot, String prefix) {
        Set <String> topK= new TreeSet<>();
        TrieNode crawl = proot;
        if(crawl.isEnd)
        {
            topK.add(prefix);
        }
        for(int i=0;i<26;i++)
        {
            if(crawl.child[i]!=null)
            {
                String tmpprefix=(prefix + (char)(i + 'a'));
                Set <String> childString = new TreeSet<>();
                childString = getString(crawl.child[i],tmpprefix);
                for(String s : childString)
                {
                    topK.add(s);
                }
            }
        }
        return topK;
    }
}
