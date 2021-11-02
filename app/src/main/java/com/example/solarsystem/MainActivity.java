package com.example.solarsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //Instanciation d'un objet EcranDemarrage et attribution du context en paramètre
        EcranDemarrage ecranDemarrage = new EcranDemarrage(this);

        //Attribution de l'objet ecranDemarrage a setContentView (c'est ce qui sera affiché à l'écran)
        setContentView(ecranDemarrage);
    }
}