package hilos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import clases.Mensaje;
import datos.DatosFicheros;
import usuarios.Empresa;

public class GuardarMensajesDAT implements Runnable {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		File f  = new File("mensajes.dat");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		
		try {
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			System.out.println("Mensajes a guardar: " + DatosFicheros.getMensajes());
			for ( Mensaje m : DatosFicheros.getMensajes()) {
				oos.writeObject(m);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
