package clases;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Empresa extends Usuario implements Serializable{

	//Atributos:
	private String nombre;
	private String telefono;
	private String correoElectronico;
	private String descripcion;
	private ArrayList<String> ubicaciones;
	private ArrayList<PuestoTrabajo> puestos;


	

	//Constructores:
	public Empresa(String nombre, String telefono, String correo, String descripcion,
			ArrayList<String> ubicaciones, ArrayList<PuestoTrabajo> puestos, ImageIcon fotoDePerfil, String password) {
		super(fotoDePerfil,password);
		this.nombre = nombre;
		this.telefono = telefono;
		this.correoElectronico = correo;
		this.descripcion = descripcion;
		this.ubicaciones = ubicaciones;
		this.puestos = puestos;
	}
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getId() + ": " + nombre + "\t" + correoElectronico + "\t" + telefono;
	}
		
	
}
