package clases;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Empresa extends Usuario {

	//Atributos:
	private String nombre;
	private String telefono;
	private String correoElectronico;
	private String descripcion;
	private ArrayList<String> ubicaciones;
	private ArrayList<PuestoTrabajo> puestos;


	

	//Constructores:
	public Empresa(ImageIcon fotoDePerfil, String nombre, String telefono, String correo, String descripcion,
			ArrayList<String> ubicaciones, ArrayList<PuestoTrabajo> puestos) {
		super(fotoDePerfil);
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
