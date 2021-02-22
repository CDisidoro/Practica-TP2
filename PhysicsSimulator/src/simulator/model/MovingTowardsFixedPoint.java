package simulator.model;

import java.util.List;
import simulator.misc.Vector2D;

public class MovingTowardsFixedPoint implements ForceLaws{
	
	private Vector2D vector;
	private double k;
	public MovingTowardsFixedPoint (Vector2D vector, double k) {
		this.vector = vector;
		this.k = k;
	}
	
	@Override
	public void apply(List<Body> bs) {
		// Pendiente de Programar
	}
	
	public String toString() {
		//Pendiente de Programar
	}
	
}
