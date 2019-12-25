package mk.ukim.finki.students.moviesomdb.services;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.maps.model.LatLng;

import java.util.logging.Logger;

import mk.ukim.finki.students.moviesomdb.client.PlacesClient;
import mk.ukim.finki.students.moviesomdb.models.Places;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LocatingBanksService extends Service implements LocationListener {
    Logger logger = Logger.getLogger("LocatingBanksService");
    private LocationManager locationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null)
            getPlacesForLocation(location);
        logger.info("onStartCommand");

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 60, 30, this);

        return super.onStartCommand(intent, flags, startId);

    }

    private void getPlacesForLocation(Location location) {
        String locationString = location.getLatitude() + "," + location.getLongitude();
        final Call<Places> moviesCall = PlacesClient.getService().getPlaces(locationString);

        moviesCall.enqueue(new Callback<Places>() {
            @Override
            public void onResponse(Call<Places> call, Response<Places> response) {
                if (response.isSuccessful()) {
                    Places places = response.body();
                    sendBroadcast(location, places);

                }
            }

            @Override
            public void onFailure(Call<Places> call, Throwable t) {
                //nothing
            }
        });
    }

    private void sendBroadcast(Location location, Places places) {
        Intent i = new Intent("message");
        i.putExtra("location", new LatLng(location.getLatitude(), location.getLongitude()));
        i.putExtra("places", places);
        LocalBroadcastManager.getInstance(this).sendBroadcast(i);

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        logger.info("Location changed");
        getPlacesForLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


}
