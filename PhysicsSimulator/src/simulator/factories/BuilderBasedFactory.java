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
		int i = 0;
		T b = null;
		while (b == null && i < builders.size()) {
			
		}
		if (b == null) throw new IllegalArgumentException();
		return b;
	}

	@Override
	public List<JSONObject> getInfo() {
		//Pendiente de Programar
		return null;
	}

}
