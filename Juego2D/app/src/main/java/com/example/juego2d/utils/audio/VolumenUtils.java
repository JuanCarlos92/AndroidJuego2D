package com.example.juego2d.utils.audio;

import android.widget.ImageView;

/**
 * Clase para gestionar el control de volumen en la aplicación.
 * Permite alternar entre los estados de mute y no mute, y actualiza la imagen del control de volumen en consecuencia.
 */
public class VolumenUtils {

    private boolean isMute = false;

    /**
     * Alterna el estado de mute y actualiza la imagen del control de volumen.
     *
     * @param volumeCtrl El ImageView que muestra el control de volumen.
     * @param muteImageResource El recurso de imagen para el estado mute.
     * @param unMuteImageResource El recurso de imagen para el estado no mute.
     */
    public void toggleMute(ImageView volumeCtrl, int muteImageResource, int unMuteImageResource) {
        isMute = !isMute;
        volumeCtrl.setImageResource(isMute ? muteImageResource : unMuteImageResource);
    }

    /**
     * Obtiene el estado actual de mute.
     *
     * @return true si el sonido está en mute, false en caso contrario.
     */
    public boolean isMute() {
        return isMute;
    }
}
