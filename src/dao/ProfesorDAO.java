package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Alumno;
import clases.Materia;
import clases.Profesor;

public class ProfesorDAO {

	String timezone = "&serverTimezone=UTC";
	String u = "root";
	String p = "";
	String url = "jdbc:mysql://localhost:3306/sistema_boletin?useSSL=false"+timezone;
	
	public ArrayList<Profesor> consultaProfesores() {
		ArrayList<Profesor> profesores = new ArrayList<>();
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, u, p);
			String sql = "SELECT U.usuario_dni, U.usuario_nombre, U.domicilio, U.correo, U.cuil,U.telefono_casa,U.telefono_celular,M.materia_nombre, C.curso,C.division FROM usuarios AS U JOIN materias AS M ON U.usuario_dni=M.dni_profesor JOIN cursos AS C ON M.id_curso=C.curso_id WHERE U.usuario_codigo_tipo=2";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				String dni = rs.getString(1);
				String nombre = rs.getString(2);
				String domicilio=rs.getString(3);
				String correo=rs.getString(4);
				int cuil=rs.getInt(5);
				String telefonoCasa=rs.getString(6);
				String celular=rs.getString(7);
				String materia=rs.getString(8);
				int año=rs.getInt(9);
				int div=rs.getInt(10);
				Materia m=new Materia(materia,año,div);
				Profesor p=new Profesor();
				p.constructor(dni,nombre,domicilio,correo,cuil,telefonoCasa,celular,m);
				profesores.add(p);
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
		return profesores;
	}
}
