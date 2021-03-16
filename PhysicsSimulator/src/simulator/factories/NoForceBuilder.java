package simulator.factories;

import org.json.JSONObject;
import simulator.model.ForceLaws;
import simulator.model.NoForce;
/**
 * Builder de la Ley Sin Fuerza que hereda de Builder con tipo ForceLaws
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 * @see Builder
 * @see ForceLaws
 * @see NoForce
 */
public class NoForceBuilder extends Builder<ForceLaws>{
	/**
	 * Constructor del Builder de Ley Sin Fuerza, que no recibe parametro pero genera un typeTag "ng"
	 */
	public NoForceBuilder() {
		this.typeTag="ng"; //Deberia ser nf
		this.desc="Sin Fuerza";
	}
	@Override
	/**
	 * Crea una instancia de Ley Sin Fuerza del JSONObject que se pasa por parametro
	 * @param info Informacion con la que se construye la instancia (No se usa nada)
	 * @return Una nueva instancia de la Ley Sin Fuerza
	 */
	protected ForceLaws createTheInstance(JSONObject info) {
		return new NoForce();
	}

}
