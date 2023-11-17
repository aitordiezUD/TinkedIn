package hilos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import clases.DatosFicheros;
import clases.Empresa;
import clases.Persona;

public class GuardarEmpresasDAT implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		File f  = new File("empresas.dat");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		
		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			ArrayList<Empresa> empresas = DatosFicheros.getEmpresas();
			for ( Empresa e : empresas) {
				System.out.println( "Escribiendo empresa" );
				System.out.println(e);
				oos.writeObject(e);
				System.out.println( "Persona empresa" );
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.err.println( "Error al leer el archivo empresas.dat" );
	}
	
}
