package com.example.juego2d.utils.ads;

import android.app.Activity;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.WindowMetrics;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

//Clase auxiliar para gestionar la configuración y carga del banner de anuncios en AdMob
public class AdMobBanner {
    private final Activity activity;
    private AdView adView;
    private FrameLayout adContainerView;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private static final int AD_DISPLAY_TIME_MS = 15000; // 15 segundos

    public AdMobBanner(Activity activity) {
        this.activity = activity;
    }

    //Configura y carga el banner de anuncios en el contenedor proporcionado.
    public void setupAdView(FrameLayout adContainerView) {
        this.adContainerView = adContainerView;
        adView = new AdView(activity);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111"); // ID de prueba
        adView.setAdSize(getAdSize());

        adContainerView.addView(adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        scheduleAdClose();
    }

    //Programa el cierre del anuncio después del tiempo especificado.
    private void scheduleAdClose() {
        handler.postDelayed(this::closeAd, AD_DISPLAY_TIME_MS);
    }

    //Cierra el anuncio eliminándolo del contenedor.
    private void closeAd() {
        if (adView != null && adContainerView != null) {
            adContainerView.removeView(adView);
            adView = null;
        }
    }

    //Calcula el tamaño del banner de anuncios de forma adaptable.
    private AdSize getAdSize() {
        DisplayMetrics displayMetrics = activity.getResources().getDisplayMetrics();
        int adWidthPixels = displayMetrics.widthPixels;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowMetrics windowMetrics = activity.getWindowManager().getCurrentWindowMetrics();
            adWidthPixels = windowMetrics.getBounds().width();
        }

        float density = displayMetrics.density;
        int adWidth = (int) (adWidthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }

    //Metodo para limpiar recursos cuando no se necesita más el anuncio.
    public void destroy() {
        handler.removeCallbacksAndMessages(null);
        if (adView != null) {
            adView.destroy();
            adView = null;
        }
    }
}