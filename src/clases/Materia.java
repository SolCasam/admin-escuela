package clases;

import java.util.ArrayList;
import java.util.HashMap;

public class Materia {
	private int año;
	private int division;
	private String nombre;
	private ArrayList<Nota> lasNotas = new ArrayList<Nota>();
	private HashMap<Integer, Integer> promedios = new HashMap();
	private String prof;
	private int id_curso;
	private int dni_profesor;
	private int id;

	public Materia() {
	}

	public Materia(String nombre, int año, int div, String prof) {
		this.nombre = nombre;
		this.año = año;
		this.division = div;
		this.prof = prof;

	}

	public Materia(String nombre, ArrayList<Nota> promedios) {
		this.nombre = nombre;
		this.lasNotas = promedios;

	}

	public Materia(String materia, int año, int div) {
		this.nombre = materia;
		this.año = año;
		this.division = div;
	}

	public Materia(String nombre, HashMap<Integer, Integer> promedios) {

		this.nombre = nombre;
		this.promedios = promedios;
	}

	public void añadirNota(Nota n) {
		lasNotas.add(n);
	}

	public ArrayList<Nota> getLasNotas() {
		return lasNotas;
	}

	public void setLasNotas(ArrayList<Nota> lasNotas) {
		this.lasNotas = lasNotas;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public int getDivision() {
		return division;
	}

	public void setDivision(int division) {
		this.division = division;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProf() {
		return prof;
	}

	public void setProf(String prof) {
		this.prof = prof;
	}

	public int getId_curso() {
		return id_curso;
	}

	public void setId_curso(int id_curso) {
		this.id_curso = id_curso;
	}

	public int getDni_profesor() {
		return dni_profesor;
	}

	public void setDni_profesor(int dni_profesor) {
		this.dni_profesor = dni_profesor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HashMap<Integer, Integer> getPromedios() {
		return promedios;
	}

	public void setPromedios(HashMap<Integer, Integer> promedios) {
		this.promedios = promedios;
	}

}
