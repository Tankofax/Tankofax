package at.htlkaindorf.tankofax.bl;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JSON_Access extends AsyncTask<String, Void, List<Map_Search>> {
    private final List<Map_Search> results = new ArrayList<>();

    @Override
    protected List<Map_Search> doInBackground(String... strings) {
        List<Map_Search> results = new ArrayList<>();
        try {
            //https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=YOUR_API_KEY
            String baseURL = "https://maps.googleapis.com/maps/api/geocode/json?address=";
            String urlString = baseURL + strings[0].replaceAll(" ", "+") + "&key=" + strings[1];
            URL url = new URL(urlString);
            ObjectMapper om = new ObjectMapper();
            JsonNode node = om.readTree(url.openConnection().getInputStream());
            for (JsonNode jsonNode: node.get("results")) {
                String formatted_address = jsonNode.get("formatted_address").asText();
                for (JsonNode jsonNode1: jsonNode.get("geometry")) {
                    JsonNode lat = jsonNode1.get("lat");
                    JsonNode lng = jsonNode1.get("lng");
                    if (lat != null && lng != null) {
                        results.add(new Map_Search(formatted_address, lat.asDouble(), lng.asDouble()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }
}

/*
{
   "results" : [
      {
         "formatted_address" : "Grottenhofstraße 102, 8052 Graz, Österreich",
         "geometry" : {
            "bounds" : {
               "northeast" : {
                  "lat" : 47.04743149999999,
                  "lng" : 15.3935312
               },
               "southwest" : {
                  "lat" : 47.0472782,
                  "lng" : 15.3929263
               }
            },
            "location" : {
               "lat" : 47.04735669999999,
               "lng" : 15.3933917
            },
            "location_type" : "ROOFTOP",
            "viewport" : {
               "northeast" : {
                  "lat" : 47.0487038302915,
                  "lng" : 15.3945777302915
               },
               "southwest" : {
                  "lat" : 47.0460058697085,
                  "lng" : 15.3918797697085
               }
            }
         },
         "place_id" : "ChIJh9aKDTA1bkcR3ELgpQWOEpQ",
         "types" : [ "premise" ]
      }
   ],
   "status" : "OK"
}*/