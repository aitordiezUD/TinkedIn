package clases;
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
	
	//Getters y setters
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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


	// Constructores
		

	
	public Usuario(File fotoDePerfil,String password) {
		if (this instanceof Persona) {
			this.tipo = Tipo.PERSONA;
		}else {
			this.tipo = Tipo.EMPRESA;
		}
		id = count;
		count++;
		this.password = password;
		new ImagenesAzure();
		ImagenesAzure.subirImagen(fotoDePerfil,id + ".jpg");
		this.fotoDePerfil = rutaBase+this.id+".jpg";
	}
	
	public Usuario(Usuario u) {
		this.fotoDePerfil = u.getFotoDePerfil();
		this.id = u.getId();
		this.password = u.getPassword();
		this.tipo = u.getTipo();
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
