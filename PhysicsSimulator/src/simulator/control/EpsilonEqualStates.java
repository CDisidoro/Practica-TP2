package simulator.control;

import org.json.JSONObject;

public class EpsilonEqualStates implements StateComparator{
	protected double eps;
	
	public EpsilonEqualStates(double eps) {
		this.eps = eps;
	}
	
	@Override
	public boolean equal(JSONObject s1, JSONObject s2) {
		// Pendiente de Programar
		return false;
	}

}
