package turismo;

public class Turista {

		private String nombre;
		private double presupuesto;
		private int tiempoDisponible;
		private int velocidadDeTraslado;
		private TipoDeAtraccion preferencia;
		private Coordenada coordenada;
		private int grupoFamiliar = 1;
		
		public Turista(String nombre, double presupuesto, int tiempoDisponible,
								int velocidadDeTraslado, TipoDeAtraccion preferencia, Coordenada coordenada, int grupoFamiliar) {
			
				this.setNombre(nombre);
				this.setPresupuesto(presupuesto);
				this.setTiempoDisponible(tiempoDisponible);
				this.setVelocidadDeTraslado(velocidadDeTraslado);
				this.setPreferencia(preferencia);
				this.setCoordenada(coordenada);
				this.setGrupoFamiliar(grupoFamiliar);
		}
	
		public void setGrupoFamiliar(int cantidadDePersonas){
			this.grupoFamiliar = cantidadDePersonas;
		}
		
		public int getGrupoFamiliar(){
			return this.grupoFamiliar;
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

		public Coordenada getCoordenada() {			
			return this.coordenada;
		}	
		
		public void setCoordenada(Coordenada coordenada) {			
			this.coordenada = coordenada;
		}
		
}