package com.example.juego2d.model;

import android.content.res.Resources;
import android.graphics.Bitmap;

import static com.example.juego2d.ui.views.GameView.screenRatioX;
import static com.example.juego2d.ui.views.GameView.screenRatioY;

import com.example.juego2d.R;
import com.example.juego2d.utils.graficos.CargarImg;

//Clase que representa una bala
public class Bala {

    public int x;
    public int y;
    public int width;
    public int height;
    public Bitmap bala;

    //Constructor de la clase bala
    public Bala(Resources res) {
        width = 100;
        height = 100;

        //Ajusta las dimensiones segun la pantalla
        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        //Carga y redimensiona la imagen de la bala
        bala = CargarImg.loadAndResizeImage(res, R.drawable.bullet, width, height);

    }
}
