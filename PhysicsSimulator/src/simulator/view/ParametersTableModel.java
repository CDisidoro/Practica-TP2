package simulator.view;

import javax.swing.table.AbstractTableModel;
import org.json.JSONArray;
import org.json.JSONObject;
import simulator.control.Controller;
/**
 * Modelo de la Tabla que contiene la informacion de las leyes fisicas disponibles
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 *
 */
@SuppressWarnings("serial")
public class ParametersTableModel extends AbstractTableModel{
	private String[] column = {"Key", "Value", "Description"};
	private String comboItem;
	private String[] keys = new String[2];
	private Controller ctrl;
	private JSONObject data, lawReturn = new JSONObject();
	private int currentLaw;
	/**
	 * Inicializa el modelo de la tabla de parametros del cuadro de dialogo
	 * @param ctrl Controlador del simulador fisico
	 * @param comboBoxItem Ley fisica seleccionada en el JComboBox del cuadro de dialogo
	 */
	ParametersTableModel(Controller ctrl, String comboBoxItem){
		comboItem = comboBoxItem;
		this.ctrl = ctrl;
		keys[0] = "";
		keys[1] = "";
		searchForceLaw();
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
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		keys[rowIndex] = aValue.toString();
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
			o = keys[rowIndex];
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
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == 1) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Resetea los datos dentro de la tabla cuando se cambia la ley elegida en el comboBox
	 * @param comboBoxItem Nueva ley fisica elegida en el comboBox
	 */
	protected void refresh(String comboBoxItem) {
		comboItem = comboBoxItem;
		keys[0] = "";
		keys[1] = "";
		searchForceLaw();
		fireTableStructureChanged();
	}
	/**
	 * Busca la ley de fuerza seleccionada por el comboBox, que sea coincidente con alguna de las leyes fisicas del simulador
	 */
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
	/**
	 * Crea el objeto JSON de retorno para el controlador del simulador, para modificar su ley fisica
	 * @return Un objeto JSON que contiene toda la informacion de la nueva ley fisica a cargar
	 */
	protected JSONObject getLaw() {
		JSONObject law = new JSONObject();
		JSONArray coords = new JSONArray();
		lawReturn.put("type", ctrl.getForceLawsInfo().get(currentLaw).getString("type"));
		try {
			for(int i=0; i < data.names().length() ;i++) {
				if(data.names().getString(i).equals("c")) {
					try {
						coords.put(Double.parseDouble(keys[i].split(",")[0]));
						coords.put(Double.parseDouble(keys[i].split(",")[1]));
					}catch(Exception e) {
						System.out.println("Error cargando las coordenadas para Punto Fijo");
					}
					law.put(data.names().getString(i), coords);
				}else {
					try {
						law.put(data.names().getString(i), Double.parseDouble(keys[i]));
					}catch(Exception e) {
						System.out.println("Error creando el JSONObject de la Ley de fuerza");
					}
				}
			}
		}catch(Exception e) {
			System.out.println("Se ha elegido ley Sin Fuerza");
		}
		lawReturn.put("data", law);
		System.out.println(lawReturn.toString());
		return lawReturn;
	}
}
