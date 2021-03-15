package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.misc.NotEqualStateException;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class Controller {
	protected PhysicsSimulator simulador;
	protected Factory<Body> factoria;
	//Constructor del Controlador del Simulador, que recibe una lista de Factorias de Cuerpos y un Simulador Fisico por parametros
	public Controller(PhysicsSimulator simulador, Factory<Body> factoria) {
		this.simulador = simulador;
		this.factoria = factoria;
	}
	//Se ejecuta el simulador durante n pasos, dando una salida out, pudiendo recibir una salida a comparar y un comparador de estados
	//especifico
	public void run(int n, OutputStream out, InputStream expOut, StateComparator cmp) throws NotEqualStateException {
		PrintStream output = new PrintStream(out);
		JSONObject compare = null;
		//Si la salida experada no es nula, se crea un JSONObject de comparacion
		if(expOut != null) {
			compare = new JSONObject(new JSONTokener(expOut));
		}
		//Se inicia el formato de salida del simulador
		output.println("{");
			output.println("\"states\": [");
				output.println(simulador.getState());
				//Compara el estado cero de ambos si hay una salida esperada para hacer la comparacion
				if(compare != null) {
					//Si los estados no son iguales, se lanza una excepcion personalizada con la salida esperada y la salida del simulador
					if(! (cmp.equal ( compare.getJSONArray("states").getJSONObject(0), simulador.getState() ) ) ) {
						throw new NotEqualStateException("Los estados no coinciden!", compare.getJSONArray("states").getJSONObject(0), simulador.getState());
					}
				}
				//Ejecuta la simulacion n pasos, haciendo un advance por cada paso e imprimiendo el estado
				for(int i = 1; i < n; i++) {
					simulador.advance();
					output.println("," + simulador.getState());
					//Si hay una salida esperada para comparar, se compara el estado i de ambas salidas
					if(compare != null) {
						//Si los estados no son iguales, se lanza una excepcion personalizada con la salida esperada y la salida del simulador
						if(! (cmp.equal ( compare.getJSONArray("states").getJSONObject(i), simulador.getState() ) ) ) {
							throw new NotEqualStateException("Los estados no coinciden!", compare.getJSONArray("states").getJSONObject(i), simulador.getState());
						}
					}
				}
			output.println("]");
		output.println("}");
	}
	//Se hace la carga de los cuerpos que se reciban por entrada
	public void localBodies(InputStream input) {
		JSONObject jinput = new JSONObject(new JSONTokener(input)); //Obtenemos el objeto JSON a traves de un tokener del InputStream
		JSONArray array = jinput.getJSONArray("bodies");//Obtenemos el array de bodies de la entrada y lo guardamos en un JSONArray
		for(int i = 0; i < array.length(); i++) {
			//Añadimos el cuerpo al simulador salido de crear una instancia con la factoría de obtener el objeto i del JSONArray
			simulador.addBody(factoria.createInstance(array.getJSONObject(i)));
		}
	}
}
