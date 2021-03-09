package simulator.model;

import java.util.List;

public class NoForce implements ForceLaws{
	//Constructor de la Ley Sin Fuerza, que no recibe par�metros
	public NoForce() {
	}

	@Override
	//Se aplica la Ley Sin Fuerza para una lista de cuerpos (No hace nada)
	public void apply(List<Body> bs) {
	}
	//Retorna el estado de la Ley Sin Fuerza (Retorna vac�o)
	public String toString() {
		return "";
	}
}
