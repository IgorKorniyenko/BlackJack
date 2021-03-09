package com.example.blackjack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

public class Juego extends AppCompatActivity {

    private Button btnApostar;
    private ImageButton imgBtnVolver;
    private ImageButton imgBtnPlantarse;
    private ImageButton imgBtnDoblar;
    private ImageButton imgBtnPedirCarta;
    private ImageButton imgBtnSeparar;
    private Context context = this;
    private TextView twApuesta;

    private TextView twPuntosJugador;
    private TextView twPuntosCrupie;

    private int contadorJugador = 0;
    private int contadorCrupie = 0;

    private List<ImageView> cartasJugador = new ArrayList<>();
    private List<ImageView> cartasCrupie = new ArrayList<>();
    private int cantApostada;
    private boolean partidaFinalizada;

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

        twPuntosJugador = findViewById(R.id.puntosJugador);
        twPuntosCrupie = findViewById(R.id.puntosCrupier);

        // TODO: añadir fondo vacío
        cartasCrupie.addAll(Arrays.asList(findViewById(R.id.imageViewCartaCrupie1), findViewById(R.id.imageViewCartaCrupie2),
                findViewById(R.id.imageViewCartaCrupie3), findViewById(R.id.imageViewCartaCrupie4),
                findViewById(R.id.imageViewCartaCrupie5), findViewById(R.id.imageViewCartaCrupie6),
                findViewById(R.id.imageViewCartaCrupie7), findViewById(R.id.imageViewCartaCrupie8)));

        cartasJugador.addAll(Arrays.asList(findViewById(R.id.imageViewCartaJugador1), findViewById(R.id.imageViewCartaJugador2),
                findViewById(R.id.imageViewCartaJugador3), findViewById(R.id.imageViewCartaJugador4),
                findViewById(R.id.imageViewCartaJugador5), findViewById(R.id.imageViewCartaJugador6),
                findViewById(R.id.imageViewCartaJugador7), findViewById(R.id.imageViewCartaJugador8)));

        //Desactivo todas las cartas menos las principales
        for (int i = 0; i < 6; i++) {
            cartasCrupie.get(i).setVisibility(View.INVISIBLE);
            cartasJugador.get(i).setVisibility(View.INVISIBLE);
        }

        etCantidadApuesta = findViewById(R.id.editTextCantidadApuesta);

        // Botón para apostar
        btnApostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle(R.string.titleDialog);

                View myView = LayoutInflater.from(context).inflate(R.layout.dialog_apostar, null);

                builder.setView(myView)
                        .setPositiveButton(R.string.dialogConfirm, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                etCantidadApuesta = myView.findViewById(R.id.editTextCantidadApuesta);

                                cantApostada = Integer.parseInt(etCantidadApuesta.getText().toString());
                                String textoApuesta = getString(R.string.tvApuesta);
                                twApuesta.setText(textoApuesta + " " + cantApostada);

                                // TODO: Restar creditos jugador

                                dialog.dismiss();

                                if (cantApostada > 0) {
                                    iniciarJuego();
                                }
                            }
                        })
                        .setNegativeButton(R.string.dialogCancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).create().show();
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

    public void iniciarJuego() {

        String pathCarta;
        int valorCartasJugador, valorCartasCrupie;


        List<Carta> cartasRepartidasJugador = new ArrayList<>();
        List<Carta> cartasRepartidasCrupie = new ArrayList<>();

        Baraja baraja = new Baraja();
        baraja.barajar();

        // Se reparten las dos primeras cartas para cada uno
        for (int i = 0; i < 2; i++) {

            mostrarSiguienteCarta(cartasRepartidasJugador, baraja, 1);
            mostrarSiguienteCarta(cartasRepartidasCrupie, baraja, 0);

            /*
            cartasRepartidasJugador.add(baraja.siguienteCarta());
            //pathCarta = "../../res/drawable-v24/" +
            pathCarta = "C:\\Users\\Cris\\AndroidStudioProjects\\ProyectoBlackjack\\app\\src\\main\\res\\drawable-v24\\" +
                    cartasRepartidasJugador.get(i).getValor().toString().toLowerCase() +
                    cartasRepartidasJugador.get(i).getPalo().toString().toLowerCase() + ".png";

            cartasJugador.get(i).setBackground(Drawable.createFromPath(pathCarta));


            cartasRepartidasCrupie.add(baraja.siguienteCarta());
            pathCarta = "../../res/drawable-v24/" +
                    cartasRepartidasCrupie.get(i).getValor().toString().toLowerCase() +
                    cartasRepartidasCrupie.get(i).getPalo().toString().toLowerCase() + ".png";

            cartasCrupie.get(i).setBackground(Drawable.createFromPath(pathCarta));

             */
        }

        // Calcular los puntos que lleva cada uno
        valorCartasJugador = calcularValorCartas(cartasRepartidasJugador);
        valorCartasCrupie = calcularValorCartas(cartasRepartidasCrupie);

        twPuntosJugador.setText(String.valueOf(valorCartasJugador));
        twPuntosCrupie.setText(String.valueOf(valorCartasCrupie));

        partidaFinalizada = false;

        if (!partidaTerminada(valorCartasCrupie, valorCartasJugador)) {
            //int contador = 0;
            boolean primerTurno = true;

            /* No vamos a separar (luego borramos esta parte)

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
        */
            if (primerTurno /*contador == 0*/) {
                imgBtnDoblar.setVisibility(View.VISIBLE);
                imgBtnDoblar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Restar creditos jugador

                        if (valorCartasCrupie < 17) {
                            mostrarSiguienteCarta(cartasRepartidasCrupie, baraja, 0);
                        }

                        mostrarSiguienteCarta(cartasRepartidasJugador, baraja, 1);

                        int puntosJugador = calcularValorCartas(cartasRepartidasJugador);
                        int puntosCrupier = calcularValorCartas(cartasRepartidasCrupie);
                        twPuntosJugador.setText("" + puntosJugador);
                        twPuntosCrupie.setText("" + puntosCrupier);

                            /*
                            cartasRepartidasJugador.add(b.siguienteCarta());
                            String pathCarta = "../../res/drawable-v24/" +
                                    cartasRepartidasJugador.get(cartasRepartidasJugador.size() - 1).getValor().toString().toLowerCase() +
                                    cartasRepartidasJugador.get(cartasRepartidasJugador.size() - 1).getPalo().toString().toLowerCase() + ".png";

                            cartasJugador.get(cartasJugador.size() - 1).setBackground(Drawable.createFromPath(pathCarta));

                            cartasRepartidasCrupie.add(b.siguienteCarta());
                            pathCarta = "../../res/drawable-v24/" +
                                    cartasRepartidasCrupie.get(cartasRepartidasCrupie.size() - 1).getValor().toString().toLowerCase() +
                                    cartasRepartidasCrupie.get(cartasRepartidasCrupie.size() - 1).getPalo().toString().toLowerCase() + ".png";

                            cartasCrupie.get(cartasRepartidasCrupie.size() - 1).setBackground(Drawable.createFromPath(pathCarta));


                             */
                        imgBtnDoblar.setVisibility(View.INVISIBLE);

                        if (partidaTerminada(puntosCrupier, puntosJugador)) {
                            determinarGanador(puntosCrupier, puntosJugador);
                            partidaFinalizada = true;
                        }
                    }
                });

                //contador++;
                primerTurno = false;
            }

            imgBtnPlantarse.setVisibility(View.VISIBLE);
            imgBtnPlantarse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgBtnPlantarse.setVisibility(View.INVISIBLE);
                    imgBtnPedirCarta.setVisibility(View.INVISIBLE);

                    while (calcularValorCartas(cartasRepartidasCrupie) < 17) {
                        mostrarSiguienteCarta(cartasRepartidasCrupie, baraja, 0);
                    }

                    int puntosJugador = calcularValorCartas(cartasRepartidasJugador);
                    int puntosCrupier = calcularValorCartas(cartasRepartidasCrupie);
                    twPuntosJugador.setText("" + puntosJugador);
                    twPuntosCrupie.setText("" + puntosCrupier);

                    determinarGanador(puntosCrupier, puntosJugador);
                    partidaFinalizada = true;
                }
            });


            imgBtnPedirCarta.setVisibility(View.VISIBLE);
            imgBtnPedirCarta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int puntosJugador = calcularValorCartas(cartasRepartidasJugador);
                    int puntosCrupier = calcularValorCartas(cartasRepartidasCrupie);

                    if (puntosCrupier < 17 && puntosJugador > 21) {
                        mostrarSiguienteCarta(cartasRepartidasCrupie, baraja, 0);
                    }

                    mostrarSiguienteCarta(cartasRepartidasJugador, baraja, 1);

                    puntosJugador = calcularValorCartas(cartasRepartidasJugador);
                    puntosCrupier = calcularValorCartas(cartasRepartidasCrupie);
                    twPuntosJugador.setText("" + puntosJugador);
                    twPuntosCrupie.setText("" + puntosCrupier);

                    if (calcularValorCartas(cartasRepartidasJugador) >= 21) {
                        imgBtnPedirCarta.setVisibility(View.INVISIBLE);
                    }

                            /*
                            cartasRepartidasJugador.add(b.siguienteCarta());
                            String pathCarta = "../../res/drawable-v24/" +
                                    cartasRepartidasJugador.get(cartasRepartidasJugador.size() - 1).getValor().toString().toLowerCase() +
                                    cartasRepartidasJugador.get(cartasRepartidasJugador.size() - 1).getPalo().toString().toLowerCase() + ".png";

                            cartasJugador.get(cartasJugador.size() - 1).setBackground(Drawable.createFromPath(pathCarta));

                            if(valorCartasCrupie < 17) {
                                cartasRepartidasCrupie.add(b.siguienteCarta());
                                pathCarta = "../../res/drawable-v24/" +
                                        cartasRepartidasCrupie.get(cartasRepartidasCrupie.size() - 1).getValor().toString().toLowerCase() +
                                        cartasRepartidasCrupie.get(cartasRepartidasCrupie.size() - 1).getPalo().toString().toLowerCase() + ".png";

                                cartasCrupie.get(cartasRepartidasCrupie.size() - 1).setBackground(Drawable.createFromPath(pathCarta));
                            }

                             */

                    if (partidaTerminada(puntosCrupier, puntosJugador)) {
                        determinarGanador(puntosCrupier, puntosJugador);
                        partidaFinalizada = true;
                    }
                }
            });

        } else {
            determinarGanador(valorCartasCrupie, valorCartasJugador);
            partidaFinalizada = true;
        }
    }

    private void mostrarSiguienteCarta(List<Carta> cartasRepartidas, Baraja baraja, int quien) {
        int posicion = 0;

        cartasRepartidas.add(baraja.siguienteCarta());
//        String pathCarta = "../../res/drawable-v24/" +
//                cartasRepartidas.get(cartasRepartidas.size() - 1).getValor().toString().toLowerCase() +
//                cartasRepartidas.get(cartasRepartidas.size() - 1).getPalo().toString().toLowerCase() + ".png";


        // PRUEBA DE ACCEDER AL DRAWABLE
        String valor = cartasRepartidas.get(cartasRepartidas.size() - 1).getValor().toString().toLowerCase();
        String palo = cartasRepartidas.get(cartasRepartidas.size() - 1).getPalo().toString().toLowerCase();
        int resourceId = this.getResources().getIdentifier(valor + palo, "drawable", this.getPackageName());
        String uri = Uri.parse("android.resource://" + R.class.getPackage().getName() + "/" + resourceId).toString();
        System.err.println(valor);
        System.err.println(palo);
        System.err.println(uri);



        if (quien == 1) {
            cartasJugador.get(contadorJugador).setVisibility(View.VISIBLE);
            cartasJugador.get(contadorJugador).setBackground(Drawable.createFromPath(uri));

            contadorJugador++;
        } else {
            cartasCrupie.get(contadorCrupie).setVisibility(View.VISIBLE);
            cartasCrupie.get(contadorCrupie).setBackground(Drawable.createFromPath(uri));

            contadorCrupie++;
        }
    }

    public void determinarGanador(int valorCartasCrupie, int valorCartasJugador) {
        if (((valorCartasCrupie > valorCartasJugador) && valorCartasCrupie < 22) || valorCartasJugador > 21) {
            reiniciarPartida(0); // Gana crupier
        } else if (((valorCartasCrupie < valorCartasJugador) && valorCartasJugador < 22) || valorCartasCrupie > 21) {
            reiniciarPartida(1); // Gana jugador
        } else if (valorCartasCrupie == valorCartasJugador) {
            reiniciarPartida(2); // Empate
        } else if (valorCartasJugador < 22 && valorCartasJugador > valorCartasCrupie) {
            reiniciarPartida(1); // Gana jugador
        //} else if (valorCartasCrupie < 22 && valorCartasCrupie < valorCartasJugador) {
        //    reiniciarPartida(0); // Gana crupier
        } else {
            reiniciarPartida(2); // Empate
        }
    }

    private boolean partidaTerminada(int valorCartasCrupie, int valorCartasJugador) {
        boolean finPartida = false;

        if (valorCartasCrupie >= 21 || valorCartasJugador >= 21) {
//            if (valorCartasCrupie > 21 || valorCartasJugador == 21) {
//                reiniciarPartida(1); // Gana el jugador
//            } else if (valorCartasJugador > 21) {
//                reiniciarPartida(0); // Gana el crupier
//            } else {
//                reiniciarPartida(0);
//            }

            finPartida = true;
        }

        return finPartida;
    }

    public void reiniciarPartida(int codigo) {
        int mensaje = 0;

        imgBtnDoblar.setVisibility(View.INVISIBLE);
        imgBtnPedirCarta.setVisibility(View.INVISIBLE);
        imgBtnPlantarse.setVisibility(View.INVISIBLE);
        imgBtnSeparar.setVisibility(View.INVISIBLE);

        switch (codigo) {
            case 0:
                mensaje = R.string.toastGanaCrupier;
                break;
            case 1:
                mensaje = R.string.toastGanaJugador;
                break;
            case 2:
                mensaje = R.string.toastEmpate;
                break;
        }
        System.err.println(mensaje);
        Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();
    }

    private boolean comprobarSiSonIguales(List<Carta> cartas) {
        boolean exito = false;

        if (cartas.get(0).getValor().getNumVal() == cartas.get(1).getValor().getNumVal()) {
            exito = true;
        }

        return exito;
    }

    public int calcularValorCartas(List<Carta> cartas) {
        int valor = 0;

        for (int i = 0; i < cartas.size(); i++) {
            valor += cartas.get(i).getValor().getNumVal();
        }

        return valor;
    }
}