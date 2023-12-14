package clases;

public class Match {

	private Usuario u1;
	private Usuario u2;
	
	public Usuario getU1() {
		return u1;
	}

	public void setU1(Usuario u1) {
		this.u1 = u1;
	}

	public Usuario getU2() {
		return u2;
	}

	public void setU2(Usuario u2) {
		this.u2 = u2;
	}



	public Match( Usuario u1, Usuario u2) {
		this.u1 = u1;
		this.u2 = u2;
	}
	
	
	
	@Override
	public String toString() {
		return "Match: " + u1 + " ; " + u2 ;
	}
	
	
}
