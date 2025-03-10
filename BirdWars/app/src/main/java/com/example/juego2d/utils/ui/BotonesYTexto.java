package com.example.juego2d.utils.ui;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

///Clase para configurar TextViews y Buttons en GameOverActivity
public class BotonesYTexto {

    //Configura un TextView con un texto especifico
    public static void setTextViewText(TextView textView, String text) {
        textView.setText(text);
    }

    //Configura un boton con un listener de clics.
    public static void setButtonOnClickListener(Button button, View.OnClickListener listener) {
        button.setOnClickListener(listener);
    }
}
