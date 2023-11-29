package hilos;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import clases.DatosFicheros;
import clases.Persona;


public class CargarPersonasDAT implements Runnable{

	DatosFicheros datos;
	
	public CargarPersonasDAT(DatosFicheros datos) {
		this.datos=datos;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		File f = new File("personas.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(f);
			ois =  new ObjectInputStream(fis);
			Object o = ois.readObject();
			while ( o != null ) {
				Persona p = ( Persona ) o;
				datos.anadirUsuarioPersona(p);
				try {
					o = ois.readObject();
				} catch (Exception e) {
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
