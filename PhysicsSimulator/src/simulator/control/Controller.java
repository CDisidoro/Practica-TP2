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
	
	public Controller(PhysicsSimulator simulador, Factory<Body> factoria) {
		this.simulador = simulador;
		this.factoria = factoria;
	}
	
	public void run(int n, OutputStream out, InputStream expOut, StateComparator cmp) throws NotEqualStateException {
		PrintStream output = new PrintStream(out);
		JSONObject compare = new JSONObject(new JSONTokener(expOut));
		output.println("{");
			output.println("\"states\": [");
				output.println(simulador.toString());
				//Recorre n pasos
				for(int i = 1; i < n; i++) {
					//output.println(Lo que tenga que sacar);
					simulador.advance();
					output.println("," + simulador.toString());
					if(! (cmp.equal ( compare, simulador.getState() ) ) ) { //Hay que revisar el condicional
						throw new NotEqualStateException("Los estados no coinciden en el punto X"); //Modificar el mensaje
					}
				}
			output.println("]");
		output.println("}");
	}
	
	public void localBodies(InputStream input) {
		JSONObject jinput = new JSONObject(new JSONTokener(input));
		JSONArray array = jinput.getJSONArray("bodies");
		for(int i = 0; i < array.length(); i++) { //Mirar si se puede mejorar a un for-each
			simulador.addBody(factoria.createInstance(array.getJSONObject(i)));
		}
	}
}
