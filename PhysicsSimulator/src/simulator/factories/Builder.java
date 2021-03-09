package simulator.factories;

import org.json.JSONObject;

public abstract class Builder<T> {
	protected String typeTag, desc;
	//Crea una instancia del Objeto de tipo T (generico)	
	public T createInstance(JSONObject info) throws IllegalArgumentException {
		/* Inicializamos el objeto a retornar como nulo y, si el typeTag no es nulo y coincide con el valor en la clave type
		 * del JSONObject recibido por parametro entonces se creara la instancia del Objeto almacenado con la clave data.
		 * En caso contrario se retornara el null original*/
		 T b = null;
		 if (typeTag != null && typeTag.equals(info.getString("type")))
			 b= createTheInstance(info.getJSONObject("data"));
		 return b;
	}
	//Obtiene la informaci�n del constructor
	public JSONObject getBuilderInfo() {
		/*Se crea el JSONObject a retornar y carga en este el typeTag, los datos del builder y su descripcion*/
		JSONObject info = new JSONObject();
		 info.put("type", typeTag);
		 info.put("data", createData());
		 info.put("desc", desc);
		 return info;
	}
	//Crea un JSONObject con la informaci�n relacionada a ese constructor
	protected JSONObject createData() {
		//Retorna un nuevo JSONObject Vac�o (Se rellena en sus clases hijas)
		return new JSONObject();
	}
	//La funci�n abstracta generica que usar� cada Builder de los modelos disponibles en el simulador
	protected abstract T createTheInstance(JSONObject info);
}
