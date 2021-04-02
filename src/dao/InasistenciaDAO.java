package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import clases.Inasistencia;
import clases.Nota;

public class InasistenciaDAO {

	String timezone = "&serverTimezone=UTC";
	String u = "root";
	String p = "";
	String url = "jdbc:mysql://localhost:3306/sistema_boletin?useSSL=false"+timezone;
	
	
	public ArrayList<Inasistencia> consulta(String dni) {
		ArrayList<Inasistencia> Inasistencias = new ArrayList<Inasistencia>();
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, u, p);
			String sql = "SELECT `inasistencia_justificada`,`inasistencia_valor`,`inasistencia_fecha` FROM inasistencias WHERE `inasistencia_dni_alumno`= ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LocalDate fecha = rs.getDate(3).toLocalDate();
				float valor = rs.getFloat(2);
				boolean just = rs.getBoolean(1);
				Inasistencia i = new Inasistencia(fecha, valor, just);
				Inasistencias.add(i);
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
		return Inasistencias;
	}

	public void insert(Inasistencia i, String dni) {
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, u, p);

			String sql = "INSERT INTO `inasistencias`(`inasistencia_fecha`,`inasistencia_valor`,`inasistencia_justificada`,`inasistencia_dni_alumno`)VALUES( ?, ?, ?, ?)";
			PreparedStatement preparedStatement = c.prepareStatement(sql);
			preparedStatement.setDate(1, Date.valueOf(i.getFecha()));
			preparedStatement.setFloat(2, i.getTipo());
			preparedStatement.setBoolean(3, i.getEsJustificada());
			preparedStatement.setString(4, dni);

			int cantRegAfectados = preparedStatement.executeUpdate();

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
	}

	public ArrayList<Integer> totalInasistenciasxTrimestre(String alumno) {

		Connection c = null;
		ArrayList<Integer> inasistencias = new ArrayList<Integer>();
		try {
			c = DriverManager.getConnection(url, u, p);
			int trimestre = 1;
			while (trimestre < 4) {
				String sql = "SELECT SUM(inasistencia_valor) FROM inasistencias WHERE inasistencia_dni_alumno=? AND inasistencia_justificada=false AND inasistencia_fecha BETWEEN (SELECT desde FROM fechas_trimestres WHERE num_trimestre=?) AND (SELECT hasta FROM fechas_trimestres WHERE num_trimestre=?)";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, alumno);
				ps.setInt(2, trimestre);
				ps.setInt(3, trimestre);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					int sumInas = rs.getInt(1);
					inasistencias.add(sumInas);
				}
				trimestre++;
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
		return inasistencias;

	}

}
