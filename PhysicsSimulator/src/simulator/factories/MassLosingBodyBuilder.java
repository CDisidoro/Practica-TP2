package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
import simulator.model.MassLosingBody;

public class MassLosingBodyBuilder extends Builder<Body>{
	//Constructor del Builder de Cuerpos Basicos, que no recibe parametro pero genera un typeTag "mlb"
	public MassLosingBodyBuilder() {
		this.typeTag="mlb";
		this.desc="Cuerpo que pierde Masa";
	}
	@Override
	//Crea una instancia MassLosingBody del JSONObject que se pasa por parametro
	protected MassLosingBody createTheInstance(JSONObject data) {
		String id = data.getString("id");
		Vector2D vel= new Vector2D(data.getJSONArray("v"));
		Vector2D pos = new Vector2D(data.getJSONArray("p"));
	    double mass = data.getDouble("m");
	    double lossFrequency = data.getDouble("freq");
	    double lossFactor = data.getDouble("factor");
	    return new MassLosingBody(id, pos, vel, mass, lossFactor, lossFrequency);
	}

	//Crea un JSONObject con la informacion que debe recibir el Builder para funcionar
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
