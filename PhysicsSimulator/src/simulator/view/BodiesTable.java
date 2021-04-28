package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import simulator.control.Controller;

/**
 * Tabla de estado de los cuerpos del simulador
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 * @see JPanel
 * @see BodiesTableModel
 */
@SuppressWarnings("serial")
public class BodiesTable extends JPanel{
	/**
	 * Constructor de la tabla de estado de los cuerpos
	 * @param ctrl Controlador del simulador Fisico
	 */
	BodiesTable(Controller ctrl){
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), 
												   "Bodies", 
												   TitledBorder.LEFT, 
												   TitledBorder.TOP));
		JTable table = new JTable(new BodiesTableModel(ctrl));
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table);
		this.add(scroll);
		this.setPreferredSize(new Dimension(800,300));
	}
}
