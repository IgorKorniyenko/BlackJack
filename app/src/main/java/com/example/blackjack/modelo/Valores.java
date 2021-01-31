package com.example.blackjack.modelo;

import java.util.ArrayList;
import java.util.List;

public enum Valores {
	//UNO,DOS,TRES,CUATRO,CINCO,SEIS,SIETE,SOTA,CABALLO,REY;
	TWO,THREE,FOUR,FIVE,
	SIX,SEVEN,EIGHT,NINE,TEN,
	J,Q,K,A;



	public static List<Valores> toList(){
		ArrayList<Valores> lista = new ArrayList<Valores>();
		
		for(Valores v : Valores.values()) {
			lista.add(v);
		}
		
		return lista;
	}
}
