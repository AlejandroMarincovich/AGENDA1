package com.example.agenda11;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Localizacion implements LocationListener {



    MapsActivity mapsActivity;
    TextView tvMensaje;


    public  MapsActivity getMapsActivity(){
        return mapsActivity;
    }

    public void setMapsActivity(MapsActivity mapsActivity, TextView tvMensaje){

        this.mapsActivity = mapsActivity;
        this.tvMensaje = tvMensaje;
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        String texto = "Mi ubicacion es: \n"
                + "Latitud =" + location.getLatitude() + "\n"
                + "Longitud =" + location.getLongitude();

        tvMensaje.setText(texto);
        mapa(location.getLatitude(), location.getLongitude());
    }

    public void mapa(double lat, double lon){
        FragmentMaps fragment = new FragmentMaps();

        Bundle bundle = new Bundle();
        bundle.putDouble("lat", new Double(lat));
        bundle.putDouble("long", new Double(lon));

        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getMapsActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment, fragment, null);
        fragmentTransaction.commit();
    }
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status){
            case LocationProvider.AVAILABLE:
                Log.d("debug", "LocationProvider.AVAILABLE");
                break;
            case LocationProvider.OUT_OF_SERVICE:
                Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                break;
        }
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

        tvMensaje.setText("GPS ACTIVADO");
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        tvMensaje.setText("GPS DESACTIVADO");
    }
}
