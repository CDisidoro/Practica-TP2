package simulator.model;

import simulator.misc.Vector2D;

public class MassLosingBody extends Body{
	protected double lossFactor , lossFrequency, con; 
	public MassLosingBody(String id, Vector2D pos, Vector2D vel, double mass, double lossFactor, double lossFrequency) {
		super(id, pos, vel, mass);
		this.lossFactor = lossFactor;
		this.lossFrequency = lossFrequency;
		this.con = 0.0;
	}
	
	public void move(double t) {
		super.move(t);
		con += t;
		if(con >= lossFrequency) {
			mass *= 1 - lossFactor;
			con = 0.0;
		}
	}
}
