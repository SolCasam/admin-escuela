package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Vacante;


public class EscuelaDAO {

	String timezone = "&serverTimezone=UTC";
	String u = "root";
	String p = "";
	String url = "jdbc:mysql://localhost:3306/sistema_boletin?useSSL=false"+timezone;
	
	public ArrayList<Vacante> consultaVacantes(int año) {
		Connection c = null;
		ArrayList<Vacante> Vacantes=new ArrayList<Vacante>();
		try {
			c = DriverManager.getConnection(url, u, p);
			String sql = "SELECT 30-COUNT(dni_alumno), C.division FROM listas AS L JOIN cursos AS C ON L.id_curso=C.curso_id WHERE C.curso=? GROUP BY C.division";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, año);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int vacantes = rs.getInt(1);
				int division= rs.getInt(2);
				Vacante v=new Vacante(vacantes,division);
				Vacantes.add(v);
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
		return Vacantes;
	}

	
	
	
	
}
