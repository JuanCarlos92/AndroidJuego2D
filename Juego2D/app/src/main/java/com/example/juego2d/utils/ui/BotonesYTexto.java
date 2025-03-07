package com.example.juego2d.utils.ui;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Clase de utilidad para configurar TextViews y Buttons en la aplicación.
 * Permite establecer texto en un TextView y configurar un listener de clics en un Button.
 */
public class BotonesYTexto {

    /**
     * Configura un TextView con un texto específico.
     *
     * @param textView El TextView a configurar.
     * @param text     El texto a mostrar.
     */
    public static void setTextViewText(TextView textView, String text) {
        textView.setText(text);
    }

    /**
     * Configura un botón con un listener de clics.
     *
     * @param button   El botón a configurar.
     * @param listener El listener de clics.
     */
    public static void setButtonOnClickListener(Button button, View.OnClickListener listener) {
        button.setOnClickListener(listener);
    }
}
