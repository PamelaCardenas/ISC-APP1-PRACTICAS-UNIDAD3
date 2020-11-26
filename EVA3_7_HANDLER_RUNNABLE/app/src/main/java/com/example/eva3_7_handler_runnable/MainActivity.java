package com.example.eva3_7_handler_runnable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

/*
 * Karla Pamela Cárdenas Leyva 18550338
 * Multitarea, se usa Handler para interactuar con la interfaz
 * Pero usando Runnables*/

public class MainActivity extends AppCompatActivity {

    TextView txtVwMensa;
    int i;
    Handler miHandler = new Handler();

    Runnable backGroundRun = new Runnable() { //TRABAJO PESADO EN SEGUNDO PLANO
        @Override
        public void run() {
            i=0;
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Cada vez que se necesite interactuar con la UI por medio del Handler se usa Post
                //Cada que se ejecute el post se ejecuta el foreground y el run y en esa seccion interactua con UI
                miHandler.post(foreGroundRun);
                Log.wtf("tHilo",i + "");
                i++;
            }
        }
    };

    Runnable foreGroundRun = new Runnable() { //MODIFICAR LA UI
        @Override
        public void run() {
            txtVwMensa.append(" valor i = " + i + "\n");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVwMensa = findViewById(R.id.txtVwMensa);

        Thread thread = new Thread(backGroundRun);

        thread.start();

    }
}