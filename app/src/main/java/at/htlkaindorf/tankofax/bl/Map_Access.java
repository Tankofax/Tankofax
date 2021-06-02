package at.htlkaindorf.tankofax.bl;

import android.app.Activity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import at.htlkaindorf.tankofax.beans.Tankstelle;

public class Map_Access implements Runnable {
    private GoogleMap map;
    private List<Tankstelle> lists;
    private Activity activity;
    private LatLng currentLocation;
    @Override
    public void run() {
        for (Tankstelle tankstelle : lists) {
            activity.runOnUiThread(() -> {
                String title = tankstelle.getName();
                LatLng position = new LatLng(tankstelle.getLocation().getLatitude(), tankstelle.getLocation().getLongitude());
                MarkerOptions mo = new MarkerOptions();
                mo.position(position);
                mo.title(title);
                mo.alpha(3);
                map.addMarker(mo);
                map.addMarker(new MarkerOptions().position(currentLocation));
            });
        }
    }

    public GoogleMap getMap() {
        return map;
    }

    /*public List<Tankstelle> getLists() {
        return lists;
    }*/

    public void setVariables(Activity activity, List<Tankstelle> lists, GoogleMap map, LatLng currentLocation) {
        this.map = map;
        this.lists = lists;
        this.activity = activity;
        this.currentLocation = currentLocation;
    }
}
