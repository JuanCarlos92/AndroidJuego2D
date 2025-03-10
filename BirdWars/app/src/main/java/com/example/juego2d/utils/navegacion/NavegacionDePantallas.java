package com.example.juego2d.utils.navegacion;

import android.content.Context;
import android.content.Intent;

//Clase de utilidad para manejar la navegación entre actividades en la aplicacion
public class NavegacionDePantallas {

    //Navega a una nueva actividad
    public static void navigateTo(Context context, Class<?> destinationClass) {
        Intent intent = new Intent(context, destinationClass);
        context.startActivity(intent);
    }

    //Navega a una nueva actividad con datos adicionales
    public static void navigateToWithExtras(Context context, Class<?> destinationClass, Intent extras) {
        Intent intent = new Intent(context, destinationClass);
        intent.putExtras(extras);
        context.startActivity(intent);
    }
}

