package com.example.appfinal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class iniciosesion extends AppCompatActivity {

    private EditText etCodigoSeguridad, etCorreo, etContrasena;
    private Button btnIniciarSesion, btnRegistrar;
    private FirebaseAuth auth;
    private DatabaseReference database;
    private static final String CODIGO_EMPRESA = "5112024";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_iniciosesion);

        etCodigoSeguridad = findViewById(R.id.etCodigoSeguridad);
        etCorreo = findViewById(R.id.etCorreo);
        etContrasena = findViewById(R.id.etContrasena);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnRegistrar = findViewById(R.id.btnRegistrar);


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference("Usuarios");

        btnIniciarSesion.setOnClickListener(v -> {
            String correo = etCorreo.getText().toString();
            String contrasena = etContrasena.getText().toString();
            iniciarSesion(correo, contrasena);
        });

        btnRegistrar.setOnClickListener(v -> {
            String codigo = etCodigoSeguridad.getText().toString();
            String correo = etCorreo.getText().toString();
            String contrasena = etContrasena.getText().toString();

            if (codigo.equals(CODIGO_EMPRESA)) {
                registrarUsuario(correo, contrasena);
            } else {
                Toast.makeText(this, "Código de seguridad incorrecto", Toast.LENGTH_SHORT).show();
            }
        });

        //boton volver
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    public boolean onSupportNavigateUp() {
        finish(); // Regresa a la actividad anterior
        return true;
    }
    private void iniciarSesion(String correo, String contrasena) {
        auth.signInWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(this, inicio.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Error al iniciar sesión: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void registrarUsuario(String correo, String contrasena) {
        auth.createUserWithEmailAndPassword(correo, contrasena)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            guardarUsuarioEnBaseDatos(user.getUid(), correo, contrasena);

                        }
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Error al registrar: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void guardarUsuarioEnBaseDatos(String uid, String correo, String contrasena) {
        database.child("Usuarios").child(uid).setValue(new Usuario(correo, contrasena))
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Usuario registrado en la base de datos", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Error al guardar usuario: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static class Usuario {
        public String correo;
        public String contrasena;

        public Usuario() {}

        public Usuario(String correo, String contrasena) {
            this.correo = correo;
            this.contrasena = contrasena;
        }
    }

}
