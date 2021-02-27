package simulator.model;

import java.util.Iterator;
import java.util.List;
import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws{
	
	protected Vector2D vector;
	protected double g;
	
	public MovingTowardsFixedPoint (Vector2D vector, double g) {
		this.vector = vector;
		this.g = g;
	}
	public MovingTowardsFixedPoint (Vector2D vector) {
		this.vector = vector;
		this.g = 9.81;
	}
	
	@Override
	public void apply(List<Body> bs) {
		// Pendiente de Programar
		Iterator<Body> iterator = bs.iterator();
		Body bi;
		while(iterator.hasNext()) {
			bi=iterator.next();
			bi.aceleracion = bi.getPosition().scale(-g);
		}
	}
	
	public String toString() {
		return vector.toString() + ", g: " + g; //Esta bien Programado? Sus unicos parametros son k y el vector
	}
	
}
