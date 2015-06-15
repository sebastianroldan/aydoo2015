package turismo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PromocionAxB implements Promocion {

		private List<Atraccion> atracciones;
		private Atraccion extra;		
			
		public PromocionAxB(List<Atraccion> atracciones, Atraccion extra){
				this.extra = extra;
				this.atracciones = atracciones;				
		}
			
		@Override
		public void aplicarPromocion(Sugerencia sugerencia, Turista turista) {
				
				if (sugerencia.getListaDeAtracciones().contains(this.extra)){ 
						sugerencia.aplicarDescuentoACostoFinal(this.extra.getCostoDeAtraccion());
				}else{
						sugerencia.agregarAtraccionExtra(this.extra, turista);
				}
		}
		
		@Override
		public boolean esAplicableLaPromocion(Sugerencia sugerencia, Turista turista) {
				Set<Atraccion> conjuntoDeAtracciones = new HashSet<Atraccion>();
				conjuntoDeAtracciones.addAll(sugerencia.getListaDeAtracciones());
				return (conjuntoDeAtracciones.containsAll(this.atracciones));
		}
				
}
