package clases;

import java.io.Serializable;
import java.util.ArrayList;

import usuarios.Empresa;

public class PuestoTrabajo implements Serializable, Comparable{

	private static final long serialVersionUID = 1L;
	private long idEmpresa;
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
	
	public long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public PuestoTrabajo(String nombre, String descripcion, ArrayList<Habilidad> habilidadesReq, Empresa empresa) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.habilidadesReq = habilidadesReq;
		this.idEmpresa = empresa.getId();
	}
	
	public PuestoTrabajo(String nombre, String descripcion, ArrayList<Habilidad> habilidadesReq, Long idEmpresa) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.habilidadesReq = habilidadesReq;
		this.idEmpresa = idEmpresa;
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
