package com.example.eva3_1_threads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
//Karla Pamela Cárdenas Leyva 18550338
//Practica con uso de hilos

//Cada aplicacion que ejecutamos tiene un hilo de ejecucion

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //10 segundos
        for(int i=0; i<10; i++){
            //Demora la ejecucion
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}