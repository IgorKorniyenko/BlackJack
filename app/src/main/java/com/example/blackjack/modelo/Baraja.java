package com.example.blackjack.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Baraja {

	private List<Carta> baraja;
	private List<Carta> monton;
	
	public Baraja() {
		this.baraja = new ArrayList<Carta>();
		this.monton = new ArrayList<Carta>();	
		
		crearBaraja();
	}
	
	public Carta getCarta(int cual){
		return baraja.get(cual);
	}
	
	public void crearBaraja() {
		Carta c = null;
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				c = new Carta(Palos.toList().get(i), Valores.toList().get(j));
				
				baraja.add(c);
			}
		}
	}
	
	public void barajar() {
		Carta aux = null;
		int posicionAleatoria = 0;
		
		Carta[] arrayBaraja = baraja.toArray(new Carta[baraja.size()]);
		
		for (int i = 0; i < baraja.size(); i++) {
			posicionAleatoria = (int) (Math.random() * baraja.size());
			
			aux = arrayBaraja[i];
			arrayBaraja[i] = arrayBaraja[posicionAleatoria];
			arrayBaraja[posicionAleatoria] = aux;
		}
		
		baraja = new ArrayList<Carta>(Arrays.asList(arrayBaraja));
	}
	
	public Carta siguienteCarta() {
		Carta c = null;
		
		if(baraja.size() == 0) {
			System.out.println("No quedan cartas en la baraja.");	
		}else{
			if(baraja.size() == 1) {
				System.out.println("Ultima carta de la baraja.");
			}
			
			c = baraja.get(baraja.size() - 1);
			
			monton.add(c);
			
			baraja.remove(c);
		}
		
		return c;
	}
	
	public int cartasDisponibles() {
		return baraja.size();
	}
	
	public List<Carta> darCartas(int cuantas){
		List<Carta> darCartas = null;
		
		if(cuantas <= baraja.size()) {
			darCartas = new ArrayList<Carta>();
			
			for (int i = 0; i < cuantas; i++) {
				darCartas.add(siguienteCarta());
			}
		}else {
			System.out.println("Numero de cartas no disponible.");
		}
		
		return darCartas;
	}
	
	public void mostrarMonton() {
		if(monton.size() > 0) {
			for(Carta c : monton) {
				System.out.println(c.toString());
			}
		}else {
			System.out.println("No hay cartas en el monton");
		}
	}
	
	public void mostrarBaraja() {
		if(baraja.size() > 0) {
			for (Carta c : baraja) {
				System.out.println(c.toString());
			}
		}else {
			System.out.println("No hay cartas en la baraja");
		}
	}
	
	
	
}
