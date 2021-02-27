package simulator.control;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import simulator.model.Body;

public class MassEqualStates implements StateComparator{

	public MassEqualStates() {
		// Pendiente de Programar
	}
	
	@Override
	public boolean equal(JSONObject s1, JSONObject s2) {
		boolean masasIguales = true;
		//Obtengo el array de Bodies del Object
		JSONArray arrayBodies1 = new JSONArray();
		JSONArray arrayBodies2 = new JSONArray();
		arrayBodies1 = s1.getJSONArray("bodies");
		arrayBodies2 = s2.getJSONArray("bodies");
		//Creo el iterador para recorrer el array
		Iterator<Object> itS1 = arrayBodies1.iterator();
		Iterator<Object> itS2 = arrayBodies2.iterator();
		//Inicio un body unitario, pues no me interesa guardar todos los bodies del array
		Body b1,b2;
		//Mientras ambos tengan elementos todavía, se ejecutara el bucle
		while(itS1.hasNext() && itS2.hasNext()) {
			b1 = (Body) itS1.next();
			b2 = (Body) itS2.next();
			//Si el id o la masa de los cuerpos es distinta del otro, se hara falso que son iguales
			if(b1.getId() != b2.getId() || b1.getMass() != b2.getMass()) {
				masasIguales = false;
			}
		}
		return (s1.getDouble("time") == s2.getDouble("time") && masasIguales);
	}

}
