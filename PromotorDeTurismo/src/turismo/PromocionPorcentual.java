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
		public void aplicarPromocion(Sugerencia recorrido) {
				montoADescontar = costoTotal()*(porcentaje/100); 
				recorrido.setCostoFinal(montoADescontar);		
		}
	
	
		@Override
		public boolean esAplicableLaPromocion(Sugerencia recorrido) {
				Set<Atraccion> conjuntoDeAtracciones = new HashSet<Atraccion>();
				conjuntoDeAtracciones.addAll(recorrido.getListaDeAtracciones());
				return (conjuntoDeAtracciones.containsAll(this.atracciones));
		}
		
}
