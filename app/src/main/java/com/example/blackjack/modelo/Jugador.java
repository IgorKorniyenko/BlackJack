package com.example.blackjack.modelo;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Jugador implements Comparable<Jugador> {

	private String login;
	@SuppressWarnings("unused")
	private String passwd;
	private int credito;
	
	public Jugador(String login, String passwd, int credito) {
		this.login = login;
		this.passwd = passwd;
		this.credito = credito;
	}
	
	public Jugador(String login) {
		this(login, null, 0);
	}
	
	public Jugador() {
		this(null, null, 0);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) throws IllegalArgumentException{
		
		if(login.length() < 2 || login.length() > 10) {
			throw new IllegalArgumentException("El login debe tener entre 2 y 10 caracteres.");
		}
		
		this.login = login;
	}


	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) throws IllegalArgumentException{
		String passwdRegexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,16}$";
		
		if(!Pattern.matches(passwdRegexp, passwd)) {
			throw new IllegalArgumentException("La contrase�a debe contener al menos un numero, "
					+ "una minuscula, una mayuscula y tener de 8 a 16 caracteres de longitud.");
		}
		
		this.passwd = passwd;
	}

	public int getCredito() {
		return credito;
	}

	public void setCredito(int credito) throws IllegalArgumentException {
		
		if(credito < 1) {
			throw new IllegalArgumentException("El credito no puede ser negativo.");
		}
		
		this.credito = credito;
	}

	@Override
	public String toString() {
		return "El jugador " + login + " tiene un credito de: " + credito + " monedas.";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		Jugador other = (Jugador) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
	
	public void leerDatos() {
		leerLogin();
		leerRestoDatos();
	}
	
	public void leerLogin() {
		boolean exito = false;
		Scanner sc = new Scanner(System.in);
		
		while(!exito) {
			try {
				setLogin(sc.nextLine());
				
				exito = true;
			}catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		
		sc.close();
	}
	
	public void leerRestoDatos() {
		boolean exito = false;
		Scanner sc = new Scanner(System.in);
		
		while(!exito) {
			try {
				setPasswd(sc.nextLine());
				
				exito = true;
			}catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		
		exito = false;
		
		while(!exito) {
			try {
				aniadirCredito(sc.nextInt());
				
				exito = true;
			}catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
		
		sc.close();
	}
	
	public void aniadirCredito(int cuanto) throws IllegalArgumentException{
		
		if(cuanto < 0) {
			throw new IllegalArgumentException("No se puede a�adir credito negativo."); 
		}
		
		setCredito(getCredito() + cuanto);
	}

	@Override
	public int compareTo(Jugador o) {
		return login.compareTo(o.getLogin());
	}
}
