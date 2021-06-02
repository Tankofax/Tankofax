package at.htlkaindorf.tankofax.bl;

import android.os.AsyncTask;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.tankofax.beans.Tankstelle;

public class API_Access extends AsyncTask<String, Void, List<Tankstelle>> {

    @Override
    protected List<Tankstelle> doInBackground(String... strings) {
        List<Tankstelle> tankstellen = new ArrayList<>();
        try {
            //https://api.e-control.at/sprit/1.0/search/gas-stations/by-address?latitude=47.0466990&longitude=15.3964080&fuelType=DIE&includeClosed=false
            String baseURL = "https://api.e-control.at/sprit/1.0/search/gas-stations/by-address?includeClosed=false";
            String urlS = baseURL + "&fuelType=" + strings[0] + "&latitude=" + strings[1] + "&longitude=" + strings[2];
            URL url = new URL(urlS);
            ObjectMapper mapper = new ObjectMapper();
            tankstellen = mapper.readValue(url.openConnection().getInputStream(), new TypeReference<List<Tankstelle>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tankstellen;
    }
}