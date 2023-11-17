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
				oos.writeObject(e);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
