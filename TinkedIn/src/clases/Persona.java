package clases;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Persona extends Usuario {
	
	//Atributos: 
	
	private int idPersona;
	private int edad;
	private Curriculum curriculum;
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
	public Curriculum getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(Curriculum curriculum) {
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
			Curriculum curriculum, ArrayList<String> habilidadesTecnicas) {
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
		return this.getNombre() + "(" + this.idPersona + "), de" + this.edad + "años de edad, tiene" + this.curriculum.getExp_laboral() + "años"
				+ " de experiencia en el sector, estudió " + this.curriculum.getEducacion() + " y le pertenecen los siguientes titulos: "
						+ this.curriculum.TitulosToString() + ". Ademas domina el uso de los siguientes idiomas: "
								+ this.curriculum.IdiomasToString() + ".";
	}
	
	
	
}
