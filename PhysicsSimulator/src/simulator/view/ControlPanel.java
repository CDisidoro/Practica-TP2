package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel implements SimulatorObserver{

	private Controller _ctrl;
	private boolean _stopped;
	//TODO Agregar atributos de JToolBar, botones, JSpinner, JFileChooser, etc.
	private JToolBar tools;
	private JButton chooseFileButton,chooseForceButton,startButton,stopButton,exitButton;
	private JSpinner stepsSpinner;
	private JFileChooser fileChooser;
	private JTextField deltaTimeField;
	public ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI() {
		//TODO Construir la tool bar
		tools = new JToolBar();
		tools.setLayout(new FlowLayout(0));
		//Configuracion del Boton de Elegir Archivo
		chooseFileButton = new JButton();
		chooseFileButton.setIcon(loadImage("resources/icons/open.png"));
		chooseFileButton.setToolTipText("Load bodies file into the simulator");
		chooseFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser = new JFileChooser("Selecciona un archivo JSON");
				fileChooser.showOpenDialog(fileChooser);
				String archivo = fileChooser.getSelectedFile().getAbsolutePath();
				_ctrl.reset();
				try {
					_ctrl.localBodies(new FileInputStream(archivo));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		//Configuracion del Boton de Elegir Ley de Fuerza
		chooseForceButton = new JButton();
		chooseForceButton.setIcon(loadImage("resources/icons/physics.png"));
		chooseForceButton.setToolTipText("Choose a Force Law");
		chooseForceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Darle accion al boton
			}
		});
		//Configuracion del Boton de Iniciar
		startButton = new JButton();
		startButton.setIcon(loadImage("resources/icons/run.png"));
		startButton.setToolTipText("Runs the Simulation");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseFileButton.setEnabled(false);
				chooseForceButton.setEnabled(false);
				startButton.setEnabled(false);
				exitButton.setEnabled(false);
				stepsSpinner.setEnabled(false);
				deltaTimeField.setEditable(false);
				_stopped = false;
				run_sim(Integer.parseInt(stepsSpinner.getValue().toString()));
			}
		});
		//Configuracion del Boton de Parar
		stopButton = new JButton();
		stopButton.setIcon(loadImage("resources/icons/stop.png"));
		stopButton.setToolTipText("Stops the Simulation");
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseFileButton.setEnabled(true);
				chooseForceButton.setEnabled(true);
				startButton.setEnabled(true);
				exitButton.setEnabled(true);
				stepsSpinner.setEnabled(true);
				deltaTimeField.setEditable(true);
				_stopped = true;
			}
		});
		//Configuracion del Boton de Salir
		exitButton = new JButton();
		exitButton.setIcon(loadImage("resources/icons/exit.png"));
		exitButton.setToolTipText("Exit the Simulator");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Darle accion al boton
				JPanel salir = new JPanel();
				int opc = JOptionPane.showConfirmDialog(salir, "Desea salir del simulador?", "Salir", 0);
				if(opc == 0) {//Se pulsa que si
					System.exit(0);
				}
			}
		});
		//Configuracion del Spinner de Steps
		stepsSpinner = new JSpinner(new SpinnerNumberModel(150, 0, null, 1));
		stepsSpinner.setPreferredSize(new Dimension(80, 40));
		stepsSpinner.setMaximumSize(new Dimension(80, 40));
		//Configuracion del Text Field de Delta Time
		deltaTimeField = new JTextField();
		deltaTimeField.setPreferredSize(new Dimension(80, 40));
		deltaTimeField.setMaximumSize(new Dimension(80, 40));
		//Agregado de los Componentes
		tools.add(chooseFileButton);
		tools.addSeparator();
		tools.add(chooseForceButton);
		tools.addSeparator();
		tools.add(startButton);
		tools.add(stopButton);
		tools.add(new JLabel("Steps: "));
		tools.add(stepsSpinner);
		tools.add(new JLabel("Delta-Time: "));
		tools.add(deltaTimeField);
		tools.addSeparator();
		tools.add(exitButton);
		tools.setPreferredSize(new Dimension(800, 60));
		this.add(tools);
	}
	//Metodos privados/protegidos
	private void run_sim(int n) {
		if (n > 0 && !_stopped) {
			try {
				_ctrl.run(1);
			}catch(Exception e) {
				//TODO Mostrar error en una ventana JOptionPane y poner a enable todos los botones
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
					JPanel error=new JPanel();
					JOptionPane.showMessageDialog(error, "Error en la simulacion","ERROR",JOptionPane.ERROR_MESSAGE);
					chooseFileButton.setEnabled(true);
					chooseForceButton.setEnabled(true);
					startButton.setEnabled(true);
					exitButton.setEnabled(true);
					stepsSpinner.setEnabled(true);
					deltaTimeField.setEditable(true);
					_stopped = true;
					return;
					}
				});
			}
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					run_sim(n-1);
				}
			});
		}else {
			_stopped = true;
			chooseFileButton.setEnabled(true);
			chooseForceButton.setEnabled(true);
			startButton.setEnabled(true);
			exitButton.setEnabled(true);
			stepsSpinner.setEnabled(true);
			deltaTimeField.setEditable(true);
		}
	}
	protected ImageIcon loadImage(String path) {
		return new ImageIcon(Toolkit.getDefaultToolkit().createImage(path));
	}
	//Metodos del Observador
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		// TODO Auto-generated method stub
		
	}

}
