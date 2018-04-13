package com.example.xp.isonidos2;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout principal =  findViewById(R.id.botones);
        int numeroLinea = 0;
        LinearLayout auxiliar = creaLineaBotones(numeroLinea);
        principal.addView(auxiliar);

        Field[] listaCanciones = R.raw.class.getFields();

        for (int i=0; i< listaCanciones.length; i++) {
            //creamos un botón por código y lo añadimos a la pantalla principal
            Button b = creaBoton(i, listaCanciones);
            //añadimos el botón al layout
            auxiliar.addView(b);
            if ( i % 3 == 2){
                auxiliar = creaLineaBotones(i);
                principal.addView(auxiliar);
            }
        }
    }

    public void sonido(View view){
        Log.i("etiqueta: ", findViewById(view.getId()).getTag().toString());
        Button b = (Button) findViewById(view.getId());
        MediaPlayer m = new MediaPlayer();
        m = MediaPlayer.create(this, (int)findViewById(view.getId()).getTag());
        m.start();
    }

    private LinearLayout creaLineaBotones(int numeroLinea){
        LinearLayout.LayoutParams parametros = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                ,LinearLayout.LayoutParams.WRAP_CONTENT);
        parametros.weight = 1;
        LinearLayout linea = new LinearLayout(this);

        linea.setOrientation(LinearLayout.HORIZONTAL);
        linea.setLayoutParams(parametros);
        linea.setId(numeroLinea);
        return linea;
    }

    private Button creaBoton(int i, Field[] _listaCanciones){
        LinearLayout.LayoutParams parametrosBotones = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT);
        parametrosBotones.weight = 1;
        Button b = new Button(this);
        b.setLayoutParams(parametrosBotones);
        b.setText(_listaCanciones[i].getName());
        b.setTextColor(Color.WHITE);
        b.setBackgroundColor(Color.BLUE);
        b.setAllCaps(false); //todas las letras del botón en minúscula
        int id = this.getResources().getIdentifier(_listaCanciones[i].getName(), "raw", this.getPackageName());
        b.setTag(id);

        b.setId(i+50);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sonido(view);
            }
        });
        return b;
    }
}
