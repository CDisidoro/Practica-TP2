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
		Iterator<Body> iterador = bod.iterator();
		Body bi;
		while(iterador.hasNext()) {
			bi=iterador.next();
			if(bi.id.equals(newBody.id))
			throw new IllegalArgumentException();
			else
			bod.add(newBody);
		}
	}
	
	public void advance() {
		// Pendiente de Programar
		Iterator<Body> iterator = bod.iterator();
		Body bi;
		while(iterator.hasNext()) {
			bi= iterator.next();
			bi.resetForce();
		}
		law.apply(bod);
		Iterator<Body> iterator2 = bod.iterator();
		Body bi2;
		while(iterator2.hasNext()) {
			bi2= iterator2.next();
			bi2.move(timePerStep);
		}
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
