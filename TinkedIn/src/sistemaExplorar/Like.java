package sistemaExplorar;

import java.io.Serializable;
import java.util.Objects;

import usuarios.Usuario;

public class Like implements Serializable{

	private Usuario From;
	private Usuario To;
	
	public Usuario getFrom() {
		return From;
	}

	public void setFrom(Usuario from) {
		From = from;
	}

	public Usuario getTo() {
		return To;
	}

	public void setTo(Usuario to) {
		To = to;
	}

	public Like(Usuario from, Usuario to) {
		this.From = from;
		this.To = to;
	}

	@Override
	public String toString() {
		return "Like [From: " + From + " ; To: " + To + "]";
	}


	@Override
	public boolean equals(Object obj) {
		Like other = (Like) obj;
		if (other.getFrom().equals(this.getFrom()) & other.getTo().equals(this.getTo())) {
			return true;
		}else {
			return false; 
		}
	}


	
	
	
}
