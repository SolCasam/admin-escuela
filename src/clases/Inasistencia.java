package clases;
import java.time.LocalDate;

public class Inasistencia {
	
	private LocalDate fecha;
	private float valor;
	private boolean esJustificada;
	
	public Inasistencia(LocalDate f, float valor, boolean just) {
		this.fecha=f;
		this.valor=valor;
		this.esJustificada=just;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public float getTipo() {
		return valor;
	}
	public void setTipo(float tipo) {
		this.valor = tipo;
	}
	public boolean getEsJustificada() {
		return esJustificada;
	}
	public void setEsJustificada(boolean esJustificada) {
		this.esJustificada = esJustificada;
	}
	
	
}
