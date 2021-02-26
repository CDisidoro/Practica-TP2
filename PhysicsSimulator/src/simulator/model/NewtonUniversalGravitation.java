package simulator.model;

import java.util.List;

public class NewtonUniversalGravitation implements ForceLaws{
	
	protected double k;
	public NewtonUniversalGravitation(double k) {
		this.k = k;
	}
	//En caso de que no se proporcione la constante
	public NewtonUniversalGravitation() {
		k = 6.67E10-11;
	}
	
	@Override
	public void apply(List<Body> bs) {
		// Pendiente de Programar
	}

	public String toString() {
		return "k: " + k;
	}
}
