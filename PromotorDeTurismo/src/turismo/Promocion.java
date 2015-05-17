package turismo;

import java.util.List;

public interface Promocion {	
		
		void agregarAtraccion(Atraccion atraccionNueva);
	
		List<Atraccion> getAtracciones();
	
		double costoTotal();
	
		boolean esAplicableLaPromocion(Sugerencia recorrido);
	
		void aplicarPromocion(Sugerencia recorrido, Turista turista);	
	
}
