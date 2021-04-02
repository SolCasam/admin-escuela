package vistas;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Vacante;
import dao.CursoDAO;
import dao.EscuelaDAO;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class EscuelaVacantes extends JPanel{
	private JTable table;
	public EscuelaVacantes(JFrame m) {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 57, 332, 202);
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);

		Object[] columnas=new Object[] {"Division","Vacantes"};
		DefaultTableModel modeloDeTabla = new DefaultTableModel(columnas,0);
		table.setModel(modeloDeTabla);
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(163, 269, 85, 21);
		add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuEscuela mEscuela = new MenuEscuela();
				mEscuela.setMarco(m);
				
				m.setContentPane(mEscuela);
				m.validate();
			}
		});
		
		JComboBox<String> comboBoxCurso = new JComboBox<String>();
		comboBoxCurso.setBounds(219, 23, 99, 21);
		add(comboBoxCurso);
		CursoDAO cDao = new CursoDAO();
		ArrayList<String> Items = cDao.años();
		comboBoxCurso.addItem("Año");
		for (String c : Items)
			comboBoxCurso.addItem(c);
		add(comboBoxCurso);
		comboBoxCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				modeloDeTabla.setRowCount(0);
				EscuelaDAO eDAO=new EscuelaDAO();
				int año=Integer.parseInt(comboBoxCurso.getSelectedItem().toString().trim().substring(0, 1));
				ArrayList<Vacante> vacantes=eDAO.consultaVacantes(año);
				for(Vacante v:vacantes) {
					modeloDeTabla.addRow(new Object[] {v.getDivision(),v.getVacantes()});
				}
			}
		});
		
		
		
		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAo.setBounds(84, 27, 46, 13);
		add(lblAo);
		

		
	}
}
