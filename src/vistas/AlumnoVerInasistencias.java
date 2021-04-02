package vistas;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Inasistencia;
import clases.Usuario;
import dao.InasistenciaDAO;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AlumnoVerInasistencias extends JPanel{
	
	private JTable table;
	private DefaultTableModel modeloDeTabla;
	private JFrame marco;
	
	public AlumnoVerInasistencias(Usuario u) {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 273, 278);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		Object[] columnas = new Object[] {"Justificada","Valor","Fecha"};
		modeloDeTabla = new DefaultTableModel(columnas,0);
		table.setModel(modeloDeTabla);
		
		InasistenciaDAO idao = new InasistenciaDAO();
		ArrayList<Inasistencia> todas = idao.consulta(u.getDni());
		for(Inasistencia i : todas) {
			modeloDeTabla.addRow(new Object[] {i.getEsJustificada(),i.getTipo(),i.getFecha()});
		}
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuAlumno mAlumno = new MenuAlumno(u);
				mAlumno.setMarco(marco);
				
				marco.setContentPane(mAlumno);
				marco.validate();
			}
		});
		btnVolver.setBounds(322, 266, 89, 23);
		add(btnVolver);
	}

	public void setMarco(JFrame marco) {
		this.marco=marco;
		
	}

}
