package clases;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Empresa extends Usuario implements Serializable{

	/**
	 * 
	 */
	//Atributos:
	private String nombre;
	private String telefono;
	private String correoElectronico;
	private String descripcion;
	private ArrayList<String> ubicaciones;
	private ArrayList<PuestoTrabajo> puestos;

	
	

	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	public String getCorreoElectronico() {
		return correoElectronico;
	}



	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public ArrayList<String> getUbicaciones() {
		return ubicaciones;
	}



	public void setUbicaciones(ArrayList<String> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}



	public ArrayList<PuestoTrabajo> getPuestos() {
		return puestos;
	}



	public void setPuestos(ArrayList<PuestoTrabajo> puestos) {
		this.puestos = puestos;
	}



	//Constructores:
	public Empresa(String nombre, String telefono, String correo, String descripcion,
			ArrayList<String> ubicaciones, ArrayList<PuestoTrabajo> puestos, @SuppressWarnings("exports") File fotoDePerfil, String password) {
		super(fotoDePerfil,password);
		this.nombre = nombre;
		this.telefono = telefono;
		this.correoElectronico = correo;
		this.descripcion = descripcion;
		this.ubicaciones = ubicaciones;
		this.puestos = puestos;
	}
	
	

	public Empresa(Empresa e) {
		super(e);
		this.nombre = e.getNombre();
		this.telefono = e.getTelefono();
		this.descripcion = e.getDescripcion();
		this.puestos = e.getPuestos();
		this.ubicaciones = e.getUbicaciones();
		this.correoElectronico = e.getCorreoElectronico();
		
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getId() + ": " + nombre + "\t" + correoElectronico + "\t" + telefono;
	}
	
	public void notificarMatch(Usuario u) {
		Persona p = (Persona) u; 
		JOptionPane.showMessageDialog(null, "¡Enhorabuena! Has hecho match con " + p.getNombre() + " " + p.getApellidos() + "." );
	}
		
	
}
