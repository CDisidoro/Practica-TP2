package simulator.control;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import simulator.model.Body;

public class EpsilonEqualStates implements StateComparator{
	protected double eps;
	
	public EpsilonEqualStates(double eps) {
		this.eps = eps;
	}
	
	@Override
	public boolean equal(JSONObject s1, JSONObject s2) {
		boolean igualModEps = true;
		//Obtengo el array de Bodies del Object
		JSONArray arrayBodies1 = new JSONArray();
		JSONArray arrayBodies2 = new JSONArray();
		//Usar un array con su longitud directamente en vez de usar iterador
		arrayBodies1 = s1.getJSONArray("bodies");
		arrayBodies2 = s2.getJSONArray("bodies");
		//Creo el iterador para recorrer el array
		Iterator<Object> itS1 = arrayBodies1.iterator();
		Iterator<Object> itS2 = arrayBodies2.iterator();
		JSONObject b1,b2;
		//Si las longitudes son distinas entonces se sabe que son distintos
		if(arrayBodies1.length() != arrayBodies2.length()) {
			return false;
		}else {
			while(itS1.hasNext() && itS2.hasNext()) { //No se puede convertir a body, hay que usar JSONObject
				b1 = s1.getJSONObject(((Body) itS1.next()).getId());
				b2 = s2.getJSONObject(((Body) itS1.next()).getId());
				
				/*
				if(b1.getId() != b2.getId() || Math.abs(b1.getMass() - b2.getMass()) > eps || 
						b1.getPosition().distanceTo(b2.getPosition()) > eps || b1.getVelocity().distanceTo(b2.getVelocity()) > eps
						|| b1.getForce().distanceTo(b2.getForce()) > eps) {
					igualModEps = false;
				}*/
			}
			return (s1.getDouble("time") == s2.getDouble("time") && igualModEps);
		}
		}

}
