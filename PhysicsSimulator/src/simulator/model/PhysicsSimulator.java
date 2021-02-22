package simulator.model;

import org.json.JSONObject;

public class PhysicsSimulator {
	protected ForceLaws law;
	protected double timePerStep;
	protected List<Body> bod;
	public PhysicsSimulator(ForceLaws law, double timePerStep) {
		this.law = law;
		this.timePerStep = timePerStep;
		//Hay que hacer algo con Body?
	}
	
	public void addBody(Body newBody) {
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
