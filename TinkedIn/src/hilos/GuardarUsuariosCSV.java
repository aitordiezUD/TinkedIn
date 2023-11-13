package hilos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GuardarUsuariosCSV implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		File f  = new File("personas2.csv");
		FileWriter fw = null;
		PrintWriter pw = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		
		try {
			fw = new FileWriter(f);
			pw = new PrintWriter(fw);
			for (int i = 0; i<p.size(); i++) {
				pw.println(p.get(i).getDni() + ";" + p.get(i).getEdad() + ";" + p.get(i).getNombre());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pw.close();
		}
	}

}
