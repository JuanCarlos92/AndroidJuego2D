package com.example.juego2d.model;

import static com.example.juego2d.ui.views.GameView.screenRatioX;
import static com.example.juego2d.ui.views.GameView.screenRatioY;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.example.juego2d.R;
import com.example.juego2d.utils.graficos.CargarImg;

//Clase que representa un pajaro
public class Pajaro {

    public int speed = 20;
    public boolean wasShot = true;
    public int x = 0;
    public int y;
    public int width;
    public int height;
    int birdCounter = 1;
    Bitmap pajaro1, pajaro2, pajaro3, pajaro4;

    //Constructor de la clase pajaro
    public Pajaro(Resources res) {

        width = 200;
        height = 200;

        //Ajusta las dimensiones segun la pantalla
        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        //Carga y redimensiona las imagenes del pajaro
        pajaro1 = CargarImg.loadAndResizeImage(res, R.drawable.bird1, width, height);
        pajaro2 = CargarImg.loadAndResizeImage(res, R.drawable.bird2, width, height);
        pajaro3 = CargarImg.loadAndResizeImage(res, R.drawable.bird3, width, height);
        pajaro4 = CargarImg.loadAndResizeImage(res, R.drawable.bird4, width, height);

        //Inicializa (la posición Y) del pájaro fuera de la pantalla (arriba)
        y = -height;
    }

    //Devuelve la imagen del pajaro correspondiente a la animacion
    public Bitmap getPajaro() {

        //Controla el ciclo de animacion cambiando el frame en cada llamada
        if (birdCounter == 1) {
            birdCounter++;
            return pajaro1;
        }
        if (birdCounter == 2) {
            birdCounter++;
            return pajaro2;
        }
        if (birdCounter == 3) {
            birdCounter++;
            return pajaro3;
        }

        //Reinicia el contador y devuelve el ultimo frame
        birdCounter = 1;
        return pajaro4;
    }
}
