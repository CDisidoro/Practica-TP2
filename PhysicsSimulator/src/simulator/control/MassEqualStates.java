package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;

public class MassEqualStates implements StateComparator{
	//Constructor del Comparador de Estados por Masa, que no recibe parametros
	public MassEqualStates() {
	}
	
	@Override
	//Comprueba si dos estados del simulador son iguales según su masa
	public boolean equal(JSONObject s1, JSONObject s2) {
		//Obtengo el array de Bodies del Object
		JSONArray arrayBodies1 = new JSONArray();
		JSONArray arrayBodies2 = new JSONArray();
		arrayBodies1 = s1.getJSONArray("bodies");
		arrayBodies2 = s2.getJSONArray("bodies");
		//Si tienen longitudes distintas se sabe que es falso
		if(arrayBodies1.length() != arrayBodies2.length()) {
			return false;
		}else {
			//Si tienen la misma longitud recorrera los array
			for(int i=0; i<arrayBodies1.length(); i++) {
				//Obtendra el id y masa de cada JSONObject en su posicion i y les comparara
				//Si no se cumplen las condiciones retornara falso
				if(! (arrayBodies1.getJSONObject(i).getString("id").equals(arrayBodies2.getJSONObject(i).getString("id")) &&
						arrayBodies1.getJSONObject(i).getDouble("m") == arrayBodies2.getJSONObject(i).getDouble("m")) ) {
					return false;
				}
			}
		}
		return (s1.getDouble("time") == s2.getDouble("time"));
	}

}
