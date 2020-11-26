package com.example.eva3_8_banner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.widget.ImageView;
import android.widget.SeekBar;

/*
* Karla Pamela Cárdenas Leyva 18550338
* Multitarea. Practica de hilos que muestra un banner en donde se cambian imagenes
* cada cierto tiempo*/
public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    int[] imagenes = {R.drawable.f1,R.drawable.f2,R.drawable.f3}; //Imagenes agregadas
    int indice = 0;
    ImageView imgVwBanner;
    SeekBar sBVal;
    long valSBar = 100;

    //Handler con Runnable

    Handler handler = new Handler();

    //Background controla el hilo
    Runnable backGrRun = new Runnable() { //Hilo con el trabajo pesado
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(valSBar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(foreGrRun); //Notificamos que cambie la imagen
            }
        }
    };

    //Para hacer cambio en la imagen
    Runnable foreGrRun = new Runnable() {
        @Override
        public void run() {
            imgVwBanner.setImageResource(imagenes[indice]); //Indice 0
            //Incrementa proceso (posicion imagen)
            if(indice == 2){
                indice=0;
            }else {
                indice++;
            }

        }
    };


    //Handler Message

    /*Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int valor_indice = (int)msg.obj;
            //Notificamos que cambie la imagen
            imgVwBanner.setImageResource(imagenes[valor_indice]); //Indice 0

        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgVwBanner = findViewById(R.id.imgVwBanner);
        sBVal = findViewById(R.id.sBVal);

        sBVal.setOnSeekBarChangeListener(this);

        Thread thread = new Thread(backGrRun);
        thread.start(); //Iniciar el hilo

        /*Thread tHilo = new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    try {
                        Thread.sleep(valSBar);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Message msg = handler.obtainMessage(1000,indice);
                    handler.sendMessage(msg);

                    //Incrementa proceso (posicion imagen)
                    if(indice == 2){
                        indice=0;
                    }else {
                        indice++;
                    }
                }
            }
        };

        tHilo.start();*/

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        valSBar = seekBar.getProgress();
    }
}