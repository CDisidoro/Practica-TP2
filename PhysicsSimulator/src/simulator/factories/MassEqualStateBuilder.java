package simulator.factories;

import org.json.JSONObject;
import simulator.control.MassEqualStates;
import simulator.control.StateComparator;

public class MassEqualStateBuilder extends Builder<StateComparator>{
	//Constructor del Builder del Comparador con Masas, que no recibe parametros pero genera un typeTag "masseq"
	public MassEqualStateBuilder() {
		this.typeTag="masseq";
		this.desc="Comparador de igualdad de masas";
	}
	@Override
	//Crea un JSONObject con la informacion que debe recibir el Builder para funcionar
	protected StateComparator createTheInstance(JSONObject info) {
		return new MassEqualStates();
	}

}
