package com.tesoem.juegovoley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {

    EditText correo, pass, nombre;
    TextView fecha;
    Button registrar;
    FirebaseAuth auth; //Autenticacion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        correo = findViewById(R.id.correoEt);
        pass = findViewById(R.id.passEt);
        nombre = findViewById(R.id.nombreEt);
        fecha = findViewById(R.id.fechaEt);
        registrar = findViewById(R.id.btnRegistrar);

        auth = FirebaseAuth.getInstance();

        Date date = new Date();
        SimpleDateFormat fechas = new SimpleDateFormat("d 'de' MMM 'del' yyyy");
        String stringFecha = fechas.format(date);

        fecha.setText(stringFecha);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = correo.getText().toString();
                String password = pass.getText().toString();

                /*Validacion de correo y contraseña*/

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    correo.setError("Correo no valido");
                    correo.setFocusable(true);
                }else if(password.length()<6){
                    pass.setError("Contraseña debe ser mayor a 6");
                    correo.setFocusable(true);
                }else{
                    registrarJugador(email, password);
                }
            }
        });
    }

    private void registrarJugador(String email, String password){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();
                            
                            int contador = 0;
                            
                            assert user != null;
                            String uidString = user.getUid();
                            String correoString = correo.getText().toString();
                            String passwordString = pass.getText().toString();
                            String nombreString = nombre.getText().toString();
                            String fechaString = fecha.getText().toString();
                            
                            //Se utiliza para asignar cables a nuestro valores
                            HashMap<Object,Object> DatosJugador = new HashMap<>();
                            
                            DatosJugador.put("Uid",uidString);
                            DatosJugador.put("Email", correoString);
                            DatosJugador.put("Password", passwordString);
                            DatosJugador.put("Nombre", nombreString);
                            DatosJugador.put("Fecha", fechaString);
                            DatosJugador.put("Score", contador);

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference reference = database.getReference("DB JUGADORES");//Referencia al nombre de la base de datos
                            reference.child(uidString).setValue(DatosJugador);

                            startActivity(new Intent(Registro.this, Menu.class));

                            Toast.makeText(Registro.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(Registro.this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registro.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}