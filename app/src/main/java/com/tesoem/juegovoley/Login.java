package com.tesoem.juegovoley;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText correo, pass;
    Button btnLogin;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correo = findViewById(R.id.correoLogin);
        pass = findViewById(R.id.passwordLogin);
        btnLogin = findViewById(R.id.loginBtn);
        auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
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
                   logeoJugador(email, password);
                }
            }
        });
    }

    private void logeoJugador(String email, String password){
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();
                            startActivity(new Intent(Login.this, Menu.class));
                            assert user!= null; //Afirmamos que el usuario no es nulo
                            Toast.makeText(Login.this, "Bienvenido(a)"+user.getEmail(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}