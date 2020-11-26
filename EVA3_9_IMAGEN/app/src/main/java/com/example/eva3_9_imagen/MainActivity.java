package com.example.eva3_9_imagen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/*
* Karla Pamela Cárdenas Leyva 18550338
* Practica con hilos, para mostrar una imagen desde una URL*/
public class MainActivity extends AppCompatActivity {

    ImageView imgVwImagen;
    Bitmap imagen;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            imgVwImagen.setImageBitmap(imagen);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgVwImagen = findViewById(R.id.imgVwImagen);
        /*
        Se produce un error porque no permite cargar la imagen directamente en el hilo principal
        imagen = cargarImagen("https://i.ytimg.com/vi/oWpwC5SMtSw/maxresdefault.jpg");
        if(imagen == null){
            imgVwImagen.setImageBitmap(imagen);
        }*/

        //Se ejecuta un hilo para cargar la imagen
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                imagen = cargarImagen("https://i.ytimg.com/vi/oWpwC5SMtSw/maxresdefault.jpg");
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);
            }
        };

        thread.start();
    }

    //Para cargar imagen por url, se lee el archivo como bits
    public Bitmap cargarImagen(String url){
        try {
            //Se obtiene la URL, es un objeto de tipo URL, pide la ruta
            InputStream inputStream = (InputStream) new URL(url).getContent();
            //BitmapFactory se usa para convertir el archivo de bits a bitmap, decodeStream
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}