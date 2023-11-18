package clases;

import java.util.ArrayList;

public class PuestoTrabajo {

	private String nombre;
	private String descripcion;
	private ArrayList<Habilidad> habilidadesReq;
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public ArrayList<Habilidad> getHabilidadesReq() {
		return habilidadesReq;
	}
	public void setHabilidadesReq(ArrayList<Habilidad> habilidadesReq) {
		this.habilidadesReq = habilidadesReq;
	}
	public PuestoTrabajo(String nombre, String descripcion, ArrayList<Habilidad> habilidadesReq) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.habilidadesReq = habilidadesReq;
	}
	
	
	
	
	
}
