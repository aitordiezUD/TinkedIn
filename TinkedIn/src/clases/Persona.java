package clases;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Persona extends Usuario {
	
	//Atributos: 
	
	private int edad;
	private String correoElectronico;
	private int nTelefeno;
	private String ubicacion;
	private String nombre;
	private String apellidos;
	private ArrayList<Habilidad> curriculum;
	private ArrayList<String> habilidadesTecnicas;


	//Getters and setters:
	
	
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

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	public int getnTelefeno() {
		return nTelefeno;
	}

	public void setnTelefeno(int nTelefeno) {
		this.nTelefeno = nTelefeno;
	}

	//Constructores: 
	
	public Persona(String nombre, String apellidos,String ubicacion, ImageIcon fotoDePerfil, int edad,
			String correoElectronico, int nTelefeno, ArrayList<Habilidad> curriculum) {
		super(fotoDePerfil);
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.correoElectronico = correoElectronico;
		this.nTelefeno = nTelefeno;
		this.curriculum = curriculum;
	}

	@Override
	public String toString() {		
	return getId() + ": " + nombre + " " + apellidos + "\t" + correoElectronico + "\t" + nTelefeno;
	}

}
