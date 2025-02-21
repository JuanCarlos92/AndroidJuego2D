package com.example.juego2d;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Background {
    public int x = 0;
    public int y = 0; // Coordenadas del fondo = Inicializada  en 0
    public Bitmap background; // Objeto Bitmap para la imagen del fondo

    // Constructor
    public Background(int screenX, int screenY, Resources res) {
        // Carga la imagen
        background = BitmapFactory.decodeResource(res, R.drawable.background_glacial);
        // Redimensiona la imagen del fondo para que se ajuste al tamaño de la pantalla
        background = Bitmap.createScaledBitmap(background, screenX, screenY, false);

    }

}
