package usuarios;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.Properties;

import javax.swing.*;

import nube.ImagenesAzure;

enum Tipo {PERSONA, EMPRESA}

public abstract class Usuario implements Serializable{
	private static final String rutaBase = "https://tinkedin.blob.core.windows.net/tinkedinv1/";
	
	private static int count = 0;
	
	//Atributos 
	private long id;
	private String fotoDePerfil;
	private String password;
	private Tipo tipo;
	private String correo;
	private String telefono;
	
	//Getters y setters
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public static int getCount() {
		return count;
	}
	public static void setCount(int count) {
		Usuario.count = count;
	}
	public long getId() {
		return id;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getFotoDePerfil() {
		return fotoDePerfil;
	}
	public void setFotoDePerfil(String fotoDePerfil) {
		this.fotoDePerfil = fotoDePerfil;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	// Constructores
		

	

	public Usuario(int id, String fotoDePerfil,String password, String correo, String telefono) {
		if (this instanceof Persona) {
			this.tipo = Tipo.PERSONA;
		}else {
			this.tipo = Tipo.EMPRESA;
		}
		this.id = id;
		this.password = password;
//		new ImagenesAzure();
//		ImagenesAzure.subirImagenFicheros(fotoDePerfil,id + ".jpg");
		this.fotoDePerfil = rutaBase+this.id+".jpg";
		this.correo = correo;
		this.telefono = telefono;
	}
	
	public Usuario(File fotoDePerfil,String password, String correo, String telefono) {
		if (this instanceof Persona) {
			this.tipo = Tipo.PERSONA;
		}else {
			this.tipo = Tipo.EMPRESA;
		}
		id = count;
		count++;
		this.password = password;
//		new ImagenesAzure();
//		ImagenesAzure.subirImagenFicheros(fotoDePerfil,id + ".jpg");
		this.fotoDePerfil = rutaBase+this.id+".jpg";
		this.correo = correo;
		this.telefono = telefono;
	}
	
	public Usuario(Usuario u) {
		this.fotoDePerfil = u.getFotoDePerfil();
		this.id = u.getId();
		this.password = u.getPassword();
		this.tipo = u.getTipo();
		this.correo = u.getCorreo();
		this.telefono = u.getTelefono();
	}

	@Override
	public boolean equals(Object obj) {
		Usuario other = (Usuario) obj;
		if (other.getId() == this.getId()) {
			return true;
		}else {
			return false;
		}
	}



}
