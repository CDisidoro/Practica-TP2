package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class Controller {
	private PhysicsSimulator simulador;
	private Factory<Body> factoria;
	
	public Controller(PhysicsSimulator simulador, Factory<Body> factoria) {
		this.simulador = simulador;
		this.factoria = factoria;
	}
	
	public void run(int steps, OutputStream output, InputStream input, StateComparator comparator) {
		// Pendiente de Programar
	}
	
	public void localBodies(InputStream input) {
		// Pendiente de Programar
	}
}
