package simulator.view;

import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

@SuppressWarnings("serial")
public class ParametersTableModel extends AbstractTableModel implements SimulatorObserver{
	private String[] column = {"Key", "Value", "Description"};
	private String comboItem;
	private Controller ctrl;
	private String [] lawObj = {};
	ParametersTableModel(Controller ctrl, String comboBoxItem){
		comboItem = comboBoxItem;
		initGUI();
		this.ctrl = ctrl;
		ctrl.addObserver(this);
	}
	//METODOS DE ABSTRACTTABLEMODEL
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getColumnCount() {
		return column.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getColumnName(int columna) {
		return column[columna];
	}
	//METODOS DE OBSERVER
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
	//METODOS PERSONALES
	private void update(String fLawDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				fireTableStructureChanged();
			}
		});
	}
	private void initGUI() {
		for(int i=0 ; i < ctrl.getForceLawsInfo().size() ; i++) {
			if(comboItem.equalsIgnoreCase(ctrl.getForceLawsInfo().get(i).getString("desc"))) {
				lawObj[i] = ctrl.getForceLawsInfo().get(i).getString("data");
			}
		}
	}
}
