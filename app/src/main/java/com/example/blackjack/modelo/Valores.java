package com.example.blackjack.modelo;

import java.util.ArrayList;
import java.util.List;

public enum Valores {
	//UNO,DOS,TRES,CUATRO,CINCO,SEIS,SIETE,SOTA,CABALLO,REY;
	TWO(2),THREE(3),FOUR(4),FIVE(5),
	SIX(6),SEVEN(7),EIGHT(8),NINE(9),TEN(10),
	J(10),Q(10),K(10) ,A(11);

	private int numVal;

	Valores(int numVal) {
		this.numVal = numVal;
	}

	public int getNumVal() {
		return numVal;
	}

	public static List<Valores> toList(){
		ArrayList<Valores> lista = new ArrayList<Valores>();
		
		for(Valores v : Valores.values()) {
			lista.add(v);
		}
		
		return lista;
	}
}
