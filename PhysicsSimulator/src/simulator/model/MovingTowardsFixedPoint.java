package simulator.model;

import java.util.List;
import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws{
	
	protected Vector2D vector;
	protected double k;
	public MovingTowardsFixedPoint (Vector2D vector, double k) {
		this.vector = vector;
		this.k = k;
	}
	
	@Override
	public void apply(List<Body> bs) {
		// Pendiente de Programar
	}
	
	public String toString() {
		return vector.toString() + "k: " + k; //Esta bien Programado?
	}
	
}
