package clases;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Persona extends Usuario {
	
	//Atributos: 
	
	private int idPersona;
	private int edad;
	private String correoElectronico;
	private int nTelefeno;
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
	
	public Persona(String nombre, String ubicacion, ImageIcon fotoDePerfil,String apellidos, int idPersona, int edad,
			String correoElectronico, int nTelefeno, ArrayList<Habilidad> curriculum,
			ArrayList<String> habilidadesTecnicas) {
		super(nombre, ubicacion, apellidos, fotoDePerfil);
		this.idPersona = idPersona;
		this.edad = edad;
		this.correoElectronico = correoElectronico;
		this.nTelefeno = nTelefeno;
		this.curriculum = curriculum;
		this.habilidadesTecnicas = habilidadesTecnicas;
	}

	
	public Persona() {
		super();
		this.idPersona = 0;
		this.edad = 0;
		this.correoElectronico = "";
		this.nTelefeno = 0;
		this.curriculum = null;
		this.habilidadesTecnicas = null;
	}


	@Override
	public String toString() {

	//	contrasena?
		
	return "Persona[idPersona=" + idPersona +
    ", nombre=" + getNombre() +
    ", apellidos=" + getApellidos() +
    ", ubicacion=" + getUbicacion() +
    ", edad=" + edad +
    ", correoElectronico=" + correoElectronico +
    ", nTelefeno=" + nTelefeno +
    ", curriculum=" + curriculum +
    ", habilidadesTecnicas=" + habilidadesTecnicas +
    ']';
	
	}
	//Atributos de REGISTRO
	
//	Nombre;
//	Apellidos;
//	Username;
//	Correo;
//	Contrasena;

//	Telefono;
//	Curriculum;
//	datos personales: ubicacion,edad,...
	
}
