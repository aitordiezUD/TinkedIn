package pruebas;

import java.awt.Dimension;
import java.util.Comparator;
import java.util.TreeSet;

import javax.swing.JFrame;

import usuarios.Empresa;

public class test {
	//prueba cambio de nombre
	public static void main(String[] args) {
//		JFrame jf  = new JFrame();
//		jf.setSize(900,650);
//		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		
//		jf.setVisible(true);
		
		TreeSet<Empresa> ts = new TreeSet<>(new Comparator<Empresa>() {

			@Override
			public int compare(Empresa o1, Empresa o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		
	}
}
