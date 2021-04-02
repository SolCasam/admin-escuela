package vistas;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import clases.Alumno;
import clases.Inasistencia;
import clases.Verificacion;
import dao.AlumnoDAO;
import dao.CursoDAO;
import dao.InasistenciaDAO;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class EscuelaInasistencias extends JPanel{
	private JTable table;
	private JTextField txtfecha;
	public EscuelaInasistencias(JFrame m) {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 88, 358, 254);
		add(scrollPane);

		txtfecha = new JTextField();
		txtfecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtfecha.setBounds(301, 39, 129, 21);
		add(txtfecha);
		txtfecha.setColumns(10);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		Object[] columnas=new Object[] {"DNI","Nombre","Valor","Justificada"};
		DefaultTableModel modeloDeTabla = new DefaultTableModel(columnas,0);
		table.setModel(modeloDeTabla);
		
		JComboBox<String> comboBoxCurso = new JComboBox<String>();
		comboBoxCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxCurso.setBounds(72, 39, 105, 21);
		add(comboBoxCurso);
		CursoDAO cDao = new CursoDAO();
		ArrayList<String> Items = cDao.cursos();
		comboBoxCurso.addItem("Cursos");
		for (String c : Items)
			comboBoxCurso.addItem(c);
		add(comboBoxCurso);
		comboBoxCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxCurso.getSelectedItem().toString()!="Cursos") {
				AlumnoDAO aDAO=new AlumnoDAO();
				int año=Integer.parseInt(comboBoxCurso.getSelectedItem().toString().substring(0, 1).trim());
				int div=Integer.parseInt(comboBoxCurso.getSelectedItem().toString().substring(3, 4).trim());
				ArrayList<Alumno> alumns=aDAO.consultaAlumnos(año, div);
				for(Alumno a:alumns) {
					modeloDeTabla.addRow(new Object[] {a.getDni(),a.getNombre()});
				}
			}}
		});
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGuardar.setBounds(318, 366, 85, 21);
		add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Verificacion v=new Verificacion();
				if(v.fechaValida(txtfecha.getText())) {
				InasistenciaDAO iDAO=new InasistenciaDAO();
				int c=0;
				while(c<modeloDeTabla.getRowCount()) {
					if(table.getValueAt(c, 2)!="-") {
						LocalDate fecha=LocalDate.parse(txtfecha.getText().trim());
						float valor=Float.parseFloat(table.getValueAt(c, 2).toString().trim());
						boolean just=false;
						if(table.getValueAt(c, 3).toString()=="Si") {
							just=true;
						}
						Inasistencia i=new Inasistencia(fecha,valor,just);
						iDAO.insert(i, table.getValueAt(c, 0).toString().trim());
					}
					c++;
				}
				JOptionPane.showMessageDialog(null, "Inasistencias guardadas");
			}else {
				JOptionPane.showMessageDialog(null, "ERROR: Fecha no valida");
			}
		}});
		
		
		
		JComboBox<String> comboBoxValorFalta = new JComboBox<String>();
		comboBoxValorFalta.addItem("-");
		comboBoxValorFalta.addItem("0.25");
		comboBoxValorFalta.addItem("0.5");
		comboBoxValorFalta.addItem("1");
		table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(comboBoxValorFalta));
		
		JComboBox<String> comboBoxJust = new JComboBox<String>();
		comboBoxJust.addItem("No");
		comboBoxJust.addItem("Si");
		table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(comboBoxJust));
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFecha.setBounds(245, 45, 46, 13);
		add(lblFecha);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnVolver.setBounds(102, 368, 85, 21);
		add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MenuEscuela mEscuela = new MenuEscuela();
				mEscuela.setMarco(m);
				
				m.setContentPane(mEscuela);
				m.validate();
			}
		});
	}
}
