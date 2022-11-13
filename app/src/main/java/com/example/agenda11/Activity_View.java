package com.example.agenda11;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_View extends AppCompatActivity {

    public void Sensor(View view) {
        Intent sensor = new Intent(this, SensorActivity.class);
        startActivity(sensor);
    }

    public void Mapa(View view){
        Intent mapa = new Intent(this, MapsActivity.class);
        startActivity(mapa);
    }
}