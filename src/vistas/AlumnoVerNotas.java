package vistas;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Nota;
import clases.Usuario;
import dao.NotaDAO;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AlumnoVerNotas extends JPanel {

	private JTable table;
	private DefaultTableModel modeloDeTabla;
	private JFrame marco;

	public AlumnoVerNotas(Usuario u) {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 32, 349, 204);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		Object[] columnas = new Object[] { "Nombre Materia", "Tipo Nota", "Fecha", "Valor" };
		modeloDeTabla = new DefaultTableModel(columnas, 0);
		table.setModel(modeloDeTabla);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuAlumno mAlumno = new MenuAlumno(u);
				mAlumno.setMarco(marco);
				
				marco.setContentPane(mAlumno);
				marco.validate();
			}
		});

		NotaDAO ndao = new NotaDAO();
		ArrayList<Nota> todas = ndao.notasPorAlumno(u.getDni());
		for (Nota n : todas) {
			modeloDeTabla.addRow(new Object[] { n.getNombre_materia(), n.getTipo(), n.getFecha(), n.getValor() });
		}

		btnVolver.setBounds(10, 266, 89, 23);
		add(btnVolver);
	}

	public void setMarco(JFrame marco) {
		this.marco = marco;

	}
}
