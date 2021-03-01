package simulator.model;

import java.util.List;

public class NoForce implements ForceLaws{
	
	public NoForce() {
	}

	@Override
	public void apply(List<Body> bs) {
	}
	
	public String toString() {
		return "";
	}
}
