package com.example.blackjack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private Button btnJugar;
    private Button btnRanking;
    private Button btnCredito;
    private Button btnReglas;
    private Button btnConfiguracion;
    private Button btnPuntuar;
    private Button btnSalir;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnJugar = findViewById(R.id.buttonJugar);
        btnRanking = findViewById(R.id.buttonRanking);
        btnCredito = findViewById(R.id.buttonCredito);
        btnReglas = findViewById(R.id.buttonReglas);
        btnConfiguracion = findViewById(R.id.buttonConfiguracion);
        btnPuntuar = findViewById(R.id.buttonPuntuar);
        btnSalir = findViewById(R.id.buttonSalir);


        // Haciendo click en Jugar, nos lleva a la actividad del juego
        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Juego.class);
                startActivity(i);
            }
        });


        // Haciendo click en Ranking, nos lleva a la actividad del ranking
        btnRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Ranking.class);
                startActivity(i);
            }
        });


        // Dialog para aumentar el crédito
        btnCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();

                dialog.show();
            }
        });


        // Haciendo click en Reglas, nos lleva a la actividad de las reglas del juego
        btnReglas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Reglas.class);
                startActivity(i);
            }
        });


        // No funciona
        // Haciendo click en Puntuar, nos lleva a la página oficial de Google Play
        btnPuntuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://play.google.com/store/apps/category/GAME?hl=es&gl=US"));
                startActivity(i);
            }
        });


        // No funciona
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}