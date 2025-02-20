package com.example.juego2d;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delay para esperar 3 segundos
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Cambiar al menu
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);  // 3000 milisegundos = 3 segundos
    }
}