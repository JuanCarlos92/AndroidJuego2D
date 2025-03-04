package com.example.juego2d.view;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.juego2d.GameOverActivity;
import com.example.juego2d.gameobject.Avion;
import com.example.juego2d.Background;
import com.example.juego2d.gameobject.Bala;
import com.example.juego2d.GameActivity;
import com.example.juego2d.MainActivity;
import com.example.juego2d.gameobject.Pajaro;
import com.example.juego2d.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase que gestiona la vista y la lógica del juego en la pantalla.
 * Esta clase se encarga de dibujar los elementos del juego y manejar la lógica de la jugabilidad,
 * como el movimiento de los objetos, la detección de colisiones y la actualización del puntaje.
 */
public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying, isGameOver = false;
    private int screenX, screenY, score = 0;
    public static float screenRatioX, screenRatioY;
    private Paint paint;
    private Pajaro[] pajaros;
    private Random random;
    private SoundPool soundPool;
    private List<Bala> balas;
    private int sound;
    private Avion avion;
    private GameActivity activity;
    private Background background1, background2;
    private float lastTouchY = 0; // Variable para rastrear la posición del toque


    /**
     * Constructor que inicializa la vista del juego.
     *
     * @param activity La actividad principal del juego (GameActivity).
     * @param screenX  Ancho de la pantalla en píxeles.
     * @param screenY  Alto de la pantalla en píxeles.
     */
    public GameView(GameActivity activity, int screenX, int screenY) {
        super(activity);
        this.activity = activity;

        // Configuración de los efectos de sonido dependiendo de la versión de Android
        AudioAttributes audioAttributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_GAME).build();

        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(5).build();

        // Carga el sonido del disparo desde los recursos
        sound = soundPool.load(activity, R.raw.shoot, 1);

        // Configuración de las dimensiones de la pantalla y relación de aspecto
        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        // Inicialización de los fondos del juego
        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());
        background2.x = screenX; // Segundo fondo colocado a la derecha del primero

        // Inicialización del avión (personaje principal)
        avion = new Avion(this, screenY, getResources());

        // Lista para las balas
        balas = new ArrayList<>();

        // Configuración de la fuente para mostrar el puntaje en pantalla
        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);

        // Inicialización de los pajaros
        pajaros = new Pajaro[4];
        for (int i = 0; i < 4; i++) {
            Pajaro pajaro = new Pajaro(getResources());
            pajaros[i] = pajaro;
        }
        random = new Random(); // Generador Random para las posiciones
    }

    /**
     * Metodo que ejecuta el bucle principal del juego.
     * Este metodo actualiza la lógica del juego, dibuja los elementos en pantalla
     * y controla la velocidad de actualización.
     */
    @Override
    public void run() {
        while (isPlaying) {
            update(); // Actualiza la lógica del juego
            draw();   // Dibuja los elementos en pantalla
            sleep();  // Controla la velocidad del juego
        }
    }

    /**
     * Metodo que actualiza la lógica del juego, moviendo los elementos y verificando las colisiones.
     */
    private void update() {
        // Movimiento del fondo (simula desplazamiento)
        background1.x -= 5 * screenRatioX;
        background2.x -= 5 * screenRatioX;

        if (background1.x + background1.background.getWidth() < 0) {
            background1.x = screenX;
        }

        if (background2.x + background2.background.getWidth() < 0) {
            background2.x = screenX;
        }

        // Movimiento del avión
        if (avion.isGoingUp) avion.y -= 30 * screenRatioY;
        else avion.y += 30 * screenRatioY;

        // Evita que el avión salga de los límites de la pantalla
        if (avion.y < 0) avion.y = 0;
        if (avion.y >= screenY - avion.height) avion.y = screenY - avion.height;

        // Lista de balas a eliminar
        List<Bala> trash = new ArrayList<>();

        for (Bala bala : balas) {
            if (bala.x > screenX) trash.add(bala);
            bala.x += 50 * screenRatioX; // Movimiento de la bala

            // Verifica colisiones entre balas y aves
            for (Pajaro pajaro : pajaros) {
                if (Rect.intersects(pajaro.getColision(), bala.getColision())) {
                    score++; // Incrementa el puntaje
                    pajaro.x = -500; // Elimina el pájaro de la pantalla
                    bala.x = screenX + 500; // Elimina la bala
                    pajaro.wasShot = true;
                }
            }
        }
        // Elimina las balas que ya no están en uso
        balas.removeAll(trash);
        // Mueve el pájaro hacia la izquierda según su velocidad
        for (Pajaro pajaro : pajaros) {
            pajaro.x -= pajaro.speed;

            // Si el pájaro sale completamente de la pantalla por la izquierda
            if (pajaro.x + pajaro.width < 0) {

                // Configura una nueva velocidad aleatoria para el pájaro
                int bound = (int) (30 * screenRatioX);
                pajaro.speed = random.nextInt(bound);
                // Asegura que la velocidad mínima sea suficiente para moverse
                if (pajaro.speed < 10 * screenRatioX) pajaro.speed = (int) (10 * screenRatioX);

                // Reinicia la posición del pájaro para que reaparezca en la pantalla
                pajaro.x = screenX;
                pajaro.y = random.nextInt(screenY - pajaro.height);
                pajaro.wasShot = false; // Indica que no ha sido derribado en esta nueva aparición
            }

            // Si el pájaro colisiona con el personaje, el juego termina
//            if (Rect.intersects(pajaro.getColision(), avion.getColision())) {
//                isGameOver = true;
//                return;
//            }

            if (Rect.intersects(pajaro.getColision(), avion.getColision())) {
                isGameOver = true;
                activity.runOnUiThread(() -> {
                    Intent intent = new Intent(activity, GameOverActivity.class);
                    intent.putExtra("score", score); // Enviar la puntuación
                    activity.startActivity(intent);
                    activity.finish();
                });
                return;
            }
        }
    }

    /**
     * Metodo que dibuja los elementos del juego en la pantalla.
     * Dibuja los fondos, el avión, los pájaros, las balas y el puntaje.
     */
    private void draw() {
        // Verifica si la superficie de dibujo es válida
        if (getHolder().getSurface().isValid()) {

            // Bloquea el canvas para comenzar a dibujar
            Canvas canvas = getHolder().lockCanvas();
            // Dibuja los fondos en la pantalla
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            // Dibuja todos los pájaros en la pantalla
            for (Pajaro pajaro : pajaros) {
                int posicionY = Math.max(pajaro.y, screenY / 4);
                canvas.drawBitmap(pajaro.getPajaro(), pajaro.x, posicionY, paint);
            }
            // Dibuja el puntaje en el centro superior de la pantalla
            canvas.drawText(score + "", screenX / 2f, 164, paint);

            // Si el juego ha terminado, dibuja la imagen del personaje muerto y espera antes de salir
            if (isGameOver) {
                isPlaying = false;
                canvas.drawBitmap(avion.getMuerte(), avion.x, avion.y, paint);
                getHolder().unlockCanvasAndPost(canvas);

                //waitBeforeExiting(); // Espera antes de salir
                return;
            }

            // Dibuja el personaje en la pantalla
            canvas.drawBitmap(avion.getAvion(), avion.x, avion.y, paint);

            // Dibuja todas las balas activas en la pantalla
            for (Bala bala : balas)
                canvas.drawBitmap(bala.bala, bala.x, bala.y, paint);

            // Libera el canvas y muestra los cambios en la pantalla
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    /**
     * Metodo que espera unos segundos antes de salir del juego tras un Game Over.
     * Después de la espera, vuelve a la pantalla principal del juego.
     */
    private void waitBeforeExiting() {
        try {
            Thread.sleep(3000); // Pausa durante 3 segundos
            activity.startActivity(new Intent(activity, MainActivity.class)); // Vuelve a la pantalla principal
            activity.finish(); // Cierra la actividad actual
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Metodo que controla la velocidad del juego aplicando una pausa breve en cada iteración del bucle principal.
     */
    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que reanuda el juego iniciando el hilo principal.
     */
    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Metodo que pausa el juego deteniendo el hilo principal.
     */
    public void pause() {
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que detecta la interacción del usuario con la pantalla táctil.
     *
     * @param event El evento de toque.
     * @return true si el evento ha sido procesado correctamente.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked(); // Tipo de evento
        int pointerIndex = event.getActionIndex(); // Índice del puntero

        float x = event.getX(pointerIndex);
        float y = event.getY(pointerIndex);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: // Se detecta un toque
                if (x < (float) screenX / 2) { // Lado izquierdo de la pantalla
                    lastTouchY = y; // Guardar posición inicial del toque
                } else { // Lado derecho: disparo
                    avion.toShoot++;
                }
                break;

            case MotionEvent.ACTION_MOVE: // Detectar el desplazamiento
                if (x < (float) screenX / 2) { // Solo si es en la izquierda
                    if (y < lastTouchY) { // Mover arriba
                        avion.isGoingUp = true;
                    } else if (y > lastTouchY) { // Mover abajo
                        avion.isGoingUp = false;
                    }
                    lastTouchY = y; // Actualizar la última posición
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP: // Se suelta el dedo
                if (x < (float) screenX / 2) {
                    avion.isGoingUp = false; // Detener movimiento cuando se suelta
                }
                break;
        }

        return true; // Seguir detectando eventos táctiles
    }

    /**
     * Genera un nuevo disparo desde la posición actual del avión.
     * Crea una nueva instancia de la clase {@link Bala}, establece su posición justo
     * frente al avión y la alinea con el centro del mismo. Luego, agrega la bala
     * a la lista de balas activas.
     * -
     * También reproduce un sonido de disparo, a menos que el juego esté en modo mudo.
     *
     * @see Bala
     */
    public void nuevaBala() {
        Bala bala = new Bala(getResources());
        bala.x = avion.x + avion.width; // La bala aparece justo delante del personaje
        bala.y = avion.y + (avion.height / 2); // Se alinea al centro del personaje
        balas.add(bala); // Agrega la bala a la lista de balas activas

        // Reproducir el sonido de disparo
        if (!activity.isMute) {
            soundPool.play(sound, 1, 1, 0, 0, 1);  // Reproduce el sonido

        }
    }
}
