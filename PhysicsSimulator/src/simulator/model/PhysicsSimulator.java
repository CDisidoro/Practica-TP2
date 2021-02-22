package simulator.model;

import org.json.JSONObject;

public class PhysicsSimulator {
	private ForceLaws law;
	private double k;
	private Body bod;
	public PhysicsSimulator(ForceLaws law, double k) {
		this.law = law;
		this.k = k;
		//Hay que hacer algo con Body?
	}
	
	public void addBody(Body bod) {
		//Pendiente de Programar
	}
	
	public void advance() {
		// Pendiente de Programar
	}
	
	public JSONObject getState() {
		// Pendiente de Programar
	}
	
	public String toString() {
		// Pendiente de Programar
	}
}
