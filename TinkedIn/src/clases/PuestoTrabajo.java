package clases;

import java.io.Serializable;
import java.util.ArrayList;

import usuarios.Empresa;

public class PuestoTrabajo implements Serializable, Comparable{

	private static final long serialVersionUID = 1L;
	private Empresa empresa;
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
	public Empresa getEmpresaPertenece() {
		return empresa;
	}
	public void setEmpresaPertenece(Empresa empresaPertenece) {
		this.empresa = empresaPertenece;
	}
	public PuestoTrabajo(String nombre, String descripcion, ArrayList<Habilidad> habilidadesReq, Empresa empresaPertence) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.habilidadesReq = habilidadesReq;
		this.empresa = empresaPertence;
	}
	@Override
	public String toString() {
		return nombre;
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
