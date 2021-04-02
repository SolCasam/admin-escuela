package vistas;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import clases.Verificacion;
import dao.InasistenciaDAO;
import clases.Alumno;
import clases.Materia;
import clases.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Boletin extends JPanel {
	private JTable table;
	private JTextField buscarDnitxt;
	private JTextField txt1trim;
	private JTextField txt2trim;
	private JTextField txt3trim;
	private JTextField txttotal;
	private JFrame marco;

	public Boletin() {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 60, 463, 322);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		Object[] columnas = new Object[] { "x", "1° Trimestre", "2° Trimestre", "3° Trimestre" };
		DefaultTableModel modeloDeTabla = new DefaultTableModel(columnas, 0);
		table.setModel(modeloDeTabla);

		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(129, 422, 85, 21);
		add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuEscuela menu = new MenuEscuela();
				menu.setMarco(marco);
				marco.setContentPane(menu);
				marco.validate();
			}
		});

		buscarDnitxt = new JTextField();
		buscarDnitxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buscarDnitxt.setBounds(129, 28, 258, 19);
		add(buscarDnitxt);
		buscarDnitxt.setColumns(10);

		JLabel lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(62, 33, 46, 13);
		add(lblDni);

		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnImprimir.setBounds(376, 422, 104, 21);
		add(btnImprimir);

		JLabel lblTrimestre = new JLabel("1\u00B0 Trimestre");
		lblTrimestre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTrimestre.setBounds(528, 60, 85, 13);
		add(lblTrimestre);

		txt1trim = new JTextField();
		txt1trim.setBounds(528, 96, 96, 19);
		add(txt1trim);
		txt1trim.setColumns(10);

		JLabel lblTrimestre_1 = new JLabel("2\u00B0 Trimestre");
		lblTrimestre_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTrimestre_1.setBounds(528, 143, 96, 13);
		add(lblTrimestre_1);

		txt2trim = new JTextField();
		txt2trim.setBounds(523, 184, 96, 19);
		add(txt2trim);
		txt2trim.setColumns(10);

		JLabel lblTrimestre_2 = new JLabel("3\u00B0 Trimestre");
		lblTrimestre_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTrimestre_2.setBounds(528, 231, 85, 13);
		add(lblTrimestre_2);

		txt3trim = new JTextField();
		txt3trim.setBounds(523, 267, 96, 19);
		add(txt3trim);
		txt3trim.setColumns(10);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotal.setBounds(528, 317, 46, 13);
		add(lblTotal);

		txttotal = new JTextField();
		txttotal.setBounds(523, 352, 96, 19);
		add(txttotal);
		txttotal.setColumns(10);

		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				modeloDeTabla.setRowCount(0);
				Verificacion v = new Verificacion();
				if (v.dniValido(buscarDnitxt.getText())) {
					Alumno a = new Alumno();
					a.setDni(buscarDnitxt.getText());
					ArrayList<Materia> boletin = a.boletin();
					for (Materia m : boletin) {

						int p1 = 0;
						int p2 = 0;
						int p3 = 0;
						if (m.getPromedios().get(1) != null) {
							p1 = m.getPromedios().get(1);
						}
						if (m.getPromedios().get(2) != null) {
							p2 = m.getPromedios().get(2);
						}
						if (m.getPromedios().get(3) != null) {
							p3 = m.getPromedios().get(3);
						}

						modeloDeTabla.addRow(new Object[] { m.getNombre(), p1, p2, p3 });
					}
					InasistenciaDAO iDAO = new InasistenciaDAO();
					iDAO.totalInasistenciasxTrimestre(a.getDni());
					ArrayList<Integer> inasistencias = new ArrayList<Integer>();
					int totalInas = 0;
					if (inasistencias.size() >= 1) {

						txt1trim.setText(Integer.toString(inasistencias.get(0)));
						totalInas += inasistencias.get(0);
						if (inasistencias.size() <= 2) {
							txt2trim.setText(Integer.toString(inasistencias.get(1)));
							totalInas += inasistencias.get(1);
							if (inasistencias.size() <= 3) {
								txt3trim.setText(Integer.toString(inasistencias.get(2)));
								totalInas += inasistencias.get(2);
							} else {
								txt3trim.setText("-");
							}
						} else {
							txt2trim.setText("-");
						}
					} else {
						txt1trim.setText("-");
					}

					txttotal.setText(Integer.toString(totalInas));
				} else {
					JOptionPane.showMessageDialog(null, "ERROR: DNI no valido");
				}
			}
		});

		txt1trim.setEditable(false);
		txt2trim.setEditable(false);
		txt3trim.setEditable(false);
		txttotal.setEditable(false);

	}

	public JFrame getMarco() {
		return marco;
	}

	public void setMarco(JFrame marco) {
		this.marco = marco;
	}
}
