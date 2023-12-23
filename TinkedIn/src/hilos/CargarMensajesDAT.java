package hilos;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import clases.Mensaje;
import datos.DatosFicheros;
import usuarios.Empresa;

public class CargarMensajesDAT implements Runnable{

	DatosFicheros datos;
	
	public CargarMensajesDAT(DatosFicheros datos) {
		this.datos=datos;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		File f = new File("mensajes.dat");
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(f);
			ois =  new ObjectInputStream(fis);
			Object o = ois.readObject();
			while ( o != null ) {
				Mensaje m = (Mensaje) o;
				DatosFicheros.getMensajes().add(m);
				try {
					o = ois.readObject();
				} catch (Exception e1) {
					// TODO: handle exception
					break;
				}
			}
			fis.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
//			System.err.println("Error la lectura del fichero empresasTest.dat");
		}
	}

}
