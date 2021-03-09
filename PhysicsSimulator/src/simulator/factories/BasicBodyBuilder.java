package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body>{
	//Constructor del Builder de Cuerpos Basicos, que no recibe parametro pero genera un typeTag "basic"
	public BasicBodyBuilder() {
		this.typeTag="basic";
		this.desc="Cuerpo Basico";
	}
	
	@Override
	//Crea una instancia Body del JSONObject que se pasa por parametro
	protected Body createTheInstance(JSONObject data) {
		String id= data.getString("id");
		Vector2D vel= new Vector2D(data.getJSONArray("vel"));
		Vector2D pos = new Vector2D(data.getJSONArray("pos"));
		double mass = data.getDouble("mass");
		
		return new Body(id,vel,pos,mass);
	}
	//Crea un JSONObject con la informacion que debe recibir el Builder para funcionar
	public JSONObject createData() {
		JSONObject data = new JSONObject();

		data.put("id", "Identificador");
		data.put("p", "Posicion");
		data.put("v", "Velocidad");
		data.put("m", "Masa");
		
		return data;
	}
	
}
