package sistemaExplorar;

import usuarios.Usuario;

public class Match {

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
		return "Match: " + u1 + " ; " + u2 ;
	}
	
	
}
