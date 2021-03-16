package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MassLosingBody;
/**
 * Builder para cuerpos que pierden masa que hereda de Builder con tipo Body
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 * @see Builder
 * @see Body
 * @see MassLosingBody
 */
public class MassLosingBodyBuilder extends Builder<Body>{
	/**
	 * Constructor del Builder de Cuerpos Basicos, que no recibe parametro pero genera un typeTag "mlb"
	 */
	public MassLosingBodyBuilder() {
		this.typeTag="mlb";
		this.desc="Cuerpo que pierde Masa";
	}
	@Override
	/**
	 * Crea una instancia MassLosingBody del JSONObject que se pasa por parametro
	 * @param data Datos con los que se creara el Cuerpo que pierde masa
	 * @return Una instancia de un Cuerpo que pierde masa
	 */
	protected MassLosingBody createTheInstance(JSONObject data) {
		String id = data.getString("id");
		Vector2D vel= new Vector2D(data.getJSONArray("v").getDouble(0), data.getJSONArray("v").getDouble(1));
		Vector2D pos = new Vector2D(data.getJSONArray("p").getDouble(0), data.getJSONArray("p").getDouble(1));
	    double mass = data.getDouble("m");
	    double lossFrequency = data.getDouble("freq");
	    double lossFactor = data.getDouble("factor");
	    return new MassLosingBody(id, pos, vel, mass, lossFactor, lossFrequency);
	}

	/**
	 * Crea un JSONObject con la informacion que debe recibir el Builder para funcionar
	 * @return Un JSONObject con la informacion necesaria para que el Builder funcione correctamente
	 */
	protected JSONObject createData() {
		JSONObject info = new JSONObject();
		info.put("id", "Identificador");
		info.put("v", "Velocidad");
		info.put("p", "Posicion");
		info.put("m", "Masa");
		info.put("freq", "Frecuencia de perdida");
		info.put("factor", "Factor de perdida");
		return info;
	}
	
}
