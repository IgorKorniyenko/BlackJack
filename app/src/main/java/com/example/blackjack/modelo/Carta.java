package com.example.blackjack.modelo;

public class Carta {

	private Palos palo;
	private Valores valor; 
	
	public Carta (Palos palo, Valores valor) {
		this.palo = palo;
		this.valor = valor;
	}
	
	public Carta () {
		this(null,null);
	}
	
	public Palos getPalo() {
		return this.palo;
	}
	
	public void setPalo(Palos palo){
		this.palo = palo;
	}

	public Valores getValor() {
		return valor;
	}

	public void setValor(Valores valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((palo == null) ? 0 : palo.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carta other = (Carta) obj;
		if (palo != other.palo)
			return false;
		if (valor != other.valor)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return valor + " de " + palo;
	}

}
