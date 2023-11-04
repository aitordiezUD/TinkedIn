package clases;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Persona extends Usuario {
	
	//Atributos: 
	
	private int idPersona;
	private int edad;
	private ArrayList<Habilidad> curriculum;
	private ArrayList<String> habilidadesTecnicas;
	
	//Getters and setters:
	
	public int getIdPersona() {
		return idPersona;
	}
	
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}

	public ArrayList<Habilidad> getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(ArrayList<Habilidad> curriculum) {
		this.curriculum = curriculum;
	}

	public ArrayList<String> getHabilidadesTecnicas() {
		return habilidadesTecnicas;
	}
	public void setHabilidadesTecnicas(ArrayList<String> habilidadesTecnicas) {
		this.habilidadesTecnicas = habilidadesTecnicas;
	}

	
	
	//Constructores: 
	
	public Persona(String nombre, String ubicacion, ImageIcon fotoDePerfil, int idPersona, int edad,
			ArrayList<Habilidad> curriculum, ArrayList<String> habilidadesTecnicas) {
		super(nombre, ubicacion, fotoDePerfil);
		this.idPersona = idPersona;
		this.edad = edad;
		this.curriculum = curriculum;
		this.habilidadesTecnicas = habilidadesTecnicas;
	}
	
	public Persona() {
		super();
		this.idPersona = 0;
		this.edad = 0;
		this.curriculum = null;
		this.habilidadesTecnicas = null;
	}

	@Override
	public String toString() {
		return "toString";
	}
	
	
	
}
