package at.htlkaindorf.tankofax;

import android.os.AsyncTask;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URL;
import java.util.List;

import at.htlkaindorf.tankofax.beans.Tankstelle;
import lombok.SneakyThrows;

public class API_Access extends AsyncTask<String, Void, List<Tankstelle>> {
    //https://api.e-control.at/sprit/1.0/search/gas-stations/by-address?latitude=47.0466990&longitude=15.3964080&fuelType=DIE&includeClosed=false
    private String baseURL = "https://api.e-control.at/sprit/1.0/search/gas-stations/by-address?includeClosed=false";

    public List<Tankstelle> getTankstelle(String fuelType, double longtitude, double latitude) {
        List<Tankstelle> tankstellen = doInBackground(fuelType, longtitude + "", latitude + "");
        return tankstellen;
    }

    @SneakyThrows
    @Override
    protected List<Tankstelle> doInBackground(String... strings) {
        String url = baseURL + "&longtitude=" + strings[1] + "&latitude=" + strings[2] + "&fuelType=" + strings[0];
        URL tank = new URL(url);
        ObjectMapper mapper = new ObjectMapper();
        List<Tankstelle> tanke = mapper.readValue(tank.openConnection().getInputStream(), new TypeReference<List<Tankstelle>>(){});
        return tanke;
    }
}
/*
    protected RSSFeed doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLReader xmlreader = parser.getXMLReader();
            RssHandler theRSSHandler = new RssHandler();
            xmlreader.setContentHandler(theRSSHandler);
            InputSource is = new InputSource(url.openStream());
            xmlreader.parse(is);

            return theRSSHandler.getFeed();
        } catch (Exception e) {
            this.exception = e;

            return null;
        } finally {
            is.close();
        }
    }

    protected void onPostExecute(RSSFeed feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}
*/