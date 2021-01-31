package com.example.blackjack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Juego extends AppCompatActivity {

    private Button btnApostar;
    private ImageButton imgBtnVolver;
    private ImageButton imgBtnPlantarse;
    private ImageButton imgBtnDoblar;
    private ImageButton imgBtnPedirCarta;
    private ImageButton imgBtnSeparar;
    private Context context = this;
    private TextView tvApuesta;

    private EditText etCantidadApuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        btnApostar = findViewById(R.id.btnApostar);
        imgBtnVolver = findViewById(R.id.btnVolver);
        imgBtnPlantarse = findViewById(R.id.imageButtonPlantarse);
        imgBtnDoblar = findViewById(R.id.imageButtonDoblar);
        imgBtnPedirCarta = findViewById(R.id.imageButtonPedir);
        imgBtnSeparar = findViewById(R.id.imageButtonSeparar);
        tvApuesta = findViewById(R.id.textViewApuesta);

        // Botón para apostar
        btnApostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle(R.string.titleDialog);

                View myView = LayoutInflater.from(context).inflate(R.layout.dialog_apostar,null);

                builder.setView(myView)
                        .setPositiveButton(R.string.dialogConfirm, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                etCantidadApuesta = findViewById(R.id.editTextCantidadApuesta);
                                dialog.dismiss();
                                // TODO: Código para guardar la apuesta que ha hecho el jugador
                    }
                })
                        .setNegativeButton(R.string.dialogCancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();

                dialog.show();


            }
        });

        // Botón para volver atrás
        imgBtnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Botón para plantarse
        imgBtnPlantarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Código para comprobar quién ha ganado y reasignar el crédito
            }
        });

        // Botón para doblar la apuesta
        imgBtnDoblar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Código para doblar la apuesta
            }
        });

        // Botón para pedir una carta cuando no se superen los 21 puntos
        imgBtnPedirCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Código para pedir una carta
            }
        });

        // Botón para separar la apuesta
        imgBtnSeparar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Código para separar la apuesta
            }
        });
    }
}