package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Alumno;
import clases.Nota;
import clases.Usuario;
import dao.AlumnoDAO;
import dao.CursoDAO;
import dao.MateriaDAO;
import dao.NotaDAO;

import javax.swing.JScrollPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;

public class ProfesorVerAlumnos extends JPanel {
	private JFrame marco;
	private JTable table;
	private DefaultTableModel modeloDeTabla;

	public ProfesorVerAlumnos(Usuario usuario) {
		setLayout(null);

		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setBounds(55, 24, 46, 13);
		add(lblCurso);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 62, 387, 193);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		Object[] columnas = new Object[] { "DNI", "Alumno", "Promedio 1°Trim", "Promedio 2°Trim", "Promedio 3°Trim" };
		modeloDeTabla = new DefaultTableModel(columnas, 0);
		table.setModel(modeloDeTabla);

		JComboBox<String> comboBoxCurso = new JComboBox<String>();
		comboBoxCurso.setBounds(166, 20, 188, 21);
		add(comboBoxCurso);
		CursoDAO cDAO = new CursoDAO();
		ArrayList<String> cursos = cDAO.cursosxProfesor(usuario);
		for (String c : cursos) {
			comboBoxCurso.addItem(c);
		}
		comboBoxCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloDeTabla.setRowCount(0);
				AlumnoDAO aDAO = new AlumnoDAO();
				int año = Integer.parseInt(comboBoxCurso.getSelectedItem().toString().substring(0, 1));
				int div = Integer.parseInt(comboBoxCurso.getSelectedItem().toString().substring(3, 4));
				ArrayList<Alumno> alumnos = aDAO.consultaAlumnos(año, div);
				MateriaDAO mDAO = new MateriaDAO();
				int materia = mDAO.materiaxProfeYCurso(usuario, cDAO.idCursoxAñoyDivision(año, div));
				NotaDAO nDAO = new NotaDAO();
				for (Alumno a : alumnos) {
					HashMap<Integer,Integer> promedios = nDAO.promedioxMateria(a.getDni(), materia);
					modeloDeTabla.addRow(new Object[] { a.getDni(), a.getNombre(), promedios.get(1),promedios.get(2),promedios.get(3) });
				
				}
			}
		});

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(55, 269, 85, 21);
		add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MenuProfesor mProfesor = new MenuProfesor(usuario);
				mProfesor.setMarco(marco);

				marco.setContentPane(mProfesor);
				marco.validate();
			}
		});

		JButton btnIngresarNota = new JButton("Ingresar Nota");
		btnIngresarNota.setBounds(287, 269, 85, 21);
		add(btnIngresarNota);
		btnIngresarNota.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(table.getSelectedRow() != -1) {

				int año = Integer.parseInt(comboBoxCurso.getSelectedItem().toString().substring(0, 1));
				int div = Integer.parseInt(comboBoxCurso.getSelectedItem().toString().substring(3, 4));

				MateriaDAO mDAO = new MateriaDAO();
				int materia = mDAO.materiaxProfeYCurso(usuario, cDAO.idCursoxAñoyDivision(año, div));
				Alumno a = new Alumno();
				a.setDni(table.getValueAt(table.getSelectedRow(), 0).toString());
				IngresarNotas iNotas = new IngresarNotas(usuario, a, materia);
				iNotas.setMarco(marco);

				marco.setContentPane(iNotas);
				marco.validate();
				}else {
					JOptionPane.showMessageDialog(null, "ERROR: No se ha seleccionado un campo");
				}
				
			}
		});

	}

	public void setMarco(JFrame marco) {
		this.marco = marco;

	}
}
