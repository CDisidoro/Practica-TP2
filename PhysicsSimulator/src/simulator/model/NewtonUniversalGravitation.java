package simulator.model;

import java.util.List;

public class NewtonUniversalGravitation implements ForceLaws{
	
	protected double k;
	public NewtonUniversalGravitation(double k) {
		this.k = k;
	}
	
	@Override
	public void apply(List<Body> bs) {
		// Pendiente de Programar
	}

	public String toString() {
		// Pendiente de Programar
	}
}
