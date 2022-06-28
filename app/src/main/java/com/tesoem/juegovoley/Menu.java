package com.tesoem.juegovoley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Menu extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    Button btnCerrarSesion, btnJugar, btnPuntuaciones, btnAbout;
    TextView miPuntuacion, uid, correo, nombre;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference JUGADORES;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        JUGADORES = firebaseDatabase.getReference("DB JUGADORES");

        btnJugar = findViewById(R.id.btnJugar);
       // btnPuntuaciones = findViewById(R.id.btnPuntuacion);
        btnAbout = findViewById(R.id.btnAbout);

        miPuntuacion = findViewById(R.id.balon);
        uid = findViewById(R.id.uidMenu);
        correo = findViewById(R.id.correoMenu);
        nombre = findViewById(R.id.nombreMenu);
        



        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesion();
            }
        });
        
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Juego.class);

                String uidString = uid.getText().toString();
                String nombreString = nombre.getText().toString();
                String puntuacionString = miPuntuacion.getText().toString();

                intent.putExtra("UID", uidString);
                intent.putExtra("NOMBRE", nombreString);
                intent.putExtra("SCORE", puntuacionString);

                startActivity(intent);

                Toast.makeText(Menu.this, "Jugar", Toast.LENGTH_SHORT).show();
            }
        });
        
        /*btnPuntuaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Menu.this, "Puntuaciones", Toast.LENGTH_SHORT).show();
            }
        });*/
        
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this, Instrucciones.class));
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
            consulta();
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

    private  void consulta(){
        Query query = JUGADORES.orderByChild("Email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    //Obtener datos
                    String voleyString = ""+ds.child("Score").getValue();
                    String uidString = ""+ds.child("Uid").getValue();
                    String emailString = ""+ds.child("Email").getValue();
                    String nombreStrnig = ""+ds.child("Nombre").getValue();
                    //Seteo de datos de la -bd
                    miPuntuacion.setText(voleyString);
                    uid.setText(uidString);
                    correo.setText("Email: " + emailString);
                    nombre.setText("Usuario: " + nombreStrnig);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}