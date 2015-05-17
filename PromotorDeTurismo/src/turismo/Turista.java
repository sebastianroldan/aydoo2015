package turismo;

public class Turista {

		private String nombre;
		private double presupuesto;
		private int tiempoDisponible;
		private int velocidadDeTraslado;
		private TipoDeAtraccion preferencia;
		
		public Turista(String nombre, double presupuesto, int tiempoDisponible,
								int velocidadDeTraslado, TipoDeAtraccion preferencia) {
			
				this.setNombre(nombre);
				this.presupuesto = presupuesto;
				this.tiempoDisponible = tiempoDisponible;
				this.velocidadDeTraslado = velocidadDeTraslado;
				this.preferencia = preferencia;
		}
	
		public double getPresupuesto() {
				return this.presupuesto;
		}
		
		public void setPresupuesto(double presupuesto) {
				this.presupuesto = presupuesto;
		}
	
		public int getTiempoDisponible() {
				return this.tiempoDisponible;
		}
	
		public void setTiempoDisponible(int tiempoDisponible) {
				this.tiempoDisponible = tiempoDisponible;
		}
	
		public TipoDeAtraccion getPreferencia() {
				return this.preferencia;
		}
	
		public void setPreferencia(TipoDeAtraccion preferencia) {
				this.preferencia = preferencia;
		}
		
		public int getVelocidadDeTraslado(){
				return this.velocidadDeTraslado;
		}
	
		public void setVelocidadDeTraslado(int velocidadDeTraslado) {
				this.velocidadDeTraslado = velocidadDeTraslado;
		}
	
		public String getNombre() {
				return nombre;
		}
	
		public void setNombre(String nombre) {
				this.nombre = nombre;
		}	
		
}