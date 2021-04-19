package simulator.model;

import java.util.List;
/**
 * Ley Sin Fuerza que implementa la interfaz ForceLaws (No hace nada esta ley)
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 * @see ForceLaws
 */
public class NoForce implements ForceLaws{
	/**
	 * Constructor de la Ley Sin Fuerza, que no recibe parámetros
	 */
	public NoForce() {
	}

	@Override
	/**
	 * Se aplica la Ley Sin Fuerza para una lista de cuerpos (No hace nada)
	 */
	public void apply(List<Body> bs) {
	}
	/**
	 * Retorna una descripcion de la Ley Sin Fuerza
	 * @return Una descripcion breve de la Ley
	 */
	public String toString() {
		return "No Force";
	}
}
