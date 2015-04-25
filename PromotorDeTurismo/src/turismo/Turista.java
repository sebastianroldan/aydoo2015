package turismo;

public class Turista {

	private String nombre;
	private double presupuesto;
	private int tiempoDisponible;
	private int velocidadDeTraslado;
	private TipoDeAtraccion preferencia;
	
	
	public Turista(String nombre, double presupuesto, int tiempoDisponible,
		int velocidadDeTraslado, TipoDeAtraccion preferencia) {
		
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.velocidadDeTraslado = velocidadDeTraslado;
		this.preferencia = preferencia;
	}

	public double getPresupuesto() {
		
		return this.presupuesto;
	}

	public int getTiempoDisponible() {
		
		return this.tiempoDisponible;
	}

	public TipoDeAtraccion getPreferencia() {
		
		return this.preferencia;
	}
	
	public int getVelocidadDeTraslado(){
		
		return this.velocidadDeTraslado;
		
	}	
	
}
