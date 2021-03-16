package simulator.model;

import org.json.JSONObject;
import simulator.misc.Vector2D;
public class Body {
	protected String id;
	protected Vector2D pos, vel, force, aceleracion;
	protected double mass;
	//Constructor de la clase Body que recibe un Identificador, una posicion, una velocidad y una masa
	public Body(String id, Vector2D pos, Vector2D vel, double mass) {
		this.id = id;
		this.pos = pos;
		this.vel = vel;
		this.mass = mass;
		this.force = new Vector2D();
	}
	//Obtiene el ID del cuerpo
	public String getId() {
		return id;
	}
	//Obtiene la masa del cuerpo
	public double getMass() {
		return mass;
	}
	//Obtiene la posicion del cuerpo
	public Vector2D getPosition() {
		return pos;
	}
	//Obtiene la Velocidad del cuerpo
	public Vector2D getVelocity() {
		return vel;
	}
	//Obtiene la Fuerza del Cuerpo
	public Vector2D getForce() {
		return force;
	}
	//Se le añade una fuerza nueva forceAdded al cuerpo
	public void addForce(Vector2D forceAdded) {
		force = force.plus(forceAdded);
	}
	//Se reestablece la fuerza
	public void resetForce() {
		force = new Vector2D();
	}
	//Se mueve el cuerpo durante t instantes de tiempo
	public void move(double t) {
		//Si la masa es cero la aceleracion es nula, en caso opuesto se usa scale para dividir la fuerza por la masa
		if(mass == 0.0) {
			aceleracion = new Vector2D();
		}else {
			aceleracion = force.scale(1.0/mass);
		}
		//A la posicion se le suma el resultado de multiplicar la velocidad por el tiempo mas el producto de
		//dividir aceleracion a la mitad y multiplicarlo por t al cuadrado
		pos = pos.plus(vel.scale(t).plus(aceleracion.scale(0.5).scale(t * t)));
		//A velocidad el sumamos el producto de la aceleracion por el tiempo
		vel = vel.plus(aceleracion.scale(t));
	}
	//Se comprueba si dos cuerpos son iguales o no
	public boolean equals(Object o) {
		//Si la instancia del objeto O no es un cuerpo retorna automaticamente falso
		if(!(o instanceof Body)) {
			return false;
		}
		Body bod = (Body) o;
		//llamara al metodo equals de String para comprobar si el id del Cuerpo actual es igual al del cuerpo pasado por parametro
		return (id.equals(bod.getId()));
	}
	//Obtiene el estado actual del cuerpo
	public JSONObject getState() {
		JSONObject estado = new JSONObject();
		estado.put("p", pos.asJSONArray());
		estado.put("v", vel.asJSONArray());
		estado.put("f", force.asJSONArray());
		estado.put("id", id);
		estado.put("m", mass);
		return estado;
	}
	//Convierte el estado actual del cuerpo a un String
	public String toString() {
		return getState().toString();
	}
}
