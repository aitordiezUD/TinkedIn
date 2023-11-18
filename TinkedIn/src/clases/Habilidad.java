package clases;

import java.io.Serializable;

public class Habilidad implements Serializable{
	
	private String campo; //Informática
	private String nombre; //Machine Learning
	private int destreza; // Rango 1 al 5
	private String descripcion; //Titulo obtenido en la Universidad, trabajo en X empresa...
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDestreza() {
		return destreza;
	}
	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Habilidad(String campo, String nombre, int destreza, String descripcion) {
		super();
		this.campo = campo;
		this.nombre = nombre;
		this.destreza = destreza;
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return nombre;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		Habilidad otra = (Habilidad) obj;
		return this.nombre.equals(otra.nombre);
	}
	
	
	
	
	
	
}
