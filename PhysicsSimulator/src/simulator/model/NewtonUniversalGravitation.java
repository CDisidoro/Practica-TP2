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
		// Pendiente de Programar
		//Calculo de la Fuerza
		Iterator<Body> iterador = bs.iterator();
		Iterator<Body> iterTemp;
		double producto, distancia, fij;
		Vector2D force = new Vector2D();
		Vector2D sumaForce = new Vector2D();
		Body bi, bj;
		while(iterador.hasNext()) {
			bi = iterador.next();
			sumaForce = new Vector2D(); //Resetea la suma de fuerzas por cada Body nuevo
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
						producto = g*bi.getMass()*bj.getMass();
						distancia = bi.getPosition().distanceTo(bj.getPosition());
						distancia *= distancia;
						fij = producto/distancia;
						force = bj.getPosition().minus(bi.getPosition()).scale(fij);
					}
					bi.addForce(force); //Suma las fuerzas
					bi.aceleracion = bi.getForce().scale(1/bi.getMass()); //Aceleracion = Fuerza / Masa
				}
			}
		}
	}

	public String toString() {
		return "G: " + g; //Esta bien programado? Su unico atributo es la constante de gravedad
	}
}
