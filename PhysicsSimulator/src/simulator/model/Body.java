package simulator.model;
import org.json.JSONObject;

import simulator.misc.Vector2D;
public class Body {
	protected String id;
	protected Vector2D pos, vel, force;
	protected double mass;
	
	public Body(String id, Vector2D pos, Vector2D vel, double mass) {
		this.id = id;
		this.pos = pos;
		this.vel = vel;
		this.mass = mass;
		this.force = new Vector2D();
	}
	
	public String getId() {
		return id;
	}
	
	public double getMass() {
		return mass;
	}
	
	public Vector2D getPosition() {
		return pos;
	}
	
	public Vector2D getVelocity() {
		return vel;
	}
	
	public Vector2D getForce() {
		return force;
	}
	
	public void addForce(Vector2D forceAdded) {
		force = force.plus(forceAdded);
	}
	
	public void resetForce() {
		force = new Vector2D();
	}
	
	public void move(double t) {
		Vector2D aceleracion;
		if(mass == 0.0) {
			aceleracion = new Vector2D();
		}else {
			aceleracion = force.scale(1/mass);
		}
		pos = pos.plus(vel.scale(t).plus(aceleracion.scale(0.5).scale(t * t))); //Ojo con esta llamada, se ve peligrosa D:
		vel = vel.plus(aceleracion.scale(t));
	}
	
	public boolean equals(Object o) {
		//Pendiente de programar
	}
	
	public JSONObject getState() {
		//Pendiente de Programar
	}
	
	public String toString() {
		return getState().toString();
	}
}
