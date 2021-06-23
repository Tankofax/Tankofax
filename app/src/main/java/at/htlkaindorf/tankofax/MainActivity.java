package at.htlkaindorf.tankofax;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

    private final Button[] fuelButton = new Button[3];


    private GoogleMap map;
    private Marker marker;
    private double lat, lon;
    private SupportMapFragment mapFragment;
    private RecyclerView recyclerView;
    private boolean moveCamera = true;

    private Toolbar toolbar;

    private LatLng inputLocation;
    private boolean gpsLocation = true;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        
        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        recyclerView = findViewById(R.id.rv_Tankstellen);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fuelButton[0] = findViewById(R.id.btn_diesel);
        fuelButton[1] = findViewById(R.id.btn_benzin);
        fuelButton[2] = findViewById(R.id.btn_gas);

        for (Button button : fuelButton) {
            button.setOnClickListener(this::onClickListener);
        }

        try {
            ApplicationInfo applicationInfo = this.getPackageManager().getApplicationInfo(this.getPackageName(), PackageManager.GET_META_DATA);
            String apikey = applicationInfo.metaData.getString("com.google.android.geo.API_KEY");
            List<Map_Search> map_searches = new JSON_Access().execute("Grottenhofstraße 102", apikey).get();
            map_searches.forEach(m1 -> inputLocation = new LatLng(m1.getLat(), m1.getLng()));
            System.out.println(inputLocation);
        } catch (NullPointerException | PackageManager.NameNotFoundException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        MenuItem.OnActionExpandListener onActionExpandListener= new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                Toast.makeText(MainActivity.this,"Search is Expanded",Toast.LENGTH_SHORT).show();
                return true;
            }
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                Toast.makeText(MainActivity.this,"Search is Collapsed",Toast.LENGTH_SHORT).show();
                return true;
            }
        };
            menu.findItem(R.id.search).setOnActionExpandListener(onActionExpandListener);
        //SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        //searchView.setQueryHint("Search for Location...");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.search:
                gpsLocation = false;
                break;
            case R.id.settings:
                onSettings(findViewById(R.id.settings));
                break;
            case R.id.location:
                gpsLocation = true;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClickListener(View v) {
        map.clear();
        Thread thread = null;
        switch (v.getId()) {
            case R.id.btn_diesel:
                try {
                    List<Tankstelle> dieList;
                    if (gpsLocation) {
                        dieList = new API_Access().execute("DIE", lat + "", lon + "").get();
                    } else {
                        dieList = new API_Access().execute("DIE", inputLocation.latitude + "", inputLocation.longitude + "").get();
                    }
                    da.setFuel(dieList);
                    recyclerView.setAdapter(da);
                    ma.setVariables(this, dieList, map);
                    thread = new Thread(ma, "diesel");
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
                    thread = new Thread(ma, "super");
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
                    thread = new Thread(ma, "gas");
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            //case R.id.btn_stateSourceChanger:
            //    break;
            default:
                break;
        }
        moveCamera = false;
        if (thread != null) {
            thread.start();
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

    public void onLanguageSettings(View v){
        PopupMenu settings = new PopupMenu(this, v);
        settings.setOnMenuItemClickListener(this);
        settings.inflate(R.menu.popup_languages);
        settings.show();
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.set1: onLanguageSettings(item.getActionView());

                break;
            case R.id.set2:

                break;
            default:
                break;
        }
        return false;
    }
}