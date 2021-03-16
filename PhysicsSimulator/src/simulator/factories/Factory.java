package simulator.factories;

import java.util.List;

import org.json.JSONObject;
/**
 * Interfaz para las Factorias de Objetos
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 * @param <T> Tipo generico de la factoria
 */
public interface Factory<T> {
	/**
	 * Crea una instancia tipo T del Objeto que se le pasa por parametro. 
	 * Si los parametros del JSONObject no coinciden con un modelo conocido, se lanza una IllegalArgumentException
	 * @param info Informacion del objeto T a crear
	 * @return Una instancia nueva del objeto T
	 * @throws IllegalArgumentException Si los argumentos no son correctos para el Builder, se lanza un IllegalArgumentException
	 */
	public T createInstance(JSONObject info) throws IllegalArgumentException;
	/**
	 * Devuelve una lista de JSONObjects con la informacion de las factorias
	 * @return Lista de JSONObject con la informacion para el funcionamiento de las factorias
	 */
	public List<JSONObject> getInfo();
}
