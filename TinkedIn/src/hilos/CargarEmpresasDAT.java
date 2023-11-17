package hilos;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import clases.DatosFicheros;
import clases.Empresa;
import clases.Persona;

public class CargarEmpresasDAT implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		File f = new File("empresas.csv");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(f);
			ois =  new ObjectInputStream(fis);
			ArrayList<Empresa> empresas = DatosFicheros.getEmpresas();
			Object o = ois.readObject();
			
			while ( o != null ) {
				Empresa e = ( Empresa ) o;
				empresas.add( e );
				o = ois.readObject();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println( "Error en la lectura del archivo empresas.dat" );
		}
		
	}
	
}
