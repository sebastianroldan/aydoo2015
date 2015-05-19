package turismo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class PromotorDeTurismo {

		private List<Atraccion> atracciones;
		private Set<Promocion> promociones;		
		
		public PromotorDeTurismo(){
				this.atracciones = new LinkedList<Atraccion>();
				this.promociones = new HashSet<Promocion>();			
		}
		
		private void aplicarPromocion(Sugerencia sugerencia, Turista turista) {
				PromocionExtranjero promoExtranjero = new PromocionExtranjero();
				if (promoExtranjero.esAplicableLaPromocion(sugerencia, turista)){
						promoExtranjero.aplicarPromocion(sugerencia);
				
				}else{
						aplicarPaqueteFamiliar(sugerencia, turista);
						for (Promocion promo: this.promociones){						
								
								if (promo.esAplicableLaPromocion(sugerencia)){
										promo.aplicarPromocion(sugerencia, turista);								
								}
						}						
				}
		}
	
		private void aplicarPaqueteFamiliar(Sugerencia sugerencia,Turista turista) {
				double descuentoGrupal = 0;
				double descuentoIndividual = 0;
				int personasCon30PorcientoDeDescuento = turista.getGrupoFamiliar() - 4;
				
				sugerencia.setCostoFinal(sugerencia.getCostoFinal()*turista.getGrupoFamiliar());
				
				if (turista.getGrupoFamiliar() >= 4){						
				
						descuentoIndividual = sugerencia.getCostoTotalSinPromociones()*0.10;						
						descuentoGrupal = descuentoIndividual*4;
						sugerencia.aplicarDescuentoACostoFinal(descuentoGrupal);
				}
				if (personasCon30PorcientoDeDescuento > 0){
					
						descuentoIndividual = sugerencia.getCostoTotalSinPromociones()*0.30;
						descuentoGrupal = descuentoIndividual*personasCon30PorcientoDeDescuento;
						sugerencia.aplicarDescuentoACostoFinal(descuentoGrupal);			
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
