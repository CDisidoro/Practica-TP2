package simulator.factories;

import org.json.JSONObject;

public abstract class Builder<T> {
	protected String typeTag, desc;
	public Builder(String typeTag, String desc) {
		this.typeTag = typeTag;
		this.desc = desc;
	}
	
	public T createInstance(JSONObject info) throws IllegalArgumentException {
		//Pendiente de Programar
		typeTag = info.getString("type");
		switch(typeTag) {
		case "basic":
			
			break;
		case "mlb":
			
			break;
		default:
			return null;
		}
	}
	
	public JSONObject getBuilderInfo() {
		//Pendiente de Programar
	}
	
	protected JSONObject createData() {
		//Pendiente de Programar
	}
	
	protected abstract T createTheInstance(JSONObject info);
}
