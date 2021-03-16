package simulator.control;

import org.json.JSONArray;
import org.json.JSONObject;
import simulator.misc.Vector2D;
/**
 * Comparador de estados Modulo Epsilon que implementa a StateComparator
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 * @see StateComparator
 */
public class EpsilonEqualStates implements StateComparator{
	protected double eps;
	/**
	 * Constructor del Comparador de Estados Modulo Epsilon, que recibe por parametro una constante epsilon
	 * @param eps Valor Epsilon con el que se haran las operaciones
	 */
	public EpsilonEqualStates(double eps) {
		this.eps = eps;
	}
	
	@Override
	/**
	 * Comprueba si dos estados del simulador son iguales usando el modulo epsilon
	 * @param s1 Estado 1 a comparar
	 * @param s2 Estado 2 a Comparar
	 * @return La comparacion de los dos estados, viendo si son iguales modulo epsilon o no
	 */
	public boolean equal(JSONObject s1, JSONObject s2) {
		Vector2D p1,p2,v1,v2,f1,f2;
		//Obtengo el array de Bodies del Object
		JSONArray arrayBodies1 = new JSONArray();
		JSONArray arrayBodies2 = new JSONArray();
		//Usar un array con su longitud directamente en vez de usar iterador
		arrayBodies1 = s1.getJSONArray("bodies");
		arrayBodies2 = s2.getJSONArray("bodies");
		JSONObject b1,b2;
		//Si las longitudes son distinas entonces se sabe que son distintos
		if(arrayBodies1.length() != arrayBodies2.length()) {
			return false;
		}else {
			//Usar un bucle for que recorre segun la longitud del array
			for(int i = 0; i< arrayBodies1.length(); i++) {
				//Covierte a JSONObject el objeto almacenado en la posicion i de S1 y S2 para poderlo manipular
				b1 = arrayBodies1.getJSONObject(i);
				b2 = arrayBodies2.getJSONObject(i);
				//Hay que obtener los vectores de cada cuerpo
				//Vectores Posicion
				p1 = new Vector2D(b1.getJSONArray("p").getDouble(0), b1.getJSONArray("p").getDouble(1));
				p2 = new Vector2D(b2.getJSONArray("p").getDouble(0), b2.getJSONArray("p").getDouble(1));
				//Vectores Velocidad
				v1 = new Vector2D(b1.getJSONArray("v").getDouble(0), b1.getJSONArray("v").getDouble(1));
				v2 = new Vector2D(b2.getJSONArray("v").getDouble(0), b2.getJSONArray("v").getDouble(1));
				//Vectores Fuerza
				f1 = new Vector2D(b1.getJSONArray("f").getDouble(0), b1.getJSONArray("f").getDouble(1));
				f2 = new Vector2D(b2.getJSONArray("f").getDouble(0), b2.getJSONArray("f").getDouble(1));
				//Condicional (Si no se cumple retorna falso)
				if(! (b1.getString("id").equals(b2.getString("id")) && Math.abs(b1.getDouble("m") - b2.getDouble("m")) <= eps
						&& p1.distanceTo(p2) <= eps && v1.distanceTo(v2) <= eps && f1.distanceTo(f2) <= eps) ) {
					return false;
				}
			}
			//Por ultimo comprueba si los tiempos son iguales en los dos estados
			return (s1.getDouble("time") == s2.getDouble("time"));
		}
		}

}
