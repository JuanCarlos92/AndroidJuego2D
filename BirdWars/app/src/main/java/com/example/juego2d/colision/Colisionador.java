package com.example.juego2d.colision;

import android.graphics.Rect;

import com.example.juego2d.model.Avion;
import com.example.juego2d.model.Bala;
import com.example.juego2d.model.Pajaro;

public class Colisionador {

    //Verifica colision entre dos rectángulos
    public static boolean verificarColision(Rect rect1, Rect rect2) {
        return Rect.intersects(rect1, rect2);
    }

    //Verifica colision entre avion - pajaro
    public static boolean verificarColisionAvionPajaro(Avion avion, Pajaro pajaro) {
        return verificarColision(getColisionAvion(avion),getColisionPajaro(pajaro));
    }

    //Verifica colisión entre bala - pajaro
    public static boolean verificarColisionBalaPajaro(Bala bala, Pajaro pajaro) {
        return verificarColision(getColisionBala(bala), getColisionPajaro(pajaro));
    }

    //Devuelve la forma de colision del avion
    public static Rect getColisionAvion(Avion avion) {
        int MargenColision = 100;  // Reduce la zona de colision
        int MargenSuperior = 130;
        return new Rect(avion.x + MargenColision, avion.y + MargenSuperior, avion.x + avion.width - MargenColision, avion.y + avion.height - MargenColision);
    }

    //Devuelve la forma de colision del pajaro
    public static Rect getColisionPajaro(Pajaro pajaro) {
        int MargenColision = 70;  // Reduce la zona de colision
        return new Rect(pajaro.x + MargenColision, pajaro.y + MargenColision, pajaro.x + pajaro.width - MargenColision, pajaro.y + pajaro.height - MargenColision);
    }

    //Devuelve la forma de colision del Bala
    public static Rect getColisionBala(Bala bala) {
        return new Rect(bala.x, bala.y, bala.x + bala.width, bala.y + bala.height);
    }
}

