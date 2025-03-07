package com.example.juego2d.model;

import android.content.res.Resources;
import android.graphics.Bitmap;

import static com.example.juego2d.ui.views.GameView.screenRatioX;
import static com.example.juego2d.ui.views.GameView.screenRatioY;

import com.example.juego2d.R;
import com.example.juego2d.utils.graficos.CargarImg;

/**
 * Clase que representa una bala en el juego.
 * La bala es un objeto que se dispara desde el avión.
 */
public class Bala {

    public int x;
    public int y;
    public int width;
    public int height;
    public Bitmap bala;

    /**
     * Constructor de la clase Bala.
     * Carga y redimensiona la imagen de la bala según la pantalla.
     *
     * @param res Recursos de la aplicación para cargar la imagen de la bala.
     */
    public Bala(Resources res) {
        width = 100; // Ancho deseado
        height = 100; // Alto deseado

        // Ajusta las dimensiones según la pantalla
        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        // Carga y redimensiona la imagen de la bala
        bala = CargarImg.loadAndResizeImage(res, R.drawable.bullet, width, height);

    }

}
