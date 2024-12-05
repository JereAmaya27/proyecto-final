package com.example.appfinal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class agregar extends AppCompatActivity {

    private EditText etTitulo, etDescripcion;
    private Button btnGuardarInformacion;
    private MqttHelper mqttHelper;
    private int contador = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_agregar);
            etTitulo = findViewById(R.id.etTitulo);
            etDescripcion = findViewById(R.id.etDescripcion);
            btnGuardarInformacion = findViewById(R.id.btnGuardarInformacion);

            // Inicializar el MqttHelper
            try {
                mqttHelper = new MqttHelper(this);
            } catch (Exception e) {
                Log.e("AgregarActivity", "Error al inicializar MqttHelper", e);
            }

            // Configurar el botón para guardar información
            btnGuardarInformacion.setOnClickListener(v -> guardarInformacion());

        } catch (Exception e) {
            Log.e("agregarActivity", "Error en onCreate: " + e.getMessage());
            Toast.makeText(this, "Error al cargar la actividad", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarInformacion() {
        // Incrementamos el contador cada vez que se guarda información
        contador++;

        // Verificamos si los campos están vacíos
        String titulo = etTitulo.getText().toString();
        String descripcion = etDescripcion.getText().toString();

        if (titulo.isEmpty() || descripcion.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guardamos la información en Firebase
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("DatosAgregados");
        String id = database.push().getKey();

        if (id != null) {
            // Crear un objeto CardItem para guardar en Firebase
            CardItem item = new CardItem(titulo, descripcion);
            database.child(id).setValue(item)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Información guardada exitosamente", Toast.LENGTH_SHORT).show();

                            // Publicamos el contador en MQTT
                            try {
                                String mqttMessage = "Cantidad de veces que se guardó información: " + contador;
                                mqttHelper.publish("test/lab" + mqttMessage);  // Publicamos el contador en el tópico MQTT
                            } catch (Exception e) {
                                Log.e("agregarActivity", "Error al publicar en MQTT: " + e.getMessage());
                            }

                            // Finalizar actividad
                            finish();
                        } else {
                            Toast.makeText(this, "Error al guardar en Firebase", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

}