package com.sulabh.coronatracker.services;

import com.sulabh.coronatracker.models.LocationData;
import lombok.Data;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class CoronaDateService {

    //data source
    private static String VIRUS_DATA_URL =
            "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationData> allData = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* 30 * * * *")
    public void fetchData(){
        List<LocationData> currentData = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();

        try {
            HttpResponse httpResponse = client.send(request,HttpResponse.BodyHandlers.ofString());
            System.out.println(httpResponse.body());

            StringReader csvStringReader = new StringReader((String) httpResponse.body());

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvStringReader);
            for (CSVRecord record : records) {
                LocationData locationData = new LocationData();
                locationData.setState(record.get("Province/State"));
                locationData.setState(record.get("Country/Region"));
                locationData.setLatestTotalCases(Integer.parseInt(record.get(record.size()-1)));
                currentData.add(locationData);
                System.out.println(locationData.toString());
            }
            this.allData = currentData;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
