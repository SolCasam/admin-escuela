package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.UsuarioDAO;
import clases.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;

public class Inicio extends JPanel {

	private JFrame marco;
	private JTextField txtfdni;
	private JTextField txtpass;

	public Inicio() {
		setLayout(null);
		
		txtfdni = new JTextField();
		txtfdni.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtfdni.setBounds(177, 170, 368, 29);
		add(txtfdni);
		txtfdni.setColumns(10);
		
		txtpass = new JTextField();
		txtpass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpass.setBounds(177, 243, 368, 29);
		add(txtpass);
		txtpass.setColumns(10);
		
		JButton btningresar = new JButton("Ingresar");
		btningresar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btningresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsuarioDAO udao = new UsuarioDAO();
				
				Usuario usuario = udao.obtener_usuario_con_dni_contrasena(txtfdni.getText(), txtpass.getText());
				if(usuario!=null) {
				switch(usuario.getTipo_usuario()) {
				case 1:
					
					MenuEscuela mEscuela = new MenuEscuela();
					mEscuela.setMarco(marco);
					
					marco.setContentPane(mEscuela);
					marco.validate();
					
					break;
				case 2:

					MenuProfesor mProfesor = new MenuProfesor(usuario);
					mProfesor.setMarco(marco);
					
					marco.setContentPane(mProfesor);
					marco.validate();
					break;
				case 3:

					MenuAlumno mAlumno = new MenuAlumno(usuario);
					mAlumno.setMarco(marco);
					
					marco.setContentPane(mAlumno);
					marco.validate();
					break;
				}
			}else {
				JOptionPane.showMessageDialog(null, "ERROR: Usuario o contraseña no validos");
			}
				}
		});
		btningresar.setBounds(177, 301, 146, 46);
		add(btningresar);
		
		JButton btnsalir = new JButton("Salir");
		btnsalir.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnsalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnsalir.setBounds(399, 301, 146, 46);
		add(btnsalir);

			
			
		
		JLabel lblUsuariodni = new JLabel("DNI:");
		lblUsuariodni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsuariodni.setBounds(177, 139, 368, 20);
		add(lblUsuariodni);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasea.setBounds(177, 212, 274, 20);
		add(lblContrasea);
		
		JLabel lblSistemaBoletnHogar = new JLabel("Iniciar Sesi\u00F3n");
		lblSistemaBoletnHogar.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblSistemaBoletnHogar.setBounds(10, 11, 680, 29);
		add(lblSistemaBoletnHogar);
		
	}



	public void setMarco(JFrame marco) {
		this.marco = marco;
	}
}
