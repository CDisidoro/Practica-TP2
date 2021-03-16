package simulator.factories;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
/**
 * Factoría de Objetos basada en Builders que implementa la interfaz Factory
 * @author Camilo Andres D'isidoro y Jose Ignacio Barrios Oros
 * @param <T> El tipo de objeto del Builder
 * @see Factory
 */
public class BuilderBasedFactory<T> implements Factory<T>{
	protected List<Builder<T>> builders;
	/**
	 * Constructor de la Factoria que recibe una lista de Builders de tipo generico
	 * @param builders Lista de Builders de tipo T genérico
	 */
	public BuilderBasedFactory(List<Builder<T>> builders) {
		this.builders = new ArrayList<Builder<T>>(builders);
	}
	@Override
	/**
	 * Crea una instancia de tipo T a partir de una info
	 * @exception Lanzara una IllegalArgumentException si la instancia no puede ser creada
	 * @param info Informacion de base para intentar crear la instancia
	 * @return Una instancia nueva de un objeto T
	 */
	public T createInstance(JSONObject info) throws IllegalArgumentException{
		//Crea un objeto T inicializado a nulo, y se usa un for-each para cada Builder de tipo T en la Lista
		//Para intentar crear una instancia según el parametro JSONObject que hemos recibido y si se retorna
		//nulo (Es decir que no coincide el tipo de Instancia) seguira buscando para cada Builder
		//Si encuentra el Builder correcto retornara el objeto. En caso contrario, lanzará una IllegalArgumentException
		T b = null;
		if(info == null) { //Si la informacion recibida es nula se lanza una excepcion de que no ha sido posible crear la instancia
			throw new IllegalArgumentException("Error al crear la instancia");
		}
		for(Builder<T> bb: builders) {
			b=bb.createInstance(info);
			if(b!=null) {
				return b;
			} 
		}
	 throw new IllegalArgumentException("Error al crear la instancia");
	}

	@Override
	/**
	 * Obtiene la informacion de los Builders creados en el simulador en forma de Lista de JSONObjects
	 * @return Una lista de JSONObjects con la informacion de cada Builder
	 */
	public List<JSONObject> getInfo() {
		/*Crea un ArrayList de JSONObjects que sera llenado usando un for-each de builders*/
		List<JSONObject> jobj=new ArrayList<JSONObject>();
		for(Builder<T> bb: builders) {
			jobj.add(bb.getBuilderInfo());
		}
		return jobj;
	}

}
