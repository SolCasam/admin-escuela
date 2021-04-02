package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import clases.Materia;
import clases.Usuario;

public class MateriaDAO {

	String timezone = "&serverTimezone=UTC";
	String u = "root";
	String p = "";
	String url = "jdbc:mysql://localhost:3306/sistema_boletin?useSSL=false"+timezone;
	
	
	public ArrayList<Materia> obtener_todos(){
		Connection conn = null;
		ArrayList<Materia> lista = new ArrayList<Materia>();
		
		try {		
			conn = DriverManager.getConnection(url,u,p);
			String sql = "SELECT materia_id, materia_nombre, dni_profesor, id_curso FROM materias";
			Statement stmt = conn.createStatement();	
			ResultSet rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				Materia mat = new Materia();
				mat.setId(rs.getInt(1));
				mat.setNombre(rs.getString(2));
				mat.setDni_profesor(rs.getInt(3));
				mat.setId_curso(rs.getInt(4));
				lista.add(mat);
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
	
	public Materia obtener_con_id (int id) {
		Connection conn = null;
		Materia mat = new Materia();
		try {
			conn = DriverManager.getConnection(url, u, p);
			String sql = "SELECT materia_id, materia_nombre, dni_profesor, id_curso FROM materias WHERE materia_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mat.setId(rs.getInt(1));
				mat.setNombre(rs.getString(2));
				mat.setDni_profesor(rs.getInt(3));
				mat.setId_curso(rs.getInt(4));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) {
					conn.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mat;
		
	}
	
	public void eliminar_con_id(int id) {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			String sql = "DELETE FROM materias WHERE materia_id=?";
			PreparedStatement pStmt = conexion.prepareStatement(sql);
			pStmt.setInt(1, id);
			
			int filasAfectadas = pStmt.executeUpdate();
		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conexion != null)
					conexion.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void ingresar_materia(Materia mat) {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url,u,p);
			String sql = "INSERT INTO materias (materia_id, materia_nombre, dni_profesor, id_curso) VALUES (?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, mat.getId());
			pStmt.setString(2, mat.getNombre());
			pStmt.setInt(3, mat.getDni_profesor());
			pStmt.setInt(4, mat.getId_curso());	
			
			pStmt.executeUpdate();
			
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
	}
	
	public void cambiar_con_id(Materia mat, int id) {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "UPDATE materias SET materia_nombre=?, dni_profesor=?, id_curso=? WHERE materia_id = ?";
			PreparedStatement pStmt = conexion.prepareStatement(sql);
			pStmt.setString(1, mat.getNombre());
			pStmt.setInt(2, mat.getDni_profesor());
			pStmt.setInt(3, mat.getId_curso());
			pStmt.setInt(4, id);
			
			int filasAfectadas = pStmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();			
		}finally {
			try {
				if(conexion != null)
					conexion.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	public ArrayList<Materia> obtener_con_nombre(String nombremateria){
		nombremateria.toUpperCase();
		ArrayList<Materia> lista = new ArrayList<Materia>();
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url, u, p);
			
			String sql = "SELECT materia_id, materia_nombre, dni_profesor , id_curso FROM materias WHERE UPPER(materia_nombre) LIKE ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, "%" + nombremateria +"%");
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				Materia mat = new Materia();
				mat.setId_curso(rs.getInt(1));
				mat.setNombre(rs.getString(2));
				mat.setDni_profesor(rs.getInt(3));
				mat.setId_curso(rs.getInt(4));
				
				lista.add(mat);
			}
			rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null)
					conn.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	
	public ArrayList<Materia> todasMateria(){
		ArrayList<Materia> Materias=new ArrayList<Materia>();
		Connection c = null;
		try {
			c = DriverManager.getConnection(url, u, p);
			String sql = "SELECT materia_nombre,C.curso,C.division,U.usuario_nombre FROM `materias` AS M JOIN cursos AS C ON M.id_curso=C.curso_id JOIN usuarios AS U ON M.dni_profesor=U.usuario_dni";
			PreparedStatement st = c.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				String nombre=rs.getString(1);
				int año=rs.getInt(2);
				int div=rs.getInt(3);
				String prof=rs.getString(4);
				Materia m=new Materia(nombre,año,div,prof);
				Materias.add(m);
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
		return Materias;
	}
	public String nombreMateria(int id) {
		String nombre=null;
		Connection c = null;
		try {
			c=DriverManager.getConnection(url,u,p);
			String sql="SELECT materia_nombre FROM materias WHERE materia_id=?";
			PreparedStatement ps=c.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {

				nombre=rs.getString(1);
			}
		}catch (SQLException e) {
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
		
		return nombre;
	}
	
	public ArrayList<Integer> materiasxalumno(String dni){
		ArrayList<Integer> materias=new ArrayList<Integer>();
		Connection c = null;
		try {
			c=DriverManager.getConnection(url,u,p);
			String sql="SELECT M.materia_id FROM materias AS M JOIN listas AS L ON M.id_curso=L.id_curso WHERE dni_alumno=?";
			PreparedStatement ps=c.prepareStatement(sql);
			ps.setString(1, dni);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				int mat=rs.getInt(1);
				materias.add(mat);
			}
		}catch (SQLException e) {
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
		return materias;
	}

	public int materiaxProfeYCurso(Usuario usuario,int idcurso) {
		Connection conexion = null;
		int idmateria=0;
		try { 
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "SELECT materia_id FROM materias WHERE id_curso=? AND dni_profesor=?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(2, usuario.getDni());
			ps.setInt(1, idcurso);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
			idmateria=rs.getInt(1);
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
		return idmateria;
	}
}
