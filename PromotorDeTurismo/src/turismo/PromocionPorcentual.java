package turismo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PromocionPorcentual implements Promocion{

		private List<Atraccion> atracciones;
		private double porcentaje;
		private double montoADescontar;
		
		public PromocionPorcentual(List<Atraccion> atracciones, int porcentaje){
				this.porcentaje = porcentaje;
				this.atracciones = atracciones;
		}
	
		private double costoAtraccionesSinDescuento() {
				double costo = 0;
				
				for (Atraccion atraccion : atracciones){
						costo = costo + atraccion.getCostoDeAtraccion();
				}
				return costo;
	    }
		
		@Override
		public void aplicarPromocion(Sugerencia sugerencia, Turista turista) {
				montoADescontar = (this.costoAtraccionesSinDescuento()*(porcentaje/100)*turista.getCantidadDeIntegrantesGrupoFamiliar()); 				
				sugerencia.aplicarDescuentoACostoFinal(montoADescontar);		
		}
	
	
		@Override
		public boolean esAplicableLaPromocion(Sugerencia sugerencia, Turista turista) {
				Set<Atraccion> conjuntoDeAtracciones = new HashSet<Atraccion>();
				conjuntoDeAtracciones.addAll(sugerencia.getListaDeAtracciones());
				return (conjuntoDeAtracciones.containsAll(this.atracciones));
		}		
		
}
