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

public class StatusBar extends JPanel implements SimulatorObserver{

	private JLabel ctTime,nBodies,Laws;
	
	public StatusBar(Controller ctrl) {
		// TODO Auto-generated constructor stub
		initGUI();
		ctrl.addObserver(this);
		
	}
	
	private void initGUI() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createBevelBorder(1));
		
		ctTime = new JLabel("");
		nBodies = new JLabel("");
		Laws = new JLabel("");
		
		add(new JLabel("Time: "));
		add(ctTime);
		add(Box.createRigidArea(new Dimension(25, 0)));
		
		add(new JLabel("Bodies: "));
		add(nBodies);
		add(Box.createRigidArea(new Dimension(25, 0)));
		
		add(new JLabel("Laws: "));
		add(Laws);
	}
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ctTime.setText(""+time);
				nBodies.setText(""+bodies.size());
				Laws.setText(fLawsDesc);
			}
		});
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ctTime.setText(""+time);
				nBodies.setText(""+bodies.size());
				Laws.setText(fLawsDesc);
			}
		});
		
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				nBodies.setText(""+bodies.size());
			}
		});
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ctTime.setText(""+time);
				nBodies.setText(""+bodies.size());
			}
		});
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Laws.setText(fLawsDesc);
			}
		});
	}

}
