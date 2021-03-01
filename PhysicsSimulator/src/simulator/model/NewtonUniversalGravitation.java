package simulator.model;

import java.util.Iterator;
import java.util.List;
import simulator.misc.Vector2D;

public class NewtonUniversalGravitation implements ForceLaws{
	
	protected double g;
	public NewtonUniversalGravitation(double g) {
		this.g = g;
	}
	//En caso de que no se proporcione la constante
	public NewtonUniversalGravitation() {
		g = 6.67E10-11;
	}
	
	@Override
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
						producto = g*bi.getMass()*bj.getMass(); // P = G * Masa^2
						distancia = bj.getPosition().distanceTo(bi.getPosition()); // D = Dj - Di
						distancia *= distancia;
						fij = producto/distancia; // f = P/D^2
						force = bj.getPosition().minus(bi.getPosition()).direction().scale(fij); // F = (Dj - Di)/(Pitagoras) * f
					}
					bi.addForce(force); //Suma las fuerzas
				}
			}
		}
	}

	public String toString() {
		return "G: " + g;
	}
}
