package com.example.appfinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class inicio extends AppCompatActivity {

    private ArrayList<CardItem> cardList;
    private CardAdapter adapter;
    private Button btnAgregar;
    private Button btnInicioSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);

        btnAgregar = findViewById(R.id.btnAgregar);
        btnInicioSesion = findViewById(R.id.btnInicioSesion);

        cardList = new ArrayList<>();
        adapter = new CardAdapter(cardList);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnInicioSesion.setOnClickListener(v -> {
            Intent intent = new Intent(this, iniciosesion.class);
            startActivity(intent);
            finish();
        });

        findViewById(R.id.btnAgregar).setOnClickListener(v -> {
            Intent intent = new Intent(this, agregar.class);
            startActivity(intent);
        });


        cargarDatosDesdeFirebase(); // Cargar los datos desde Firebase
    }

    private void cargarDatosDesdeFirebase() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("DatosAgregados");

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cardList.clear(); // Limpia la lista antes de cargar nuevos datos
                for (DataSnapshot data : snapshot.getChildren()) {
                    CardItem item = data.getValue(CardItem.class);
                    if (item != null) {
                        cardList.add(item); // Agregar cada elemento cargado
                    }
                }
                adapter.notifyDataSetChanged(); // Notificar al adaptador
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(inicio.this, "Error al cargar datos: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}