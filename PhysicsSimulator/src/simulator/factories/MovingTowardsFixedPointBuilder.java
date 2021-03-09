package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws>{
	//Constructor del Builder de Ley de Movimiento a un Punto Fijo, que no recibe parametro pero genera un typeTag "mtfp"
	public MovingTowardsFixedPointBuilder() {
		this.typeTag="mtfp";
		this.desc="Moviendose hacia un punto fijo";
	}
	@Override
	//Crea una instancia de Ley de Movimiento a un Punto Fijo del JSONObject que se pasa por parametro
	protected ForceLaws createTheInstance(JSONObject info) {
		if(info.has("g")) {
			if(info.has("c")) {
				return new MovingTowardsFixedPoint(new Vector2D(info.getJSONArray("c")), info.getDouble("g"));
			}else {
				return new MovingTowardsFixedPoint(new Vector2D(), info.getDouble("g"));
			}
		}else {
			if(info.has("c")) {
				return new MovingTowardsFixedPoint(new Vector2D(info.getJSONArray("c")), 9.81);
			}else {
				return new MovingTowardsFixedPoint(new Vector2D(), 9.81);
			}
		}
	}

	//Crea un JSONObject con la informacion que debe recibir el Builder para funcionar
	protected JSONObject createData() {
		JSONObject info = new JSONObject();
		info.put("c", "Punto al que se mueven los cuerpos");
		info.put("g", "Constante de Gravedad");
		return info;
	}

}
