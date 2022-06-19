package com.tesoem.juegovoley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Juego extends AppCompatActivity {

    String nombreS, uidS, scoreS;

    TextView puntuacion, nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        puntuacion = findViewById(R.id.scoreJuego);
        nombre = findViewById(R.id.nombreJuego);

        Bundle intent = getIntent().getExtras();

        uidS = intent.getString("UID");
        nombreS = intent.getString("NOMBRE");
        scoreS = intent.getString("SCORE");

        puntuacion.setText(scoreS);
        nombre.setText(nombreS);
    }
}