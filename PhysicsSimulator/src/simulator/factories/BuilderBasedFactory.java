package simulator.factories;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T>{
	protected List<Builder<T>> builders;
	
	public BuilderBasedFactory(List<Builder<T>> builders) {
		this.builders = new ArrayList<Builder<T>>(builders);
	}
	@Override
	public T createInstance(JSONObject info) throws IllegalArgumentException{
		//Crea un objeto T inicializado a nulo, y se usa un for-each para cada Builder de tipo T en la Lista
		//Para intentar crear una instancia según el parametro JSONObject que hemos recibido y si se retorna
		//nulo (Es decir que no coincide el tipo de Instancia) seguira buscando para cada Builder
		//Si encuentra el Builder correcto retornara el objeto. En caso contrario, lanzará una IllegalArgumentException
		T b = null;
		for(Builder<T> bb: builders) {
			b=bb.createInstance(info);
			if(b!=null) {
				return b;
			} 
		}
	 throw new IllegalArgumentException("Error al crear la instancia");
	}

	@Override
	public List<JSONObject> getInfo() {
		/*Crea un ArrayList de JSONObjects que sera llenado usando un for-each de builders*/
		List<JSONObject> jobj=new ArrayList<JSONObject>();
		for(Builder<T> bb: builders) {
			jobj.add(bb.getBuilderInfo());
		}
		return jobj;
	}

}
