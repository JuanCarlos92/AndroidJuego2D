package com.example.juego2d.ui.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.juego2d.colision.Colisionador;
import com.example.juego2d.ui.Activity.GameOverActivity;
import com.example.juego2d.model.Avion;
import com.example.juego2d.model.Bala;
import com.example.juego2d.ui.Activity.GameActivity;
import com.example.juego2d.ui.Activity.MainActivity;
import com.example.juego2d.model.Pajaro;
import com.example.juego2d.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Gestiona la vista y logica del juego en la pantalla
 * <p>
 * Esta clase se encarga de dibujar los elementos del juego y manejar la logica de la jugabilidad,
 * como el movimiento de los objetos, la deteccion de colisiones y la actualizacion del puntaje
 */
@SuppressLint("ViewConstructor")
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
    private float lastTouchY = 0;

    //Constructor que inicializa la vista del juego
    public GameView(GameActivity activity, int screenX, int screenY) {
        super(activity);
        this.activity = activity;

        //Configuracion de los efectos de sonido dependiendo de la versión de Android
        AudioAttributes audioAttributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_GAME).build();

        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).setMaxStreams(5).build();

        //Carga el sonido del disparo desde los recursos
        sound = soundPool.load(activity, R.raw.shoot, 1);

        //Configuración de las dimensiones de la pantalla y relacion de aspecto
        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        //Inicializacion de los fondos del juego
        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());
        background2.x = screenX; // Segundo fondo colocado a la derecha del primero

        //Inicializacion del avion (personaje principal)
        avion = new Avion(this, screenY, getResources());

        //Lista para las balas
        balas = new ArrayList<>();

        //Configuración de la fuente para mostrar el puntaje en pantalla
        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);

        //Inicialización de los pajaros
        pajaros = new Pajaro[4];
        for (int i = 0; i < 4; i++) {
            Pajaro pajaro = new Pajaro(getResources());
            pajaros[i] = pajaro;
        }
        random = new Random(); //Generador Random para las posiciones
    }

    //Ejecuta el BUCLE PRINCIPAL DEL JUEGO. Actualiza la logica , Dibuja los elementos y controla la velocidad
    @Override
    public void run() {
        while (isPlaying) {
            update(); //Actualiza la logica
            draw();   //Dibuja los elementos
            sleep();  //Controla la velocidad
        }
    }

    //ACTUALIZA LA LOGICA DEL JUEGO, moviendo los elementos y verificando las colisiones
    private void update() {
        //Movimiento del fondo (simula desplazamiento)
        background1.x -= 5 * screenRatioX;
        background2.x -= 5 * screenRatioX;

        if (background1.x + background1.background.getWidth() < 0) {
            background1.x = screenX;
        }

        if (background2.x + background2.background.getWidth() < 0) {
            background2.x = screenX;
        }

        //Movimiento del avión
        if (avion.isGoingUp) {
            avion.y -= 30 * screenRatioY;
        } else if (avion.isGoingDown) {
            avion.y += 30 * screenRatioY;
        }

        //Evita que el avión salga de los límites de la pantalla
        if (avion.y < 0) avion.y = 0;
        if (avion.y >= screenY - avion.height) avion.y = screenY - avion.height;

        //Lista de balas a eliminar
        List<Bala> trash = new ArrayList<>();

        for (Bala bala : balas) {
            if (bala.x > screenX) trash.add(bala);
            bala.x += 50 * screenRatioX; // Movimiento de la bala

            //Verifica colisiones entre balas y aves
            for (Pajaro pajaro : pajaros) {
                if (Colisionador.verificarColisionBalaPajaro(bala, pajaro)) {
                    score++; // Incrementa el puntaje
                    pajaro.x = -500; // Elimina el pájaro de la pantalla
                    bala.x = screenX + 500; // Elimina la bala
                    pajaro.wasShot = true;
                }
            }
        }
        //Elimina las balas que ya no estan en uso
        balas.removeAll(trash);
        //Mueve el pájaro hacia la izquierda según su velocidad
        for (Pajaro pajaro : pajaros) {
            pajaro.y = Math.max(pajaro.y, screenY / 4);
            pajaro.x -= pajaro.speed;

            //Si el pájaro sale completamente de la pantalla por la izquierda
            if (pajaro.x + pajaro.width < 0) {

                //Configura una nueva velocidad aleatoria para el pájaro
                int bound = (int) (30 * screenRatioX);
                pajaro.speed = random.nextInt(bound);
                //Asegura que la velocidad mínima sea suficiente para moverse
                if (pajaro.speed < 10 * screenRatioX) pajaro.speed = (int) (10 * screenRatioX);

                //Reinicia la posición del pájaro para que reaparezca en la pantalla
                pajaro.x = screenX;
                pajaro.y = random.nextInt(screenY - pajaro.height);
                pajaro.wasShot = false; // Indica que no ha sido derribado en esta nueva aparición
            }

            if (Colisionador.verificarColisionAvionPajaro(avion, pajaro)) {
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

    //Dibuja los elementos del juego en la pantalla. Fondos, Avion, Pajaros, Balas y Score
    private void draw() {
        //Verifica si la superficie de dibujo es válida
        if (getHolder().getSurface().isValid()) {

            //Bloquea el canvas para comenzar a dibujar
            Canvas canvas = getHolder().lockCanvas();

            //Dibuja los fondos en la pantalla
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            //Dibuja todos los pajaros en la pantalla
            for (Pajaro pajaro : pajaros) {
                int posicionY = Math.max(pajaro.y, screenY / 4);
                canvas.drawBitmap(pajaro.getPajaro(), pajaro.x, posicionY, paint);
            }

            //Dibuja el puntaje en el centro superior de la pantalla
            canvas.drawText(score + "", screenX / 2f, 164, paint);

            // Si el juego ha terminado, dibuja la imagen del personaje muerto y espera antes de salir
            if (isGameOver) {
                isPlaying = false;
                canvas.drawBitmap(avion.getMuerte(), avion.x, avion.y, paint);
                getHolder().unlockCanvasAndPost(canvas);

                return;
            }

            //Dibuja el personaje en la pantalla
            canvas.drawBitmap(avion.getAvion(), avion.x, avion.y, paint);

            //Dibuja todas las balas activas en la pantalla
            for (Bala bala : balas)
                canvas.drawBitmap(bala.bala, bala.x, bala.y, paint);

            //Libera el canvas y muestra los cambios en la pantalla
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    //Controla la velocidad del juego aplicando una pausa breve en cada iteración del bucle principal
    private void sleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Reanuda el juego iniciando el hilo principal.
    public void resume() {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    //Detecta la interaccion del usuario con la pantalla tactil
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        int pointerIndex = event.getActionIndex();

        float x = event.getX(pointerIndex);
        float y = event.getY(pointerIndex);

        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: //DETECTA EL DEDO
                if (x < (float) screenX / 2) { //Solo si es en la Derecha
                    lastTouchY = y;
                } else {
                    avion.toShoot++;
                }
                break;

            case MotionEvent.ACTION_MOVE: //Detectar el desplazamiento
                if (x < (float) screenX / 2) { //Solo si es en la izquierda
                    if (y < lastTouchY) {
                        avion.isGoingUp = true;
                        avion.isGoingDown = false;
                    } else if (y > lastTouchY) {
                        avion.isGoingUp = false;
                        avion.isGoingDown = true;
                    }
                    lastTouchY = y;
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP: //NO DETECTA EL DEDO
                if (x < (float) screenX / 2) {
                    avion.isGoingUp = false; //Detener movimiento
                    avion.isGoingDown = false;
                }
                break;
        }

        return true;
    }

    //Genera un nuevo disparo desde la posicion actual del avion
    public void nuevaBala() {
        Bala bala = new Bala(getResources());
        bala.x = avion.x + avion.width; //La bala aparece delante del avion
        bala.y = avion.y + (avion.height / 2); //Se alinea al centro del avion
        balas.add(bala);

        // Reproducir el sonido de disparo
        if (!activity.isMute) {
            soundPool.play(sound, 1, 1, 0, 0, 1);

        }
    }
}
