package com.example.eva3_6_handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

/*
* Karla Pamela Cárdenas Leyva 18550338
* Multitarea, se usa Handler para interactuar con la interfaz*/

public class MainActivity extends AppCompatActivity {

    TextView txtVwMensa;

    //handleMessage
    //Se encarga de procesar las peticiones a la interfaz grafica
    Handler miHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //AQUI PODEMOS MODIFICAR LA UI
            //Envia los valores
            int valor_i = (int)msg.obj;
            //Se muestra en la etiqueta, se usa what para identificar el hilo
            txtVwMensa.append("Hilo: " + msg.what + " valor i = " + valor_i + "\n");
            //Ya no es el hilo secundario quien genera la modificacion de la UI sino el principal
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtVwMensa = findViewById(R.id.txtVwMensa);

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
                    //int what - permite identificar que hilo lo esta generando
                    //object - facilidad para enviar cualquier tipo de parametro dentro del mensaje
                    Message msg = miHandler.obtainMessage(1000, i);
                    //Enviar mensaje de vuelta
                    miHandler.sendMessage(msg);
                    Log.wtf("tHilo",i + "");
                    i++;
                }

            }
        };
        tHilo.start();
    }
}