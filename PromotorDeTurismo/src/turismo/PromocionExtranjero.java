package turismo;

import java.util.Iterator;

public class PromocionExtranjero{
				
		public boolean esAplicableLaPromocion(Sugerencia sugerencia, Turista turista) {
				Iterator<Atraccion> iteradorAtracciones = sugerencia.getListaDeAtracciones().iterator();
				boolean esAplicable = !sugerencia.getListaDeAtracciones().isEmpty();
				while (iteradorAtracciones.hasNext() && esAplicable){
						Atraccion atraccionActual = iteradorAtracciones.next();
						esAplicable = (turista.getCoordenada().
							calcularDistanciaDesdeEstaCoordenada(atraccionActual.getCoordenadas())  > 200);																
				}
				return esAplicable;
		}
	
		
		public void aplicarPromocion(Sugerencia sugerencia) {
				
			double descuento = sugerencia.getCostoFinal()/2;
			sugerencia.aplicarDescuentoACostoFinal(descuento);
		}

}
