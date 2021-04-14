package simulator.model;

import java.util.List;

/**
 * Interfaz de Observador de la Simulacion
 * @author Camilo Andres y Jose Ignacio Barrios Oros
 *
 */
public interface SimulatorObserver {
	/**
	 * Observa si el simulador esta en proceso de Registro
	 * @param bodies Lista actual de cuerpos
	 * @param time Tiempo actual del simulador
	 * @param dt Tiempo por paso del simulador
	 * @param fLawsDesc Descripcion de la Ley Fisica Actual
	 */
	public void onRegister(List<Body> bodies, double time, double dt, String fLawsDesc);
	/**
	 * Observa si el simulador esta en proceso de Reset
	 * @param bodies Lista actual de cuerpos
	 * @param time Tiempo actual del simulador
	 * @param dt Tiempo por paso del simulador
	 * @param fLawsDesc Descripcion de la Ley Fisica Actual
	 */
	public void onReset(List<Body> bodies, double time, double dt, String fLawsDesc);
	/**
	 * Observa si se ha agregado un cuerpo nuevo a la lista
	 * @param bodies Lista actual de cuerpos
	 * @param b Cuerpo que se desea observar
	 */
	public void onBodyAdded(List<Body> bodies, Body b);
	/**
	 * Observa si se ha avanzado en la simulacion
	 * @param bodies Lista actual de cuerpos
	 * @param time Tiempo actual del simulador
	 */
	public void onAdvance(List<Body> bodies, double time);
	/**
	 * Observa si el Tiempo por paso ha cambiado
	 * @param dt Tiempo por paso del simulador
	 */
	public void onDeltaTimeChanged(double dt);
	/**
	 * Observa si la ley de fuerza ha cambiado
	 * @param fLawsDesc Descripcion de la ley de Fuerza
	 */
	public void onForceLawsChanged(String fLawsDesc);
}
