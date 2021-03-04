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
		/*typeTag = info.getString("type");
		switch(typeTag) {
		case "basic":
			
			break;
		case "mlb":
			
			break;
		default:
			return null;
		}*/
		 T b = null;
		 if (typeTag != null && typeTag.equals(info.getString("type")))
			 return null;//b = ? crear un objeto de tipo 
		 return b;
	}
	
	public JSONObject getBuilderInfo() {
		JSONObject info = new JSONObject();
		 info.put("type", typeTag);
		 info.put("data", new JSONObject());
		 info.put("desc", desc);
		 return info;
	}
	
	protected JSONObject createData() {
		//Pendiente de Programar
	}
	
	protected abstract T createTheInstance(JSONObject info);
}
