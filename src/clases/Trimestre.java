package clases;

import java.time.LocalDate;

public class Trimestre {
	private int num;
	private LocalDate desde;
	private LocalDate hasta;
	
	public Trimestre(int num,LocalDate desde, LocalDate hasta) {
		this.num=num;
		this.desde=desde;
		this.hasta=hasta;
	}
	public LocalDate getDesde() {
		return desde;
	}
	public void setDesde(LocalDate desde) {
		this.desde = desde;
	}
	public LocalDate getHasta() {
		return hasta;
	}
	public void setHasta(LocalDate hasta) {
		this.hasta = hasta;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	

}
