package at.htlkaindorf.tankofax;

import android.os.AsyncTask;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import at.htlkaindorf.tankofax.beans.Tankstelle;
import lombok.SneakyThrows;

public class API_Access extends  AsyncTask<String, Void, List<Tankstelle>>{
    //https://api.e-control.at/sprit/1.0/search/gas-stations/by-address?latitude=47.0466990&longitude=15.3964080&fuelType=DIE&includeClosed=false
    private String baseURL = "https://api.e-control.at/sprit/1.0/search/gas-stations/by-address?includeClosed=false";

    @Override
    protected List<Tankstelle> doInBackground(String... strings) {
        try {
            String urlS = baseURL + "&fuelType=" + strings[0] + "&latitude=" + strings[1] + "&longitude=" + strings[2];
            URL url = new URL(urlS);
            ObjectMapper mapper = new ObjectMapper();
            //File jsonFile = new File(url.toURI());
            return mapper.readValue(url.openConnection().getInputStream(), new TypeReference<List<Tankstelle>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}