package simulator.factories;

import org.json.JSONObject;
import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;
/**
 * Builder de la Ley de Gravitacion Universal de Newton que hereda de Builder con tipo ForceLaws
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 * @see Builder
 * @see ForceLaws
 * @see NewtonUniversalGravitation
 */
public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{
	/**
	 * Constructor del Builder de Ley de Newton, que no recibe parametro pero genera un typeTag "nlug"
	 */
	public NewtonUniversalGravitationBuilder() {
		this.typeTag="nlug";
		this.desc="Newton's Law of Universal Gravitation";
	}

	@Override
	/**
	 * Crea una instancia de Ley de Newton del JSONObject que se pasa por parametro
	 * @param info Informacion con la que va a construirse la instancia de la Ley de Newton
	 * @return Una nueva instancia de la Ley de Newton
	 */
	protected ForceLaws createTheInstance(JSONObject info) {
		if(info.has("G")) {
			return new NewtonUniversalGravitation(info.getDouble("G"));
		}
		return new NewtonUniversalGravitation(6.67E-11);
	}

	/**
	 * Crea un JSONObject con la informacion que debe recibir el Builder para funcionar
	 * @return Un JSONObject con la informacion necesaria para el funcionamiento del Builder
	 */
	protected JSONObject createData() {
		JSONObject info = new JSONObject();
		info.put("G", "The gravitation constant (a number)");
		return info;
	}
	
}
