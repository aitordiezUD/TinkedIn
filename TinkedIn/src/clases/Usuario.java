package clases;
import javax.swing.*;


public class Usuario {

	//Atributos 
	
	private String nombre;
	private String ubicacion;
	private ImageIcon fotoDePerfil;
	
	//Getters y setters
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public ImageIcon getFotoDePerfil() {
		return fotoDePerfil;
	}
	public void setFotoDePerfil(ImageIcon fotoDePerfil) {
		this.fotoDePerfil = fotoDePerfil;
	}

	// Constructores
	
	public Usuario(String nombre, String ubicacion, ImageIcon fotoDePerfil) {
		super();
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.fotoDePerfil = fotoDePerfil;
	} 
	
	public Usuario() {
		super();
		this.nombre = "";
		this.ubicacion = "";
		this.fotoDePerfil = null;
	}

	
}
