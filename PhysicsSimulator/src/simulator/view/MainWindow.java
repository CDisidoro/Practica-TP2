package simulator.view;

import java.awt.BorderLayout;
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
		setContentPane(mainPanel);
		//TODO Completar la construccion de GUI
	}
	//TODO Agregar metodos private y protected
}
