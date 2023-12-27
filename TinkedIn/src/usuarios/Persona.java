package usuarios;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import clases.Habilidad;
import sistemaExplorar.Like;

public class Persona extends Usuario implements Serializable{
	
	//Atributos: 
	
	/**
	 * 
	 */
	private Date nacimiento;
//	private String correoElectronico;
//	private String telefono;
	private String ubicacion;
	private String nombre;
	private String apellidos;
	private ArrayList<Habilidad> Habilidades;
	private Vector<Like> likes;


	//Getters and setters:
	
	
	public Date getEdad() {
		return nacimiento;
	}
	public void setEdad(Date edad) {
		this.nacimiento = edad;
	}

	public ArrayList<Habilidad> getCurriculum() {
		return Habilidades;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public void setCurriculum(ArrayList<Habilidad> curriculum) {
		this.Habilidades = curriculum;
	}

//	public String getCorreoElectronico() {
//		return correoElectronico;
//	}
//
//	public void setCorreoElectronico(String correoElectronico) {
//		this.correoElectronico = correoElectronico;
//	}
//	
//	public String getTelefono() {
//		return telefono;
//	}
//
//	public void setTelefono(String telefeno) {
//		this.telefono = telefeno;
//	}

	public Vector<Like> getLikes() {
		return likes;
	}
	public void setLikes(Vector<Like> likes) {
		this.likes = likes;
	}
	
	//Constructores: 
	
	
	public Persona(String nombre, String apellidos,String ubicacion, Date nacimiento,
			String correo, String telefono, ArrayList<Habilidad> habilidades,File fotoDePerfil, String password) {
		super(fotoDePerfil,password,correo,telefono);
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacimiento = nacimiento;
		this.ubicacion = ubicacion;
//		this.correoElectronico = correoElectronico;
//		this.telefono = telefeno;
		this.Habilidades = habilidades;
		this.likes = new Vector<>();
	}
	
//	Para cargar de la Base de Datos Remota:
	public Persona(int id, String nombre, String apellidos,String ubicacion, Date nacimiento,
			String correo, String telefono, ArrayList<Habilidad> habilidades,String fotoDePerfil, String password) {
		super(id,fotoDePerfil,password,correo,telefono);
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nacimiento = nacimiento;
		this.ubicacion = ubicacion;
//		this.correoElectronico = correoElectronico;
//		this.telefono = telefeno;
		this.Habilidades = habilidades;
		this.likes = new Vector<>();
	}
	
//	Para cargar de Ficheros:
	public Persona( Persona p ) {
		super(p);
		this.nombre = p.getNombre();
		this.apellidos = p.getApellidos();
		this.ubicacion = p.getUbicacion();
		this.nacimiento = p.getEdad();
//		this.correoElectronico = p.getCorreoElectronico();
//		this.telefono = p.getTelefono();
		this.Habilidades = p.getCurriculum();
		this.likes = p.getLikes();
	}
	
	@Override
	public String toString() {		
	return getId() + ": " + nombre + " " + apellidos + " ;" + getCorreo() + " ; " + getTelefono();
	}
	
	public void notificarMatch(Usuario u) {
		Empresa e = (Empresa) u; 
		JOptionPane.showMessageDialog(null, "¡Enhorabuena! Has hecho match con " + e.getNombre() + "" );
	}
	
}
