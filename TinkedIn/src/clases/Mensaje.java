package clases;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

public class Mensaje implements Serializable, Comparable{
	private long from;
	private long to;
	private String mensaje;
	private LocalDateTime date;
	public long getFrom() {
		return from;
	}
	public void setFrom(long from) {
		this.from = from;
	}
	public long getTo() {
		return to;
	}
	public void setTo(long to) {
		this.to = to;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public Mensaje(int from, int to, String mensaje, LocalDateTime date) {
		this.from = from;
		this.to = to;
		this.mensaje = mensaje;
		this.date = date;
	}
	public Mensaje(long from, long to, String mensaje, LocalDateTime date) {
		this.from = from;
		this.to = to;
		this.mensaje = mensaje;
		this.date = date;
	}
	@Override
	public String toString() {
		return "[From: " + from + " To: " + to + "] " + mensaje + " (" + date + ")";
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		Mensaje otro = (Mensaje) o;
		if (this.getDate().equals(otro.getDate())) {
			if (this.getFrom() != otro.getFrom()) {
				return (int) (this.getFrom()-otro.getFrom());
			}else {
				return 1;
			}
		}else {
			return this.date.compareTo(otro.getDate());
		}
	}
	
	

}
