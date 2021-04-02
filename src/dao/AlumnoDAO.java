package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Alumno;


public class AlumnoDAO {

	String timezone = "&serverTimezone=UTC";
	String u = "root";
	String p = "";
	String url = "jdbc:mysql://localhost:3306/sistema_boletin?useSSL=false"+timezone;
	
	public ArrayList<Alumno> consultaAlumnos(int año,int division) {
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();;
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, u, p);
			String sql = "SELECT U.usuario_dni, U.usuario_nombre,U.domicilio,U.telefono_casa,U.telefono_celular FROM `usuarios` AS U JOIN listas AS L ON U.usuario_dni=L.dni_alumno JOIN cursos AS C ON L.id_curso=C.curso_id WHERE C.curso=? &&C.division=?";
			PreparedStatement st = c.prepareStatement(sql);
			st.setInt(1, año);
			st.setInt(2,division);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Alumno a=new Alumno();
				a.setNombre(rs.getString(2));
				a.setDni(rs.getString(1));
				a.setDomicilio(rs.getString(3));
				a.setTelefono(rs.getString(4));
				a.setCelular(rs.getString(5));
				alumnos.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return alumnos;
	}

	public ArrayList<Alumno> todosAlumnos() {
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, u, p);
			String sql = "SELECT U.usuario_dni, U.usuario_nombre,U.domicilio,U.telefono_casa,U.telefono_celular FROM `usuarios` AS U JOIN listas AS L ON U.usuario_dni=L.dni_alumno JOIN cursos AS C ON L.id_curso=C.curso_id";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Alumno a=new Alumno();
				a.setNombre(rs.getString(2));
				a.setDni(rs.getString(1));
				a.setDomicilio(rs.getString(3));
				a.setTelefono(rs.getString(4));
				a.setCelular(rs.getString(5));
				alumnos.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return alumnos;
	}
}
