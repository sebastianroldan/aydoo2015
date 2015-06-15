package turismo;

public class Turista {

		private String nombre;
		private double presupuesto;
		private int tiempoDisponible;
		private int velocidadDeTraslado;
		private TipoDeAtraccion preferencia;
		private Domicilio direccion;
		private int cantidadDeIntegrantesGrupoFamiliar = 1;
		
		public Turista(String nombre, double presupuesto, int tiempoDisponible,
								int velocidadDeTraslado, TipoDeAtraccion preferencia, Domicilio direccion, int cantidadDeIntegrantesDeGrupoFamiliar) {
			
				this.setNombre(nombre);
				this.setPresupuesto(presupuesto);
				this.setTiempoDisponible(tiempoDisponible);
				this.setVelocidadDeTraslado(velocidadDeTraslado);
				this.setPreferencia(preferencia);
				this.setDireccion(direccion);
				this.setCantidadDeIntegrantesGrupoFamiliar(cantidadDeIntegrantesDeGrupoFamiliar);
		}
	
		public void setCantidadDeIntegrantesGrupoFamiliar(int cantidadDePersonas){
			this.cantidadDeIntegrantesGrupoFamiliar = cantidadDePersonas;
		}
		
		public int getCantidadDeIntegrantesGrupoFamiliar(){
			return this.cantidadDeIntegrantesGrupoFamiliar;
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

		public Domicilio getDireccion() {			
			return this.direccion;
		}	
		
		public void setDireccion(Domicilio direccion) {			
			this.direccion = direccion;
		}
		
		public boolean puedeHacer(Atraccion proximaAtraccion, Sugerencia sugerencia) {
			
			return (alcanzaPresupuestoPara(proximaAtraccion, sugerencia) 
						&& (alcanzaTiempoPara(proximaAtraccion, sugerencia) 
								&& (proximaAtraccion.hayCupo(this.getCantidadDeIntegrantesGrupoFamiliar()))));
		}
		
		public boolean alcanzaTiempoPara(Atraccion proximaAtraccion, Sugerencia sugerencia) {
			
			double tiempoDeTraslado = sugerencia.calcularTiempoDeViajeHastaLaProximaAtraccion(
											this.getVelocidadDeTraslado(),proximaAtraccion.getCoordenadas(), this.getDireccion().getCoordenada());
			
			double tiempoTotalTranscurrido = sugerencia.getTiempoTotal(this.getVelocidadDeTraslado());
			
			return tiempoDeTraslado+proximaAtraccion.getTiempoNecesario()+tiempoTotalTranscurrido 
											<= this.getTiempoDisponible();
		}

		public boolean alcanzaPresupuestoPara(Atraccion proximaAtraccion, Sugerencia sugerencia){

			return ((proximaAtraccion.getCostoDeAtraccion() + sugerencia.getCostoTotalSinPromociones())*this.getCantidadDeIntegrantesGrupoFamiliar()
									<= this.getPresupuesto());
		}
		
}