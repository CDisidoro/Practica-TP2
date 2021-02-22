package simulator.model;

import simulator.misc.Vector2D;

public class MassLosingBody extends Body{
	protected double lossFactor , lossFrequency; 
	public MassLosingBody(String id, Vector2D pos, Vector2D vel, double mass, double lossFactor, double lossFrequency) {
		super(id, pos, vel, mass);
		this.lossFactor = lossFactor;
		this.lossFrequency = lossFrequency;
	}
	
	public void move(double moving) {
		
	}
}
