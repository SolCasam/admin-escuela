package app;

import javax.swing.JFrame;

import vistas.Inicio;

public class App {
	
	public static void main(String[] args) {
		
		JFrame marco = new JFrame("Sistema Boletín");
		
		marco.setBounds(0, 0, 800, 600);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		marco.setVisible(true);

		Inicio inicio = new Inicio();
		inicio.setMarco(marco);
		
		marco.setContentPane(inicio);
		marco.validate();	

	}
	
}
