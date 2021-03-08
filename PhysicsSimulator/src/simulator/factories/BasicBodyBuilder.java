package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body>{

	public BasicBodyBuilder() {
		this.typeTag="basic";
		this.desc="cuerpoBasico";
	}
	
	@Override
	protected Body createTheInstance(JSONObject data) {
		String id= data.getString("id");
		Vector2D vel= new Vector2D(data.getJSONArray("vel"));//como sacar el vector de data tipo JSONObject
		Vector2D pos = new Vector2D(data.getJSONArray("pos"));;
		double mass = data.getDouble("mass");
		
		return new Body(id,vel,pos,mass);
	}
	
	public JSONObject createData() {
		JSONObject data = new JSONObject();

		data.put("id", //????);///uqe pongo aqui?
		data.put("pos", //???);
		data.put("vel", );
		data.put("mass", );
		
		return data;
	}
	
}
