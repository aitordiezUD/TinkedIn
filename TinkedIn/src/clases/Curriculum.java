package clases;

import java.util.*;

public class Curriculum {

	//Atributos
	
	private int exp_laboral;
	private String educacion;
	private ArrayList<String> titulos;
	private ArrayList<String> idiomas;
	
	
	//Getters and setters
	public int getExp_laboral() {
		return exp_laboral;
	}
	public void setExp_laboral(int exp_laboral) {
		this.exp_laboral = exp_laboral;
	}
	public String getEducacion() {
		return educacion;
	}
	public void setEducacion(String educacion) {
		this.educacion = educacion;
	}
	public ArrayList<String> getTitulos() {
		return titulos;
	}
	public String TitulosToString() {
		String listaTitulos = "";
		for(int i = 0; i < this.titulos.size()-1; i++) {
			listaTitulos = listaTitulos + i;
		}
		return listaTitulos;
	}
	public void setTitulos(ArrayList<String> titulos) {
		this.titulos = titulos;
	}
	public ArrayList<String> getIdiomas() {
		return idiomas;
	}
	public String IdiomasToString() {
		String listaIdiomas = "";
		for(int i = 0; i < this.idiomas.size()-1; i++) {
			listaIdiomas = listaIdiomas + i;
		}
		return listaIdiomas;
	}
	public void setIdiomas(ArrayList<String> idiomas) {
		this.idiomas = idiomas;
	}
	
	
	
	
	
	

}
