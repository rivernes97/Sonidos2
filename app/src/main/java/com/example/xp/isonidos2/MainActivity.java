package com.example.xp.isonidos2;

import android.graphics.Color;
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


        //selecciono el main layout
        LinearLayout principal =  findViewById(R.id.botones);

        Field[] listaCanciones = R.raw.class.getFields();
        for (int i=0; i< listaCanciones.length; i++) {
            //creamos un botón por código y lo añadimos a la pantalla principal
            Button b = new Button(this);
            b.setText(listaCanciones[i].getName());
            b.setTextColor(Color.WHITE);
            b.setBackgroundColor(Color.BLUE);
            b.setAllCaps(false); //todas las letras del botón en minúscula
            b.setTag(listaCanciones[i].getName());
            //listaCanciones[i].
            b.setId(i);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sonido(view);
                }
            });
            //añadimos el botón al layout
            principal.addView(b);
        }
    }

    public void sonido(View view){
        Log.i("etiqueta: ", findViewById(view.getId()).getTag().toString());

    }
}
