package simulator.model;

import simulator.misc.Vector2D;

public class MassLosingBody extends Body{
	protected double lossFactor, lossFrequency, con; 
	//Constructor de un Cuerpo que pierde masa, que ademas de recibir los parametros habituales de un Cuerpo normal, recibe un
	//Factor de perdida de masa y la frecuencia a la que pierde masa el cuerpo
	public MassLosingBody(String id, Vector2D pos, Vector2D vel, double mass, double lossFactor, double lossFrequency) {
		super(id, pos, vel, mass);
		this.lossFactor = lossFactor;
		this.lossFrequency = lossFrequency;
		this.con = 0.0;
	}
	//Mueve el cuerpo durante t instantes de tiempo, llamando a la clase padre y perdiendo masa cuando sea necesario
	public void move(double t) {
		//Hara el move de la clase padre
		super.move(t);
		//Acumulara los segundos del movimiento realizado
		con += t;
		//Si ha pasado el tiempo requerido para que el cuerpo pierda masa, se reducira su masa y resetea el conteo de tiempo
		if(con >= lossFrequency) {
			mass *= 1 - lossFactor;
			con = 0.0;
		}
	}
}
