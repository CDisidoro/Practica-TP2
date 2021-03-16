package simulator.control;

import org.json.JSONObject;
/**
 * Interfaz de Comparadores de Estados
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 *
 */
public interface StateComparator {
	/**
	 * Comprueba si dos estados de la simulacion son iguales
	 * @param s1 Estado 1 con el que hace la comparacion
	 * @param s2 Estado 2 con el que hace la comparacion
	 * @return Si los dos estados son iguales retorna true, en caso contrario retorna false
	 */
	boolean equal(JSONObject s1, JSONObject s2); 
}
