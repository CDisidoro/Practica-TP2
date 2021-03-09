package simulator.factories;

import org.json.JSONObject;
import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{
	//Constructor del Builder de Ley de Newton, que no recibe parametro pero genera un typeTag "nlug"
	public NewtonUniversalGravitationBuilder() {
		this.typeTag="nlug";
		this.desc="Ley de Fuerza Gravitacional de Newton";
	}

	@Override
	//Crea una instancia de Ley de Newton del JSONObject que se pasa por parametro
	protected ForceLaws createTheInstance(JSONObject info) {
		if(info.has("G")) {
			return new NewtonUniversalGravitation(info.getDouble("G"));
		}
		return new NewtonUniversalGravitation(6.67E10-11);
	}

	//Crea un JSONObject con la informacion que debe recibir el Builder para funcionar
	protected JSONObject createData() {
		JSONObject info = new JSONObject();
		info.put("G", "Constante de gravedad");
		return info;
	}
	
}
