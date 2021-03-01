package simulator.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator {
	protected ForceLaws law;
	protected double timePerStep, currentTime;
	protected List<Body> bod;
	public PhysicsSimulator(ForceLaws law, double timePerStep) throws IllegalArgumentException{
		this.law = law;
		this.timePerStep = timePerStep;
		bod = new ArrayList<Body>();
		currentTime = 0.0;
	}
	
	public void addBody(Body newBody) {
		if(bod.contains(newBody))
			throw new IllegalArgumentException("El cuerpo que se ha intentado añadir ya existe");
		bod.add(newBody);
	}
	
	public void advance() {
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
		currentTime = currentTime + timePerStep;
	}
	
	public JSONObject getState() {
		JSONObject estado = new JSONObject();
		JSONArray arrayBodies = new JSONArray();
		//Iterador de Bodies
		Iterator<Body> iterador = bod.iterator();
		estado.put("time", currentTime);
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
