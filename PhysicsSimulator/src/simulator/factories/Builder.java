package simulator.factories;

import org.json.JSONObject;
/**
 * Clase que permitira Construir instancias de los objetos que maneja el simulador
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 * @param <T> Tipo de Objeto que recibe el Builder
 */
public abstract class Builder<T> {
	protected String typeTag, desc;
	/**
	 * Crea una instancia del Objeto de tipo T (generico)	
	 * @param info Informacion con la que se intenta crear una instancia de un objeto
	 * @return Un objeto tipo T construido segun los datos de info
	 * @throws IllegalArgumentException Si no se puede crear ningun objeto con la info pasada, dara una IllegalArgumentException
	 */
	public T createInstance(JSONObject info) throws IllegalArgumentException {
		/* Inicializamos el objeto a retornar como nulo y, si el typeTag no es nulo y coincide con el valor en la clave type
		 * del JSONObject recibido por parametro entonces se creara la instancia del Objeto almacenado con la clave data.
		 * En caso contrario se retornara el null original*/
		 T b = null;
		 if (typeTag != null && typeTag.equals(info.getString("type")))
			 b= createTheInstance(info.getJSONObject("data"));
		 return b;
	}
	/**
	 * Obtiene la información del constructor
	 * @return Un JSONObject con la información necesaria para el funcionamiento del Constructor
	 */
	public JSONObject getBuilderInfo() {
		/*Se crea el JSONObject a retornar y carga en este el typeTag, los datos del builder y su descripcion*/
		JSONObject info = new JSONObject();
		 info.put("type", typeTag);
		 info.put("data", createData());
		 info.put("desc", desc);
		 return info;
	}
	/**
	 * Crea un JSONObject con la información relacionada a ese constructor
	 * @return Un JSONObject que contiene la informacion necesaria para que el constructor funcione
	 */
	protected JSONObject createData() {
		//Retorna un nuevo JSONObject Vacío (Se rellena en sus clases hijas)
		return new JSONObject();
	}
	/**
	 * La función abstracta generica que usará cada Builder de los modelos disponibles en el simulador
	 * @param info La informacion que debe recibir el Builder para intentar construir un objeto
	 * @return Un objeto T nuevo
	 */
	protected abstract T createTheInstance(JSONObject info);
}
