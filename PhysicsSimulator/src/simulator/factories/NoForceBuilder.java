package simulator.factories;

import org.json.JSONObject;
import simulator.model.ForceLaws;
import simulator.model.NoForce;

public class NoForceBuilder extends Builder<ForceLaws>{
	//Constructor del Builder de Ley Sin Fuerza, que no recibe parametro pero genera un typeTag "ng"
	public NoForceBuilder() {
		this.typeTag="ng"; //Deberia ser nf
		this.desc="Sin Fuerza";
	}
	@Override
	//Crea una instancia de Ley Sin Fuerza del JSONObject que se pasa por parametro
	protected ForceLaws createTheInstance(JSONObject info) {
		return new NoForce();
	}

}
