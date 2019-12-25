package mk.ukim.finki.students.moviesomdb;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.intentfilter.androidpermissions.PermissionManager;
import com.intentfilter.androidpermissions.models.DeniedPermissions;

import java.util.Arrays;
import java.util.logging.Logger;

import mk.ukim.finki.students.moviesomdb.models.Location;
import mk.ukim.finki.students.moviesomdb.models.Places;
import mk.ukim.finki.students.moviesomdb.services.LocatingBanksService;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    Logger logger = Logger.getLogger("MapsActivity");
    private GoogleMap mMap;
    Intent banksIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        banksIntent = new Intent(this, LocatingBanksService.class);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }

    private BroadcastReceiver bReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            assert extras != null;
            LatLng location = (LatLng) extras.get("location");
            Places places = (Places) extras.get("places");
            logger.info("Got new location:" + location.toString());
            setPlaces(places);
            setMyLocation(location);
        }
    };

    private void setPlaces(Places places) {

        if (places.results.size() > 0) {
            mMap.clear();
            places.results.forEach(a -> {
                Location l = a.geometry.location;
                LatLng position = new LatLng(l.lat, l.lng);
                mMap.addMarker(new MarkerOptions().position(position).title(a.name));
            });
        }
    }

    public void setMyLocation(LatLng latLng) {
        mMap.addMarker(new MarkerOptions().position(latLng).title("My location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(14f));
    }


    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(bReceiver, new IntentFilter("message"));
    }

    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(bReceiver);
    }

    @Override
    protected void onStart() {
        super.onStart();
        doSomething();
//        listenLocationUpdates();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(banksIntent);
    }
    private void doSomething(){
        PermissionManager permissionManager = PermissionManager.getInstance(getApplicationContext());
        permissionManager.checkPermissions(Arrays.asList(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), new PermissionManager.PermissionRequestListener() {
            @Override
            public void onPermissionGranted() {
                startService(banksIntent);

            }


            @Override
            public void onPermissionDenied(DeniedPermissions deniedPermissions) {
            }
        });
    }
//
//    private void registerLocationRegister() {
//        startService(banksIntent);
//    }

//    private void listenLocationUpdates() {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
//            return;
//        }
//        registerLocationRegister();
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            registerLocationRegister();
//
//        }
//    }


}
