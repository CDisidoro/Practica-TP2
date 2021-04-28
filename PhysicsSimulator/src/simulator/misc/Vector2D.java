package simulator.misc;

import org.json.JSONArray;
/**
 * Clase para guardar un Vector bidimensional
 * @author Ana María
 */
public class Vector2D {

	double _x;
	double _y;

	/**
	 *  Crea un vector cuyas coordenadas son cero
	 */
	public Vector2D() {
		_x = _y = 0.0;
	}

	/**
	 * Constructor que copia otro vector
	 * @param v Vector original que se desea copiar
	 */
	public Vector2D(Vector2D v) {
		_x = v._x;
		_y = v._y;
	}

	/**
	 * Crea un vector a partir de dos parametros
	 * @param x Coordenada en el eje X
	 * @param y Coordenada en el eje Y
	 */
	public Vector2D(double x, double y) {
		_x = x;
		_y = y;
	}
	
	/**
	 * Crea un vector a partir de un JSONArray
	 * @param jsonArray Un JSONArray con 2 datos [X,Y]
	 * @author Jose Ignacio Barrios Oros
	 * @deprecated Puede dar fallos si se envía directamente el JSONArray
	 */
	public Vector2D (JSONArray jsonArray) {
		_x=jsonArray.getDouble(0);
		_y=jsonArray.getDouble(1);
	}
	
	/**
	 * Retorna el producto interno del vector
	 * @param that El vector al que se le desea calcular el producto interno
	 * @return La suma de los cuadrados de los ejes del vector
	 */
	public double dot(Vector2D that) {
		return _x * that._x + _y * that._y;
	}

	/**
	 * Retorna la longitud del vector
	 * @return La raíz cuadrada de la suma de los cuadrados de los ejes
	 * @see #dot(Vector2D)
	 */
	public double magnitude() {
		return Math.sqrt(dot(this));
	}

	/**
	 * Retorna la distancia entre el vector actual y el vector that
	 * @param that El vector con el que se desea calcular su distancia
	 * @return La magnitud de la resta entre el vector actual y el de parametro
	 * @see #magnitude()
	 */
	public double distanceTo(Vector2D that) {
		return minus(that).magnitude();
	}

	/**
	 * Realiza una suma vectorial entre el vector actual y el vector that
	 * @param that El vector que se desea sumar
	 * @return Un nuevo vector resultado de sumar sus coordenadas
	 */
	public Vector2D plus(Vector2D that) {
		return new Vector2D(_x + that._x, _y + that._y);
	}

	/**
	 * Realiza una resta vectorial entre el vector actual y el vector that
	 * @param that El vector que se desea restar
	 * @return Un nuevo vector resultado de restar sus coordenadas
	 */
	public Vector2D minus(Vector2D that) {
		return new Vector2D(_x - that._x, _y - that._y);
	}

	/**
	 * Obtiene la coordenada X del vector
	 * @return La coordenada X del Vector
	 */
	public double getX() {
		return _x;
	}
	/**
	 * Obtiene la coordenada Y del vector
	 * @return La coordenada Y del Vector
	 */
	public double getY() {
		return _y;
	}

	/**
	 * Realiza una multiplicación vectorial entre el vector actual y un factor
	 * @param factor El factor con el que se desea multiplicar
	 * @return Un nuevo vector producto de multiplicar sus ejes por el factor
	 */
	public Vector2D scale(double factor) {
		return new Vector2D(_x * factor, _y * factor);
	}

	/**
	 * Retorna el vector unitario correspondiente
	 * @return El mismo vector si la magnitud es igual a cero, o la division 1/magnitud si la magnitud es mayor que cero
	 * @see #magnitude()
	 */
	public Vector2D direction() {
		if (magnitude() > 0.0)
			return scale(1.0 / magnitude());
		else
			return new Vector2D(this);
	}
	/**
	 * Crea un JSONArray con el vector actual
	 * @return Un JSONArray que contiene las ejes del vector actual
	 */
	public JSONArray asJSONArray() {
		JSONArray a = new JSONArray();
		a.put(_x);
		a.put(_y);
		return a;
	}

	/**
	 * Retorna una representacion en String del vector
	 * @return Representacion en String del vector con sus ejes
	 */
	public String toString() {
		return "[" + _x + "," + _y + "]";
	}

}
