package com.example.juego2d;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        // Obtener la puntuación pasada desde GameView
        int score = getIntent().getIntExtra("score", 0);

        // Mostrar la puntuación en el TextView
        TextView scoreTextView = findViewById(R.id.textScore);
        scoreTextView.setText("Puntuación: " + score);

        // Botón para volver al menú principal
        Button btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(v -> {
            Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // Botón para reiniciar el juego
        Button btnRestart = findViewById(R.id.btnRestart);
        btnRestart.setOnClickListener(v -> {
            Intent intent = new Intent(GameOverActivity.this, GameActivity.class);
            startActivity(intent);
            finish();
        });
    }
}

