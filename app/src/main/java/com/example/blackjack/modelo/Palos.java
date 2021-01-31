package com.example.blackjack.modelo;

import java.util.ArrayList;
import java.util.List;

public enum Palos {
	//OROS,COPAS,ESPADAS,BASTOS;
	H,S,C,D;
	
	public static List<Palos> toList() {
		ArrayList<Palos> lista = new ArrayList<Palos>();
		
		for(Palos s : Palos.values()) {
			lista.add(s);
		}
		
		return lista;
	}
}
