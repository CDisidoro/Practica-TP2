package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class Controller {
	protected PhysicsSimulator simulador;
	protected Factory<Body> factoria;
	
	public Controller(PhysicsSimulator simulador, Factory<Body> factoria) {
		this.simulador = simulador;
		this.factoria = factoria;
	}
	
	public void run(int steps, OutputStream output, InputStream input, StateComparator comparator) {
		// Pendiente de Programar
	}
	
	public void localBodies(InputStream input) {
		JSONObject jinput = new JSONObject(new JSONTokener(input));
		JSONArray array = jinput.getJSONArray("bodies");
		for(int i = 0; i < array.length(); i++) { //Mirar si se puede mejorar a un for-each
			simulador.addBody(factoria.createInstance(array.getJSONObject(i)));
		}
	}
}
