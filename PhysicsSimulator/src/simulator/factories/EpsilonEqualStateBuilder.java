package simulator.factories;

import org.json.JSONObject;

import simulator.control.EpsilonEqualStates;
import simulator.control.StateComparator;

public class EpsilonEqualStateBuilder extends Builder<StateComparator>{
	//Constructor del Builder del Comparador Modulo Epsilon, que no recibe parametros pero genera un typeTag "epseq"
	public EpsilonEqualStateBuilder() {
		this.typeTag="epseq";
		this.desc="Comparador de Igualdad Modulo Epsilon";
	}
	@Override
	//Crea una instancia de un Comparador Modulo Epsilon con el JSONObject facilitado
	protected StateComparator createTheInstance(JSONObject info) {
		if(info.has("eps")) {
			return new EpsilonEqualStates(info.getDouble("eps"));
		}
		return new EpsilonEqualStates(0.0);
	}
	//Crea un JSONObject con la informacion que debe recibir el Builder para funcionar
	protected JSONObject createData() {
		JSONObject info = new JSONObject();
		info.put("eps", "Modulo Epsilon");
		return info;
	}

}
