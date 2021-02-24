package simulator.factories;

import org.json.JSONObject;

public abstract class Builder<T> {
	public Builder() {
		
	}
	
	public T createInstance (JSONObject info) {
		//Pendiente de Programar
	}
	
	public JSONObject getBuilderInfo() {
		//Pendiente de Programar
	}
	
	protected JSONObject createData() {
		//Pendiente de Programar
	}
	
	protected abstract T createTheInstance(JSONObject info);
}
