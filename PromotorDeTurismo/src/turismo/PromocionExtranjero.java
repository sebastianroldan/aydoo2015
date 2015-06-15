package turismo;

import java.util.Iterator;

public class PromocionExtranjero implements Promocion{
	
		
		@Override
		public boolean esAplicableLaPromocion(Sugerencia sugerencia, Turista turista) {
				Iterator<Atraccion> iteradorAtracciones = sugerencia.getListaDeAtracciones().iterator();
				boolean esAplicable = !sugerencia.getListaDeAtracciones().isEmpty();
				while (iteradorAtracciones.hasNext() && esAplicable){
						Atraccion atraccionActual = iteradorAtracciones.next();
						esAplicable = (turista.getDireccion().getCoordenada().
							calcularDistanciaDesdeEstaCoordenada(atraccionActual.getCoordenadas())  > 200);																
				}
				return esAplicable;
		}
	
		@Override
		public void aplicarPromocion(Sugerencia sugerencia, Turista turista) {
				
			double descuento = sugerencia.getCostoFinal()/2;
			sugerencia.aplicarDescuentoACostoFinal(descuento);
		}

}
