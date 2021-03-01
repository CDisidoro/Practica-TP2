package simulator.model;

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
		pos = pos.plus(vel.scale(t).plus(aceleracion.scale(0.5).scale(t * t)));
		//A velocidad el sumamos el producto de la aceleracion por el tiempo
		vel = vel.plus(aceleracion.scale(t));
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Body)) {
			return false;
		}
		Body bod = (Body) o;
		return (id.equals(bod.getId()));
	}
	
	public JSONObject getState() {
		JSONObject estado = new JSONObject();
		estado.put("id", id);
		estado.put("m", mass);
		estado.put("p", pos.asJSONArray());
		estado.put("v", vel.asJSONArray());
		estado.put("f", force.asJSONArray());
		return estado;
	}
	
	public String toString() {
		return getState().toString();
	}
}
