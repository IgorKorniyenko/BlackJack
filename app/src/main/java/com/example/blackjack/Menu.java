package com.example.blackjack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    private Button btnJugar;
    private Button btnRanking;
    private Button btnCredito;
    private Button btnReglas;
    private Button btnConfiguracion;
    private Button btnPuntuar;
    private Button btnSalir;
    private Context context = this;

    private EditText etCambioUsuario;
    private EditText etCambioPassword;

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

                //AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.dialogCredito));

                builder.setTitle(R.string.btnCredito)
                        .setItems(R.array.opcionesCredito, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                // TODO: Código para aumentar el crédito al jugador según la opción elegida
                            }
                        });

                builder.create().show();
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


        // Haciendo click en Configuración nos abre un Dialog para cambiar el usuario y/o la contraseña
        btnConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                View myView = LayoutInflater.from(context).inflate(R.layout.dialog_configuracion,null);

                builder.setView(myView)
                        .setPositiveButton(R.string.dialogConfirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                etCambioUsuario = findViewById(R.id.editTextCambioUsuario);
                                etCambioPassword = findViewById(R.id.editTextCambioPassword);

                                dialog.dismiss();

                                // TODO: Código para cambiar el usuario o la contraseña en la base de datos

//                                if (No ha habido error) {
//                                    Toast.makeText(context, R.string.toastExito, Toast.LENGTH_SHORT).show();
//                                }
//                                else {
//                                    Toast.makeText(context, R.string.toastError, Toast.LENGTH_SHORT).show();
//                                }
                            }
                        })
                        .setNegativeButton(R.string.dialogCancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        });

                builder.create().show();
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