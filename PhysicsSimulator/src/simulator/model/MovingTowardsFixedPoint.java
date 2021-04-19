package simulator.model;

import java.util.Iterator;
import java.util.List;
import simulator.misc.Vector2D;
/**
 * Ley de Movimiento a un Punto Fijo que implementa la interfaz ForceLaws
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 * @see ForceLaws
 */
public class MovingTowardsFixedPoint implements ForceLaws{
	
	protected Vector2D vector;
	protected double g;
	/**
	 * Constructor de la Ley de Movimiento a un Punto Fijo, que recibe por parametros el Punto Fijo a donde iran los cuerpos y la constante de Gravedad
	 * @param vector Vector de posicion del punto a donde se mueven los cuerpos
	 * @param g Constante de gravedad
	 */
	public MovingTowardsFixedPoint (Vector2D vector, double g) {
		this.vector = vector;
		this.g = g;
	}
	
	@Override
	/**
	 * Se aplica la Ley de Movimiento a un Punto Fijo para cada cuerpo de la lista de cuerpos
	 */
	public void apply(List<Body> bs) {
		//Crea un iterador para recorrer la lista de cuerpos
		Iterator<Body> iterator = bs.iterator();
		Body bi;
		while(iterator.hasNext()) {
			bi=iterator.next();
			if(vector.getX() == 0.0 && vector.getY() == 0.0) {
				bi.aceleracion = bi.getPosition().direction().scale(-g); //Aceleracion = Direccion * -G
			}else {
				bi.aceleracion = vector.minus(bi.getPosition()).direction(); //Aceleracion = (Punto - Posicion)/(Pitagoras)
			}
			//Agrega la fuerza
			bi.addForce(bi.aceleracion.scale(bi.getMass())); // Fuerza = Masa * Aceleracion
		}
	}
	/**
	 * Retorna el estado de la Ley de Movimiento a un Punto Fijo en formato String
	 * @return Retorna la informacion relacionada con la Ley en formato String
	 */
	public String toString() {
		return "Moving towards " + vector.toString() + " with constant acceleration -" + g;
	}
	
}
