package clases;

public class Profesor extends Usuario{

	private Materia materia;
	private int cuil;
	private String correo;
	
	public void constructor(String dni,String nombre, String domicilio, String correo, int cuil, String telefono,String celular,Materia m ) {
		this.setDni(dni);
		this.setDomicilio(domicilio);
		this.setNombre(nombre);
		this.correo=correo;
		this.cuil=cuil;
		this.setTelefono(telefono);
		this.setCelular(celular);
		this.materia=m;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getCuil() {
		return cuil;
	}

	public void setCuil(int cuil) {
		this.cuil = cuil;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	
}
