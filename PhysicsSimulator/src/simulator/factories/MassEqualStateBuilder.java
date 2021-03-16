package simulator.factories;

import org.json.JSONObject;
import simulator.control.MassEqualStates;
import simulator.control.StateComparator;
/**
 * Builder del Comparador de Estados segun Masa que heredan de Builder con tipo StateComparator
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 * @see Builder
 * @see StateComparator
 * @see MassEqualStates
 */
public class MassEqualStateBuilder extends Builder<StateComparator>{
	/**
	 * Constructor del Builder del Comparador con Masas, que no recibe parametros pero genera un typeTag "masseq"
	 */
	public MassEqualStateBuilder() {
		this.typeTag="masseq";
		this.desc="Comparador de igualdad de masas";
	}
	@Override
	/**
	 * Crea un JSONObject con la informacion que debe recibir el Builder para funcionar
	 * @param info Informacion con la que se debe crear el Comparador de Estado
	 * @return Un Comparador de Estados segun Masa
	 */
	protected StateComparator createTheInstance(JSONObject info) {
		return new MassEqualStates();
	}

}
