package at.htlkaindorf.tankofax.bl;

import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;

import java.util.List;

import at.htlkaindorf.tankofax.beans.Tankstelle;

public class Map_Access extends AsyncTask<GoogleMap, Void, GoogleMap> {
    private List<Tankstelle> tankstellen;



    @Override
    protected GoogleMap doInBackground(GoogleMap... googleMaps) {
        return null;
    }

    public List<Tankstelle> getTankstellen() {
        return tankstellen;
    }

    public void setTankstellen(List<Tankstelle> tankstellen) {
        this.tankstellen = tankstellen;
    }
}
