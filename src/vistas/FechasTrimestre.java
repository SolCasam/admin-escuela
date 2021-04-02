package vistas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clases.Trimestre;
import clases.Verificacion;
import dao.FechaDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class FechasTrimestre extends JPanel{

	JFrame marco = new JFrame();
	private JTextField txtFecha1Desde;
	private JTextField txtFecha1Hasta;
	private JTextField txtFecha2Desde;
	private JTextField txtFecha2Hasta;
	private JTextField txtFecha3Desde;
	private JTextField txtFecha3Hasta;
	
	public FechasTrimestre() {
		
		setLayout(null);
		
		txtFecha1Desde = new JTextField();
		txtFecha1Desde.setBounds(130, 88, 118, 19);
		add(txtFecha1Desde);
		txtFecha1Desde.setColumns(10);
		
		txtFecha1Hasta = new JTextField();
		txtFecha1Hasta.setBounds(307, 88, 118, 19);
		add(txtFecha1Hasta);
		txtFecha1Hasta.setColumns(10);
		
		txtFecha2Desde = new JTextField();
		txtFecha2Desde.setBounds(130, 151, 118, 19);
		add(txtFecha2Desde);
		txtFecha2Desde.setColumns(10);
		
		txtFecha2Hasta = new JTextField();
		txtFecha2Hasta.setBounds(307, 151, 118, 19);
		add(txtFecha2Hasta);
		txtFecha2Hasta.setColumns(10);
		
		txtFecha3Desde = new JTextField();
		txtFecha3Desde.setBounds(130, 214, 118, 19);
		add(txtFecha3Desde);
		txtFecha3Desde.setColumns(10);
		
		txtFecha3Hasta = new JTextField();
		txtFecha3Hasta.setBounds(307, 214, 118, 19);
		add(txtFecha3Hasta);
		txtFecha3Hasta.setColumns(10);
		
		JLabel lblTrimestre = new JLabel("3\u00B0 Trimestre");
		lblTrimestre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTrimestre.setBounds(42, 217, 78, 13);
		add(lblTrimestre);
		
		JLabel lblTrimestre_1 = new JLabel("2\u00B0 Trimestre");
		lblTrimestre_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTrimestre_1.setBounds(42, 154, 78, 13);
		add(lblTrimestre_1);
		
		JLabel lblTrimestre_2 = new JLabel("1\u00B0 Trimestre");
		lblTrimestre_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTrimestre_2.setBounds(42, 91, 78, 13);
		add(lblTrimestre_2);
		
		FechaDAO fDAO=new FechaDAO();
		for(Trimestre t:fDAO.fechasTrimestre()) {
			switch(t.getNum()) {
			
			case 1:
				txtFecha1Desde.setText(t.getDesde().toString());
				txtFecha1Hasta.setText(t.getHasta().toString());
			case 2:	
				txtFecha2Desde.setText(t.getDesde().toString());
				txtFecha2Hasta.setText(t.getHasta().toString());
				
			case 3:	
				txtFecha3Desde.setText(t.getDesde().toString());
				txtFecha3Hasta.setText(t.getHasta().toString());
				
			}
		}
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnVolver.setBounds(141, 293, 85, 21);
		add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuEscuela mEscuela = new MenuEscuela();
				mEscuela.setMarco(marco);
				
				marco.setContentPane(mEscuela);
				marco.validate();
			}
		});
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.setBounds(329, 293, 85, 21);
		add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Verificacion v=new Verificacion();
				if(v.fechaValida(txtFecha1Desde.getText())&&v.fechaValida(txtFecha1Hasta.getText())&&v.fechaValida(txtFecha2Desde.getText())&&v.fechaValida(txtFecha2Hasta.getText())&&v.fechaValida(txtFecha3Desde.getText())&&v.fechaValida(txtFecha3Hasta.getText())) {

					ArrayList<Trimestre> trimestres=new ArrayList<Trimestre>();
					
					Trimestre t1=new Trimestre(1,LocalDate.parse(txtFecha1Desde.getText()),LocalDate.parse(txtFecha1Hasta.getText()));
					trimestres.add(t1);
					Trimestre t2=new Trimestre(2,LocalDate.parse(txtFecha2Desde.getText()),LocalDate.parse(txtFecha2Hasta.getText()));
					trimestres.add(t2);
					Trimestre t3=new Trimestre(3,LocalDate.parse(txtFecha3Desde.getText()),LocalDate.parse(txtFecha3Hasta.getText()));
					trimestres.add(t3);
					
					fDAO.cambioFechasTrimestre(trimestres);
					
					MenuEscuela mEscuela = new MenuEscuela();
					mEscuela.setMarco(marco);
					
					marco.setContentPane(mEscuela);
					marco.validate();
				}else {
					JOptionPane.showMessageDialog(null, "ERROR: Fecha no valida");
				}
				
			}
		});
		
		
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDesde.setBounds(151, 36, 46, 13);
		add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHasta.setBounds(355, 36, 46, 13);
		add(lblHasta);
	}

	

	public void setMarco(JFrame marco) {
		this.marco = marco;
	}
}
