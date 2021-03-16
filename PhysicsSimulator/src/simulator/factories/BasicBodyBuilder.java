package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector2D;
import simulator.model.Body;
/**
 * Builder de Cuerpos Básicos
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 */
public class BasicBodyBuilder extends Builder<Body>{
	/**
	 * Constructor del Builder de Cuerpos Basicos, que no recibe parametro pero genera un typeTag "basic"
	 */
	public BasicBodyBuilder() {
		this.typeTag="basic";
		this.desc="Cuerpo Basico";
	}
	
	@Override
	/**
	 * Crea una instancia Body del JSONObject que se pasa por parametro
	 * @param data Datos que permitiran crear la instancia de un Body
	 * @return Un cuerpo básico nuevo con los parámetros de data
	 */
	protected Body createTheInstance(JSONObject data) {
		String id= data.getString("id");
		Vector2D vel= new Vector2D(data.getJSONArray("v").getDouble(0), data.getJSONArray("v").getDouble(1));
		Vector2D pos = new Vector2D(data.getJSONArray("p").getDouble(0), data.getJSONArray("p").getDouble(1));
		double mass = data.getDouble("m");
		
		return new Body(id,pos,vel,mass);
	}
	/**
	 * Crea un JSONObject con la informacion que debe recibir el Builder para funcionar
	 * @return Un JSONobject que describe la información que necesita este Builder para funcionar
	 */
	public JSONObject createData() {
		JSONObject data = new JSONObject();

		data.put("id", "Identificador");
		data.put("p", "Posicion");
		data.put("v", "Velocidad");
		data.put("m", "Masa");
		
		return data;
	}
	
}
