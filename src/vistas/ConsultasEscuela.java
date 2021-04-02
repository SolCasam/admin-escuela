package vistas;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import app.App;
import clases.Alumno;
import clases.Materia;
import clases.Profesor;
import clases.Usuario;
import clases.Verificacion;
import dao.AlumnoDAO;
import dao.CursoDAO;
import dao.MateriaDAO;
import dao.ProfesorDAO;

import javax.swing.JScrollPane;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class ConsultasEscuela extends JPanel {
	private JTable table;

	public ConsultasEscuela(JFrame m) {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(52, 74, 587, 396);
		add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(158, 502, 85, 21);
		add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuEscuela mEscuela = new MenuEscuela();
				mEscuela.setMarco(m);
				
				m.setContentPane(mEscuela);
				m.validate();
			}
		});

		JComboBox<String> comboBoxOpcion = new JComboBox<String>();
		comboBoxOpcion.setBounds(87, 29, 231, 21);
		add(comboBoxOpcion);
		comboBoxOpcion.addItem("Filtro");
		comboBoxOpcion.addItem("Alumnos");
		comboBoxOpcion.addItem("Profesores");
		comboBoxOpcion.addItem("Materias");

		JComboBox<String> comboBoxCurso = new JComboBox<String>();
		comboBoxCurso.setBounds(451, 29, 155, 21);
		add(comboBoxCurso);
		CursoDAO cDao = new CursoDAO();
		ArrayList<String> Items = cDao.cursos();
		comboBoxCurso.addItem("Cursos");
		for (String c : Items)
			comboBoxCurso.addItem(c);
		add(comboBoxCurso);
		comboBoxCurso.disable();

		comboBoxOpcion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxOpcion.getSelectedItem() == "Alumnos") {
					comboBoxCurso.enable();
				}else {
					comboBoxCurso.disable();
				}
			}
		});

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFiltrar.setBounds(419, 504, 85, 21);
		add(btnFiltrar);
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxOpcion.getSelectedItem() == "Profesores") {
					Object[] columnas = new Object[] { "DNI", "Nombre", "Domicilio", "Correo", "CUIL", "Telefono",
							"Celular", "Materia", "Año","Division" };
					DefaultTableModel modeloDeTabla = new DefaultTableModel(columnas, 0);
					table.setModel(modeloDeTabla);
					ProfesorDAO pDAO = new ProfesorDAO();
					ArrayList<Profesor> profs = pDAO.consultaProfesores();
					for (Profesor p : profs) {
						modeloDeTabla.addRow(new Object[] { p.getDni(), p.getNombre(), p.getDomicilio(), p.getCorreo(),p.getCuil(),
								p.getTelefono(), p.getCelular(), p.getMateria().getNombre(), p.getMateria().getAño(),p.getMateria().getDivision() });
					}
				}
				if (comboBoxOpcion.getSelectedItem() == "Alumnos") {
					Object[] columnas = new Object[] { "DNI", "Nombre", "Domicilio", "Telefono", "Celular" };
					DefaultTableModel modeloDeTabla = new DefaultTableModel(columnas, 0);
					table.setModel(modeloDeTabla);

					AlumnoDAO aDAO = new AlumnoDAO();
					ArrayList<Alumno> alus = new ArrayList<Alumno>();
					if (comboBoxCurso.getSelectedItem() == "Cursos") {
						alus = aDAO.todosAlumnos();
					} else {
						String curso = comboBoxCurso.getSelectedItem().toString().trim();
						int año = Integer.parseInt(curso.substring(0, 1));
						int division = Integer.parseInt(curso.substring(3, 4));

						alus = aDAO.consultaAlumnos(año, division);
					}
					for (Alumno a : alus) {
						modeloDeTabla.addRow(new Object[] { a.getDni(), a.getNombre(), a.getDomicilio(),
								a.getTelefono(), a.getCelular() });
					}
				}
				if (comboBoxOpcion.getSelectedItem() == "Materias") {

					Object[] columnas = new Object[] { "Nombre", "Año", "Division", "Profesor" };
					DefaultTableModel modeloDeTabla = new DefaultTableModel(columnas, 0);
					table.setModel(modeloDeTabla);

					MateriaDAO mDAO = new MateriaDAO();
					ArrayList<Materia> mats = mDAO.todasMateria();
					for (Materia m : mats) {
						modeloDeTabla.addRow(new Object[] { m.getNombre(), m.getAño(), m.getDivision(), m.getProf() });
					}
				}

			}
		});

	}
}
