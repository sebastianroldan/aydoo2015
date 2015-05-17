package turismo;

import java.util.List;

public interface Promocion {	
		
		void agregarAtraccion(Atraccion atraccionNueva);
	
		List<Atraccion> getAtracciones();
	
		double costoAtraccionesSinDescuento();
	
		boolean esAplicableLaPromocion(Sugerencia sugerencia);
	
		void aplicarPromocion(Sugerencia sugerencia, Turista turista);

	
}
