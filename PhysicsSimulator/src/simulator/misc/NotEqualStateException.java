package simulator.misc;

import org.json.JSONObject;
/**
 * Excepcion de tipo NotEqualState, que ser� disparada cuando dos estados de la simulaci�n no sean iguales entre la salida actual y la esperada
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 */
public class NotEqualStateException extends Exception {
	private static final long serialVersionUID = 4480510639217765494L;
	/**
	 * Constructor de la Excepcion NotEqualStateException, que recibe por parametro un mensaje generico, un JSONObject con la salida
	 * Esperada y otro con la salida del simulador
	 * @param msg Mensaje gen�rico de error
	 * @param expected Salida esperada por el simulador f�sico
	 * @param output Salida que ha dado la ejecuci�n del simulador
	 */
	public NotEqualStateException(String msg, JSONObject expected, JSONObject output) {
		super(msg + "\nSalida Esperada: " + expected + "\nSalida del Simulador: " + output);
	}
}
