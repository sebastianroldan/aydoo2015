package turismo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class PromotorDeTurismo {

		private List<Atraccion> atracciones;
		private Set<Promocion> promociones;
		private List<Turista> turistas;
		
		public PromotorDeTurismo(){
				this.atracciones = new LinkedList<Atraccion>();
				this.promociones = new HashSet<Promocion>();
				this.turistas = new LinkedList<Turista>();
		}
		
		private void aplicarPromocion(Sugerencia sugerencia, Turista turista) {
				for (Promocion promo: this.promociones){						
						
						if (promo.esAplicableLaPromocion(sugerencia)){
								promo.aplicarPromocion(sugerencia, turista);								
						}
				}
		}
	
		private Sugerencia generarSugerencia(Turista turista, int i) {
				Sugerencia sugerencia = new Sugerencia();
				switch (i) {
						case 0: sugerencia.generarSugerenciaConAtraccionesPorMenorCosto(this.atracciones, turista);
						break;
						case 1: sugerencia.generarSugerenciaConAtraccionesPorTiempoDisponible(this.atracciones, turista);
						break;			
						case 2: sugerencia.generarSugerenciaConAtraccionesPorPreferencia(this.atracciones, turista);								
						break;			
						case 3: sugerencia.generarSugerenciaConAtraccionesMasCostosas(this.atracciones, turista);
						break;			
						default: sugerencia.generarSugerencia(this.atracciones, turista);
						break;			
				}
					
				return sugerencia;
		}	
		
		public void agregarAtraccion(Atraccion atraccion){
				this.atracciones.add(atraccion);
		}
		
		public void agregarTurista(Turista turista){
				this.turistas.add(turista);
		}
			
		public void agregarPromocion(Promocion promocion){
				this.promociones.add(promocion);
		}
		
		public Sugerencia[] sugerirListaDeItinerarios(Turista turista) {
				Sugerencia[] sugerencias = new Sugerencia[5];
				
				for (int i=0; i < 5; i++){
							sugerencias[i] = generarSugerencia(turista, i);
							aplicarPromocion(sugerencias[i], turista);
				}
				return sugerencias;
		}

}
