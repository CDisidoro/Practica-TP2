package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import simulator.control.Controller;

@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	protected Controller ctrl;
	public MainWindow(Controller controlador){
		super("Simulador Fisico");
		ctrl = controlador;
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		this.setPreferredSize(new Dimension(800,600));
		//TODO Completar la construccion de GUI
		mainPanel.add(new ControlPanel(ctrl));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	//TODO Agregar metodos private y protected
}
