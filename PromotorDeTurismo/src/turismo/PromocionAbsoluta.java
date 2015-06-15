package turismo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PromocionAbsoluta implements Promocion{

		private List<Atraccion> atracciones;	
		private double costoPorPromocion;
		private double montoADescontar;
		
		public PromocionAbsoluta(List<Atraccion> atracciones, double costo){
				this.costoPorPromocion = costo;
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
				montoADescontar =  (this.costoAtraccionesSinDescuento() - this.costoPorPromocion)*turista.getCantidadDeIntegrantesGrupoFamiliar();
				sugerencia.aplicarDescuentoACostoFinal(montoADescontar);		
			
		}
		
		@Override
		public boolean esAplicableLaPromocion(Sugerencia sugerencia, Turista turista) {
				Set<Atraccion> conjuntoDeAtracciones = new HashSet<Atraccion>();
				conjuntoDeAtracciones.addAll(sugerencia.getListaDeAtracciones());
				return (conjuntoDeAtracciones.containsAll(this.atracciones));
		}

}
