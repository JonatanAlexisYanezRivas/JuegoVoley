package com.tesoem.juegovoley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class Juego extends AppCompatActivity {

    String nombreS, uidS, scoreS;

    TextView puntuacion, nombre;

    CheckBox j1, j2, j3, j4 ,j5, j6, e1, e2, e3, e4, e5, e6;

    Button btnPase, btnAcomodo, btnRemate, btnConfirmar;

    LastMovement movement = new LastMovement();

    int contMov, contGanadas, contRemate;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference JUGADORES;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        puntuacion = findViewById(R.id.scoreJuego);
        nombre = findViewById(R.id.nombreJuego);
    //Datos obtenidos de menu
        Bundle intent = getIntent().getExtras();

        uidS = intent.getString("UID");
        nombreS = intent.getString("NOMBRE");
        scoreS = intent.getString("SCORE");

        puntuacion.setText("Puntuación: " + String.valueOf(contGanadas));
        nombre.setText(nombreS);
    //Base de datos
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        JUGADORES = firebaseDatabase.getReference("DB JUGADORES");
    //Referencia a juagdores
        j1 = findViewById(R.id.jg1);
        j2 = findViewById(R.id.jg2);
        j3 = findViewById(R.id.jg3);
        j4 = findViewById(R.id.jg4);
        j5 = findViewById(R.id.jg5);
        j6 = findViewById(R.id.jg6);

        e1 = findViewById(R.id.en1);
        e2 = findViewById(R.id.en2);
        e3 = findViewById(R.id.en3);
        e4 = findViewById(R.id.en4);
        e5 = findViewById(R.id.en5);
        e6 = findViewById(R.id.en6);
    //referencia a botones
        btnAcomodo = findViewById(R.id.btnAcomodo);
        btnPase = findViewById(R.id.btnPase);
        btnRemate = findViewById(R.id.btnRemate);
        btnConfirmar = findViewById(R.id.btnConfirmar);

        start();

    //Confirmar movimiento
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(Juego.this, "El id es" + movement.getMovJugador(), Toast.LENGTH_SHORT).show();

                contMov++;
                if(contMov <=3 ){
                    if(movement.getMovJugador() == 1){
                        j1.setText("Balón");
                        j2.setText("jg2");
                        j3.setText("jg3");
                        j4.setText("jg4");
                        j5.setText("jg5");
                        j6.setText("jg6");

                        j1.setTextColor(Color.parseColor("#00e059"));

                        j1.setChecked(true);

                        j2.setChecked(false);
                        j3.setChecked(false);
                        j4.setChecked(false);
                        j5.setChecked(false);
                        j6.setChecked(false);



                        //  j1.setEnabled(false);
                    }
                    if(movement.getMovJugador() == 2){
                        j2.setText("Balón");
                        j1.setText("jg1");
                        j3.setText("jg3");
                        j4.setText("jg4");
                        j5.setText("jg5");
                        j6.setText("jg6");
                        j2.setTextColor(Color.parseColor("#00e059"));

                        j1.setChecked(false);
                        j2.setChecked(true);
                        j3.setChecked(false);
                        j4.setChecked(false);
                        j5.setChecked(false);
                        j6.setChecked(false);

                        //  j2.setEnabled(false);
                    }
                    if(movement.getMovJugador() == 3){
                        j3.setText("Balón");
                        j2.setText("jg2");
                        j1.setText("jg1");
                        j4.setText("jg4");
                        j5.setText("jg5");
                        j6.setText("jg6");
                        j3.setTextColor(Color.parseColor("#00e059"));

                        j1.setChecked(false);
                        j2.setChecked(false);
                        j3.setChecked(true);
                        j4.setChecked(false);
                        j5.setChecked(false);
                        j6.setChecked(false);

                        //  j3.setEnabled(false);
                    }
                    if(movement.getMovJugador() == 4){
                        j4.setText("Balón");
                        j2.setText("jg2");
                        j3.setText("jg3");
                        j1.setText("jg1");
                        j5.setText("jg5");
                        j6.setText("jg6");
                        j4.setTextColor(Color.parseColor("#00e059"));

                        j1.setChecked(false);
                        j2.setChecked(false);
                        j3.setChecked(false);
                        j4.setChecked(true);
                        j5.setChecked(false);
                        j6.setChecked(false);

                        // j4.setEnabled(false);
                    }
                    if(movement.getMovJugador() == 5){
                        j5.setText("Balón");
                        j2.setText("jg2");
                        j3.setText("jg3");
                        j4.setText("jg4");
                        j1.setText("jg1");
                        j6.setText("jg6");
                        j5.setTextColor(Color.parseColor("#00e059"));

                        j1.setChecked(false);
                        j2.setChecked(false);
                        j3.setChecked(false);
                        j4.setChecked(false);
                        j5.setChecked(true);
                        j6.setChecked(false);

                        // j5.setEnabled(false);
                    }
                    if(movement.getMovJugador() == 6){
                        j6.setText("Balón");
                        j2.setText("jg2");
                        j3.setText("jg3");
                        j4.setText("jg4");
                        j5.setText("jg5");
                        j1.setText("jg1");
                        j6.setTextColor(Color.parseColor("#00e059"));

                        j1.setChecked(false);
                        j2.setChecked(false);
                        j3.setChecked(false);
                        j4.setChecked(false);
                        j5.setChecked(false);
                        j6.setChecked(true);

                        // j6.setEnabled(false);
                    }
                    if(movement.getObj() == movement.getMovEnemigo()){

                        Toast.makeText(Juego.this, "Ganasteeee :)", Toast.LENGTH_SHORT).show();

                        contGanadas++;

                        if(contGanadas > Integer.parseInt(scoreS)){
                            guardarResultado("Score", contGanadas);
                        }

                        puntuacion.setText("Puntuación: " + String.valueOf(contGanadas));

                        j1.setChecked(false);
                        j2.setChecked(false);
                        j3.setChecked(false);
                        j4.setChecked(false);
                        j5.setChecked(false);
                        j6.setChecked(false);

                        e1.setChecked(false);
                        e2.setChecked(false);
                        e3.setChecked(false);
                        e4.setChecked(false);
                        e5.setChecked(false);
                        e6.setChecked(false);

                        start();
                    }
                    if(movement.getMovEnemigo()!= movement.getObj() && contRemate == 1){
                        Toast.makeText(Juego.this, "Perdisteee ):", Toast.LENGTH_SHORT).show();

                        contGanadas = 0;

                        j1.setChecked(false);
                        j2.setChecked(false);
                        j3.setChecked(false);
                        j4.setChecked(false);
                        j5.setChecked(false);
                        j6.setChecked(false);

                        e1.setChecked(false);
                        e2.setChecked(false);
                        e3.setChecked(false);
                        e4.setChecked(false);
                        e5.setChecked(false);
                        e6.setChecked(false);

                        start();
                    }

                }else{
                    Toast.makeText(Juego.this, "Se han terminado tus 3 movimientos vuelve a intentarlo", Toast.LENGTH_SHORT).show();

                    contGanadas = 0;

                    j1.setChecked(false);
                    j2.setChecked(false);
                    j3.setChecked(false);
                    j4.setChecked(false);
                    j5.setChecked(false);
                    j6.setChecked(false);

                    e1.setChecked(false);
                    e2.setChecked(false);
                    e3.setChecked(false);
                    e4.setChecked(false);
                    e5.setChecked(false);
                    e6.setChecked(false);

                    start();
                }

                j1.setEnabled(false);
                j2.setEnabled(false);
                j3.setEnabled(false);
                j4.setEnabled(false);
                j5.setEnabled(false);
                j6.setEnabled(false);

                e1.setEnabled(false);
                e2.setEnabled(false);
                e3.setEnabled(false);
                e4.setEnabled(false);
                e5.setEnabled(false);
                e6.setEnabled(false);

                e1.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                e2.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                e3.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                e4.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                e5.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                e6.setButtonTintList(getResources().getColorStateList(R.color.rojo));

                j1.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                j2.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                j3.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                j4.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                j5.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                j6.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
            }


        });
    //Habilitar los pases
        btnPase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(j1.isChecked()){
                    j4.setEnabled(true);
                    j2.setEnabled(true);

                    j1.setEnabled(false);
                    j3.setEnabled(false);
                    j5.setEnabled(false);
                    j6.setEnabled(false);

                    j4.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j1.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.purple_200));


                    //j4.setButtonTintList(Color.parseColor("#00722e"));
                    //j2.setButtonTintList(ColorStateList.valueOf("#00722e"));
                }
                if(j2.isChecked()){
                    j1.setEnabled(true);
                    j3.setEnabled(true);
                    j5.setEnabled(true);

                    j2.setEnabled(false);
                    j4.setEnabled(false);
                    j6.setEnabled(false);

                    j1.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j4.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                }
                if(j3.isChecked()){
                    j6.setEnabled(true);
                    j2.setEnabled(true);

                    j1.setEnabled(false);
                    j3.setEnabled(false);
                    j4.setEnabled(false);
                    j5.setEnabled(false);

                    j1.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j4.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.verde));
                }
                if(j4.isChecked()){
                    j1.setEnabled(true);
                    j5.setEnabled(true);

                    j2.setEnabled(false);
                    j3.setEnabled(false);
                    j4.setEnabled(false);
                    j6.setEnabled(false);

                    j1.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j4.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                }
                if(j5.isChecked()){
                    j4.setEnabled(true);
                    j6.setEnabled(true);
                    j2.setEnabled(true);

                    j1.setEnabled(false);
                    j3.setEnabled(false);
                    j5.setEnabled(false);

                    j1.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j4.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.verde));
                }
                if(j6.isChecked()){
                    j5.setEnabled(true);
                    j3.setEnabled(true);

                    j1.setEnabled(false);
                    j2.setEnabled(false);
                    j4.setEnabled(false);
                    j6.setEnabled(false);

                    j1.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j4.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                }
            }
        });
    //Habilitar los acomodos
        btnAcomodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(j1.isChecked()){
                    j5.setEnabled(true);
                    j3.setEnabled(true);

                    j1.setEnabled(false);
                    j2.setEnabled(false);
                    j4.setEnabled(false);
                    j6.setEnabled(false);

                    j1.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j4.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                }
                if(j2.isChecked()){
                    j4.setEnabled(true);
                    j6.setEnabled(true);

                    j1.setEnabled(false);
                    j2.setEnabled(false);
                    j3.setEnabled(false);
                    j5.setEnabled(false);

                    j1.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j4.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.verde));
                }
                if(j3.isChecked()){
                    j5.setEnabled(true);
                    j1.setEnabled(true);

                    j2.setEnabled(false);
                    j3.setEnabled(false);
                    j4.setEnabled(false);
                    j6.setEnabled(false);

                    j1.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j4.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                }
                if(j4.isChecked()){
                    j6.setEnabled(true);
                    j2.setEnabled(true);

                    j1.setEnabled(false);
                    j3.setEnabled(false);
                    j4.setEnabled(false);
                    j5.setEnabled(false);

                    j1.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j4.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.verde));
                }
                if(j5.isChecked()){
                    j1.setEnabled(true);
                    j3.setEnabled(true);

                    j2.setEnabled(false);
                    j4.setEnabled(false);
                    j5.setEnabled(false);
                    j6.setEnabled(false);

                    j1.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j4.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                }
                if(j6.isChecked()){
                    j4.setEnabled(true);
                    j2.setEnabled(true);

                    j1.setEnabled(false);
                    j3.setEnabled(false);
                    j5.setEnabled(false);
                    j6.setEnabled(false);

                    j1.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j4.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                }
            }
        });
    //Habilitar los remates
        btnRemate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contRemate = 1;

                if(j1.isChecked()){
                    e1.setEnabled(true);

                    j1.setEnabled(false);
                    j2.setEnabled(false);
                    j3.setEnabled(false);
                    j4.setEnabled(false);
                    j5.setEnabled(false);
                    j6.setEnabled(false);

                    e1.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    e2.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e3.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e4.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e5.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e6.setButtonTintList(getResources().getColorStateList(R.color.rojo));

                    j1.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j4.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                }
                if(j2.isChecked()){
                    e2.setEnabled(true);

                    j1.setEnabled(false);
                    j2.setEnabled(false);
                    j3.setEnabled(false);
                    j4.setEnabled(false);
                    j5.setEnabled(false);
                    j6.setEnabled(false);

                    e1.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e2.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    e3.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e4.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e5.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e6.setButtonTintList(getResources().getColorStateList(R.color.rojo));

                    j1.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j4.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                }
                if(j3.isChecked()){
                    e3.setEnabled(true);

                    j1.setEnabled(false);
                    j2.setEnabled(false);
                    j3.setEnabled(false);
                    j4.setEnabled(false);
                    j5.setEnabled(false);
                    j6.setEnabled(false);

                    e1.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e2.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e3.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    e4.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e5.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e6.setButtonTintList(getResources().getColorStateList(R.color.rojo));

                    j1.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j4.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                }
                if(j4.isChecked()){
                    e4.setEnabled(true);
                    e2.setEnabled(true);

                    j1.setEnabled(false);
                    j2.setEnabled(false);
                    j3.setEnabled(false);
                    j4.setEnabled(false);
                    j5.setEnabled(false);
                    j6.setEnabled(false);

                    e1.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e2.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    e3.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e4.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    e5.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e6.setButtonTintList(getResources().getColorStateList(R.color.rojo));

                    j1.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j4.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                }
                if(j5.isChecked()){
                    e5.setEnabled(true);
                    e1.setEnabled(true);
                    e3.setEnabled(true);

                    j1.setEnabled(false);
                    j2.setEnabled(false);
                    j3.setEnabled(false);
                    j4.setEnabled(false);
                    j5.setEnabled(false);
                    j6.setEnabled(false);

                    e1.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    e2.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e3.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    e4.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e5.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    e6.setButtonTintList(getResources().getColorStateList(R.color.rojo));

                    j1.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j4.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                }
                if(j6.isChecked()){
                    e6.setEnabled(true);
                    e2.setEnabled(true);

                    j1.setEnabled(false);
                    j2.setEnabled(false);
                    j3.setEnabled(false);
                    j4.setEnabled(false);
                    j5.setEnabled(false);
                    j6.setEnabled(false);

                    e1.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e2.setButtonTintList(getResources().getColorStateList(R.color.verde));
                    e3.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e4.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e5.setButtonTintList(getResources().getColorStateList(R.color.rojo));
                    e6.setButtonTintList(getResources().getColorStateList(R.color.verde));

                    j1.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j2.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j3.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j4.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j5.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                    j6.setButtonTintList(getResources().getColorStateList(R.color.purple_200));
                }
            }
        });

    }
    /*Guardar la posicion de los movimientos*/
    public void movements(View v){
        switch (v.getId()){
            case R.id.jg1:
                movement.setMovJugador(1);
                break;
            case R.id.jg2:
                movement.setMovJugador(2);
                break;
            case R.id.jg3:
                movement.setMovJugador(3);
                break;
            case R.id.jg4:
                movement.setMovJugador(4);
                break;
            case R.id.jg5:
                movement.setMovJugador(5);
                break;
            case R.id.jg6:
                movement.setMovJugador(6);
                break;
        }
    }

    public void movementsEnemy(View v){
        switch (v.getId()){
            case R.id.en1:
                movement.setMovEnemigo(1);
                break;
            case R.id.en2:
                movement.setMovEnemigo(2);
                break;
            case R.id.en3:
                movement.setMovEnemigo(3);
                break;
            case R.id.en4:
                movement.setMovEnemigo(4);
                break;
            case R.id.en5:
                movement.setMovEnemigo(5);
                break;
            case R.id.en6:
                movement.setMovEnemigo(6);
                break;
        }
    }

    //Comienza el juego
    public void start(){

        Random random = new Random();
        int balon = (int) (Math.random()*6 + 1);
        int objectivo =  (int) (Math.random()*6 + 1);

        contMov = 0;
        contRemate = 0;

        puntuacion.setText("Puntuación: " + String.valueOf(contGanadas));

        movement.setMovJugador(balon);
        movement.setObj(objectivo);

        j1.setTextColor(Color.parseColor("#000000"));
        j2.setTextColor(Color.parseColor("#000000"));
        j3.setTextColor(Color.parseColor("#000000"));
        j4.setTextColor(Color.parseColor("#000000"));
        j5.setTextColor(Color.parseColor("#000000"));
        j6.setTextColor(Color.parseColor("#000000"));

        e1.setTextColor(Color.parseColor("#000000"));
        e2.setTextColor(Color.parseColor("#000000"));
        e3.setTextColor(Color.parseColor("#000000"));
        e4.setTextColor(Color.parseColor("#000000"));
        e5.setTextColor(Color.parseColor("#000000"));
        e6.setTextColor(Color.parseColor("#000000"));

        j1.setText("jg1");
        j2.setText("jg2");
        j3.setText("jg3");
        j4.setText("jg4");
        j5.setText("jg5");
        j6.setText("jg6");

        e1.setText("en1");
        e2.setText("en2");
        e3.setText("en3");
        e4.setText("en4");
        e5.setText("en5");
        e6.setText("en6");


        if(balon == 1){
            j1.setText("Balón");
            j1.setTextColor(Color.parseColor("#00e059"));
            j1.setChecked(true);
            //j1.setEnabled(false);
        }
        if(balon == 2){
            j2.setText("Balón");
            j2.setTextColor(Color.parseColor("#00e059"));
            j2.setChecked(true);
            //2.setEnabled(false);
        }
        if(balon == 3){
            j3.setText("Balón");
            j3.setTextColor(Color.parseColor("#00e059"));
            j3.setChecked(true);
            //j3.setEnabled(false);
        }
        if(balon == 4){
            j4.setText("Balón");
            j4.setTextColor(Color.parseColor("#00e059"));
            j4.setChecked(true);
            //j4.setEnabled(false);
        }
        if(balon == 5){
            j5.setText("Balón");
            j5.setTextColor(Color.parseColor("#00e059"));
            j5.setChecked(true);
            //j5.setEnabled(false);
        }
        if(balon == 6){
            j6.setText("Balón");
            j6.setTextColor(Color.parseColor("#00e059"));
            j6.setChecked(true);
           // j6.setEnabled(false);
        }

        if(objectivo == 1){
            e1.setText("Punto");
            e1.setTextColor(Color.parseColor("#ff0000"));
            //e1.setChecked(true);
            //e1.setEnabled(false);
        }
        if(objectivo == 2){
            e2.setText("Punto");
            e2.setTextColor(Color.parseColor("#ff0000"));
            //e2.setChecked(true);
            //e2.setEnabled(false);
        }
        if(objectivo == 3){
            e3.setText("Punto");
            e3.setTextColor(Color.parseColor("#ff0000"));
            //e3.setChecked(true);
            //e3.setEnabled(false);
        }
        if(objectivo == 4){
            e4.setText("Punto");
            e4.setTextColor(Color.parseColor("#ff0000"));
            //e4.setChecked(true);
            //e4.setEnabled(false);
        }
        if(objectivo == 5){
            e5.setText("Punto");
            e5.setTextColor(Color.parseColor("#ff0000"));
            //e5.setChecked(true);
            //e5.setEnabled(false);
        }
        if(objectivo == 6){
            e6.setText("Punto");
            e6.setTextColor(Color.parseColor("#ff0000"));
            //e6.setChecked(true);
           // e6.setEnabled(false);
        }

        j1.setEnabled(false);
        j2.setEnabled(false);
        j3.setEnabled(false);
        j4.setEnabled(false);
        j5.setEnabled(false);
        j6.setEnabled(false);

        e1.setEnabled(false);
        e2.setEnabled(false);
        e3.setEnabled(false);
        e4.setEnabled(false);
        e5.setEnabled(false);
        e6.setEnabled(false);



    }
    //Actualiza puntuacion en la base de datos
    private void guardarResultado(String key, int score){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(key, score);
        JUGADORES.child(user.getUid()).updateChildren(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Juego.this, "Nuevo record", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
