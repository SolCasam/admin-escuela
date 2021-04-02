package vistas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import clases.Alumno;
import clases.Nota;
import clases.Usuario;
import clases.Verificacion;
import dao.AlumnoDAO;
import dao.CursoDAO;
import dao.NotaDAO;
import dao.TipoNotaDAO;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;

public class IngresarNotas extends JPanel {
	private JTextField txtNota;
	private JTextField txtFecha;
	private JFrame marco;

	public IngresarNotas(Usuario usuario,Alumno a,int materia) {
		setLayout(null);

		JLabel lblNota = new JLabel("Nota");
		lblNota.setBounds(73, 65, 46, 14);
		add(lblNota);

		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(73, 96, 46, 14);
		add(lblFecha);

		JLabel lblTipoNota = new JLabel("Tipo Nota");
		lblTipoNota.setBounds(73, 127, 46, 14);
		add(lblTipoNota);

		txtNota = new JTextField();
		txtNota.setBounds(129, 62, 86, 20);
		add(txtNota);
		txtNota.setColumns(10);

		txtFecha = new JTextField();
		txtFecha.setBounds(129, 93, 86, 20);
		add(txtFecha);
		txtFecha.setColumns(10);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(129, 124, 86, 20);
		add(comboBox);
		TipoNotaDAO tpDao = new TipoNotaDAO();
		ArrayList<String> Items = tpDao.todos();
		for (String c : Items) {
			comboBox.addItem(c);
		}

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(103, 204, 89, 23);
		add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Verificacion v = new Verificacion();
				if (v.notaValida(txtNota.getText())) {
					if (v.fechaValida(txtFecha.getText())) {
						NotaDAO nDAO = new NotaDAO();
						float valor=Float.parseFloat(txtNota.getText());
						LocalDate fecha=LocalDate.parse(txtFecha.getText());
						String tipoNota=comboBox.getSelectedItem().toString();
						Nota n=new Nota(valor,fecha,tipoNota);
						nDAO.IngresarNota(n, a, materia);

						ProfesorVerAlumnos pvAlum = new ProfesorVerAlumnos(usuario);
						pvAlum.setMarco(marco);
						
						marco.setContentPane(pvAlum);
						marco.validate();
					} else {
						JOptionPane.showMessageDialog(null, "ERROR: Fecha no valida");
					}
				} else {
					JOptionPane.showMessageDialog(null, "ERROR: Nota no valida");
				}

			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(235, 204, 89, 23);
		add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProfesorVerAlumnos pvAlum = new ProfesorVerAlumnos(usuario);
				pvAlum.setMarco(marco);
				
				marco.setContentPane(pvAlum);
				marco.validate();
			}
		});
	}

	public void setMarco(JFrame marco) {
		this.marco = marco;

	}
}
