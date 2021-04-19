package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import simulator.control.Controller;

@SuppressWarnings("serial")
/**
 * Ventana Principal del GUI Simulador Fisico
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 * @see JFrame
 */
public class MainWindow extends JFrame{
	protected Controller ctrl;
	/**
	 * Constructor de la Ventana principal, que crea la ventana y llama a initGUI
	 * @param controlador Controlador del Simulador Fisico
	 */
	public MainWindow(Controller controlador){
		super("Simulador Fisico");
		ctrl = controlador;
		initGUI();
	}
	/**
	 * Inicializa la interfaz grafica,cargandole todos los componentes para su funcionamiento
	 */
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		this.setPreferredSize(new Dimension(825,600));
		setLocationByPlatform(true);
		//TODO Completar la construccion de GUI
		mainPanel.add(new ControlPanel(ctrl));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	//TODO Agregar metodos private y protected
}
