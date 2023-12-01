package clases;

public class Like {

	private Usuario daLike;
	private Usuario recibeLike;
	
	public Like(Usuario da, Usuario recibe) {
		this.daLike = da;
		this.recibeLike = recibe;
	}

	@Override
	public String toString() {
		return "Like de: " + daLike + ", recibe el like: " + recibeLike;
	}
	
	
	
}
