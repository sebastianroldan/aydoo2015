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
		public void agregarAtraccion(Atraccion atraccionNueva) {
				
				this.atracciones.add(atraccionNueva);		
		}
	
		@Override
		public List<Atraccion> getAtracciones() {
			
				return this.atracciones;		
		}
	
		@Override
		public double costoTotal() {
				double costo = 0;
				
				for (Atraccion atraccion : atracciones){
						costo = costo + atraccion.getCostoDeAtraccion();
				}
				return costo;
		}
	
	
		@Override
		public void aplicarPromocion(Sugerencia sugerencia, Turista turista) {
				
				if (sugerencia.getListaDeAtracciones().contains(this.extra)){ 
						sugerencia.setCostoFinal(this.extra.getCostoDeAtraccion());
				}else{
						sugerencia.agregarAtraccionExtra(this.extra, turista);
				}
		}
	
	
		@Override
		public boolean esAplicableLaPromocion(Sugerencia sugerencia) {
				Set<Atraccion> conjuntoDeAtracciones = new HashSet<Atraccion>();
				conjuntoDeAtracciones.addAll(sugerencia.getListaDeAtracciones());
				return (conjuntoDeAtracciones.containsAll(this.atracciones));
		}
				
}
