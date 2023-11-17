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

	@Override
	public void run() {
		// TODO Auto-generated method stub
		File f = new File("personas.csv");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(f);
			ois =  new ObjectInputStream(fis);
			ArrayList<Persona> personas = DatosFicheros.getPersonas();
			Object o = ois.readObject();
			
			while ( o != null ) {
			Persona p = ( Persona ) o;
			personas.add( p );
		}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
