package com.tesoem.juegovoley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    Button btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesion();
            }
        });
    }

    //Se ejecuta cunado se abre el juego
    @Override
    protected void onStart() {
        usuarioLogueado();
        super.onStart();
    }
    // verifica si ya se ha logueado el usuario
    private void usuarioLogueado(){
        if(user != null){
            Toast.makeText(this, "Jugador en linea" + user.getEmail(), Toast.LENGTH_SHORT).show();
        }else{
            startActivity(new Intent(Menu.this, MainActivity.class));
        }
    }
    //Cierra sesion
    private void cerrarSesion(){
        auth.signOut();
        startActivity(new Intent(Menu.this, MainActivity.class));
        Toast.makeText(this, "Se ha cerrado la sesión", Toast.LENGTH_SHORT).show();
    }
}