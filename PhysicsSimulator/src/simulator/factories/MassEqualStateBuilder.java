package simulator.factories;

import org.json.JSONObject;
import simulator.control.MassEqualStates;
import simulator.control.StateComparator;

public class MassEqualStateBuilder extends Builder<StateComparator>{
	public MassEqualStateBuilder() {
		this.typeTag="masseq";
		this.desc="Comparador de igualdad de masas";
	}
	@Override
	protected StateComparator createTheInstance(JSONObject info) {
		return new MassEqualStates();
	}

}
