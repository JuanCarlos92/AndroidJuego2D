package com.example.juego2d.gameobject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.juego2d.view.GameView.screenRatioX;
import static com.example.juego2d.view.GameView.screenRatioY;

import com.example.juego2d.R;

public class Bala {

    public int x;
    public int y;
    int width;
    int height;
    public Bitmap bala;

    // Constructor
    public Bala(Resources res) {
        // Carga la imagen
        bala = BitmapFactory.decodeResource(res, R.drawable.bullet);

        // Obtiene las dimensiones de la imagen
        width = bala.getWidth();
        height = bala.getHeight();

        // Redimensiona la imagen para reducir el tamaño
        width /= 5;
        height /= 5;

        // Ajusta las dimensiones de la bala la pantalla
        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        // Crea una nueva imagen con las dimensiones ajustadas
        bala = Bitmap.createScaledBitmap(bala, width, height, false);

    }

    // Metodo que devuelve la forma de colision de la baja
    public Rect getColision() {
        return new Rect(x, y, x + width, y + height);
    }

}
