package com.example.agenda11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ExtrasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras);
    }

    public void Mapa(View view){
        Intent mapa = new Intent(this, MapsActivity.class);
        startActivity(mapa);
    }

    public void Sensor(View view){
        Intent sensor = new Intent(this, SensorActivity.class);
        startActivity(sensor);
    }
}