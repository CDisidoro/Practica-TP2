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
	//ATRIBUTOS PARTE 2
	protected List<SimulatorObserver> observers;
	/**
	 * Constructor del simulador fisico
	 * @param law Ley Fisica que se va a implementar
	 * @param timePerStep El tiempo que hay entre cada paso
	 * @throws IllegalArgumentException Si hay algun argumento erroneo se lanza una IllegalArgumentException
	 */
	public PhysicsSimulator(ForceLaws law, double timePerStep) throws IllegalArgumentException{
		observers = new ArrayList<SimulatorObserver>();
		bod = new ArrayList<Body>();
		setForceLaws(law);
		setDeltaTime(timePerStep);
		reset();
	}
	/**
	 * Añade un cuerpo nuevo a la simulacion, en caso de ya existir el cuerpo se lanza un IllegalArgumentException
	 * @param newBody Cuerpo nuevo que sera agregado a la simulacion
	 */
	public void addBody(Body newBody) {
		if(bod.contains(newBody))
			throw new IllegalArgumentException("El cuerpo que se ha intentado añadir ya existe");
		bod.add(newBody);
		//Notifica que se ha cargado un nuevo cuerpo a todos los observadores
		Iterator<SimulatorObserver> iter = observers.iterator();
		while(iter.hasNext()) {
			iter.next().onBodyAdded(bod, newBody);
		}
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
		//Se notifica a todos los observadores que se ha avanzado en la simulacion
		Iterator<SimulatorObserver> iter = observers.iterator();
		while(iter.hasNext()) {
			iter.next().onAdvance(bod, currentTime);
		}
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
	
	//METODOS PARTE 2
	/**
	 * Se vacia la lista de cuerpos y establece el tiempo actual a cero
	 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
	 */
	public void reset() {
		bod.clear();
		currentTime = 0.0;
		//Notifica el reset a todos los observadores registrados
		Iterator<SimulatorObserver> iter = observers.iterator();
		while(iter.hasNext()) {
			iter.next().onReset(bod, currentTime, timePerStep, law.toString());
		}
	}
	/**
	 * Establece el Delta Time a un valor especifico pasado por parametro. Si no es valido, se lanzara un IllegalArgumentException
	 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
	 * @param dt Tiempo delta que va a cargarse
	 * @throws IllegalArgumentException Si el tiempo delta tiene un valor no valido, se lanzara la excepcion
	 */
	public void setDeltaTime(double dt) throws IllegalArgumentException{
		try {
			timePerStep = dt;
		}catch(IllegalArgumentException ex){
			System.out.println("El valor " + dt + "es invalido");
		}
		//Se notifica a todos los observadores que el Delta Time ha cambiado
		Iterator<SimulatorObserver> iter = observers.iterator();
		while(iter.hasNext()) {
			iter.next().onDeltaTimeChanged(timePerStep);
		}
	}
	/**
	 * Establece la Ley de fuerza que utilizará el simulador. Si es nula, se lanzara un IllegalArgumentException
	 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
	 * @param forceLaws La ley de fuerza que se va a implementar
	 * @throws IllegalArgumentException Si la Ley de fuerza es nula se manda un IllegalArgumentException
	 */
	public void setForceLaws(ForceLaws forceLaws) throws IllegalArgumentException{
		if(forceLaws == null) {
			throw new IllegalArgumentException("La ley de fuerza no puede ser nula!");
		}else {
			law = forceLaws;
		}
		//Se notifica a todos los observadores que la Ley de Fuerza ha cambiado
		Iterator<SimulatorObserver> iter = observers.iterator();
		while(iter.hasNext()) {
			iter.next().onForceLawsChanged(law.toString());
		}
	}
	/**
	 * Agrega un nuevo observador a la simulacion
	 * @param o Nuevo observador de la simulacion
	 */
	public void addObserver(SimulatorObserver o) {
		if(!observers.contains(o)) {
			observers.add(o);
		}
		//Notifica el registro del observador al observador agregado
		o.onRegister(bod, currentTime, timePerStep, law.toString());
	}
}
