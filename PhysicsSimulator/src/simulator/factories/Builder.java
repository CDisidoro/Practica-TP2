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
	
	public JSONObject createData() { //Es el modificador de acceso correcto? (En UML es un rombo amarillo)
		//Pendiente de Programar
	}
	
	public abstract T createTheInstance(JSONObject info); //Es el modificador de acceso correcto? (Rombo amarillo en UML)
}
