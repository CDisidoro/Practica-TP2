package simulator.factories;

import java.util.List;

import org.json.JSONObject;

public interface Factory<T> {
	//Crea una instancia tipo T del Objeto que se le pasa por parametro. Si los parametros del JSONObject no coinciden con un
	//modelo conocido, se lanza una IllegalArgumentException
	public T createInstance(JSONObject info) throws IllegalArgumentException;
	//Devuelve una lista de JSONObjects con la informacion de las factorias
	public List<JSONObject> getInfo();
}
