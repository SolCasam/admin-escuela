package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;

public class MenuEscuela extends JPanel {

	JFrame marco = new JFrame();
	
	public MenuEscuela() {
		setLayout(null);
		
		JButton btnABMUsuarios = new JButton("ABM Usuarios");
		btnABMUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnABMUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ABMUsuarios abmusuarios = new ABMUsuarios();
				abmusuarios.setMarco(marco);
				
				marco.setContentPane(abmusuarios);
				marco.validate();
				
			}
		});
		btnABMUsuarios.setBounds(159, 75, 177, 74);
		add(btnABMUsuarios);
		
		JButton btnABMMaterias = new JButton("ABM Materias");
		btnABMMaterias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ABMMaterias abmaterias = new ABMMaterias();
				abmaterias.setMarco(marco);
				
				marco.setContentPane(abmaterias);
				marco.validate();
				
			}
		});
		btnABMMaterias.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnABMMaterias.setBounds(159, 160, 177, 74);
		add(btnABMMaterias);
		
		JButton btnBoletin = new JButton("Bolet\u00EDn");
		btnBoletin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBoletin.setBounds(159, 244, 177, 74);
		add(btnBoletin);
		btnBoletin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Boletin boletin = new Boletin();
				boletin.setMarco(marco);
				marco.setContentPane(boletin);
				marco.validate();
				
			}
		});
		
		JButton btnConsultas = new JButton("Consultas");
		btnConsultas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConsultas.setBounds(346, 75, 182, 74);
		add(btnConsultas);
		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				marco.setContentPane(new ConsultasEscuela(marco));
				marco.validate();
				
			}
		});
		
		JButton btnIngresoInasistencias = new JButton("Ingreso Inasistencias");
		btnIngresoInasistencias.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnIngresoInasistencias.setBounds(346, 160, 182, 74);
		add(btnIngresoInasistencias);
		btnIngresoInasistencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				marco.setContentPane(new EscuelaInasistencias(marco));
				marco.validate();
				
			}
		});
		
		JButton btnConsultaVacantes = new JButton("Consulta Vacantes");
		btnConsultaVacantes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConsultaVacantes.setBounds(346, 244, 182, 74);
		add(btnConsultaVacantes);
		btnConsultaVacantes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				marco.setContentPane(new EscuelaVacantes(marco));
				marco.validate();
				
			}
		});
		
		JButton btnModificarFechasDe = new JButton("Modificar Fechas de los Trimestres");
		btnModificarFechasDe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnModificarFechasDe.setBounds(159, 328, 369, 74);
		add(btnModificarFechasDe);
		
		JLabel lblMenEscuela = new JLabel("Men\u00FA Escuela");
		lblMenEscuela.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblMenEscuela.setBounds(10, 11, 326, 21);
		add(lblMenEscuela);
		btnModificarFechasDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FechasTrimestre fTrimestres = new FechasTrimestre();
				fTrimestres.setMarco(marco);
				
				marco.setContentPane(fTrimestres );
				marco.validate();
				
			}
		});

	}

	
	public void setMarco(JFrame marco) {
		this.marco = marco;
	}
}
