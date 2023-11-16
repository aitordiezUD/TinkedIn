package clases;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Persona extends Usuario implements Serializable{
	
	//Atributos: 
	
	private int edad;
	private String correoElectronico;
	private String telefeno;
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
	
	public String getTelefeno() {
		return telefeno;
	}

	public void setTelefeno(String telefeno) {
		this.telefeno = telefeno;
	}

	//Constructores: 
	
	public Persona(String nombre, String apellidos,String ubicacion, int edad,
			String correoElectronico, String telefeno, ArrayList<Habilidad> curriculum, ImageIcon fotoDePerfil, String password) {
		super(fotoDePerfil,password);
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.correoElectronico = correoElectronico;
		this.telefeno = telefeno;
		this.curriculum = curriculum;
	}

	@Override
	public String toString() {		
	return getId() + ": " + nombre + " " + apellidos + "\t" + correoElectronico + "\t" + telefeno;
	}

}
