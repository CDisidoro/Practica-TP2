package simulator.model;

import simulator.misc.Vector2D;

public class MassLosingBody extends Body{
	private double a , b; //Nombres temporales hasta saber que significan estas variables
	public MassLosingBody(String id, Vector2D pos, Vector2D vel, double mass, double a, double b) {
		super(id, pos, vel, mass);
		this.a = a;
		this.b = b;
	}
	
	public void move(double moving) {
		
	}
}
