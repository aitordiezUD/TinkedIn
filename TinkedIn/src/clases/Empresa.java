package clases;

import javax.swing.ImageIcon;

public class Empresa extends Usuario {

	//Atributos:
	
	private int idEmpresa;
	private String ubicacionEmpresa;
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
		return ubicacionEmpresa;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacionEmpresa = ubicacionEmpresa;
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
	
	public Empresa(String nombre,String apellidos, String ubicacion, ImageIcon fotoDePerfil, int idEmpresa, String ubicacionEmpresa,
		String infoContacto, String propietarios) {
	super(nombre,apellidos,ubicacion, fotoDePerfil);
	this.idEmpresa = idEmpresa;
	this.ubicacionEmpresa = ubicacionEmpresa;
	this.infoContacto = infoContacto;
	this.propietarios = propietarios;
	}

	public Empresa() {
		super();
		this.idEmpresa = 0;
		this.ubicacionEmpresa = "";
		this.infoContacto = "";
		this.propietarios = "";
	}
		
	
}
