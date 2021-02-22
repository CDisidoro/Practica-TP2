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
		this.force = new Vector2D(0,0);
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
		//force = forceAdded; Esto no deberia ser suficiente, hay que revisarlo bien
	}
	
	public void resetForce() {
		//force = 0; Hay que hacer algo distinto para resetear la fuerza
	}
	
	public void move(double moving) {
		//Pendiente de programar
	}
	
	public boolean equals(Object o) {
		//Pendiente de programar
	}
	
	public JSONObject getState() {
		//Pendiente de Programar
	}
	
	public String toString() {
		//Pendiente de Programar
	}
}
