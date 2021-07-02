package org.covid_tracker.middleware.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utility {

    public HttpResponse executeRequest(String url) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        return response;
    }
    public void downloadFile(String url, String path) throws IOException {
        HttpResponse response = executeRequest(url);

        HttpEntity entity = response.getEntity();

        int responseCode = response.getStatusLine().getStatusCode();

        InputStream is = entity.getContent();

        // CSV file preprocessing
        String pathToFile = path;
        FileOutputStream fos = new FileOutputStream(new File(pathToFile));
        int inByte;
        while((inByte = is.read()) != -1) {
            fos.write(inByte);
        }
        is.close();
        fos.close();

    }
}
