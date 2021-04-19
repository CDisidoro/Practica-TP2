package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

@SuppressWarnings("serial")
/**
 * Modelo de la tabla de estado de los cuerpos del simulador
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 */
public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver{
	private List<Body> _bodies;
	private String [] columnas = {"ID", "Mass", "Position", "Velocity", "Force"};
	/**
	 * Constructor del Modelo de la tabla de Cuerpos
	 * @param ctrl Controlador del Simulador
	 */
	BodiesTableModel(Controller ctrl){
		_bodies = new ArrayList<>();
		ctrl.addObserver(this);
	}
	//Metodos de AbstractTableModel
	@Override
	public int getRowCount() {
		return _bodies.size();
	}
	@Override
	public int getColumnCount() {
		return columnas.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String o = null;
		switch(columnIndex) {
		case 0:
			o = _bodies.get(rowIndex).getId();
			break;
		case 1:
			o = ""+_bodies.get(rowIndex).getMass();
			break;
		case 2:
			o = _bodies.get(rowIndex).getPosition().toString();
			break;
		case 3:
			o = _bodies.get(rowIndex).getVelocity().toString();
			break;
		case 4:
			o = _bodies.get(rowIndex).getForce().toString();
			break;
		}
		return o;
	}
	@Override
	public String getColumnName(int columna) {
		return columnas[columna];
	}
	//Metodos de Observers
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		update(bodies);
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		update(bodies);
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		update(bodies);
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		update(bodies);
	}
	@Override
	public void onDeltaTimeChanged(double dt) {
	}
	@Override
	public void onForceLawsChanged(String fLawsDesc) {
	}
	//Metodos Personales
	private void update(List<Body> bod) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_bodies = bod;
				fireTableStructureChanged();
			}
		});
	}
} 
