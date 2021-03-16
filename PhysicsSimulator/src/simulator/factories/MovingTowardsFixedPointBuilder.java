package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.ForceLaws;
import simulator.model.MovingTowardsFixedPoint;
/**
 * Builder de la Ley de Movimiento hacia un punto fijo que hereda de Builder con tipo ForceLaws
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 * @see Builder
 * @see ForceLaws
 * @see MovingTowardsFixedPoint
 */
public class MovingTowardsFixedPointBuilder extends Builder<ForceLaws>{
	/**
	 * Constructor del Builder de Ley de Movimiento a un Punto Fijo, que no recibe parametro pero genera un typeTag "mtfp"
	 */
	public MovingTowardsFixedPointBuilder() {
		this.typeTag="mtfp";
		this.desc="Moviendose hacia un punto fijo";
	}
	@Override
	/**
	 * Crea una instancia de Ley de Movimiento a un Punto Fijo del JSONObject que se pasa por parametro
	 * @param info Informacion con la que se construira la Ley de Movimiento a un Punto Fijo
	 * @return Una nueva instancia de Ley de Movimiento a un Punto Fijo
	 */
	protected ForceLaws createTheInstance(JSONObject info) {
		if(info.has("g")) {
			if(info.has("c")) {
				return new MovingTowardsFixedPoint(new Vector2D(info.getJSONArray("c").getDouble(0), info.getJSONArray("c").getDouble(1)), info.getDouble("g"));
			}else {
				return new MovingTowardsFixedPoint(new Vector2D(), info.getDouble("g"));
			}
		}else {
			if(info.has("c")) {
				return new MovingTowardsFixedPoint(new Vector2D(info.getJSONArray("c").getDouble(0), info.getJSONArray("c").getDouble(1)), 9.81);
			}else {
				return new MovingTowardsFixedPoint(new Vector2D(), 9.81);
			}
		}
	}

	/**
	 * Crea un JSONObject con la informacion que debe recibir el Builder para funcionar
	 * @return Un JSONObject con la informacion que necesita el Builder para funcionar correctamente
	 */
	protected JSONObject createData() {
		JSONObject info = new JSONObject();
		info.put("c", "Punto al que se mueven los cuerpos");
		info.put("g", "Constante de Gravedad");
		return info;
	}

}
