package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clases.Alumno;
import clases.Escuela;
import clases.Profesor;
import clases.Usuario;

import java.sql.Statement;


public class UsuarioDAO {

	String timezone = "&serverTimezone=UTC";
	String u = "root";
	String p = "";
	String url = "jdbc:mysql://localhost:3306/sistema_boletin?useSSL=false"+timezone;
	
	public Usuario obtener_usuario_con_dni_contrasena(String dni, String pass) {
		Connection conexion = null;
		Usuario us = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);	
			String sql = "\r\n" + 
					"SELECT usuario_dni, usuario_nombre, usuario_contrasena, usuario_codigo_tipo FROM usuarios WHERE usuario_dni = ? AND usuario_contrasena = ?";
			PreparedStatement pStmt = conexion.prepareStatement(sql);
			pStmt.setString(1, dni);
			pStmt.setString(2, pass);
			ResultSet rs = pStmt.executeQuery();
					
			if(rs.next()){
				switch(rs.getInt(4)) {
				case 1:
					us = new Escuela();
					us.setDni(rs.getString(1));
					us.setNombre(rs.getString(2));
					us.setContrasena(rs.getString(3));
					us.setTipo_usuario(rs.getInt(4));
					break;
				case 2:
					us = new Profesor();
					us.setDni(rs.getString(1));
					us.setNombre(rs.getString(2));
					us.setContrasena(rs.getString(3));
					us.setTipo_usuario(rs.getInt(4));
					break;
				case 3:
					us = new Alumno();
					us.setDni(rs.getString(1));
					us.setNombre(rs.getString(2));
					us.setContrasena(rs.getString(3));
					us.setTipo_usuario(rs.getInt(4));
					break;
				}	
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
		return us;
		
	}
	
	
	public boolean guardar_enDB(Usuario us) {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(url,u,p);
			String sql = "INSERT INTO usuarios (`usuario_dni`, `usuario_nombre`, `usuario_contrasena`, `usuario_codigo_tipo`, domicilio, correo, telefono_casa, telefono_celular, cuil) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, us.getDni());
			pStmt.setString(2, us.getNombre());
			pStmt.setString(3, us.getContrasena());
			pStmt.setInt(4, us.getTipo_usuario());
			pStmt.setString(5, us.getDomicilio());
			pStmt.setString(6, us.getCorreo());
			pStmt.setString(7, us.getTelefono());
			pStmt.setString(8, us.getCelular());
			pStmt.setString(9, us.getCuit());
			
			pStmt.executeUpdate();
			
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
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
		
	public ArrayList<Usuario> obtener_todos(){
		Connection conn = null;
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		try {		
			conn = DriverManager.getConnection(url,u,p);
			String sql = "SELECT usuario_id, usuario_dni, usuario_nombre, usuario_contrasena, usuario_codigo_tipo, domicilio, correo, telefono_casa, telefono_celular, cuil FROM usuarios";
			Statement stmt = conn.createStatement();	
			ResultSet rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				Usuario us = null;
				switch(rs.getInt(5)) {
				case 1:
					us = new Escuela();
					us.setId(rs.getInt(1));
					us.setDni(rs.getString(2));
					us.setNombre(rs.getString(3));
					us.setContrasena(rs.getString(4));
					us.setTipo_usuario(rs.getInt(5));
					us.setDomicilio(rs.getString(6));
					us.setCorreo(rs.getString(7));
					us.setTelefono(String.valueOf(rs.getInt(8)));
					us.setCelular(String.valueOf(rs.getInt(9)));
					us.setCuit(String.valueOf(rs.getInt(10)));
					break;
				case 2:
					us = new Profesor();
					us.setId(rs.getInt(1));
					us.setDni(rs.getString(2));
					us.setNombre(rs.getString(3));
					us.setContrasena(rs.getString(4));
					us.setTipo_usuario(rs.getInt(5));
					us.setDomicilio(rs.getString(6));
					us.setCorreo(rs.getString(7));
					us.setTelefono(String.valueOf(rs.getInt(8)));
					us.setCelular(String.valueOf(rs.getInt(9)));
					us.setCuit(String.valueOf(rs.getInt(10)));
					break;
				case 3:
					us = new Alumno();
					us.setId(rs.getInt(1));
					us.setDni(rs.getString(2));
					us.setNombre(rs.getString(3));
					us.setContrasena(rs.getString(4));
					us.setTipo_usuario(rs.getInt(5));
					us.setDomicilio(rs.getString(6));
					us.setCorreo(rs.getString(7));
					us.setTelefono(String.valueOf(rs.getInt(8)));
					us.setCelular(String.valueOf(rs.getInt(9)));
					us.setCuit(String.valueOf(rs.getInt(10)));
					break;
				}
				lista.add(us);
				
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


	public ArrayList<Usuario> obtener_con_nombre(String nombre){

		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		
		nombre = nombre.toUpperCase();
		
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "SELECT usuario_id, usuario_dni, usuario_nombre, usuario_contrasena, usuario_codigo_tipo, domicilio, correo, telefono_casa, telefono_celular, cuil FROM usuarios WHERE UPPER(usuario_nombre) LIKE ?";
			
			PreparedStatement pStmt = conexion.prepareStatement(sql);
			pStmt.setString(1, "%" + nombre +"%");
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()){
				Usuario us = null;
				switch(rs.getInt("usuario_codigo_tipo")) {
				case 1:
					us = new Escuela();
					us.setId(rs.getInt(1));
					us.setDni(rs.getString(2));
					us.setNombre(rs.getString(3));
					us.setContrasena(rs.getString(4));
					us.setTipo_usuario(rs.getInt(5));
					us.setDomicilio(rs.getString(6));
					us.setCorreo(rs.getString(7));
					us.setTelefono(String.valueOf(rs.getInt(8)));
					us.setCelular(String.valueOf(rs.getInt(9)));
					us.setCuit(String.valueOf(rs.getInt(10)));
					break;
				case 2:
					us = new Profesor();
					us.setId(rs.getInt(1));
					us.setDni(rs.getString(2));
					us.setNombre(rs.getString(3));
					us.setContrasena(rs.getString(4));
					us.setTipo_usuario(rs.getInt(5));
					us.setDomicilio(rs.getString(6));
					us.setCorreo(rs.getString(7));
					us.setTelefono(String.valueOf(rs.getInt(8)));
					us.setCelular(String.valueOf(rs.getInt(9)));
					us.setCuit(String.valueOf(rs.getInt(10)));
					break;
				case 3:
					us = new Alumno();
					us.setId(rs.getInt(1));
					us.setDni(rs.getString(2));
					us.setNombre(rs.getString(3));
					us.setContrasena(rs.getString(4));
					us.setTipo_usuario(rs.getInt(5));
					us.setDomicilio(rs.getString(6));
					us.setCorreo(rs.getString(7));
					us.setTelefono(String.valueOf(rs.getInt(8)));
					us.setCelular(String.valueOf(rs.getInt(9)));
					us.setCuit(String.valueOf(rs.getInt(10)));
					break;
				}	
				
				lista.add(us);
				
			}
			rs.close();
			
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
		return lista;
	}

	public Usuario obtener_usuario_con_dni(String dni) {
		Connection conexion = null;
		Usuario us = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);	
			String sql = "SELECT usuario_id, usuario_dni, usuario_nombre, usuario_contrasena, usuario_codigo_tipo, domicilio, correo, telefono_casa, telefono_celular, cuil FROM usuarios WHERE usuario_dni = ?";
			PreparedStatement pStmt = conexion.prepareStatement(sql);
			pStmt.setString(1, dni);
			ResultSet rs = pStmt.executeQuery();
					
			if(rs.next()){
				switch(rs.getInt("usuario_codigo_tipo")) {
				case 1:
					us = new Escuela();
					us.setId(rs.getInt(1));
					us.setDni(rs.getString(2));
					us.setNombre(rs.getString(3));
					us.setContrasena(rs.getString(4));
					us.setTipo_usuario(rs.getInt(5));
					us.setDomicilio(rs.getString(6));
					us.setCorreo(rs.getString(7));
					us.setTelefono(String.valueOf(rs.getInt(8)));
					us.setCelular(String.valueOf(rs.getInt(9)));
					us.setCuit(String.valueOf(rs.getInt(10)));
					break;
				case 2:
					us = new Profesor();
					us.setId(rs.getInt(1));
					us.setDni(rs.getString(2));
					us.setNombre(rs.getString(3));
					us.setContrasena(rs.getString(4));
					us.setTipo_usuario(rs.getInt(5));
					us.setDomicilio(rs.getString(6));
					us.setCorreo(rs.getString(7));
					us.setTelefono(String.valueOf(rs.getInt(8)));
					us.setCelular(String.valueOf(rs.getInt(9)));
					us.setCuit(String.valueOf(rs.getInt(10)));
					break;
				case 3:
					us = new Alumno();
					us.setId(rs.getInt(1));
					us.setDni(rs.getString(2));
					us.setNombre(rs.getString(3));
					us.setContrasena(rs.getString(4));
					us.setTipo_usuario(rs.getInt(5));
					us.setDomicilio(rs.getString(6));
					us.setCorreo(rs.getString(7));
					us.setTelefono(String.valueOf(rs.getInt(8)));
					us.setCelular(String.valueOf(rs.getInt(9)));
					us.setCuit(String.valueOf(rs.getInt(10)));
					break;
				}	
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
		return us;
		
	}

	public void eliminar_con_dni(String dni) {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			String sql = "DELETE FROM usuarios WHERE usuario_dni=?";
			PreparedStatement pStmt = conexion.prepareStatement(sql);
			pStmt.setString(1, dni);
			
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
	
	public ArrayList<Profesor> obtener_todos_profesores(){
		ArrayList<Profesor> lista = new ArrayList<Profesor>();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, u, p);	
			String sql = "SELECT usuario_dni, usuario_nombre FROM usuarios WHERE usuario_codigo_tipo = 2";
			Statement stmt = conn.createStatement();	
			ResultSet rs = stmt.executeQuery(sql);
					
			while(rs.next()){
				Profesor prof = new Profesor();
				prof.setDni(rs.getString(1));
				prof.setNombre(rs.getString(2));				
				lista.add(prof);
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

	public void cambiar_con_id(int id, Usuario us) {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(url, u, p);
			
			String sql = "UPDATE usuarios SET usuario_dni=?, usuario_nombre=?, usuario_contrasena=?, usuario_codigo_tipo=?, domicilio=?, correo=?, telefono_casa=?, telefono_celular=?, cuil=? WHERE usuario_id = ?";
			PreparedStatement pStmt = conexion.prepareStatement(sql);
			pStmt.setString(1, us.getDni());
			pStmt.setString(2, us.getNombre());
			pStmt.setString(3, us.getContrasena());
			pStmt.setInt(4, us.getTipo_usuario());
			pStmt.setString(5, us.getDomicilio());
			pStmt.setString(6, us.getCorreo());
			pStmt.setString(7, us.getTelefono());
			pStmt.setString(8, us.getCelular());
			pStmt.setString(9, us.getCuit());
			pStmt.setInt(10, id);
			
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
}
	

