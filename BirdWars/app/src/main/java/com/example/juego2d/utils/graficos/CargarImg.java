package com.example.juego2d.utils.graficos;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

//Clase para cargar y redimensionar img desde los recursos
public class CargarImg {

    //Carga una imagen desde los recursos y la redimensiona
    public static Bitmap loadAndResizeImage(Resources res, int drawableId, int width, int height) {
        Bitmap bitmap = BitmapFactory.decodeResource(res, drawableId);
        return Bitmap.createScaledBitmap(bitmap, width, height, false);
    }
}
