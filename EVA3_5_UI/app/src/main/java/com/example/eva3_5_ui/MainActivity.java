package com.example.eva3_5_ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
/*
* Karla Pamela Cárdenas Leyva 18550338
* Multitarea.
* No se puede modificar la interfaz, solo puede ser desde el hilo principal*/

public class MainActivity extends AppCompatActivity {

    TextView txtVwDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtVwDatos = findViewById(R.id.txtVwDatos);

        Thread tHilo = new Thread(){
            @Override
            public void run() {
                super.run();
                int i=0;
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.wtf("MiRunnable",i + "");
                    //No se puede porque no se esta ejecutando desde
                    //el hilo principal
                    //Los hilos secundarios no pueden modificar un objeto de la UI
                    txtVwDatos.append(i + "\n");
                    i++;
                }
            }
        };
        tHilo.start();
    }
}