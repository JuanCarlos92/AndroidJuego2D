package com.example.juego2d.ui.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juego2d.R;
import com.example.juego2d.utils.ads.AdMobBanner;
import com.example.juego2d.utils.navegacion.NavegacionDePantallas;
import com.example.juego2d.utils.audio.VolumenUtils;
import com.google.android.gms.ads.MobileAds;

/**
 * Actividad principal de la aplicación que gestiona la pantalla de inicio.
 * Esta actividad permite iniciar el juego, salir de la aplicación y controlar el estado de muteo del sonido.
 */
public class MainActivity extends AppCompatActivity {

    private VolumenUtils volumenUtils = new VolumenUtils();
    private MediaPlayer mediaPlayer;
    private AdMobBanner adMobBanner;

    /**
     * Metodo que se llama al crear la actividad.
     * Inicializa los elementos de la interfaz de usuario y establece los oyentes de eventos para los botones.
     *
     * @param savedInstanceState El estado guardado de la actividad anterior, si existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, initializationStatus -> {});

        FrameLayout adContainerView = findViewById(R.id.adContainerView);
        adMobBanner = new AdMobBanner(this);
        adMobBanner.setupAdView(adContainerView);

        // Inicializar y reproducir música de fondo
        mediaPlayer = MediaPlayer.create(this, R.raw.menu);
        mediaPlayer.setLooping(true); // Repetir la música en bucle
        if (!volumenUtils.isMute()) {
            mediaPlayer.start();
        }

        // Iniciar el juego
        findViewById(R.id.play).setOnClickListener(view -> {
            // Detener la música del menú si está sonando
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release(); // Liberar recursos
                mediaPlayer = null;
            }

            Intent extras = new Intent();
            extras.putExtra("isMute", volumenUtils.isMute()); // Pasamos el valor de isMute
            NavegacionDePantallas.navigateToWithExtras(this, GameActivity.class, extras);
        });

        // Salir de la aplicación
        findViewById(R.id.exit).setOnClickListener(view -> finishAffinity());

        // Control de volumen
        final ImageView volumeCtrl = findViewById(R.id.volumeCtrl);
        volumeCtrl.setImageResource(R.drawable.ic_volume_up_black_24dp);

        volumeCtrl.setOnClickListener(view -> {
            volumenUtils.toggleMute(volumeCtrl, R.drawable.ic_volume_off_black_24dp, R.drawable.ic_volume_up_black_24dp);

            if (mediaPlayer != null) {
                if (volumenUtils.isMute()) {
                    mediaPlayer.setVolume(0, 0); // Silencia la música
                } else {
                    mediaPlayer.setVolume(1, 1); // Restaura el volumen
                }
            }
        });
    }
}