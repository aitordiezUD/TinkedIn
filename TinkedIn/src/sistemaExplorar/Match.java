package sistemaExplorar;

import java.io.Serializable;

import usuarios.Usuario;

public class Match implements Serializable{


	private static final long serialVersionUID = 1L;
	private int u1;
	private int u2;
	
	public int getU1() {
		return u1;
	}

	public void setU1(int u1) {
		this.u1 = u1;
	}

	public int getU2() {
		return u2;
	}

	public void setU2(int u2) {
		this.u2 = u2;
	}



	public Match( int u1, int u2) {
		this.u1 = u1;
		this.u2 = u2;
	}
	
	
	
	@Override
	public String toString() {
		return "Match[ Usuario1: " + u1 + " ; Usuario2: " + u2 + "]";
	}
	
	
}
