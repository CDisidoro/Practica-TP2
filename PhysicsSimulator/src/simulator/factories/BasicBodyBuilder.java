package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body>{

	public BasicBodyBuilder() {
		this.typeTag="basic";
		this.desc="Cuerpo Basico";
	}
	
	@Override
	protected Body createTheInstance(JSONObject data) {
		String id= data.getString("id");
		Vector2D vel= new Vector2D(data.getJSONArray("vel"));
		Vector2D pos = new Vector2D(data.getJSONArray("pos"));
		double mass = data.getDouble("mass");
		
		return new Body(id,vel,pos,mass);
	}
	
	public JSONObject createData() {
		JSONObject data = new JSONObject();

		data.put("id", "Identificador");
		data.put("p", "Posicion");
		data.put("v", "Velocidad");
		data.put("m", "Masa");
		
		return data;
	}
	
}
