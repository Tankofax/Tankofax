package at.htlkaindorf.tankofax;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.tankofax.beans.Tankstelle;

public class MainActivity extends AppCompatActivity implements LocationListener, OnMapReadyCallback, PopupMenu.OnMenuItemClickListener {
    private static final int REQUEST_LOCATION = 1;
    private GoogleMap map;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private double lat, lon;

    private SupportMapFragment mapFragment;

    private API_Access api = new API_Access();

    private List<Tankstelle> dieTankstellen;
    private List<Tankstelle> supTankstellen;
    private List<Tankstelle> gasTankstellen;

    private Button[] fuelType = new Button[3];

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);

        //dieTankstellen = api.getTankstelle("DIE", lon, lat);

        fuelType[0] = findViewById(R.id.btn_diesel);
        fuelType[1] = findViewById(R.id.btn_benzin);
        fuelType[2] = findViewById(R.id.btn_sonstiges);
        for (Button button : fuelType) {
            button.setOnClickListener(this::onClickListener);
        }
    }

    public void onClickListener(View v) {
        switch (v.getId()) {
            case R.id.btn_diesel:
                api.getTankstelle("DIE", lon, lat);
                break;
            case R.id.btn_benzin:
                api.getTankstelle("SUP", lon, lat);
                break;
            case R.id.btn_sonstiges:
                api.getTankstelle("GAS", lon, lat);
                break;
            default:
                break;
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        lat = location.getLatitude();
        lon = location.getLongitude();
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng currentPosition = new LatLng(lat, lon);
        map.addMarker(new MarkerOptions()
                .position(currentPosition));
        map.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));
        map.setMinZoomPreference(10);
    }

    public void onSettings(View v){
        PopupMenu settings = new PopupMenu(this,v);
        settings.setOnMenuItemClickListener(this);
        settings.inflate(R.menu.popup_settings);
        settings.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item){
        switch(item.getItemId()){
            case R.id.item1:
                Toast.makeText(this,"Item 1 clicked",Toast.LENGTH_LONG).show();
                break;
            case R.id.item2:
                Toast.makeText(this,"Item 2 clicked", Toast.LENGTH_LONG).show();
                break;
            default:
                return false;

        }
        return false;
    }
}