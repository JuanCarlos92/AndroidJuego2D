package com.example.juego2d;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad de inicio (Splash) que se muestra cuando se abre la aplicación.
 * Esta actividad muestra una pantalla de bienvenida durante 3 segundos antes de redirigir a la actividad principal.
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * Metodo que se llama al crear la actividad.
     * Establece el diseño de la pantalla de inicio y configura un retraso de 3 segundos antes de pasar a la siguiente actividad.
     *
     * @param savedInstanceState El estado guardado de la actividad anterior, si existe.
     */
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