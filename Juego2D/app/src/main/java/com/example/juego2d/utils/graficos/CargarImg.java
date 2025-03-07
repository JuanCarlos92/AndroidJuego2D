package com.example.juego2d.utils.graficos;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Clase de utilidad para cargar y redimensionar imágenes desde los recursos de la aplicación.
 */
public class CargarImg {

    /**
     * Carga una imagen desde los recursos y la redimensiona.
     *
     * @param res      El objeto Resources que permite acceder a los recursos de la aplicación.
     * @param drawableId El identificador del recurso drawable.
     * @param width     El ancho deseado de la imagen.
     * @param height    El alto deseado de la imagen.
     * @return Un objeto Bitmap con la imagen cargada y redimensionada.
     */
    public static Bitmap loadAndResizeImage(Resources res, int drawableId, int width, int height) {
        Bitmap bitmap = BitmapFactory.decodeResource(res, drawableId);
        return Bitmap.createScaledBitmap(bitmap, width, height, false);
    }
}
