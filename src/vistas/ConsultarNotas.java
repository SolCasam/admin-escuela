package vistas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ConsultarNotas extends JPanel {

	private JTable table;
	private DefaultTableModel modeloDeTabla;
	private JFrame marco;

	public ConsultarNotas(Usuario usuario) {
		setLayout(null);

		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setBounds(47, 34, 46, 14);
		add(lblCurso);

		JComboBox<String> comboBoxCurso = new JComboBox<String>();
		comboBoxCurso.setBounds(92, 31, 106, 20);
		add(comboBoxCurso);
		CursoDAO cDAO = new CursoDAO();
		ArrayList<String> cursos = cDAO.cursosxProfesor(usuario);
		for (String c : cursos) {
			comboBoxCurso.addItem(c);
		}
		comboBoxCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NotaDAO nDAO = new NotaDAO();
				int año = Integer.parseInt(comboBoxCurso.getSelectedItem().toString().substring(0, 1));
				int div = Integer.parseInt(comboBoxCurso.getSelectedItem().toString().substring(3, 4));
				MateriaDAO mDAO=new MateriaDAO();
				ArrayList<Nota> notas = nDAO.notasDeAlumnosPorMateria(año, div,mDAO.materiaxProfeYCurso(usuario, cDAO.idCursoxAñoyDivision(año, div)));
				for (Nota n : notas) {
					modeloDeTabla.addRow(new Object[] {n.getDni(),n.getNombreAlumno(),n.getValor(),n.getFecha(),n.getTipo() });
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 72, 430, 149);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		Object[] columnas = new Object[] { "DNI", "Alumno", "Nota", "Fecha", "Tipo de Nota" };
		modeloDeTabla = new DefaultTableModel(columnas, 0);
		table.setModel(modeloDeTabla);


		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MenuProfesor mProfesor = new MenuProfesor(usuario);
				mProfesor.setMarco(marco);

				marco.setContentPane(mProfesor);
				marco.validate();
			}
		});
		btnVolver.setBounds(308, 251, 89, 23);
		add(btnVolver);

	}

	public void setMarco(JFrame marco) {
		this.marco = marco;
	}
}
