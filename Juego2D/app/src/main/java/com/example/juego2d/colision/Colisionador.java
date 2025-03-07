package com.example.juego2d.colision;

import android.graphics.Rect;

import com.example.juego2d.model.Avion;
import com.example.juego2d.model.Bala;
import com.example.juego2d.model.Pajaro;

public class Colisionador {

    /**
     * Verifica si hay una colisión entre dos rectángulos.
     *
     * @param rect1 Primer rectángulo.
     * @param rect2 Segundo rectángulo.
     * @return true si los rectángulos se intersectan, false en caso contrario.
     */
    public static boolean verificarColision(Rect rect1, Rect rect2) {
        return Rect.intersects(rect1, rect2);
    }

    /**
     * Verifica si hay una colisión entre el avión y un pájaro.
     *
     * @param avion El avión.
     * @param pajaro El pájaro.
     * @return true si hay una colisión, false en caso contrario.
     */
    public static boolean verificarColisionAvionPajaro(Avion avion, Pajaro pajaro) {
        return verificarColision(getColisionAvion(avion),getColisionPajaro(pajaro));
    }

    /**
     * Verifica si hay una colisión entre una bala y un pájaro.
     *
     * @param bala La bala.
     * @param pajaro El pájaro.
     * @return true si hay una colisión, false en caso contrario.
     */
    public static boolean verificarColisionBalaPajaro(Bala bala, Pajaro pajaro) {
        return verificarColision(getColisionBala(bala), getColisionPajaro(pajaro));
    }

    /**
     * Devuelve la forma de colisión del avion.
     *
     * @param avion El avión.
     * @return Un objeto Rect que representa la zona de colisión del avión.
     */
    public static Rect getColisionAvion(Avion avion) {
        int MargenColision = 100;  // Reduce la zona de colisión
        int MargenSuperior = 130;
        return new Rect(avion.x + MargenColision, avion.y + MargenSuperior, avion.x + avion.width - MargenColision, avion.y + avion.height - MargenColision);
    }

    /**
     * Devuelve la forma de colisión del pajaro.
     *
     * @return Rect que representa la zona de colisión del pájaro.
     */
    public static Rect getColisionPajaro(Pajaro pajaro) {
        int MargenColision = 70;  // Reduce la zona de colisión
        return new Rect(pajaro.x + MargenColision, pajaro.y + MargenColision, pajaro.x + pajaro.width - MargenColision, pajaro.y + pajaro.height - MargenColision);
    }

    /**
     * Devuelve un rectángulo que representa el área de colisión de la bala.
     *
     * @return Rect con la posición y dimensiones de la bala.
     */
    public static Rect getColisionBala(Bala bala) {
        return new Rect(bala.x, bala.y, bala.x + bala.width, bala.y + bala.height);
    }
}

