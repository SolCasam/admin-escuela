package clases;

import java.util.ArrayList;
import java.util.HashMap;

import dao.MateriaDAO;
import dao.NotaDAO;

public class Alumno extends Usuario{

	public void constructor(String dni, String nombre, String domicilio, String tel, String cel) {
		this.setDni(dni);
		this.setNombre(nombre);
		this.setDomicilio(domicilio);
		this.setTelefono(tel);
		this.setCelular(cel);
		
	}
	public ArrayList<Materia> boletin(){
		ArrayList<Materia> boletin=new ArrayList<Materia>();
		MateriaDAO mDAO=new MateriaDAO();
		ArrayList<Integer> materias=mDAO.materiasxalumno(getDni());
	    for(int i: materias) {
	    	NotaDAO nDAO=new NotaDAO();
	    	String nombre=mDAO.nombreMateria(i);
	    	HashMap<Integer, Integer> promedios=nDAO.promedioxMateria(getDni(), i);
	    	Materia m=new Materia(nombre, promedios);
	    	boletin.add(m);
	    }
		return boletin;
	}

}
