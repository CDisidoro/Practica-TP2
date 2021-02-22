package simulator.factories;

import java.util.List;
import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T>{
	public BuilderBasedFactory(List<Builder<T>> builders) {
		//Pendiente de Programar
	}
	@Override
	public T createInstance(JSONObject info) {
		//Pendiente de Programar
		return null;
	}

	@Override
	public List<JSONObject> getInfo() {
		//Pendiente de Programar
		return null;
	}

}
