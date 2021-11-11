package com.example.solarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class JeuxOuvert extends AppCompatActivity {
    private AlienSolarSysteme alienSolarSysteme;
    ListView listAstre;
    AstreCeleste myAstre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_jeux_ouvert);
        alienSolarSysteme = new AlienSolarSysteme(this);
        setContentView(alienSolarSysteme);
    }


}