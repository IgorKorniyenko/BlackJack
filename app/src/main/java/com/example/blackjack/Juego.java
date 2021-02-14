package com.example.blackjack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

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

        imgBtnDoblar.setVisibility(View.INVISIBLE);
        imgBtnPedirCarta.setVisibility(View.INVISIBLE);
        imgBtnPlantarse.setVisibility(View.INVISIBLE);
        imgBtnSeparar.setVisibility(View.INVISIBLE);

        // TODO: añadir fondo vacío
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


                                // TODO: Restar creditos jugador


                                if(cantApostada > 0){
                                    iniciarJuego();
                                }

                                dialog.dismiss();

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

    }

    public void iniciarJuego(){

        String pathCarta;
        int valorCartasJugador, valorCartasCrupie;


        List<Carta> cartasRepartidasJugador = new ArrayList<>();
        List<Carta> cartasRepartidasCrupie = new ArrayList<>();

        Baraja b = new Baraja();
        b.barajar();

        for(int i=0; i<2; i++){
            cartasRepartidasJugador.add(b.siguienteCarta());
            pathCarta = "../../../../res/drawable-v24/" +
                    cartasRepartidasJugador.get(i).getValor().toString().toLowerCase() +
                    cartasRepartidasJugador.get(i).getPalo().toString().toLowerCase() + ".png";

            cartasJugador.get(i).setBackground(Drawable.createFromPath(pathCarta));
            cartasJugador.get(i).setBackgroundResource(R.drawable.eightd);



            cartasRepartidasCrupie.add(b.siguienteCarta());
            // JORGE
            pathCarta = "../../../../res/drawable-v24/" +
                    cartasRepartidasCrupie.get(i).getValor().toString().toLowerCase() +
                    cartasRepartidasCrupie.get(i).getPalo().toString().toLowerCase() + ".png";

            cartasCrupie.get(i).setBackground(Drawable.createFromPath(pathCarta));
            cartasCrupie.get(i).setBackgroundResource(R.drawable.ac);
            //cartasCrupie.get(i).setBackground(Drawable.createFromPath("res/drawable-v24/ah.png"));
        }

        valorCartasJugador = calcularValorCartas(cartasRepartidasJugador);
        valorCartasCrupie = calcularValorCartas(cartasRepartidasCrupie);

        if(!partidaTerminada(valorCartasCrupie, valorCartasJugador)){
            int contador = 0;

                if(contador == 0){
                    if(comprobarSiSonIguales(cartasRepartidasJugador)){
                        imgBtnSeparar.setVisibility(View.VISIBLE);
                        imgBtnSeparar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Restar creditos
                                //Repartir dos cartas mas
                                //Dar carta a crupie

                                imgBtnSeparar.setVisibility(View.INVISIBLE);
                            }
                        });
                    }

                    imgBtnDoblar.setVisibility(View.VISIBLE);
                    imgBtnDoblar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Restar creditos jugador
                            //Repartir cartas crupie y jugador

                            imgBtnDoblar.setVisibility(View.INVISIBLE);

                            if(partidaTerminada(valorCartasCrupie,valorCartasJugador)){
                                determinarGanador(valorCartasCrupie, valorCartasJugador);
                            }
                        }
                    });

                    contador ++;
                }

                    imgBtnPlantarse.setVisibility(View.VISIBLE);
                    imgBtnPlantarse.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //pedirCartarCrupier
                            determinarGanador(valorCartasCrupie, valorCartasJugador);
                        }
                    });


                    imgBtnPedirCarta.setVisibility(View.VISIBLE);
                    imgBtnPedirCarta.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Repartir cartas crupie y jugador

                            if(partidaTerminada(valorCartasCrupie,valorCartasJugador)){

                                determinarGanador(valorCartasCrupie, valorCartasJugador);

                            }
                        }
                    });


            }else{
                determinarGanador(valorCartasCrupie, valorCartasJugador);
        }
    }

    public void determinarGanador(int valorCartasCrupie, int valorCartasJugador){
        if((valorCartasCrupie > valorCartasJugador) && valorCartasCrupie < 22){
            reiniciarPartida(0);
        }else{
            reiniciarPartida(1);
        }
    }

    // TODO: revisar
    private boolean partidaTerminada(int valorCartasCrupie, int valorCartasJugador) {
        boolean finPartida = false;

        if(valorCartasCrupie == 21 || valorCartasJugador == 21){
            if(valorCartasCrupie == 21){
               // reiniciarPartida(0);
            }else{
               // reiniciarPartida(1);
            }

            finPartida = true;
        }

        return finPartida;
    }

    public void reiniciarPartida(int codigo){
        String mensaje = "";

        switch(codigo){
            case 0:
                mensaje = "Gana el crupie";
                break;
            case 1:
                mensaje = "Gana el jugador";
                break;
        }

        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    private boolean comprobarSiSonIguales(List<Carta> cartas) {
        boolean exito = false;

        if(cartas.get(0).getValor().getNumVal() == cartas.get(1).getValor().getNumVal()){
            exito = true;
        }

        return exito;
    }

    public int calcularValorCartas(List<Carta> cartas){
        int valor = 0;

        for(int i=0 ; i<cartas.size(); i++){
            valor += cartas.get(i).getValor().getNumVal();
        }

        return valor;
    }
}