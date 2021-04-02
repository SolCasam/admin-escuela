package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import clases.Trimestre;
import clases.Vacante;

public class FechaDAO {


	String timezone = "&serverTimezone=UTC";
	String u = "root";
	String p = "";
	String url = "jdbc:mysql://localhost:3306/sistema_boletin?useSSL=false"+timezone;
	
	public ArrayList<Trimestre> fechasTrimestre() {
		Connection c = null;
		ArrayList<Trimestre> trimestres=new ArrayList<Trimestre>();
		try {
			c = DriverManager.getConnection(url, u, p);
			int trim=1;
			while(trim<4) {
			String sql = "SELECT desde,hasta,num_trimestre FROM `fechas_trimestres` WHERE num_trimestre=?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, trim);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Trimestre t=new Trimestre(rs.getInt(3),rs.getDate(1).toLocalDate(),rs.getDate(2).toLocalDate());
				trimestres.add(t);
			}
			trim++;
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
		return trimestres;
	}
	
	public void cambioFechasTrimestre(ArrayList<Trimestre> trimestres) {
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, u, p);
			borrarFechasTrimestre(trimestres);
			for(Trimestre t:trimestres) {
			String sql = "INSERT INTO fechas_trimestres(num_trimestre,desde,hasta) VALUES(?,?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, t.getNum());
			ps.setDate(2, Date.valueOf(t.getDesde()));
			ps.setDate(3, Date.valueOf(t.getHasta()));
			int regAfectados= ps.executeUpdate();
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
	}
	public void borrarFechasTrimestre(ArrayList<Trimestre> trimestres) {
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, u, p);
			for(Trimestre t:trimestres) {
			
			String sql = "DELETE FROM fechas_trimestres WHERE num_trimestre=?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, t.getNum());
			int regAfectados= ps.executeUpdate();
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
	}
	
}
