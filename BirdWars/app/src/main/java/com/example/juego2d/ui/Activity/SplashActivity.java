package com.example.juego2d.ui.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juego2d.R;
import com.example.juego2d.utils.tiempo.TiempoDelay;
import com.example.juego2d.utils.navegacion.NavegacionDePantallas;

//Actividad de inicio (Splash) que se muestra cuando se abre la aplicación.
@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    //Metodo que se llama al crear la actividad
    //Establece el diseño de la pantalla de inicio y configura un retraso de 3 segundos antes de pasar a la siguiente actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Delay para esperar 3 segundos
        TiempoDelay.postDelayed(new Runnable() {
            @Override
            public void run() {

                //Cambiar al menu
                NavegacionDePantallas.navigateTo(SplashActivity.this, MainActivity.class);
                finish();
            }
        }, 3000);  // 3000 milisegundos = 3 segundos
    }
}
