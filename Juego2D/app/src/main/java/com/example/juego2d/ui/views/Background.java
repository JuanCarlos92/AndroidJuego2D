package com.example.juego2d.ui.views;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.example.juego2d.R;
import com.example.juego2d.utils.graficos.CargarImg;

/**
 * Clase que representa el fondo de pantalla del juego.
 * Esta clase se encarga de cargar y redimensionar la imagen del fondo
 * para ajustarla a las dimensiones de la pantalla del dispositivo.
 */
public class Background {
    public int x = 0, y = 0; // Coordenadas del fondo = Inicializada en 0
    public Bitmap background; // Objeto Bitmap para la imagen del fondo

    /**
     * Constructor de la clase Background.
     * Carga la imagen del fondo desde los recursos y la redimensiona
     * para ajustarse a las dimensiones de la pantalla.
     *
     * @param screenX El ancho de la pantalla en píxeles.
     * @param screenY El alto de la pantalla en píxeles.
     * @param res     El objeto Resources que permite acceder a los recursos de la aplicación.
     */
    public Background(int screenX, int screenY, Resources res) {
        // Carga y redimensiona la imagen utilizando ImageUtils
        background = CargarImg.loadAndResizeImage(res, R.drawable.background_glacial, screenX, screenY);
    }
}
