package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.json.JSONObject;

import simulator.control.Controller;
/**
 * Tabla de parametros de la ley fisica a modificar en el simulador fisico
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 *
 */
@SuppressWarnings("serial")
public class ParametersTable extends JPanel{
	private ParametersTableModel modelo;
	ParametersTable(Controller ctrl, String comboBoxItem){
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1)));
		modelo = new ParametersTableModel(ctrl, comboBoxItem);
		JTable tabla = new JTable(modelo);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(tabla);
		this.add(scroll);
		this.setPreferredSize(new Dimension(600,600));
	}
	protected void refresh(String comboBoxItem) {
		modelo.refresh(comboBoxItem);
	}
	protected JSONObject getForceLaw() {
		return modelo.getLaw();
	}
}
