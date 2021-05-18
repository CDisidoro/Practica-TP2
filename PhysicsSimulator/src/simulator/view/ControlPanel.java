package simulator.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
import javax.swing.filechooser.FileNameExtensionFilter;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

/**
 * Panel de Control del Simulador Fisico, donde estan los botones esenciales para su funcionamiento
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 * @see JPanel
 */
@SuppressWarnings("serial")
public class ControlPanel extends JPanel implements SimulatorObserver{

	private Controller _ctrl;
	private boolean _stopped;
	private JToolBar tools;
	private JButton chooseFileButton,chooseForceButton,startButton,stopButton,exitButton;
	private JSpinner stepsSpinner, delaySpinner;
	private JFileChooser fileChooser;
	private JTextField deltaTimeField;
	private ForceLawDialog forceDialog;
	private MainWindow mainWindow;
	private volatile Thread simThread;
	String archivo;
	/**
	 * Constructor del Panel de control que llama a initGUI y añade al controlador como un observador
	 * @param ctrl Controlador del Simulador Fisico
	 * @param mainWindow Ventana principal del Simulador
	 */
	public ControlPanel(Controller ctrl, MainWindow mainWindow) {
		_ctrl = ctrl;
		_stopped = true;
		this.mainWindow = mainWindow;
		initGUI();
		_ctrl.addObserver(this);
	}
	/**
	 * Inicializa el panel de botones del panel de control
	 */
	private void initGUI() {
		tools = new JToolBar();
		tools.setLayout(new BoxLayout(tools, BoxLayout.LINE_AXIS));
		tools.setFloatable(false);
		//Creacion del File Chooser
		fileChooser = new JFileChooser("Selecciona un archivo JSON");
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("JSON", "json");
		fileChooser.setFileFilter(filtro);
		//Configuracion del Boton de Elegir Archivo
		chooseFileButton = new JButton();
		chooseFileButton.setIcon(loadImage("resources/icons/open.png"));
		chooseFileButton.setToolTipText("Load bodies file into the simulator");
		chooseFileButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int respuesta = fileChooser.showOpenDialog(fileChooser);
				if(respuesta == JFileChooser.APPROVE_OPTION) {
					archivo = fileChooser.getSelectedFile().getAbsolutePath();
					reload(archivo);
				}
			}
		});
		//Creacion del cuadro de dialogo de Ley Fisica
		forceDialog = new ForceLawDialog(_ctrl, mainWindow);
		//Configuracion del Boton de Elegir Ley de Fuerza
		chooseForceButton = new JButton();
		chooseForceButton.setIcon(loadImage("resources/icons/physics.png"));
		chooseForceButton.setToolTipText("Choose a Force Law");
		chooseForceButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(forceDialog.open() == 1) {
					reload(archivo);
					_ctrl.setForceLaws(forceDialog.getLaw());
				}else {
					System.out.println("Cambio de ley cancelada");
				}
			}
		});
		//Configuracion del Boton de Iniciar
		startButton = new JButton();
		startButton.setIcon(loadImage("resources/icons/run.png"));
		startButton.setToolTipText("Runs the Simulation");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(simThread == null) {
					setStatus(false);
					try {
						_ctrl.setDeltaTime(Double.parseDouble(deltaTimeField.getText()));
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Error en el parseo de Delta Time","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					//Inicializacion del Hilo
					simThread = new Thread(new Runnable() {
						@Override
						public void run() {
							run_sim(Integer.parseInt(stepsSpinner.getValue().toString()), Long.parseLong(delaySpinner.getValue().toString()));
							setStatus(true);
							simThread = null;
						}
					});
					//Ejecucion del hilo
					simThread.start();
				}
			}
		});
		//Configuracion del Boton de Parar
		stopButton = new JButton();
		stopButton.setIcon(loadImage("resources/icons/stop.png"));
		stopButton.setToolTipText("Stops the Simulation");
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(simThread != null) {
					simThread.interrupt();
				}
			}
		});
		//Configuracion del Boton de Salir
		exitButton = new JButton();
		exitButton.setIcon(loadImage("resources/icons/exit.png"));
		exitButton.setToolTipText("Exit the Simulator");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel salir = new JPanel();
				int opc = JOptionPane.showConfirmDialog(salir, "Desea salir del simulador?", "Salir", 0);
				if(opc == 0) {//Se pulsa que si
					System.exit(0);
				}
			}
		});
		//Configuracion del Spinner de Steps
		stepsSpinner = new JSpinner(new SpinnerNumberModel(150, 0, null, 500));
		stepsSpinner.setPreferredSize(new Dimension(80, 40));
		stepsSpinner.setMaximumSize(new Dimension(80, 40));
		//Configuracion del Text Field de Delta Time
		deltaTimeField = new JTextField();
		deltaTimeField.setPreferredSize(new Dimension(80, 40));
		deltaTimeField.setMaximumSize(new Dimension(80, 40));
		//Configuracion del Spinner de Delay - PRACTICA 3
		delaySpinner = new JSpinner(new SpinnerNumberModel(1 , 0, 1000, 1));
		delaySpinner.setPreferredSize(new Dimension(80, 40));
		delaySpinner.setMaximumSize(new Dimension(80, 40));
		//Agregado de los Componentes
		tools.add(chooseFileButton);
		tools.addSeparator();
		tools.add(chooseForceButton);
		tools.addSeparator();
		tools.add(startButton);
		tools.add(stopButton);
		tools.add(new JLabel("Delay: "));
		tools.add(delaySpinner);
		tools.add(new JLabel("Steps: "));
		tools.add(stepsSpinner);
		tools.addSeparator();
		tools.add(new JLabel("Delta-Time: "));
		tools.add(deltaTimeField);
		tools.add(Box.createHorizontalGlue());
		tools.add(exitButton);
		tools.setPreferredSize(new Dimension(980, 60));
		this.add(tools);
	}
	//Metodos privados/protegidos
	/**
	 * Reinicia la posicion de los cuerpos y la simulacion
	 * @param archivo Archivo de ruta del JSON que contiene la informacion de los cuerpos
	 */
	protected void reload(String archivo) {
		_ctrl.reset();
		try {
			_ctrl.localBodies(new FileInputStream(archivo));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * Ejecuta la simulacion un numero determinado de pasos
	 * @param n Numero de pasos a ejecutar en la simulacion
	 */
	private void run_sim(int n) {
		if (n > 0 && !_stopped) {
			try {
				_ctrl.run(1);
			}catch(Exception e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
					JPanel error=new JPanel();
					JOptionPane.showMessageDialog(error, "Error en la simulacion","ERROR",JOptionPane.ERROR_MESSAGE);
					setStatus(true);
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
			setStatus(true);
		}
	}
	/**
	 * Ejecuta la simulacion un numero determinado de pasos
	 * @param n Numero de pasos a ejecutar en la simulacion
	 * @param delay Tiempo de retardo entre cada paso de simulacion
	 */
	private void run_sim(int n, long delay) {
		while (n > 0 && !Thread.interrupted()) {
			try {
				_ctrl.run(1);
			}catch(Exception e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						JPanel error=new JPanel();
						JOptionPane.showMessageDialog(error, "Error en la simulacion","ERROR",JOptionPane.ERROR_MESSAGE);
						setStatus(true);
						return;
					}
				});
			}
			try {
				Thread.sleep(delay);
			}catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			n--;
		}
		setStatus(true);
	}
	/**
	 * Carga una imagen en un boton
	 * @param path Ruta de la imagen que tendra el boton
	 * @return Un nuevo objeto ImageIcon con la ruta de path
	 */
	protected ImageIcon loadImage(String path) {
		return new ImageIcon(Toolkit.getDefaultToolkit().createImage(path));
	}
	/**
	 * Cambia el estado de parada y de los botones segun la variable status
	 * @param status Variable de estado para los botones, el estado de parada, el spinner y el campo de texto
	 */
	private void setStatus(boolean status) {
		_stopped = status;
		chooseFileButton.setEnabled(status);
		chooseForceButton.setEnabled(status);
		startButton.setEnabled(status);
		exitButton.setEnabled(status);
		stepsSpinner.setEnabled(status);
		delaySpinner.setEnabled(status);
		deltaTimeField.setEditable(status);
	}
	/**
	 * Actualiza el texto dentro del campo delta time
	 * @param dt Valor Delta Time del Simulador
	 */
	private void update(double dt) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				deltaTimeField.setText(""+dt);
			}
		});
	}
	//Metodos del Observador
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		update(dt);
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		update(dt);
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
	}
	@Override
	public void onDeltaTimeChanged(double dt) {
		update(dt);
	}
	@Override
	public void onForceLawsChanged(String fLawsDesc) {
	}

}
