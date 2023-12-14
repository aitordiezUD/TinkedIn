package clases;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Persona extends Usuario implements Serializable{
	
	//Atributos: 
	
	/**
	 * 
	 */
	private int edad;
	private String correoElectronico;
	private String telefono;
	private String ubicacion;
	private String nombre;
	private String apellidos;
	private ArrayList<Habilidad> curriculum;
	private ArrayList<String> habilidadesTecnicas;
	private Vector<Like> likes;


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
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefeno) {
		this.telefono = telefeno;
	}

	public Vector<Like> getLikes() {
		return likes;
	}
	public void setLikes(Vector<Like> likes) {
		this.likes = likes;
	}
	
	//Constructores: 
	
	
	public Persona(String nombre, String apellidos,String ubicacion, int edad,
			String correoElectronico, String telefeno, ArrayList<Habilidad> curriculum, @SuppressWarnings("exports") File fotoDePerfil, String password) {
		super(fotoDePerfil,password);
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.ubicacion = ubicacion;
		this.correoElectronico = correoElectronico;
		this.telefono = telefeno;
		this.curriculum = curriculum;
		this.likes = new Vector<>();
	}
	
	public Persona( Persona p ) {
		super(p);
		this.nombre = p.getNombre();
		this.apellidos = p.getApellidos();
		this.ubicacion = p.getUbicacion();
		this.edad = p.getEdad();
		this.correoElectronico = p.getCorreoElectronico();
		this.telefono = p.getTelefono();
		this.curriculum = p.getCurriculum();
		this.likes = p.getLikes();
	}
	
	@Override
	public String toString() {		
	return getId() + ": " + nombre + " " + apellidos + " ;" + correoElectronico + " ; " + telefono;
	}
	
	public void notificarMatch(Usuario u) {
		Empresa e = (Empresa) u; 
		JOptionPane.showMessageDialog(null, "¡Enhorabuena! Has hecho match con " + e.getNombre() + "" );
	}
	
}
