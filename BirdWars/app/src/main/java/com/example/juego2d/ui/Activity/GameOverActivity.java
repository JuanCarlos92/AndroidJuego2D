package com.example.juego2d.ui.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.juego2d.R;
import com.example.juego2d.utils.navegacion.NavegacionDePantallas;
import com.example.juego2d.utils.ui.BotonesYTexto;

public class GameOverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        // Obtener la puntuación pasada desde GameView
        int score = getIntent().getIntExtra("score", 0);

        // Mostrar la puntuación en el TextView
        TextView scoreTextView = findViewById(R.id.textScore);
        BotonesYTexto.setTextViewText(scoreTextView, "Score: " + score);

        // Botón para volver al menú principal
        Button btnMenu = findViewById(R.id.btnMenu);
        BotonesYTexto.setButtonOnClickListener(btnMenu, v -> {
            NavegacionDePantallas.navigateTo(GameOverActivity.this, MainActivity.class);
            finish();
        });

        // Botón para reiniciar el juego
        Button btnRestart = findViewById(R.id.btnRestart);
        BotonesYTexto.setButtonOnClickListener(btnRestart, v -> {
            NavegacionDePantallas.navigateTo(GameOverActivity.this, GameActivity.class);
            finish();
        });
    }
}


