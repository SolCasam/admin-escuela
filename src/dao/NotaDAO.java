package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import clases.Alumno;
import clases.Inasistencia;
import clases.Nota;

public class NotaDAO {

	String timezone = "&serverTimezone=UTC";
	String u = "root";
	String p = "";
	String url = "jdbc:mysql://localhost:3306/sistema_boletin?useSSL=false" + timezone;

	public HashMap<Integer,Integer> promedioxMateria(String alumno, int materia) {

		Connection c = null;
		HashMap<Integer, Integer> promedios=new HashMap<Integer, Integer>();
		try {
			c = DriverManager.getConnection(url, u, p);
			int trimestre = 1;
			while (trimestre < 4) {
				String sql = "SELECT SUM(nota_valor)/COUNT(nota_id) FROM `notas` WHERE dni_alumno=? AND id_materia=? AND nota_fecha BETWEEN (SELECT desde FROM fechas_trimestres WHERE num_trimestre=?) AND (SELECT hasta FROM fechas_trimestres WHERE num_trimestre=?)";
				PreparedStatement ps = c.prepareStatement(sql);
				ps.setString(1, alumno);
				ps.setInt(2, materia);
				ps.setInt(3, trimestre);
				ps.setInt(4, trimestre);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					int prom = Math.round(rs.getFloat(1));
					
					if (prom != 0) {
						promedios.put(trimestre, rs.getInt(1));
					}
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
		return promedios;
	}

	public ArrayList<Nota> notasPorAlumno(String dni) {
		ArrayList<Nota> Notas = new ArrayList<Nota>();
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, u, p);
			String sql = "SELECT `nota_fecha`,nota_valor,M.materia_nombre,TN.tiponota_nombre FROM notas AS N JOIN materias AS M ON N.id_materia=M.materia_id JOIN tipos_nota AS TN ON N.id_tipo_nota=TN.tiponota_id WHERE `dni_alumno`= ? ORDER BY M.materia_nombre AND N.nota_fecha";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				LocalDate fecha = rs.getDate("nota_fecha").toLocalDate();
				float valor = rs.getFloat("nota_valor");
				String tipo = rs.getString("tiponota_nombre");
				String nombre_materia = rs.getString("materia_nombre");

				Nota n = new Nota(nombre_materia, valor, tipo, fecha);
				Notas.add(n);
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
		return Notas;
	}

	public ArrayList<Nota> notasDeAlumnosPorMateria(int curso, int division, int materia) {
		ArrayList<Nota> Notas = new ArrayList<Nota>();
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, u, p);
			String sql = "SELECT U.usuario_dni,U.usuario_nombre,N.nota_valor,N.nota_fecha,TN.tiponota_nombre \r\n"
					+ "FROM notas AS N JOIN usuarios AS U ON N.dni_alumno=U.usuario_dni \r\n"
					+ "JOIN tipos_nota AS TN ON N.id_tipo_nota=TN.tiponota_id \r\n"
					+ "JOIN listas AS L ON U.usuario_dni=L.dni_alumno\r\n"
					+ "JOIN cursos AS C ON L.id_curso=C.curso_id\r\n"
					+ "WHERE C.curso=? AND C.division=? AND N.id_materia=? ORDER BY U.usuario_dni AND N.nota_fecha";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, curso);
			ps.setInt(2, division);
			ps.setInt(3, materia);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String dni = rs.getString(1);
				String nombre = rs.getString(2);
				float valor = rs.getFloat(3);
				LocalDate fecha = rs.getDate(4).toLocalDate();
				String tipoNota = rs.getString(5);

				Nota n = new Nota(dni, nombre, valor, fecha, tipoNota);
				Notas.add(n);
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
		return Notas;
	}

	public void IngresarNota(Nota n, Alumno a, int materia) {
		Connection conexion = null;
		try {
			TipoNotaDAO tnDAO = new TipoNotaDAO();
			conexion = DriverManager.getConnection(url, u, p);
			String sql = "INSERT INTO `notas` (`nota_valor`,`nota_fecha`,`id_tipo_nota`,id_materia,dni_alumno)"
					+ "VALUES(?,?,?,?,?)";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setFloat(1, n.getValor());
			ps.setDate(2, Date.valueOf(n.getFecha()));
			ps.setInt(3, tnDAO.recuperarIdTipoNota(n.getTipo()));
			ps.setInt(4, materia);
			ps.setString(5, a.getDni());

			int regAfect = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conexion != null)
					conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
