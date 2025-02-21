package com.example.juego2d;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juego2d.view.GameView;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    public boolean isMute;

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

    // Metodo cuando se pausa el juego (por ejemplo, al minimizar la app)
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    // Metodo cuando se reanuda el juego (por ejemplo, al volver a la app)
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}


