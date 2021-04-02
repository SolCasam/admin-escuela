package clases;

import java.time.LocalDate;

public class Nota {
	
	private String nombre_materia;
	private int trimestre;
	private float valor;
	private String tipo;
	private LocalDate fecha;
	
	private String dni;
	private String nombreAlumno;
	

	public Nota(String nombre_materia, float valor, String tipo, LocalDate fecha) {
		this.nombre_materia=nombre_materia;
		this.valor=valor;
		this.tipo=tipo;
		this.fecha=fecha;
	}
	
	public Nota(int valor, int trimestre) {
		this.valor=valor;
		this.trimestre=trimestre;
	}

	public Nota(String dni, String nombre, float valor, LocalDate fecha, String tipoNota) {
		this.valor=valor;
		this.tipo=tipoNota;
		this.fecha=fecha;
		this.dni=dni;
		this.nombreAlumno=nombre;
	}

	public Nota(float valor, LocalDate fecha, String tipoNota) {
		this.valor=valor;
		this.tipo=tipoNota;
		this.fecha=fecha;
	}

	public int getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(int trimestre) {
		this.trimestre = trimestre;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getNombre_materia() {
		return nombre_materia;
	}

	public void setNombre_materia(String nombre_materia) {
		this.nombre_materia = nombre_materia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombreAlumno() {
		return nombreAlumno;
	}

	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}
	
	
}
