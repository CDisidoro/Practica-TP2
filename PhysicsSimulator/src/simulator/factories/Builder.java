package simulator.factories;

import org.json.JSONObject;

public abstract class Builder<T> {
	protected String typeTag, desc;
		
	public T createInstance(JSONObject info) throws IllegalArgumentException {
		 T b = null;
		 if (typeTag != null && typeTag.equals(info.getString("type")))
			 b= createTheInstance(info.getJSONObject("data"));
		 return b;
	}
	
	public JSONObject getBuilderInfo() {
		JSONObject info = new JSONObject();
		 info.put("type", typeTag);
		 info.put("data", createData());
		 info.put("desc", desc);
		 return info;
	}
	
	protected JSONObject createData() {
		return new JSONObject();
	}
	
	protected abstract T createTheInstance(JSONObject info);
}
