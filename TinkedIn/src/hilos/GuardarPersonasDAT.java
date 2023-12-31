package hilos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import datos.DatosFicheros;
import usuarios.Persona;

public class GuardarPersonasDAT implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		File f  = new File("personas.dat");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		
		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			for (Persona p : DatosFicheros.getPersonas()) {
				oos.writeObject(p);
			}
		}catch(Exception e) {
			System.err.println("Error: en el guardado de personas.");
			e.printStackTrace();

		}
	}

}
