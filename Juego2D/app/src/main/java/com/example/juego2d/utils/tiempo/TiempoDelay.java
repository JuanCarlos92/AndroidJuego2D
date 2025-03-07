package com.example.juego2d.utils.tiempo;

import android.os.Handler;

/**
 * Clase de utilidad para manejar retrasos en la ejecución de tareas.
 * Permite ejecutar una tarea después de un retraso especificado.
 */
public class TiempoDelay {

    /**
     * Ejecuta una tarea después de un retraso especificado.
     *
     * @param runnable La tarea a ejecutar.
     * @param delay El tiempo de retraso en milisegundos.
     */
    public static void postDelayed(Runnable runnable, long delay) {
        new Handler().postDelayed(runnable, delay);
    }
}

