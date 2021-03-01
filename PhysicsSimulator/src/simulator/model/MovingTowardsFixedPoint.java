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
		Iterator<Body> iterator = bs.iterator();
		Body bi;
		while(iterator.hasNext()) {
			bi=iterator.next();
			if(vector.getX() == 0.0 && vector.getY() == 0.0) {
				bi.aceleracion = bi.getPosition().scale(-g); //Aceleracion = Direccion * -G
			}else {
				bi.aceleracion = vector.minus(bi.getPosition()).direction(); //Aceleracion = (Punto - Posicion)/(Pitagoras)
			}
			//Agrega la fuerza
			bi.addForce(bi.aceleracion.scale(bi.getMass())); // Fuerza = Masa * Aceleracion
		}
	}
	
	public String toString() {
		return vector.toString() + ", g: " + g;
	}
	
}
