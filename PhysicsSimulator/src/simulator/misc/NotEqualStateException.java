package simulator.misc;

import org.json.JSONObject;

public class NotEqualStateException extends Exception {
	private static final long serialVersionUID = 4480510639217765494L;
	//Constructor de la Excepcion NotEqualStateException, que recibe por parametro un mensaje generico, un JSONObject con la salida
	//Esperada y otro con la salida del simulador
	public NotEqualStateException(String msg, JSONObject expected, JSONObject output) {
		super(msg + "\nSalida Esperada: " + expected + "\nSalida del Simulador: " + output);
	}
}
