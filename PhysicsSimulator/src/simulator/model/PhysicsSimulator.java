package simulator.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 * Simulador Fisico que hace avanzar los cuerpos en el espacio y añade cuerpos a la simulacion
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 *
 */
public class PhysicsSimulator {
	protected ForceLaws law;
	protected double timePerStep, currentTime;
	protected List<Body> bod;
	/**
	 * Constructor del simulador fisico
	 * @param law Ley Fisica que se va a implementar
	 * @param timePerStep El tiempo que hay entre cada paso
	 * @throws IllegalArgumentException Si hay algun argumento erroneo se lanza una IllegalArgumentException
	 */
	public PhysicsSimulator(ForceLaws law, double timePerStep) throws IllegalArgumentException{
		this.law = law;
		this.timePerStep = timePerStep;
		bod = new ArrayList<Body>();
		currentTime = 0.0;
	}
	/**
	 * Añade un cuerpo nuevo a la simulacion, en caso de ya existir el cuerpo se lanza un IllegalArgumentException
	 * @param newBody Cuerpo nuevo que sera agregado a la simulacion
	 */
	public void addBody(Body newBody) {
		if(bod.contains(newBody))
			throw new IllegalArgumentException("El cuerpo que se ha intentado añadir ya existe");
		bod.add(newBody);
	}
	/**
	 * Se avanza un paso en la simulación
	 */
	public void advance() {
		//Creamos un iterador de la lista de cuerpos para recorrer el arrayList
		Iterator<Body> iterator = bod.iterator();
		Body bi;
		//Se resetea la fuerza para todos los cuerpos
		while(iterator.hasNext()) {
			bi= iterator.next();
			bi.resetForce();
		}
		//Se aplican las leyes correspondientes para toda la lista de cuerpos
		law.apply(bod);
		Iterator<Body> iterator2 = bod.iterator();
		Body bi2;
		//Se mueven todos los cuerpos durante timePerStep unidades de tiempo
		while(iterator2.hasNext()) {
			bi2= iterator2.next();
			bi2.move(timePerStep);
		}
		//Se incrementa el tiempo actual con el tiempo que dura cada paso
		currentTime = currentTime + timePerStep;
	}
	/**
	 * Obtiene el estado actual de la simulacion
	 * @return JSONObject que contiene la lista de cuerpos con su informacion en el punto X de la simulacion y el tiempo que lleva en ejecucion esta
	 */
	public JSONObject getState() {
		JSONObject estado = new JSONObject();
		JSONArray arrayBodies = new JSONArray();
		//Iterador de Bodies
		Iterator<Body> iterador = bod.iterator();
		//Llena el array de Bodies llamando al getState del Body encontrado
		while(iterador.hasNext()) {
			arrayBodies.put(iterador.next().getState());
		}
		estado.put("bodies", arrayBodies);
		estado.put("time", currentTime);
		return estado;
	}
	/**
	 * Obtiene el estado de la simulacion en formato String
	 * @return Estado de la simulacion actualmente, en formato String
	 */
	public String toString() {
		return getState().toString();
	}
}
