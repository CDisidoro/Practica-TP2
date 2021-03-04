package simulator.factories;

import java.util.List;
import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T>{
	protected List<Builder<T>> builders;
	public BuilderBasedFactory(List<Builder<T>> builders) {
		this.builders = builders;
	}
	@Override
	public T createInstance(JSONObject info) throws IllegalArgumentException{
		//Pendiente de Programar
		
		return null;
	}

	@Override
	public List<JSONObject> getInfo() {
		//Pendiente de Programar
		return null;
	}

}
