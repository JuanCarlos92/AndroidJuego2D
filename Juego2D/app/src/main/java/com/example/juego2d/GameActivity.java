package com.example.juego2d;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juego2d.view.GameView;

/**
 * Actividad principal del juego que gestiona la vista del juego y la interacción con el usuario.
 * Esta actividad se encarga de inicializar y mostrar la pantalla del juego, controlar el muteo del sonido,
 * y gestionar las pausas y reanudaciones del juego cuando la aplicación se minimiza o vuelve a primer plano.
 */
public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    public boolean isMute;

    /**
     * Metodo que se llama al crear la actividad.
     * Inicializa la vista del juego y configura la pantalla a pantalla completa.
     *
     * @param savedInstanceState El estado guardado de la actividad anterior, si existe.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtener el valor de 'isMute' pasado desde MainActivity
        isMute = getIntent().getBooleanExtra("isMute", false);

        // ocultar la barra
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        // Obtener el tamaño de la pantalla
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        // Crea una instancia de GameView con el tamaño de la pantalla
        gameView = new GameView(this, point.x, point.y);
        setContentView(gameView);
    }

    /**
     * Metodo que se llama cuando la actividad se pausa, por ejemplo, cuando la aplicación se minimiza.
     * En este metodo se pausa el juego.
     */
//    @Override
//    protected void onPause() {
//        super.onPause();
//        gameView.pause();
//    }

    /**
     * Metodo que se llama cuando la actividad se reanuda, por ejemplo, cuando la aplicación vuelve al primer plano.
     * En este metodo se reanuda el juego.
     */
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}


