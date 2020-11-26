package com.example.eva3_4_interrupt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
/*
* Karla Pamela Cárdenas Leyva 18550338
* Tareas infinitas y detenerlas con interrupt*/

public class MainActivity extends AppCompatActivity {

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //Ciclo infinito
            int i=0;
            while(true){
                try {
                    Thread.sleep(1000);
                    Log.wtf("Runnable ", i+"");
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //Poner código para detener el hilo
                    break;
                }
            }
        }
    };
    Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thread = new Thread(runnable);
        thread.start();


    }

    @Override
    protected void onStop() {
        super.onStop();
        //Detener la ejecucion de cualquier hilo
        thread.interrupt();
    }
}