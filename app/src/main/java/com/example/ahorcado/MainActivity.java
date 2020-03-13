package com.example.ahorcado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

int numeroFallos =0;
    String palabraOculta = eligePalabra();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.ventanaJuego, new VentanaAhorcado()).commit();
        }

        String auxiliar = "";
        for (int i = 0; i < palabraOculta.length(); i++) {
            auxiliar = auxiliar + "_ ";
        }
        TextView textoConGuiones =  ((TextView) findViewById(R.id.palabraConGuiones));
    }
public void botonPulsado(View vista){
    Button boton = findViewById(vista.getId());
    boton.setVisibility(View.INVISIBLE);
    chequeaLetra(boton.getText().toString());
}

private void chequeaLetra(String letra){
        letra = letra.toUpperCase();
    ImageView imagenAhorcado = ((ImageView) findViewById(R.id.imagenAhorcado));
    TextView textoConGuiones =  ((TextView) findViewById(R.id.palabraConGuiones));
    String palabrasConGuiones = textoConGuiones.getText().toString();

    boolean partidaTerminada = false;
    if (palabraOculta.contains(letra)) {
        char letraPulsada = letra.charAt(0);
        for (int i = 0; i < palabraOculta.length(); i++) {
            if (palabraOculta.charAt(i) == letra.charAt(0)) {
                palabrasConGuiones = palabrasConGuiones.substring(0, 2 * i) + letra + palabrasConGuiones.substring(2 * i + 1);

            }
        }
        textoConGuiones.setText(palabrasConGuiones);
        if (!palabrasConGuiones.contains("_")) {
            imagenAhorcado.setImageResource(R.drawable.acertastetodo);
            partidaTerminada = true;
        }
    }else {
        numeroFallos++;
        if (numeroFallos == 6) {
            partidaTerminada = true;
        }
        }

    if(!partidaTerminada){
        numeroFallos++;
        switch (numeroFallos){
            case 0:imagenAhorcado.setImageResource(R.drawable.ahorcado_0); break;
            case 1:imagenAhorcado.setImageResource(R.drawable.ahorcado_1); break;
            case 2:imagenAhorcado.setImageResource(R.drawable.ahorcado_2); break;
            case 3:imagenAhorcado.setImageResource(R.drawable.ahorcado_3); break;
            case 4:imagenAhorcado.setImageResource(R.drawable.ahorcado_4); break;
            case 5:imagenAhorcado.setImageResource(R.drawable.ahorcado_5); break;
            default:imagenAhorcado.setImageResource(R.drawable.ahorcado_fin); break;

        }
    }
}
    private String eligePalabra() {
        String[] listaPalabras = {"HOLA", "VLADIKAKA", "BORREGUITO", "BABYYODA"};
        Random aleatorio = new Random();
//variable aleatoria para elegir una palabra al azar
        int posicion = aleatorio.nextInt(listaPalabras.length);
        return listaPalabras[posicion].toUpperCase();

    }
}
