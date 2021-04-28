package simulator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

/**
 * Barra de Estados del Simulador Fisico
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 * @see JPanel
 * @see SimulatorObserver
 */
@SuppressWarnings("serial")
public class StatusBar extends JPanel implements SimulatorObserver{

	private JLabel _currTime,_numOfBodies,_currLaws;
	/**
	 * Constructor de la Barra de estados que recibe el controlador
	 * @param ctrl Controlador del Simulador Fisico
	 */
	public StatusBar(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
		
	}
	/**
	 * Inicializacion de la barra de estados del Simulador Fisico
	 */
	private void initGUI() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createBevelBorder(1));
		
		_currTime = new JLabel("");
		_numOfBodies = new JLabel("");
		_currLaws = new JLabel("");
		
		add(new JLabel("Time: "));
		add(_currTime);
		add(Box.createRigidArea(new Dimension(25, 0)));
		
		add(new JLabel("Bodies: "));
		add(_numOfBodies);
		add(Box.createRigidArea(new Dimension(25, 0)));
		
		add(new JLabel("Laws: "));
		add(_currLaws);
	}
	//METODOS DEL OBSERVADOR
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_currTime.setText(""+time);
				_numOfBodies.setText(""+bodies.size());
				_currLaws.setText(fLawsDesc);
			}
		});
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_currTime.setText(""+time);
				_numOfBodies.setText(""+bodies.size());
				_currLaws.setText(fLawsDesc);
			}
		});
		
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_numOfBodies.setText(""+bodies.size());
			}
		});
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_currTime.setText(""+time);
				_numOfBodies.setText(""+bodies.size());
			}
		});
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
	}

	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_currLaws.setText(fLawsDesc);
			}
		});
	}

}
