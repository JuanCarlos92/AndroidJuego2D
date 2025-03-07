package com.example.juego2d.utils.navegacion;

import android.content.Context;
import android.content.Intent;

/**
 * Clase de utilidad para manejar la navegación entre actividades en la aplicación.
 * Permite navegar a nuevas actividades, con o sin datos adicionales.
 */
public class NavegacionDePantallas {

    /**
     * Navega a una nueva actividad.
     *
     * @param context El contexto actual.
     * @param destinationClass La clase de la actividad de destino.
     */
    public static void navigateTo(Context context, Class<?> destinationClass) {
        Intent intent = new Intent(context, destinationClass);
        context.startActivity(intent);
    }

    /**
     * Navega a una nueva actividad con datos adicionales.
     *
     * @param context El contexto actual.
     * @param destinationClass La clase de la actividad de destino.
     * @param extras Los datos adicionales a pasar a la actividad de destino.
     */
    public static void navigateToWithExtras(Context context, Class<?> destinationClass, Intent extras) {
        Intent intent = new Intent(context, destinationClass);
        intent.putExtras(extras);
        context.startActivity(intent);
    }
}

