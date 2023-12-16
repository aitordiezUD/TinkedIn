package hilos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import datos.DatosFicheros;
import usuarios.Empresa;
import usuarios.Persona;

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
			System.out.println("Tamaño empresas: " + DatosFicheros.getEmpresas().size());
			for ( Empresa e : DatosFicheros.getEmpresas()) {
				oos.writeObject(e);
//				System.out.println("Una empresa guardada");
				System.out.println(e);
			}
			
			System.out.println("Guardado finalizado");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.err.println( "Error al guardar en el archivo empresasTesT.dat" );
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Finalizado");
		
	}
	
}
