package simulator.model;

import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator {
	protected ForceLaws law;
	protected double timePerStep;
	protected List<Body> bod;
	public PhysicsSimulator(ForceLaws law, double timePerStep) throws IllegalArgumentException{
		this.law = law;
		this.timePerStep = timePerStep;
		//Hay que hacer algo con Body?
	}
	
	public void addBody(Body newBody) {
		bod.add(newBody);
	}
	
	public void advance() {
		// Pendiente de Programar
	}
	
	public JSONObject getState() {
		JSONObject estado = new JSONObject();
		JSONArray arrayBodies = new JSONArray();
		//Iterador de Bodies
		Iterator<Body> iterador = bod.iterator();
		estado.put("time", timePerStep);
		//Llena el array de Bodies llamando al getState del Body encontrado
		while(iterador.hasNext()) {
			arrayBodies.put(iterador.next().getState());
		}
		estado.put("bodies", arrayBodies);
		return estado;
	}
	
	public String toString() {
		return getState().toString();
	}
}
