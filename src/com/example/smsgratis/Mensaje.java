package com.example.smsgratis;

public class Mensaje {
	private long id;
	private int numero;
	private String mensaje;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public int getNumero()
	{
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getMensaje() {
		return mensaje;
	}
		
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
