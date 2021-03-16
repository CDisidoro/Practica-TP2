package simulator.model;

import java.util.List;
/**
 * Interfaz para las Leyes de Fuerza del Simulador
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 */
public interface ForceLaws {
	/**
	 * Se aplica una ley de fuerza a una lista de cuerpos
	 * @param bs Lista de cuerpos a los que se les aplica la fuerza
	 */
	public void apply(List<Body> bs);
}
