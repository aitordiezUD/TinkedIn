package clases;

public class Match {

	private Empresa e;
	private Persona p;
	
	public Empresa getE() {
		return e;
	}
	public void setE(Empresa e) {
		this.e = e;
	}
	public Persona getP() {
		return p;
	}
	public void setP(Persona p) {
		this.p = p;
	}
	
	public Match( Empresa e, Persona p) {
		this.e = e;
		this.p = p;
	}
	
}
