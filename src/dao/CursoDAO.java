package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Curso;
import clases.Usuario;

public class CursoDAO {
	String timezone = "&serverTimezone=UTC";
	String u = "root";
	String p = "";
	String url = "jdbc:mysql://localhost:3306/sistema_boletin?useSSL=false"+timezone;
	
	public Curso obtener_curso_conid(int id) {
		Connection conexion = null;
		Curso cur = new Curso();
		try {
			conexion = DriverManager.getConnection(url, u, p);	
			String sql = "SELECT curso_id, curso, division FROM cursos WHERE curso_id = ?";
			PreparedStatement pStmt = conexion.prepareStatement(sql);
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
					
			if(rs.next()){
				cur.setId(rs.getInt(1));
				cur.setCurso(rs.getInt(2));
				cur.setDivision(rs.getInt(3));
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conexion != null) {
					conexion.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cur;
	}

	public Curso obtener_con_c_y_d (int c, int d) {
		Connection conexion = null;
		Curso cur = new Curso();
		try {
			conexion = DriverManager.getConnection(url, u, p);	
			String sql = "SELECT curso_id, curso, division FROM cursos WHERE curso = ? AND division = ?";
			PreparedStatement pStmt = conexion.prepareStatement(sql);
			pStmt.setInt(1, c);
			pStmt.setInt(2, d);
			ResultSet rs = pStmt.executeQuery();
					
			if(rs.next()){
				cur.setId(rs.getInt(1));
				cur.setCurso(rs.getInt(2));
				cur.setDivision(rs.getInt(3));
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conexion != null) {
					conexion.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cur;
	}
	
	public ArrayList<Curso> obtener_todos_cursos(){
		Connection conn = null;
		ArrayList<Curso> lista = new ArrayList<Curso>();
		
		try {		
			conn = DriverManager.getConnection(url,u,p);
			String sql = "SELECT curso, division FROM cursos";
			Statement stmt = conn.createStatement();	
			ResultSet rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				Curso cur = new Curso();
				cur.setCurso(rs.getInt(1));
				cur.setDivision(rs.getInt(2));
				lista.add(cur);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(conn != null)
					conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}			
		return lista;
	}
	
	public ArrayList<String> cursos() {
		ArrayList<String> todos = new ArrayList<String>();
		Connection conexion = null;
		try { 
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "SELECT curso,division FROM `cursos` ORDER BY curso,division";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
			String curso=rs.getString(1)+"° "+rs.getString(2)+"°";
			todos.add(curso);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.toString());

		}finally {
			try {
				if(conexion != null)
					conexion.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return todos;
	}

	public int idCursoxAñoyDivision(int año,int div) {
		Connection conexion = null;
		int id=0;
		try { 
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "SELECT curso_id FROM `cursos` WHERE curso=? AND division=?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setInt(1, año);
			ps.setInt(2, div);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				id=rs.getInt(1);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.toString());

		}finally {
			try {
				if(conexion != null)
					conexion.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}
	public ArrayList<String> cursosxProfesor(Usuario usuario) {
		ArrayList<String> todos = new ArrayList<String>();
		Connection conexion = null;
		try { 
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "SELECT curso,division FROM `cursos`AS C JOIN materias AS M ON M.id_curso=C.curso_id WHERE M.dni_profesor=? ORDER BY curso,division";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, usuario.getDni());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
			String curso=rs.getString(1)+"° "+rs.getString(2)+"°";
			todos.add(curso);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.toString());

		}finally {
			try {
				if(conexion != null)
					conexion.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return todos;
	}
	public ArrayList<String> años() {
		ArrayList<String> todos = new ArrayList<String>();
		Connection conexion = null;
		try { 
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "SELECT DISTINCT curso FROM `cursos` ORDER BY curso";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
			String año=rs.getString(1)+"°";
			todos.add(año);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.toString());

		}finally {
			try {
				if(conexion != null)
					conexion.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return todos;
	}
	public ArrayList<Integer> divisionxMatyProf(String dniProf) {
		ArrayList<Integer> todos = new ArrayList<Integer>();
		Connection conexion = null;
		try { 
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "SELECT division FROM `cursos` AS C JOIN materias AS M ON M.id_curso= C.curso_id WHERE M.dni_profesor=?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, dniProf);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int div=rs.getInt(1);
				todos.add(div);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.toString());

		}finally {
			try {
				if(conexion != null)
					conexion.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return todos;
	}
	
}
