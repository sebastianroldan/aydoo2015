package turismo;

public class Atraccion {
	
		private String nombre;
		private Coordenada coordenadas;
		private double costoDeAtraccion;
		private double tiempoNecesario;
		private int cantidadDeVisitantes;
		private int cupoMaximo;
		private TipoDeAtraccion tipoAtraccion;

		public Atraccion(String nombre, Coordenada coordenadas, double costo,
				double tiempo, int cupo, TipoDeAtraccion tipo) {
			
				this.setNombre(nombre);
				this.setCoordenadas(coordenadas);
				this.setCostoDeAtraccion(costo);
				this.setTiempoNecesario(tiempo);
				this.setCupoMaximo(cupo);
				this.setTipoAtraccion(tipo);
				this.cantidadDeVisitantes = 0;
		}
	
		public double getTiempoNecesario() {
				return this.tiempoNecesario;
		}
	
		public void setTiempoNecesario(double tiempo) {
				if (tiempo > 0){
						this.tiempoNecesario = tiempo;
				}	
		}
	
		public double getCostoDeAtraccion() {
				return this.costoDeAtraccion;
		}
		
		public void setCostoDeAtraccion(double costoDeAtraccion) {
				if (costoDeAtraccion >= 0){
						this.costoDeAtraccion = costoDeAtraccion;
				}		
		}
		
		public Coordenada getCoordenadas() {
				return this.coordenadas;
		}
	
		public void setCoordenadas(Coordenada coordenadas) {
				this.coordenadas = coordenadas;
		}
	
		public int getCupoMaximo() {
				return this.cupoMaximo;
		}
	
		public void setCupoMaximo(int cupoMaximo) {
				if (cupoMaximo > 0){
						this.cupoMaximo = cupoMaximo;
				}		
		}
	
		public String getNombre() {
				return nombre;
		}
	
		public void setNombre(String nombre) {
				this.nombre = nombre;
		}
	
		public TipoDeAtraccion getTipoAtraccion() {
			return tipoAtraccion;
		}
	
		public void setTipoAtraccion(TipoDeAtraccion tipoAtraccion) {
				this.tipoAtraccion = tipoAtraccion;
		}
		
		public int getCantidadDeVisitantes(){
				return this.cantidadDeVisitantes;
		}
		
		public void agregarVisitante(int nuevasVisitas){
				if (this.hayCupo(nuevasVisitas)){						
						this.cantidadDeVisitantes = this.cantidadDeVisitantes + nuevasVisitas;						
				}
		}
	
		public boolean hayCupo(int nuevasVisitas) {
				return this.getCupoMaximo() >= (this.getCantidadDeVisitantes()+nuevasVisitas);
		}
		
		@Override
		public boolean equals(Object obj) {
				Atraccion atraccion = (Atraccion) obj;
				return this.getNombre() == atraccion.getNombre();	
		}
	
}