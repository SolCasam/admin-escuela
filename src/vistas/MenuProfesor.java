package vistas;

import javax.swing.JPanel;

import clases.Usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MenuProfesor extends JPanel{
	
	private JFrame marco;
	
	public MenuProfesor(Usuario usuario) {
		
		setLayout(null);
		
		JButton btnConsultarNotas = new JButton("Consultar Notas");
		btnConsultarNotas.setBounds(10, 11, 430, 129);
		add(btnConsultarNotas);
		btnConsultarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ConsultarNotas cNotas = new ConsultarNotas(usuario);
				cNotas.setMarco(marco);

				marco.setContentPane(cNotas);
				marco.validate();
			}
		});
		
		JButton btnIngresarNotas = new JButton("Ingresar Notas");
		btnIngresarNotas.setBounds(10, 142, 430, 148);
		add(btnIngresarNotas);
		btnIngresarNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ProfesorVerAlumnos pvAlumnos = new ProfesorVerAlumnos(usuario);
				pvAlumnos.setMarco(marco);

				marco.setContentPane(pvAlumnos);
				marco.validate();
			}
		});
	}

	public void setMarco(JFrame marco) {
		this.marco=marco;
		
	}
}
