package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import simulator.control.Controller;

@SuppressWarnings("serial")
public class ParametersTable extends JPanel{
	ParametersTable(Controller ctrl, String comboBoxItem){
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1)));
		JTable tabla = new JTable(new ParametersTableModel(ctrl, comboBoxItem));
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(tabla);
		this.add(scroll);
		this.setPreferredSize(new Dimension(600,600));
	}	
}
