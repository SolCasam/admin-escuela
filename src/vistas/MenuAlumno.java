package vistas;

import javax.swing.JPanel;

import clases.Usuario;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuAlumno extends JPanel{

	 private JFrame marco;
	public MenuAlumno(Usuario u) {
		setLayout(null);
		
		JButton btnVerNotas = new JButton("Ver Notas\r\n");
		btnVerNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AlumnoVerNotas verNotas = new AlumnoVerNotas(u);
				verNotas.setMarco(marco);
				
				marco.setContentPane(verNotas);
				marco.validate();
			}
		});
		btnVerNotas.setBounds(186, 74, 89, 63);
		add(btnVerNotas);
		
		JButton btnVerInasistencias = new JButton("Ver Inasistencias");
		btnVerInasistencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AlumnoVerInasistencias verInasistencias = new AlumnoVerInasistencias(u);
				verInasistencias.setMarco(marco);
				
				marco.setContentPane(verInasistencias);
				marco.validate();
			}
		});
		btnVerInasistencias.setBounds(156, 148, 151, 72);
		add(btnVerInasistencias);
	}

	public void setMarco(JFrame marco) {
		this.marco=marco;
	}
}
