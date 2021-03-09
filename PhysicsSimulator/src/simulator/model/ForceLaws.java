package simulator.model;

import java.util.List;

public interface ForceLaws {
	//Se aplica una ley de fuerza a una lista de cuerpos
	public void apply(List<Body> bs);
}
