package simulator.model;
import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Vector2D;
public class Body {
	protected String id;
	protected Vector2D pos, vel, force, aceleracion;
	protected double mass;
	
	public Body(String id, Vector2D pos, Vector2D vel, double mass) {
		this.id = id;
		this.pos = pos;
		this.vel = vel;
		this.mass = mass;
		this.force = new Vector2D();
		this.aceleracion = new Vector2D();
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
		//Si la masa es cero la aceleracion es nula, en caso opuesto se usa scale para dividir la fuerza por la masa
		if(mass == 0.0) {
			aceleracion = new Vector2D();
		}else {
			aceleracion = force.scale(1/mass);
		}
		//A la posicion se le suma el resultado de multiplicar la velocidad por el tiempo mas el producto de
		//dividir aceleracion a la mitad y multiplicarlo por t al cuadrado
		pos = pos.plus(vel.scale(t).plus(aceleracion.scale(0.5).scale(t * t))); //Ojo con esta llamada, se ve peligrosa D:
		//A velocidad el sumamos el producto de la aceleracion por el tiempo
		vel = vel.plus(aceleracion.scale(t));
	}
	
	public boolean equals(Object o) {
		//Pendiente de programar
		return false;
	}
	
	public JSONObject getState() {
		//Crea el JSON a retornar
		JSONObject estado = new JSONObject();
		//Crea los JSON Arrays para cada vector del cuerpo
		JSONArray position = new JSONArray();
		JSONArray velocity = new JSONArray();
		JSONArray force = new JSONArray();
		estado.put("id", id);
		estado.put("m", mass);
		//Llenamos el array posicion
		position.put(pos.getX());
		position.put(pos.getY());
		estado.put("p", position);
		//Llenamos el array velocidad
		velocity.put(vel.getX());
		velocity.put(vel.getY());
		estado.put("v", velocity);
		//Llenamos el array fuerza
		force.put(this.force.getX());
		force.put(this.force.getY());
		estado.put("f", force);
		return estado;
	}
	
	public String toString() {
		return getState().toString();
	}
}
