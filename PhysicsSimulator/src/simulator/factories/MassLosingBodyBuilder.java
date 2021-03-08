package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MassLosingBody;

public class MassLosingBodyBuilder extends Builder<Body>{
	public MassLosingBodyBuilder() {
		this.typeTag="mlb";
		this.desc="Cuerpo que pierde Masa";
	}
	@Override
	protected Body createTheInstance(JSONObject data) {
		String id = data.getString("id");
		Vector2D vel= new Vector2D(data.getJSONArray("vel"));
		Vector2D pos = new Vector2D(data.getJSONArray("pos"));
	    double mass = data.getDouble("mass");
	    double lossFrequency = data.getDouble("freq");
	    double lossFactor = data.getDouble("factor");
	    return new MassLosingBody(id, pos, vel, mass, lossFactor, lossFrequency);
	}
	
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
