package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipoNotaDAO {
	
	String timezone = "&serverTimezone=UTC";
	String u = "root";
	String p = "";
	String url = "jdbc:mysql://localhost:3306/sistema_boletin?useSSL=false"+timezone;
	

	public ArrayList<String> todos() {
		ArrayList<String> todos = new ArrayList<String>();
		ResultSet rs = null;
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);

			String sql = "SELECT tiponota_id, tiponota_nombre FROM tipos_nota";
			PreparedStatement ps = conexion.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				todos.add(rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.toString());

		} finally {
			try {
				if (conexion != null)
					conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return todos;
	}
	
	public int recuperarIdTipoNota(String tiponota) {
		Connection conexion = null;
		int idtiponota = 0;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			String sql = "SELECT `tiponota_id` FROM `tipos_nota` WHERE `tiponota_nombre` = ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, tiponota);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				idtiponota = rs.getInt(1);
			}

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
		return idtiponota;
	}
	
}
