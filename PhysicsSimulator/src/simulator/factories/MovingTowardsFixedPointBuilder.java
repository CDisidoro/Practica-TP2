package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;

public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws>{
	public MovingTowardsFixedPointBuilder() {
		this.typeTag="mtcp";
		this.desc="Moviendose hacia un punto fijo";
	}
	@Override
	protected ForceLaws createTheInstance(JSONObject info) {
		Vector2D point = new Vector2D(info.getJSONArray("c"));
		if(info.has("g")) {
			return new MovingTowardsFixedPoint(point, info.getDouble("g"));
		}
		return new MovingTowardsFixedPoint(point);
	}
	
	protected JSONObject createData() {
		JSONObject info = new JSONObject();
		info.put("c", "Punto al que se mueven los cuerpos");
		info.put("g", "Constante de Gravedad");
		return info;
	}

}
