package clases;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.swing.*;

import nube.ImagenesAzure;

enum Tipo {PERSONA, EMPRESA}

public abstract class Usuario implements Serializable{
	
	private static int count = 0;
	
	//Atributos 
	private long id;
	private File fotoDePerfil;
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

	public File getFotoDePerfil() {
		return fotoDePerfil;
	}
	public void setFotoDePerfil(File fotoDePerfil) {
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
		this.fotoDePerfil = fotoDePerfil;
		this.password = password;
		new ImagenesAzure();
		ImagenesAzure.subirImagen(fotoDePerfil,id + ".jpg");
	}
	
	public Usuario(Usuario u) {
		this.fotoDePerfil = u.getFotoDePerfil();
		this.id = u.getId();
		this.password = u.getPassword();
		this.tipo = u.getTipo();
	}



}
