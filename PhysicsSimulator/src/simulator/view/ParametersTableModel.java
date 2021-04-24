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
	private JSONObject data;
	private int currentLaw;
	ParametersTableModel(Controller ctrl, String comboBoxItem){
		comboItem = comboBoxItem;
		this.ctrl = ctrl;
		searchForceLaw();
		ctrl.addObserver(this);
	}
	//METODOS DE ABSTRACTTABLEMODEL
	@Override
	public int getRowCount() {
		return data.length();
	}
	@Override
	public int getColumnCount() {
		return column.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String o = null;
		switch(columnIndex) {
		case 0:
			if(data.names().length() > 0) {
				o = data.names().get(rowIndex).toString();
			}
			break;
		case 1:
			break;
		case 2:
			if(currentLaw == 0) {
				o = data.getString("G");
			}else if(currentLaw == 1) {
				if(rowIndex == 0) {
					o = data.getString("c");
				}else {
					o = data.getString("g");
				}
			}
			break;
		}
		return o;
	}
	@Override
	public String getColumnName(int columna) {
		return column[columna];
	}
	//METODOS DE OBSERVER
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc) {
		update(fLawsDesc);
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc) {
		update(fLawsDesc);
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
	}
	@Override
	public void onDeltaTimeChanged(double dt) {
	}
	@Override
	public void onForceLawsChanged(String fLawsDesc) {
		update(fLawsDesc);
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
	protected void refresh(String comboBoxItem) {
		comboItem = comboBoxItem;
		searchForceLaw();
		fireTableStructureChanged();
	}
	private void searchForceLaw() {
		boolean found = false;
		int i = 0;
		while(!found && i < ctrl.getForceLawsInfo().size()) {
			if(comboItem.equalsIgnoreCase(ctrl.getForceLawsInfo().get(i).getString("desc"))) {
				data = ctrl.getForceLawsInfo().get(i).getJSONObject("data");
				currentLaw = i;
				found = true;
			}
			i++;
		}
	}
	protected JSONObject getLaw() {
		JSONObject law = new JSONObject();
		
		return law;
	}
}
