package com.example.eva3_3_runnable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

/*
* Karla Pamela Cárdenas Leyva 18550338
* Funcion de hilos con Runnable
* Manejo de actividades en segundo plano */

public class MainActivity extends AppCompatActivity {


    Runnable runnable = new Runnable() {
        @Override
        //Metodo que hace el trabajo en segundo plano
        public void run() {
            for(int i=0; i<10; i++){
                try {
                    Thread.sleep(1000);
                    Log.wtf("Runnable 1", i +"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Al llamar el metodo run directamente se ejecuta en el hilo principal
        //Sigue bloqueando
        //No se tiene el metodo start
        //runnable.run();

        //Para ejecutar en segundo plano se hace por medio de Thread
        //Un hilo que reciba como argumento en el constructor un objeto Runnable
        Thread thread = new Thread(runnable);
        thread.start();

        MiRunnable miRunnable = new MiRunnable();
        Thread thread1 = new Thread(miRunnable);
        thread1.start();

    }
}

//Se pueden crear clases que implementen Runnable
class MiRunnable implements Runnable{

    @Override
    public void run() {
        for(int i=0; i<10; i++){
            try {
                Thread.sleep(1000);
                Log.wtf("Runnable 2", i +"");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}