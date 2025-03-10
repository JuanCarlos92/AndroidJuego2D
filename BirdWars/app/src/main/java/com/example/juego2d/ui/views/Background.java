package com.example.juego2d.ui.views;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.example.juego2d.R;
import com.example.juego2d.utils.graficos.CargarImg;

//Clase que representa el fondo de pantalla del juego.
public class Background {
    public int x = 0, y = 0; //Coordenadas del fondo
    public Bitmap background;

    //Constructor de la clase Background
    public Background(int screenX, int screenY, Resources res) {
        // Carga y redimensiona la imagen utilizando ImageUtils
        background = CargarImg.loadAndResizeImage(res, R.drawable.background_glacial, screenX, screenY);
    }
}
