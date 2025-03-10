package com.example.juego2d.utils.ui;

import android.app.Activity;
import android.graphics.Point;
import android.view.View;

//Clase para manejar la configuracion de la pantalla completa y obtener el tamaño de la pantalla
public class PantallaCompleta {

    //Configura la pantalla a pantalla completa
    public static void setFullScreen(Activity activity) {
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    //Obtiene el tamaño de la pantalla
    public static Point getScreenSize(Activity activity) {
        Point point = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(point);
        return point;
    }
}
