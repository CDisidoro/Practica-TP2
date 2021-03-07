package simulator.factories;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T>{
	protected List<Builder<T>> builders;
	
	public BuilderBasedFactory(List<Builder<T>> builders) {
		this.builders = new ArrayList<Builder<T>>(builders);
	}
	@Override
	public T createInstance(JSONObject info) throws IllegalArgumentException{
		T b = null;
		for(Builder<T> bb: builders) {
			b=bb.createInstance(info);
			if(b!=null) {
				return b;
			} 
		}
	 throw new IllegalArgumentException();
	}

	@Override
	public List<JSONObject> getInfo() {
		List<JSONObject> jobj=new ArrayList<JSONObject>();
		for(Builder<T> bb: builders) {
			jobj.add(bb.getBuilderInfo());
		}
		return jobj;
	}

}
