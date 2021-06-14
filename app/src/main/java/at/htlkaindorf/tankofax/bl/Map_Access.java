package at.htlkaindorf.tankofax.bl;

import android.app.Activity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import at.htlkaindorf.tankofax.beans.Tankstelle;

public class Map_Access implements Runnable {
    private GoogleMap map;
    private List<Tankstelle> tankstellen;
    private Activity activity;

    @Override
    public void run() {
        for (Tankstelle tankstelle : tankstellen) {
            activity.runOnUiThread(() -> {
                String title = tankstelle.getName();
                LatLng position = new LatLng(tankstelle.getLocation().getLatitude(), tankstelle.getLocation().getLongitude());
                MarkerOptions mo = new MarkerOptions();
                mo.position(position);
                mo.title(title);
                mo.alpha(5);
                map.addMarker(mo);
            });
        }
    }

    public GoogleMap getMap() {
        return map;
    }

    public void setVariables(Activity activity, List<Tankstelle> tankstellen, GoogleMap map) {
        this.map = map;
        this.tankstellen = tankstellen;
        this.activity = activity;
    }
}