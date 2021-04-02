package vistas;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Alumno;
import clases.Escuela;
import clases.Profesor;
import clases.Usuario;
import clases.Verificacion;
import dao.UsuarioDAO;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ABMUsuarios extends JPanel {

	JFrame marco = new JFrame();
	private JTable table;
	private DefaultTableModel modeloTabla;
	private JTextField txtFieldBusqueda;
	private String nombre = "";
	private JTextField txtF_dni;
	private JTextField txtF_nombre;
	private JTextField txtF_contra;
	int id;
	boolean modif;
	private JTextField txtF_domicilio;
	private JTextField txtF_correo;
	private JTextField txtf_telefono;
	private JTextField txtf_celular;
	private JTextField txtf_cuit;

	public ABMUsuarios() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(300, 39, 372, 297);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		modeloTabla = new DefaultTableModel(new Object[] {"DNI", "NOMBRE", "TIPO", "CORREO", "DOMICILIO", "TEL\u00C9FONO", "CELULAR", "CUIT"}, 0);
		table.setModel(modeloTabla);
		
		llenarTablaConTodos();
		
		modif = false;
	
		txtFieldBusqueda = new JTextField();
		txtFieldBusqueda.setBounds(300, 8, 296, 20);
		txtFieldBusqueda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				modeloTabla.setRowCount(0);
				UsuarioDAO uDAO = new UsuarioDAO();
				
				if(e.getKeyChar() == '\b')
			    {  
					modeloTabla.setRowCount(0);
					if(nombre.length()!=0)
						nombre = nombre.substring(0, nombre.length() - 1);
			    	for (Usuario us : uDAO.obtener_con_nombre(nombre)) {		
						modeloTabla.addRow(new Object[] {us.getDni(), us.getNombre(), obtener_tipo(us)});
					}
			    }
			    else{
			    modeloTabla.setRowCount(0);
				nombre = nombre + Character.toString(e.getKeyChar());
				for (Usuario us : uDAO.obtener_con_nombre(nombre)) {		
					modeloTabla.addRow(new Object[] {us.getDni(), us.getNombre(), obtener_tipo(us)});
				}
			    }
				
			}
			
		});
		add(txtFieldBusqueda);
		txtFieldBusqueda.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(24, 40, 46, 14);
		add(lblDni);
		
		txtF_dni = new JTextField();
		txtF_dni.setBounds(126, 37, 164, 20);
		add(txtF_dni);
		txtF_dni.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(24, 69, 73, 14);
		add(lblNombre);
		
		txtF_nombre = new JTextField();
		txtF_nombre.setBounds(126, 66, 164, 20);
		add(txtF_nombre);
		txtF_nombre.setColumns(10);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setBounds(24, 100, 119, 14);
		add(lblContrasea);
		
		txtF_contra = new JTextField();
		txtF_contra.setBounds(126, 97, 164, 20);
		add(txtF_contra);
		txtF_contra.setColumns(10);
		
		JLabel lblTipoDeUsuario = new JLabel("Tipo de usuario:");
		lblTipoDeUsuario.setBounds(24, 128, 119, 14);
		add(lblTipoDeUsuario);
		
		
		JComboBox comboBox_tipoUsuario = new JComboBox();
		comboBox_tipoUsuario.setBounds(126, 125, 164, 20);
		add(comboBox_tipoUsuario);
		comboBox_tipoUsuario.addItem("Escuela");
		comboBox_tipoUsuario.addItem("Profesor");
		comboBox_tipoUsuario.addItem("Alumno");
		
		JButton btn_Alta = new JButton("Dar de alta");
		btn_Alta.setBounds(24, 305, 164, 31);
		btn_Alta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Verificacion v=new Verificacion();
				if(v.dniValido(txtF_dni.getText())) {
					if(v.telValido(txtf_telefono.getText())&&v.telValido(txtf_celular.getText())) {
						if(modif) {
							Usuario us = devolver_usuario_del_tipo_correspondiente(comboBox_tipoUsuario.getSelectedIndex()+1);
							us.setNombre(txtF_nombre.getText());
							us.setDni(txtF_dni.getText());
							us.setContrasena(txtF_contra.getText());
							us.setTipo_usuario(comboBox_tipoUsuario.getSelectedIndex()+1);
							us.setDomicilio(txtF_domicilio.getText());
							us.setCorreo(txtF_correo.getText());
							us.setTelefono((txtf_telefono.getText()));
							us.setCelular((txtf_celular.getText()));
							us.setCuit((txtf_cuit.getText()));
							UsuarioDAO udao = new UsuarioDAO();
							udao.cambiar_con_id(id, us);
							modeloTabla.setRowCount(0);
							llenarTablaConTodos();
						}
						else {
							Usuario us = devolver_usuario_del_tipo_correspondiente(comboBox_tipoUsuario.getSelectedIndex()+1);
							us.setNombre(txtF_nombre.getText());
							us.setDni(txtF_dni.getText());
							us.setContrasena(txtF_contra.getText());
							us.setTipo_usuario(comboBox_tipoUsuario.getSelectedIndex()+1);
							us.setDomicilio(txtF_domicilio.getText());
							us.setCorreo(txtF_correo.getText());
							us.setTelefono((txtf_telefono.getText()));
							us.setCelular((txtf_celular.getText()));
							us.setCuit((txtf_cuit.getText()));
							UsuarioDAO udao = new UsuarioDAO();
							udao.guardar_enDB(us);
							modeloTabla.setRowCount(0);
							llenarTablaConTodos();
							
						}
					}else {
						JOptionPane.showMessageDialog(null, "ERROR: Telefono no valido");
					}
				}else {
					JOptionPane.showMessageDialog(null, "ERROR: DNI no valido");
				}
				
			}
		});
		add(btn_Alta);

		JButton btnModif = new JButton("Cambiar");
		btnModif.setBounds(300, 347, 143, 31);
		btnModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = table.getSelectedRow();
				String dni = (String) modeloTabla.getValueAt(fila, 0);
				UsuarioDAO uDAO = new UsuarioDAO();
				Usuario u = uDAO.obtener_usuario_con_dni(dni);
				txtF_nombre.setText(u.getNombre());
				txtF_contra.setText(u.getContrasena());
				txtF_dni.setText(u.getDni());
				txtF_domicilio.setText(u.getDomicilio());
				txtF_correo.setText(u.getCorreo());
				txtf_telefono.setText(String.valueOf(u.getTelefono()));
				txtf_celular.setText(String.valueOf(u.getCelular()));
				txtf_cuit.setText(String.valueOf(u.getCuit()));
				switch(u.getTipo_usuario()) {
				case 1:
					comboBox_tipoUsuario.setSelectedIndex(0);
					break;
				case 2:
					comboBox_tipoUsuario.setSelectedIndex(1);
					break;
				case 3:
					comboBox_tipoUsuario.setSelectedIndex(2);
					break;				
				}
				btn_Alta.setText("Cambiar");
				id = u.getId();
				modif=true;
			}
		});
		add(btnModif);
		
		
		//botón cancelar
		JButton btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.setBounds(24, 345, 86, 31);
		btn_Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtF_dni.setText("");
				txtF_nombre.setText("");
				txtF_contra.setText("");
				txtF_domicilio.setText("");
				txtF_correo.setText("");
				txtf_telefono.setText("");
				txtf_celular.setText("");
				txtf_cuit.setText("");
				txtFieldBusqueda.setText("");
				nombre="";
				id=0;
				btn_Alta.setText("Dar de alta");
			}
		});
		add(btn_Cancelar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(453, 347, 143, 31);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = table.getSelectedRow();
				String dni = (String) modeloTabla.getValueAt(fila, 0);
				UsuarioDAO udao = new UsuarioDAO();
				udao.eliminar_con_dni(dni);
				modeloTabla.setRowCount(0);
				llenarTablaConTodos();
			}
		});
		add(btnEliminar);
		
		JButton button = new JButton("<--");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuEscuela menuescuela = new MenuEscuela();
				menuescuela.setMarco(marco);
				
				marco.setContentPane(menuescuela);
				marco.validate();
			}
		});
		button.setBounds(0, 0, 67, 23);
		add(button);
		
		JLabel lblDomicilio = new JLabel("Domicilio:");
		lblDomicilio.setBounds(24, 156, 119, 14);
		add(lblDomicilio);
		
		txtF_domicilio = new JTextField();
		txtF_domicilio.setColumns(10);
		txtF_domicilio.setBounds(126, 153, 164, 20);
		add(txtF_domicilio);
		
		JLabel lbl_correo = new JLabel("Correo: ");
		lbl_correo.setBounds(24, 184, 119, 14);
		add(lbl_correo);
		
		txtF_correo = new JTextField();
		txtF_correo.setColumns(10);
		txtF_correo.setBounds(126, 181, 164, 20);
		add(txtF_correo);
		
		JLabel lbl_telefonocasa = new JLabel("Tel\u00E9fono:");
		lbl_telefonocasa.setBounds(24, 212, 119, 14);
		add(lbl_telefonocasa);
		
		txtf_telefono = new JTextField();
		txtf_telefono.setColumns(10);
		txtf_telefono.setBounds(126, 209, 164, 20);
		add(txtf_telefono);
		
		JLabel lbl_celular = new JLabel("Celular:");
		lbl_celular.setBounds(24, 240, 119, 14);
		add(lbl_celular);
		
		txtf_celular = new JTextField();
		txtf_celular.setColumns(10);
		txtf_celular.setBounds(126, 237, 164, 20);
		add(txtf_celular);
		
		JLabel lbl_cuit = new JLabel("CUIT: ");
		lbl_cuit.setBounds(24, 268, 119, 14);
		add(lbl_cuit);
		
		txtf_cuit = new JTextField();
		txtf_cuit.setColumns(10);
		txtf_cuit.setBounds(126, 265, 164, 20);
		add(txtf_cuit);
		
	 
		
		
		
	}
	
	
	
	
	
	
	//sets y gets
	
	public void setMarco(JFrame marco) {
		this.marco = marco;
	}
		
	
	//methods random
	
	public void llenarTablaConTodos() {
		UsuarioDAO ldao = new UsuarioDAO();
		for (Usuario us : ldao.obtener_todos()) {
			modeloTabla.addRow(new Object[] {us.getDni(), us.getNombre(), obtener_tipo(us), us.getCorreo(), us.getDomicilio(), us.getTelefono(), us.getCelular(), us.getCuit()});
		}
	}
	
	public String obtener_tipo(Usuario us) {
		String tipo = "";
		switch (us.getTipo_usuario()) {
		case 1:
			tipo = "Escuela";
			break;
		case 2:
			tipo = "Profesor";
			break;
		case 3:
			tipo = "Alumno";
			break;
		}
		return tipo;
	}
	
	public Usuario devolver_usuario_del_tipo_correspondiente(int i) {
		Usuario us = null;
		switch (i) {
		case 1:
			us = new Escuela();
			break;
		case 2:
			us = new Profesor();
		case 3:
			us = new Alumno();
		}
		return us;
	}
}
