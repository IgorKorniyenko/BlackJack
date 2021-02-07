package com.example.blackjack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blackjack.modelo.Baraja;
import com.example.blackjack.modelo.Carta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.blackjack.modelo.Palos.C;

public class Juego extends AppCompatActivity {

    private Button btnApostar;
    private ImageButton imgBtnVolver;
    private ImageButton imgBtnPlantarse;
    private ImageButton imgBtnDoblar;
    private ImageButton imgBtnPedirCarta;
    private ImageButton imgBtnSeparar;
    private Context context = this;
    private TextView twApuesta;

    private List<ImageView> cartasJugador = new ArrayList<>();
    private List<ImageView> cartasCrupie = new ArrayList<>();
    private int cantApostada;
    boolean finPartida = false;

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
        twApuesta = findViewById(R.id.textViewApuesta);

        cartasCrupie.addAll(Arrays.asList(findViewById(R.id.imageViewCarta1),findViewById(R.id.imageViewCarta2)));
        cartasJugador.addAll(Arrays.asList(findViewById(R.id.imageViewCarta3),findViewById(R.id.imageViewCarta4)));

        etCantidadApuesta = findViewById(R.id.editTextCantidadApuesta);

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

                                etCantidadApuesta = myView.findViewById(R.id.editTextCantidadApuesta);

                                twApuesta.setText("Apuesta:" + etCantidadApuesta.getText().toString());
                                cantApostada = Integer.parseInt(etCantidadApuesta.getText().toString());
                                //Restar creditos jugador


                                if(cantApostada > 0){
                                    iniciarJuego();
                                }

                                dialog.dismiss();
                                //Hecho
                                //Código para guardar la apuesta que ha hecho el jugador
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

    public void iniciarJuego(){
        String uriCarta;
        int valorCartasJugador, valorCartasCrupie;


        List<Carta> cartasRepartidasJugador = new ArrayList<>();
        List<Carta> cartasRepartidasCrupie = new ArrayList<>();

        Baraja b = new Baraja();
        b.barajar();

        for(int i=0; i<2; i++){
            cartasRepartidasJugador.add(b.siguienteCarta());
            uriCarta = "R.drawable." + cartasRepartidasJugador.get(i).getValor().toString().toLowerCase() +cartasRepartidasJugador.get(i).getPalo().toString().toLowerCase();
            cartasJugador.get(i).setImageURI(Uri.parse(uriCarta));


            cartasRepartidasCrupie.add(b.siguienteCarta());
            uriCarta = "R.drawable." + cartasRepartidasCrupie.get(i).getValor().toString().toLowerCase() +cartasRepartidasJugador.get(i).getPalo().toString().toLowerCase();
            cartasCrupie.get(i).setImageURI(Uri.parse(uriCarta));
        }

        valorCartasJugador = calcularValorCartas(cartasRepartidasJugador);
        valorCartasCrupie = calcularValorCartas(cartasRepartidasCrupie);

        //desactivar boton apuesta
        //desactivar dividir
        //activar boton doblar
        //activar boton rendirse

        if(!partidaTerminada(valorCartasCrupie, valorCartasJugador)){
            int contador = 0;


            while(!partidaTerminada(valorCartasCrupie,valorCartasJugador) || !finPartida){

                if(contador == 0){
                    if(comprobarSiSonIguales(cartasRepartidasJugador)){
                        //Activar boton separar
                        imgBtnSeparar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Restar creditos
                                //Repartir dos cartas mas
                                //Dar carta a crupie
                                //Desactivar boton separar
                            }
                        });
                    }

                    imgBtnDoblar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Restar creditos jugador
                            //Repartir cartas crupie y jugador
                            //Desactivar boton doblar

                            if(partidaTerminada(valorCartasCrupie,valorCartasJugador)){
                                //reiniciarPartida();
                            }
                        }
                    });
                }


                    imgBtnPlantarse.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //reiniciarPartida(2);
                        }
                    });



                    imgBtnPedirCarta.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Repartir cartas crupie y jugador

                            if(partidaTerminada(valorCartasCrupie,valorCartasJugador)){
                                if((valorCartasCrupie > valorCartasJugador) && valorCartasCrupie < 22){
                                    //reiniciarPartida(1);
                                }else{
                                    //reiniciarPartida(0);
                                }

                                finPartida = true;
                            }
                        }
                    });

                    contador ++;
                }
            }else{
                //reiniciarPartida();
        }
    }




    private boolean partidaTerminada(int valorCartasCrupie, int valorCartasJugador) {
        boolean finPartida = false;

        if(valorCartasCrupie == 21 || valorCartasJugador == 21){
            if(valorCartasCrupie == 21){
                //gana curpie
                //reiniciarPartida(0);
            }else{
                //gana jugador
                //reiniciarPartida(1);
            }

            finPartida = true;
            //Toast din partida
        }

        return finPartida;
    }

    private boolean comprobarSiSonIguales(List<Carta> cartas) {
        boolean exito = false;

        if(cartas.get(0) == cartas.get(1)){
            exito = true;
        }

        return exito;
    }

    public int calcularValorCartas(List<Carta> cartas){
        int valor = 0;

       for(int i=0; i<cartas.size();i++){
           switch(cartas.get(i).getValor()){
               case TWO:
                   valor +=2;
                   break;
               case THREE:
                   valor +=3;
                   break;
               case FOUR:
                   valor +=4;
                   break;
               case FIVE:
                   valor +=5;
                   break;
               case SIX:
                   valor +=6;
                   break;
               case SEVEN:
                   valor +=7;
                   break;
               case EIGHT:
                   valor +=8;
                   break;
               case NINE:
                   valor +=9;
                   break;
               case TEN:
               case J:
               case Q:
               case K:
                   valor +=10;
                   break;
               case A:
                   valor +=11;
                   break;
           }
       }

        return valor;
    }
}