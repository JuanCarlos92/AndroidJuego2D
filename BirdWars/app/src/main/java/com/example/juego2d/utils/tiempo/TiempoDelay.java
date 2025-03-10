package com.example.juego2d.utils.tiempo;

import android.os.Handler;

//Clase para manejar retrasos en la ejecución de tareas.
public class TiempoDelay {

    //Ejecuta una tarea después de un retraso especificado.
    public static void postDelayed(Runnable runnable, long delay) {
        new Handler().postDelayed(runnable, delay);
    }
}

