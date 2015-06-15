package turismo;

public interface Promocion {	
					
		boolean esAplicableLaPromocion(Sugerencia sugerencia, Turista turista);
	
		void aplicarPromocion(Sugerencia sugerencia, Turista turista);

	
}
