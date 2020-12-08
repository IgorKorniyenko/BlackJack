package com.example.blackjack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Ranking extends AppCompatActivity {

    private TextView tvRanking;
    private TextView tvRanking2;
    private TextView tvPrimero;
    private TextView tvPrimero2;
    private TextView tvSegundo;
    private TextView tvSegundo2;
    private TextView tvTercero;
    private TextView tvTercero2;
    private TextView tvCuarto;
    private TextView tvCuarto2;
    private TextView tvQuinto;
    private TextView tvQuinto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        tvRanking = findViewById(R.id.textViewRanking);
        tvRanking2 = findViewById(R.id.textViewRanking2);
        tvPrimero = findViewById(R.id.textViewPrimero);
        tvPrimero2 = findViewById(R.id.textViewPrimero2);
        tvSegundo = findViewById(R.id.textViewSegundo);
        tvSegundo2 = findViewById(R.id.textViewSegundo2);
        tvTercero = findViewById(R.id.textViewTercero);
        tvTercero2 = findViewById(R.id.textViewTercero2);
        tvCuarto = findViewById(R.id.textViewCuarto);
        tvCuarto2 = findViewById(R.id.textViewCuarto2);
        tvQuinto = findViewById(R.id.textViewQuinto);
        tvQuinto2 = findViewById(R.id.textViewQuinto2);
    }
}