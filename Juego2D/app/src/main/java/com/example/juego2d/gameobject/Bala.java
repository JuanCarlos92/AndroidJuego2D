package com.example.juego2d.gameobject;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.juego2d.view.GameView.screenRatioX;
import static com.example.juego2d.view.GameView.screenRatioY;

import com.example.juego2d.R;

/**
 * Clase que representa una bala en el juego.
 * La bala es un objeto que se dispara desde el avión.
 */
public class Bala {

    public int x;
    public int y;
    int width;
    int height;
    public Bitmap bala;

    /**
     * Constructor de la clase Bala.
     * Carga y redimensiona la imagen de la bala según la pantalla.
     *
     * @param res Recursos de la aplicación para cargar la imagen de la bala.
     */
    public Bala(Resources res) {
        // Carga la imagen
        bala = BitmapFactory.decodeResource(res, R.drawable.bullet);

        // Obtiene las dimensiones de la imagen
        width = bala.getWidth();
        height = bala.getHeight();

        // Redimensiona la imagen para reducir el tamaño
        width /= 2;
        height /= 2;

        // Ajusta las dimensiones de la bala la pantalla
        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        // Crea una nueva imagen con las dimensiones ajustadas
        bala = Bitmap.createScaledBitmap(bala, width, height, false);

    }

    /**
     * Devuelve un rectángulo que representa el área de colisión de la bala.
     *
     * @return Rect con la posición y dimensiones de la bala.
     */
    public Rect getColision() {
        return new Rect(x, y, x + width, y + height);
    }
}
