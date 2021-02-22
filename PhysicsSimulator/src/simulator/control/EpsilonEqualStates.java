package simulator.control;

import org.json.JSONObject;

public class EpsilonEqualStates implements StateComparator{
	private double epsilon;
	
	public EpsilonEqualStates(double epsilon) {
		this.epsilon = epsilon;
	}
	
	@Override
	public boolean equal(JSONObject s1, JSONObject s2) {
		// Pendiente de Programar
		return false;
	}

}
