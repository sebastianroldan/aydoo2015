package turismo;

public class PromocionPaqueteFamiliar implements Promocion{

	@Override
	public boolean esAplicableLaPromocion(Sugerencia sugerencia, Turista turista) {
			
			return (turista.getCantidadDeIntegrantesGrupoFamiliar() >= 4);
	}

	@Override
	public void aplicarPromocion(Sugerencia sugerencia, Turista turista) {
					
			double descuentoGrupal = 0;
			double descuentoIndividual = 0;
			int personasCon30PorcientoDeDescuento = turista.getCantidadDeIntegrantesGrupoFamiliar() - 4;				
			
			descuentoIndividual = sugerencia.getCostoTotalSinPromociones()*0.10;						
			descuentoGrupal = descuentoIndividual*4;
			sugerencia.aplicarDescuentoACostoFinal(descuentoGrupal);
			if (personasCon30PorcientoDeDescuento > 0){
				
					descuentoIndividual = sugerencia.getCostoTotalSinPromociones()*0.30;
					descuentoGrupal = descuentoIndividual*personasCon30PorcientoDeDescuento;
					sugerencia.aplicarDescuentoACostoFinal(descuentoGrupal);			
			}
	}
	
}
