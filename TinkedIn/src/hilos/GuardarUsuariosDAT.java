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

import clases.DatosFicheros;
import clases.Persona;

public class GuardarUsuariosDAT implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		File f  = new File("personas.csv");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		
		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			ArrayList<Persona> personas = DatosFicheros.getPersonas();
			for (Persona p : personas) {
				oos.writeObject(p);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
