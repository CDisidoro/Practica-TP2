package simulator.model;

import java.util.Iterator;
import java.util.List;
import simulator.misc.Vector2D;
/**
 * Ley de la Gravitacion Universal de Newton que implementa la interfaz ForceLaws
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 * @see ForceLaws
 */
public class NewtonUniversalGravitation implements ForceLaws{
	
	protected double g;
	/**
	 * Constructor de la Ley de Newton, que recibe como parametro la constante de gravedad
	 * @param g Constante de Gravitacion Universal
	 */
	public NewtonUniversalGravitation(double g) {
		this.g = g;
	}
	
	@Override
	/**
	 * Se aplica la Ley de Newton a una lista de cuerpos
	 */
	public void apply(List<Body> bs) {
		//Calculo de la Fuerza
		Iterator<Body> iterador = bs.iterator();
		Iterator<Body> iterTemp;
		double producto, distancia, fij;
		Vector2D force = new Vector2D();
		Body bi, bj;
		while(iterador.hasNext()) {
			bi = iterador.next();
			if(bi.getMass() == 0.0) {
				bi.vel = new Vector2D();
				bi.aceleracion = new Vector2D();
			}else {
				iterTemp = bs.iterator();
				while(iterTemp.hasNext()) {
					producto = 0.0;
					distancia = 0.0;
					fij = 0.0;
					bj = iterTemp.next();
					if(!(bi.equals(bj))) {
						//Calculo del vector fuerza
						producto = g*(bi.getMass()*bj.getMass()); // P = G * Masa i * Masa j
						distancia = bj.getPosition().minus(bi.getPosition()).magnitude();
						distancia *= distancia;
						fij = producto/distancia; // f = P/D^2
						force = bj.getPosition().minus(bi.getPosition()).direction().scale(fij); // F = (Dj - Di)/(Pitagoras) * f
						bi.addForce(force); //Suma las fuerzas
					}
				}
			}
		}
	}
	/**
	 * Devuelve el estado de la Ley de Newton en formato String
	 * @return Estado de la Ley de Newton en formato String
	 */
	public String toString() {
		return "G: " + g;
	}
}
