package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Curso;
import clases.Materia;
import clases.Usuario;
import dao.CursoDAO;
import dao.MateriaDAO;
import dao.UsuarioDAO;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ABMMaterias extends JPanel {
	JFrame marco = new JFrame();
	public void setMarco(JFrame marco) {
		this.marco = marco;
	}

	private JTable tablaMaterias;
	private DefaultTableModel modeloTabla;
	private JTextField txtF_buscarmaterias;
	private JTextField textfieldnombre;
	
	private boolean modif=false;
	private int id;
	private String nombremateria="";
	
	public ABMMaterias() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(335, 36, 270, 285);
		add(scrollPane);
		
		tablaMaterias = new JTable();
		scrollPane.setViewportView(tablaMaterias);
		
		modeloTabla = new DefaultTableModel(new Object[] {"ID", "NOMBRE", "PROFESOR", "CURSO"}, 0);
		tablaMaterias.setModel(modeloTabla);
		
		txtF_buscarmaterias = new JTextField();
		txtF_buscarmaterias.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				modeloTabla.setRowCount(0);
				MateriaDAO matdao = new MateriaDAO();
				
				
				if(e.getKeyChar()=='\b') {
					modeloTabla.setRowCount(0);
					
					if(nombremateria.length()!=0) 
						nombremateria = nombremateria.substring(0, nombremateria.length() - 1);
					for (Materia mat : matdao.obtener_con_nombre(nombremateria)) {
						modeloTabla.addRow(new Object[] {mat.getId() , mat.getNombre(), obtenerNombreProf(mat), obtenerCurso(mat)});
					}
				}
				else {
					modeloTabla.setRowCount(0);
					nombremateria = nombremateria + Character.toString(e.getKeyChar());
					for (Materia mat : matdao.obtener_con_nombre(nombremateria)) {
						modeloTabla.addRow(new Object[] {mat.getId() ,mat.getNombre(), obtenerNombreProf(mat), obtenerCurso(mat)});
					}
				}
			}
		});

		txtF_buscarmaterias.setBounds(335, 11, 270, 20);
		add(txtF_buscarmaterias);
		txtF_buscarmaterias.setColumns(10);
		
		JButton botonatras = new JButton("<--");
		botonatras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MenuEscuela menu = new MenuEscuela();
				menu.setMarco(marco);
				marco.setContentPane(menu);
				marco.validate();
			}
		});
		botonatras.setBounds(0, 0, 58, 23);
		add(botonatras);
		
		JLabel lblNombreDeLa = new JLabel("Nombre de la materia:");
		lblNombreDeLa.setBounds(20, 42, 188, 14);
		add(lblNombreDeLa);
		
		textfieldnombre = new JTextField();
		textfieldnombre.setBounds(20, 67, 137, 20);
		add(textfieldnombre);
		textfieldnombre.setColumns(10);
		
		JLabel lblProfesor = new JLabel("Profesor:");
		lblProfesor.setBounds(20, 98, 137, 14);
		add(lblProfesor);
		
		JComboBox comboBoxprofesores = new JComboBox();
		comboBoxprofesores.setBounds(20, 123, 137, 20);
		add(comboBoxprofesores);
		
		//llenar combobox profes
		UsuarioDAO udao = new UsuarioDAO();
		for (Usuario us : udao.obtener_todos_profesores()) {
			String item = us.getNombre() + " " + us.getDni();
			comboBoxprofesores.addItem(item);
		}

		
		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setBounds(20, 154, 137, 14);
		add(lblCurso);
		
		JComboBox comboBoxcursos = new JComboBox();
		comboBoxcursos.setBounds(20, 179, 137, 20);
		add(comboBoxcursos);
		
		//llenar combobox cursos
		CursoDAO cdao = new CursoDAO();
		for (Curso cur : cdao.obtener_todos_cursos()) {
			String item = Integer.toString(cur.getCurso()) + "° "+Integer.toString(cur.getDivision())+"°";
			comboBoxcursos.addItem(item);
		}
		
		JButton btnDarDeAlta = new JButton("Dar de alta");
		btnDarDeAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(modif) {
					MateriaDAO mdao = new MateriaDAO();
					Materia materia = new Materia();
					materia.setNombre(textfieldnombre.getText());
					
					String profesor = (String) comboBoxprofesores.getSelectedItem();
					String dni="";
					for (char c : profesor.toCharArray()) {
						if(Character.isDigit(c)) 
							dni=dni+c;
					}
					materia.setDni_profesor(Integer.parseInt(dni));
					
					String curso = (String) comboBoxcursos.getSelectedItem();
					String curso1 = "";
					for (char c : curso.toCharArray()) {
						if(Character.isDigit(c))
							curso1 = curso1+c;
					}
					int c= Integer.parseInt(curso1.substring(0,1));
					int d= Integer.parseInt(curso1.substring(curso1.length()-1));
					CursoDAO cdao = new CursoDAO(); Curso cur = cdao.obtener_con_c_y_d(c, d);
					
					materia.setId_curso(cur.getId());
					
					System.out.println(id);
					int fila = tablaMaterias.getSelectedRow();
					int id = (int) modeloTabla.getValueAt(fila, 0);
					System.out.println(materia.getNombre());
					mdao.cambiar_con_id(materia, id);
					modeloTabla.setRowCount(0);
					llenarTablaConTodos();
				
				}
				else {
						MateriaDAO mdao = new MateriaDAO();
						Materia materia = new Materia();
						materia.setNombre(textfieldnombre.getText());
						
						String profesor = (String) comboBoxprofesores.getSelectedItem();
						String dni="";
						for (char c : profesor.toCharArray()) {
							if(Character.isDigit(c)) 
								dni=dni+c;
						}
						materia.setDni_profesor(Integer.parseInt(dni));
						
						String curso = (String) comboBoxcursos.getSelectedItem();
						String curso1 = "";
						for (char c : curso.toCharArray()) {
							if(Character.isDigit(c))
								curso1 = curso1+c;
						}
						int c= Integer.parseInt(curso1.substring(0,1));
						int d= Integer.parseInt(curso1.substring(curso1.length()-1));
						CursoDAO cdao = new CursoDAO(); Curso cur = cdao.obtener_con_c_y_d(c, d);
						
						materia.setId_curso(cur.getId());
						
						mdao.ingresar_materia(materia);
						modeloTabla.setRowCount(0);
						llenarTablaConTodos();
					}
				}
		});
		btnDarDeAlta.setBounds(20, 218, 137, 30);
		add(btnDarDeAlta);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int fila = tablaMaterias.getSelectedRow();
				int id = (int) tablaMaterias.getValueAt(fila, 0);
				MateriaDAO matdao = new MateriaDAO();
				matdao.eliminar_con_id(id);
				modeloTabla.setRowCount(0);
				llenarTablaConTodos();
			}
		});
		btnEliminar.setBounds(481, 332, 124, 36);
		add(btnEliminar);
		
		JButton botonmodif = new JButton("Modificar");
		botonmodif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila = tablaMaterias.getSelectedRow();
				int id = (int) modeloTabla.getValueAt(fila, 0);
				MateriaDAO mdao = new MateriaDAO();
				Materia mat = mdao.obtener_con_id(id);
				textfieldnombre.setText(mat.getNombre());
				
				UsuarioDAO udao = new UsuarioDAO();
				Usuario u = udao.obtener_usuario_con_dni(String.valueOf(mat.getDni_profesor()));
				String pcomparar = u.getNombre() + " " + u.getDni();
				comboBoxprofesores.setSelectedItem(pcomparar);
				
				CursoDAO cdao = new CursoDAO();
				Curso c = cdao.obtener_curso_conid(mat.getId_curso());
				String cursoencombobox = Integer.toString(c.getCurso()) + "° "+Integer.toString(c.getDivision())+"°";
				comboBoxcursos.setSelectedItem(cursoencombobox);
				
				btnDarDeAlta.setText("Modificar");
				modif = true;
				
			}
		});
		botonmodif.setBounds(335, 332, 124, 36);
		add(botonmodif);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnDarDeAlta.setText("Dar de Alta");
				txtF_buscarmaterias.setText("");
				textfieldnombre.setText("");
				modif = false;
			}
		});
		btnCancelar.setBounds(20, 291, 137, 30);
		add(btnCancelar);

		llenarTablaConTodos();
		
	}
	
	public void llenarTablaConTodos() {
		MateriaDAO matDAO = new MateriaDAO();
		for (Materia mat : matDAO.obtener_todos()) {
			modeloTabla.addRow(new Object[] {mat.getId() ,mat.getNombre(), obtenerNombreProf(mat), obtenerCurso(mat)});
		}
	}

	private String obtenerNombreProf(Materia mat) {
		UsuarioDAO udao = new UsuarioDAO();
		Usuario u = udao.obtener_usuario_con_dni(Integer.toString(mat.getDni_profesor()));
		return u.getNombre();
	}
	
	private String obtenerCurso(Materia mat) {
		CursoDAO cdao = new CursoDAO();
		Curso c = cdao.obtener_curso_conid(mat.getId_curso());
		return (Integer.toString(c.getCurso())+ "°"+Integer.toString(c.getDivision())+ "°");
	}
}
