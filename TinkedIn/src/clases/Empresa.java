package clases;

import javax.swing.ImageIcon;

public class Empresa extends Usuario {

	//Atributos:
	
	private int idEmpresa;
	private String ubicacion;
	private String infoContacto;
	private String propietarios;
	
	//Getters y setters:
	
	
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getInfoContacto() {
		return infoContacto;
	}
	public void setInfoContacto(String infoContacto) {
		this.infoContacto = infoContacto;
	}
	public String getPropietarios() {
		return propietarios;
	}
	public void setPropietarios(String propietarios) {
		this.propietarios = propietarios;
	}
	
	
	//Constructores:
	
	public Empresa(String nombre, String ubicacion, ImageIcon fotoDePerfil, int idEmpresa, String ubicacion2,
		String infoContacto, String propietarios) {
	super(nombre, ubicacion, fotoDePerfil);
	this.idEmpresa = idEmpresa;
	ubicacion = ubicacion2;
	this.infoContacto = infoContacto;
	this.propietarios = propietarios;
	}

	public Empresa() {
		super();
		this.idEmpresa = 0;
		this.ubicacion = "";
		this.infoContacto = "";
		this.propietarios = "";
	}
		
	
}
