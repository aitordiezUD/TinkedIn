package hilos;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import clases.DatosFicheros;
import clases.Empresa;
import clases.Persona;

public class CargarEmpresasDAT implements Runnable {

	DatosFicheros datos;
	
	public CargarEmpresasDAT(DatosFicheros datos) {
		this.datos=datos;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		File f = new File("empresasTest.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(f);
			ois =  new ObjectInputStream(fis);
			Object o = ois.readObject();
			while ( o != null ) {
				Empresa e = ( Empresa ) o;
				datos.anadirUsuarioEmpresa(e);
				try {
					o = ois.readObject();
				} catch (Exception e1) {
					// TODO: handle exception
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
//			System.err.println("Error la lectura del fichero personas.dat");
		}
	}
		
	
}
