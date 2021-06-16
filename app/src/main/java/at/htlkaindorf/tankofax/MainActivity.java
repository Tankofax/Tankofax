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
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.concurrent.ExecutionException;

import at.htlkaindorf.tankofax.beans.Tankstelle;
import at.htlkaindorf.tankofax.bl.API_Access;
import at.htlkaindorf.tankofax.bl.DetailAdapter;
import at.htlkaindorf.tankofax.bl.JSON_Access;
import at.htlkaindorf.tankofax.bl.Map_Access;
import at.htlkaindorf.tankofax.bl.Map_Search;

public class MainActivity extends AppCompatActivity implements LocationListener, OnMapReadyCallback, PopupMenu.OnMenuItemClickListener {
    private static final int REQUEST_LOCATION = 1;
    private final Map_Access ma = new Map_Access();
    private final DetailAdapter da = new DetailAdapter();
    private final JSON_Access json = new JSON_Access();

    private final Button[] fuelButton = new Button[3];
    private final ImageButton[] locationButton = new ImageButton[2];

    private GoogleMap map;
    private Marker marker;
    private double lat, lon;
    private SupportMapFragment mapFragment;

    private RecyclerView recyclerView;

    private boolean moveCamera = true;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        recyclerView = findViewById(R.id.rv_Tankstellen);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fuelButton[0] = findViewById(R.id.btn_diesel);
        fuelButton[1] = findViewById(R.id.btn_benzin);
        fuelButton[2] = findViewById(R.id.btn_gas);

        locationButton[0] = findViewById(R.id.btn_navmap);
        locationButton[0] = findViewById(R.id.btn_navsearch);

        for (Button button : fuelButton) {
            button.setOnClickListener(this::onClickListener);
        }
        for (ImageButton imageButton: locationButton) {
            imageButton.setOnClickListener(this::onClickListener);
        }

        try {
            List<Map_Search> map_searches = new JSON_Access().execute("Grottenhofstraße 102").get();
            map_searches.forEach(m1 -> System.out.println(m1.getFormattedAddress() + " : " + m1.getLat() + ";" + m1.getLng()));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("NonConstantResourceId")
    public void onClickListener(View v) {
        map.clear();
        switch (v.getId()) {
            case R.id.btn_diesel:
                try {
                    List<Tankstelle> dieList = new API_Access().execute("DIE", lat + "", lon + "").get();
                    da.setFuel(dieList);
                    recyclerView.setAdapter(da);
                    ma.setVariables(this, dieList, map);
                    Thread thread = new Thread(ma, "diesel");
                    thread.start();
                    moveCamera = !moveCamera;
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_benzin:
                try {
                    List<Tankstelle> supList = new API_Access().execute("SUP", lat + "", lon + "").get();
                    da.setFuel(supList);
                    recyclerView.setAdapter(da);
                    ma.setVariables(this, supList, map);
                    Thread thread = new Thread(ma, "super");
                    thread.start();
                    moveCamera = !moveCamera;
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_gas:
                try {
                    List<Tankstelle> gasList = new API_Access().execute("GAS", lat + "", lon + "").get();
                    DetailAdapter da = new DetailAdapter();
                    da.setFuel(gasList);
                    recyclerView.setAdapter(da);
                    ma.setVariables(this, gasList, map);
                    Thread thread = new Thread(ma, "gas");
                    thread.start();
                    moveCamera = !moveCamera;
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_navmap:
                break;
            case R.id.btn_navsearch:
                break;
            default:
                break;
        }

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (marker != null) {
            marker.remove();
        }
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
        marker = map.addMarker(new MarkerOptions().position(currentPosition));
        if (moveCamera) {
            map.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));
        }
        map.setMinZoomPreference(10);
    }

    public void onSettings(View v) {
        PopupMenu settings = new PopupMenu(this, v);
        settings.setOnMenuItemClickListener(this);
        settings.inflate(R.menu.popup_settings);
        settings.show();
    }

    public void dialogSetup(View v) {
        PopupMenu language = new PopupMenu(this, v);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.set1:

                break;
            case R.id.set2:

                break;
            default:
                break;
        }
        return false;
    }
}