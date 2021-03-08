package simulator.factories;

import org.json.JSONObject;

import simulator.control.EpsilonEqualStates;
import simulator.control.StateComparator;

public class EpsilonEqualStateBuilder extends Builder<StateComparator>{
	public EpsilonEqualStateBuilder() {
		this.typeTag="epseq";
		this.desc="Comparador de Igualdad Modulo Epsilon";
	}
	@Override
	protected StateComparator createTheInstance(JSONObject info) {
		double eps = info.getDouble("eps");
		return new EpsilonEqualStates(eps);
	}
	
	protected JSONObject createData() {
		JSONObject info = new JSONObject();
		info.put("eps", "Modulo Epsilon");
		return info;
	}

}
