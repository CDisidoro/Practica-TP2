package simulator.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu.Separator;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.ForceLaws;
import simulator.model.SimulatorObserver;

@SuppressWarnings("serial")
public class ForceLawDialog extends JDialog implements SimulatorObserver{
	private int _status;
	private JLabel textoSuperior, textoComboBox;
	private JButton aceptar,cancelar;
	private JComboBox<String> leyComboBox;
	private ParametersTable tablaParam;
	private Controller ctrl;
	ForceLawDialog(Controller _ctrl, Frame parent){
		super(parent, true);
		ctrl = _ctrl;
		ctrl.addObserver(this);
		initGUI();
	}
	public void initGUI() {
		JPanel panel = new JPanel(new BorderLayout());
		//panel.setLayout(new BoxLayout(panel, BoxLayout));
		
		//Creacion del texto superior
		textoSuperior = new JLabel("Select a force law and provide values for the parametes in the Value column (default values are used for parametes with no value).");
		textoSuperior.setAlignmentX(LEFT_ALIGNMENT);
		panel.add(textoSuperior, BorderLayout.NORTH);
		panel.add(Box.createRigidArea(new Dimension(0, 100)));
		//Creacion ComboBox
		leyComboBox = new JComboBox<String>();
		for(int i=0 ; i < ctrl.getForceLawsInfo().size() ; i++) {
			leyComboBox.addItem(ctrl.getForceLawsInfo().get(i).getString("desc"));
		}
		leyComboBox.setPreferredSize(new Dimension(300, 20));
		leyComboBox.setMaximumSize(new Dimension(800, 20));
		//Creacion de la tabla de datos
		tablaParam = new ParametersTable(ctrl,leyComboBox.getSelectedItem().toString());
		//Escuchador del ComboBox cuando cambia de ley
		leyComboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				tablaParam.refresh(leyComboBox.getSelectedItem().toString());
			}
		});
		panel.add(tablaParam, BorderLayout.CENTER);
		
		//Creacion de la zona del ComboBox
		JPanel comboBoxPanel = new JPanel();
		//comboBoxPanel.setLayout(new BoxLayout(comboBoxPanel, BoxLayout.X_AXIS));
		textoComboBox = new JLabel("Force Law: ");
		comboBoxPanel.add(textoComboBox);
		comboBoxPanel.add(leyComboBox);
		//comboBoxPanel.setAlignmentX(CENTER_ALIGNMENT);
		//panel.add(comboBoxPanel);
		
		//Creacion de la zona de botones
		cancelar = new JButton("Cancel");
		aceptar = new JButton("OK");
		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		aceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//ctrl.setForceLaws(tablaParam.getForceLaw());
				dispose();
			}
		});
		
		
		
		JPanel botonPanel = new JPanel();
		//botonPanel.setLayout(new BoxLayout(botonPanel, BoxLayout.X_AXIS));
		botonPanel.add(cancelar);
		botonPanel.add(Box.createHorizontalStrut(20));
		botonPanel.add(aceptar);
		//panel.add(botonPanel);
		
		//Region Sur
		Box boxcvertical=Box.createVerticalBox();
		boxcvertical.add(comboBoxPanel);
		boxcvertical.add(Box.createVerticalStrut(10));
		boxcvertical.add(botonPanel);
		boxcvertical.add(Box.createVerticalStrut(50));
		panel.add(boxcvertical,BorderLayout.SOUTH);
		
		//Configuracion Final del Panel del JDialog
		this.add(panel);
		setPreferredSize(new Dimension(800,600));
	}
	
	public int open() {
		setLocation(getParent().getLocation().x + 50, getParent().getLocation().y + 50);
		pack();
		setVisible(true);
		return _status;
	}
	//METODOS DEL OBSERVADOR
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
