package com.example.appfinal;

import android.net.Uri;

public class CardItem {
    private String titulo;
    private String descripcion;

    // Constructor vacío necesario para Firebase
    public CardItem() {}

    public CardItem(String titulo, String descripcion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
}





