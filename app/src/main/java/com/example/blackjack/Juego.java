package com.example.blackjack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
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

public class Juego extends AppCompatActivity {
    private static final int JUGADOR = 1;
    private static final int CRUPIER = 0;

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
        btnApostar.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setTitle(R.string.titleDialog);

            View myView = LayoutInflater.from(context).inflate(R.layout.dialog_apostar, null);

            builder.setView(myView)
                    .setPositiveButton(R.string.dialogConfirm, (dialog, id) -> {

                        etCantidadApuesta = myView.findViewById(R.id.editTextCantidadApuesta);

                        cantApostada = Integer.parseInt(etCantidadApuesta.getText().toString());
                        String textoApuesta = getString(R.string.tvApuesta);
                        twApuesta.setText(textoApuesta + " " + cantApostada);

                        // TODO: Restar creditos jugador

                        dialog.dismiss();

                        if (cantApostada > 0) {
                            iniciarJuego();
                        }
                    })
                    .setNegativeButton(R.string.dialogCancel, (dialog, id) -> dialog.cancel()).create().show();
        });


        // Botón para volver atrás
        imgBtnVolver.setOnClickListener(v -> onBackPressed());
    }

    public void iniciarJuego() {

        int valorCartasJugador, valorCartasCrupie;

        List<Carta> cartasRepartidasJugador = new ArrayList<>();
        List<Carta> cartasRepartidasCrupie = new ArrayList<>();

        Baraja baraja = new Baraja();
        baraja.barajar();

        // Ocultamos las dos cartas que aparecen bocabajo al principio
        for (int i = 6; i < 8; i++) {
            cartasCrupie.get(i).setVisibility(View.INVISIBLE);
            cartasJugador.get(i).setVisibility(View.INVISIBLE);
        }

        // Se reparten las dos primeras cartas para cada uno
        for (int i = 0; i < 2; i++) {
            mostrarSiguienteCarta(cartasRepartidasJugador, baraja, JUGADOR);
            mostrarSiguienteCarta(cartasRepartidasCrupie, baraja, CRUPIER);
        }

        // Calculamos los puntos que lleva cada uno y ponerlos en los TextView
        valorCartasJugador = calcularValorCartas(cartasRepartidasJugador);
        valorCartasCrupie = calcularValorCartas(cartasRepartidasCrupie);

        String textoPuntosJugador = getString(R.string.tvPuntosJugador);
        String textoPuntosCrupier = getString(R.string.tvPuntosCrupier);

        twPuntosJugador.setText(textoPuntosJugador + " " + valorCartasJugador);
        twPuntosCrupie.setText(textoPuntosCrupier + " " + valorCartasCrupie);

        partidaFinalizada = false;

        if (!partidaTerminada(valorCartasCrupie, valorCartasJugador)) {
            boolean primerTurno = true;

            if (primerTurno) {
                imgBtnDoblar.setVisibility(View.VISIBLE);
                imgBtnDoblar.setOnClickListener(v -> {
                    //Restar creditos jugador

                    if (valorCartasCrupie < 17) {
                        mostrarSiguienteCarta(cartasRepartidasCrupie, baraja, CRUPIER);
                    }

                    mostrarSiguienteCarta(cartasRepartidasJugador, baraja, JUGADOR);

                    // Recalculamos los puntos que lleva cada uno y seteamos los TextView
                    int puntosJugador = calcularValorCartas(cartasRepartidasJugador);
                    int puntosCrupier = calcularValorCartas(cartasRepartidasCrupie);

                    String textoPuntosJugador1 = getString(R.string.tvPuntosJugador);
                    String textoPuntosCrupier1 = getString(R.string.tvPuntosCrupier);

                    twPuntosJugador.setText(textoPuntosJugador1 + " " + puntosJugador);
                    twPuntosCrupie.setText(textoPuntosCrupier1 + " " + puntosCrupier);

                    // Ocultamos el botón ya que no se puede volver a usar
                    imgBtnDoblar.setVisibility(View.INVISIBLE);

                    if (partidaTerminada(puntosCrupier, puntosJugador)) {
                        determinarGanador(puntosCrupier, puntosJugador);
                        partidaFinalizada = true;
                    }
                });

                primerTurno = false;
            }

            imgBtnPlantarse.setVisibility(View.VISIBLE);
            imgBtnPlantarse.setOnClickListener(v -> {
                imgBtnPlantarse.setVisibility(View.INVISIBLE);
                imgBtnPedirCarta.setVisibility(View.INVISIBLE);

                // Mientras que el crupier tenga menos de 17 puntos, va a seguir pidiendo cartas
                while (calcularValorCartas(cartasRepartidasCrupie) < 17) {
                    mostrarSiguienteCarta(cartasRepartidasCrupie, baraja, CRUPIER);
                }

                // Recalculamos los puntos que lleva cada uno y seteamos los TextView
                int puntosJugador = calcularValorCartas(cartasRepartidasJugador);
                int puntosCrupier = calcularValorCartas(cartasRepartidasCrupie);

                String textoPuntosJugador12 = getString(R.string.tvPuntosJugador);
                String textoPuntosCrupier12 = getString(R.string.tvPuntosCrupier);

                twPuntosJugador.setText(textoPuntosJugador12 + " " + puntosJugador);
                twPuntosCrupie.setText(textoPuntosCrupier12 + " " + puntosCrupier);

                determinarGanador(puntosCrupier, puntosJugador);
                partidaFinalizada = true;
            });


            imgBtnPedirCarta.setVisibility(View.VISIBLE);
            imgBtnPedirCarta.setOnClickListener(v -> {
                int puntosJugador = calcularValorCartas(cartasRepartidasJugador);
                int puntosCrupier = calcularValorCartas(cartasRepartidasCrupie);

                if (puntosCrupier < 17 && puntosJugador > 21) {
                    mostrarSiguienteCarta(cartasRepartidasCrupie, baraja, CRUPIER);
                }

                // Se muestra una nueva carta para el jugador
                mostrarSiguienteCarta(cartasRepartidasJugador, baraja, JUGADOR);

                // Recalculamos los puntos que lleva cada uno y seteamos los TextView
                puntosJugador = calcularValorCartas(cartasRepartidasJugador);
                puntosCrupier = calcularValorCartas(cartasRepartidasCrupie);

                String textoPuntosJugador13 = getString(R.string.tvPuntosJugador);
                String textoPuntosCrupier13 = getString(R.string.tvPuntosCrupier);

                twPuntosJugador.setText(textoPuntosJugador13 + " " + puntosJugador);
                twPuntosCrupie.setText(textoPuntosCrupier13 + " " + puntosCrupier);

                // Si los puntos del jugador son 21 o más, se oculta el botón ya que no se puede seguir usando
                if (calcularValorCartas(cartasRepartidasJugador) >= 21) {
                    imgBtnPedirCarta.setVisibility(View.INVISIBLE);
                }

                if (partidaTerminada(puntosCrupier, puntosJugador)) {
                    determinarGanador(puntosCrupier, puntosJugador);
                    partidaFinalizada = true;
                }
            });

        } else {
            determinarGanador(valorCartasCrupie, valorCartasJugador);
            partidaFinalizada = true;
        }
    }

    private void mostrarSiguienteCarta(List<Carta> cartasRepartidas, Baraja baraja, int quien) {
        cartasRepartidas.add(baraja.siguienteCarta());

        // Obtenemos el valor y el palo de la última carta repartida para "construir" el Drawable
        Carta ultimaRepartida = cartasRepartidas.get(cartasRepartidas.size() - 1);
        String valor = ultimaRepartida.getValor().toString().toLowerCase();
        String palo = ultimaRepartida.getPalo().toString().toLowerCase();

        // Obtenemos el id del Drawable
        int resourceId = this.getResources().getIdentifier(valor + palo, "drawable", this.getPackageName());

        // "Pintamos" el Drawable a quien corresponda
        if (quien == JUGADOR) {
            cartasJugador.get(contadorJugador).setVisibility(View.VISIBLE);
            cartasJugador.get(contadorJugador).setImageResource(resourceId);

            contadorJugador++;
        } else {
            cartasCrupie.get(contadorCrupie).setVisibility(View.VISIBLE);
            cartasCrupie.get(contadorCrupie).setImageResource(resourceId);

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
        } else {
            reiniciarPartida(2); // Empate
        }
    }

    private boolean partidaTerminada(int valorCartasCrupie, int valorCartasJugador) {
        boolean finPartida = false;

        if (valorCartasCrupie >= 21 || valorCartasJugador >= 21) {
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

    public int calcularValorCartas(List<Carta> cartas) {
        int valor = 0;

        for (int i = 0; i < cartas.size(); i++) {
            valor += cartas.get(i).getValor().getNumVal();
        }

        return valor;
    }
}