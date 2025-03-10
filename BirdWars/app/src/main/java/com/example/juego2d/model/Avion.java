package com.example.juego2d.model;

import android.content.res.Resources;
import android.graphics.Bitmap;

import static com.example.juego2d.ui.views.GameView.screenRatioX;
import static com.example.juego2d.ui.views.GameView.screenRatioY;

import com.example.juego2d.R;
import com.example.juego2d.utils.graficos.CargarImg;
import com.example.juego2d.ui.views.GameView;

//Clase que representa un avion
public class Avion {

    public int toShoot = 0;
    public boolean isGoingUp = false, isGoingDown = false;
    public int x;
    public int y;
    public int width;
    public int height;
    int wingCounter = 0;
    int shootCounter = 1;
    Bitmap avion1, avion2, disparo1, disparo2, disparo3, disparo4, disparo5, muerte;
    private GameView gameView;

    //Constructor de la clase avion
    public Avion(GameView gameView, int screenY, Resources res) {
        this.gameView = gameView;

        //Define las dimensiones deseadas para las imagenes
        width = 500;
        height = 500;

        //Ajusta las dimensiones segun la pantalla
        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);

        //Carga y redimensiona img del avion normal
        avion1 = CargarImg.loadAndResizeImage(res, R.drawable.fly1, width, height);
        avion2 = CargarImg.loadAndResizeImage(res, R.drawable.fly2, width, height);

        //Carga y redimensiona img de disparo
        disparo1 = CargarImg.loadAndResizeImage(res, R.drawable.shoot1, width, height);
        disparo2 = CargarImg.loadAndResizeImage(res, R.drawable.shoot2, width, height);
        disparo3 = CargarImg.loadAndResizeImage(res, R.drawable.shoot3, width, height);
        disparo4 = CargarImg.loadAndResizeImage(res, R.drawable.shoot4, width, height);
        disparo5 = CargarImg.loadAndResizeImage(res, R.drawable.shoot5, width, height);

        //Carga y redimensiona img del avion roto
        muerte = CargarImg.loadAndResizeImage(res, R.drawable.dead, width, height);

        // Posiciona el personaje en el centro vertical de la pantalla
        y = screenY / 2;
        x = (int) (64 * screenRatioX);

    }

    //Devuelve la imagen del avion segun su estado
    public Bitmap getAvion() {
        if (toShoot != 0) {

            //Si el personaje esta disparando ...
            if (shootCounter == 1) {
                shootCounter++;
                return disparo1;
            }
            if (shootCounter == 2) {
                shootCounter++;
                return disparo2;
            }
            if (shootCounter == 3) {
                shootCounter++;
                return disparo3;
            }
            if (shootCounter == 4) {
                shootCounter++;
                return disparo4;
            }

            //Reinicia la animacion de disparo y genera una nueva bala
            shootCounter = 1;
            toShoot--;
            gameView.nuevaBala();

            return disparo5;
        }

        //Alterna entre las dos imagenes del vuelo normal
        if (wingCounter == 0) {
            wingCounter++;
            return avion1;
        }
        wingCounter--;
        return avion2;
    }

    //Devuelve la imagen del avion cuando ha sido destruido
    public Bitmap getMuerte() {
        return muerte;
    }
}
