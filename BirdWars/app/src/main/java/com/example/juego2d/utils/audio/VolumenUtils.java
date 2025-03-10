package com.example.juego2d.utils.audio;

import android.widget.ImageView;

//Clase para gestionar el control de volumen en la aplicacion.
public class VolumenUtils {

    private boolean isMute = false;

    //Alterna el estado de mute y actualiza la imagen del control de volumen.
    public void toggleMute(ImageView volumeCtrl, int muteImageResource, int unMuteImageResource) {
        isMute = !isMute;
        volumeCtrl.setImageResource(isMute ? muteImageResource : unMuteImageResource);
    }

    //Obtiene el estado actual de mute.
    public boolean isMute() {
        return isMute;
    }
}
