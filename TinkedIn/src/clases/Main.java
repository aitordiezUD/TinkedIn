package clases;

import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import ventanas.PnlExplorar;

public class Main {

	public static void main(String[] args) {
		
		DatosFicheros datos = new DatosFicheros();
//		Persona p1 = new Persona("Aitor", "Diez Mateo", "Alava", 19, "aitor.diez@opendeusto.es",
//				"688680738",new ArrayList<Habilidad>(), null, "passWd");
//		Empresa e1 = new Empresa("Google", "673264634", "google@gmail.com", "",
//				null, null, null, "psswd") ;
		
		Habilidad h = new Habilidad("campo","loreak mendian",4,"lauburu");
		ArrayList<Habilidad> curriculum = new ArrayList<Habilidad>();
		curriculum.add(h);
		Persona pAdmin = new Persona("admin", "admin", "Alava",20,"admin","admin", curriculum,new File("adminpng.png"),"admin");
		datos.anadirUsuarioPersona(pAdmin);
		
		ArrayList<PuestoTrabajo> puestosEjemplo = new ArrayList<>();
		
		for(int i = 0; i<5;i++) {
			PuestoTrabajo pt = new PuestoTrabajo(String.valueOf(i), String.valueOf(i), null, null);
			puestosEjemplo.add(pt);
		}
		
		Empresa adminE = new Empresa("adminE", "adminE", "adminE","adminE",null,puestosEjemplo, new File("TinkedinPNG.png"),"adminE");
		
		Empresa pruebaPuestos = new Empresa("prueba","prueba", "prueba","prueba", null, puestosEjemplo, new File("TinkedinPNG.png") , "prueba");
		
		datos.anadirUsuarioEmpresa(adminE);
		datos.anadirUsuarioEmpresa(pruebaPuestos);
//		Empresa adminE2 = new Empresa("adminE2", "adminE2", "adminE2","adminE2",null,null, (new ImageIcon(PnlExplorar.class.getResource("fotoPerfilEjemplo.jpg"))),"adminE2");
//		datos.anadirUsuarioEmpresa(adminE2);
		
		datos.fin();
		
	}
	
}
