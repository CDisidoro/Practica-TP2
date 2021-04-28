package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import simulator.control.Controller;

/**
 * Ventana Principal del GUI Simulador Fisico
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 * @see JFrame
 */
@SuppressWarnings("serial")
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
		//Layout en Caja para los componentes centrales
		JPanel centro = new JPanel();
		centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);
		this.setPreferredSize(new Dimension(1000,700));
		setLocationByPlatform(true);
		mainPanel.add(new ControlPanel(ctrl, this), BorderLayout.NORTH);
		//Agrega la tabla de cuerpos y el Viewer al BoxLayout
		centro.add(new BodiesTable (ctrl));
		centro.add(new JPanel().add(new Viewer(ctrl)));
		//Se agrega el box layout al main Panel
		mainPanel.add(centro, BorderLayout.CENTER);
		mainPanel.add(new StatusBar(ctrl), BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
}
