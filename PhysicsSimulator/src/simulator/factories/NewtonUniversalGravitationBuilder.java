package simulator.factories;

import org.json.JSONObject;
import simulator.model.ForceLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<ForceLaws>{
	public NewtonUniversalGravitationBuilder() {
		this.typeTag="nlug";
		this.desc="Ley de Fuerza Gravitacional de Newton";
	}

	@Override
	protected ForceLaws createTheInstance(JSONObject info) {
		if(info.has("G")) {
			return new NewtonUniversalGravitation(info.getDouble("G"));
		}
		return new NewtonUniversalGravitation();
	}
	
	protected JSONObject createData() {
		JSONObject info = new JSONObject();
		info.put("G", "Constante de gravedad");
		return info;
	}
	
}
