package turismoTest;

import org.junit.Assert;
import org.junit.Test;

import turismo.PromotorDeTurismo;
import turismo.Sugerencia;
import turismo.TipoDeAtraccion;
import turismo.Turista;

public class PromotorDeTurismoTest {
	
	
		@Test
		public void sugerirCincoItinerariosPorCadaTuristaTest(){
				PromotorDeTurismo promotor = new PromotorDeTurismo();
				Turista turista = new Turista("Marcos", 3000, 300, 15, TipoDeAtraccion.DEGUSTACION);
				
				Sugerencia[] sugerenciasParaTurista = new Sugerencia[5];
				sugerenciasParaTurista = promotor.sugerirListaDeItinerarios(turista);		
				Assert.assertEquals(5, sugerenciasParaTurista.length);
		}
		
		
		@Test
		public void generarSugerenciasQueNoSuperenPresupuestoDelTuristaTest(){		
			
				PromotorDeTurismo promotor = new PromotorDeTurismo();
				Turista turista = new Turista("Marcos", 3000, 300, 15, TipoDeAtraccion.DEGUSTACION);
				Sugerencia[] sugerenciasParaTurista = new Sugerencia[5];
				
				sugerenciasParaTurista = promotor.sugerirListaDeItinerarios(turista);
				double maximoCosto = turista.getPresupuesto();		
				
				Assert.assertTrue(maximoCosto >= sugerenciasParaTurista[0].getCostoTotalSinPromociones());
				Assert.assertTrue(maximoCosto >= sugerenciasParaTurista[1].getCostoTotalSinPromociones());
				Assert.assertTrue(maximoCosto >= sugerenciasParaTurista[2].getCostoTotalSinPromociones());
				Assert.assertTrue(maximoCosto >= sugerenciasParaTurista[3].getCostoTotalSinPromociones());
				Assert.assertTrue(maximoCosto >= sugerenciasParaTurista[4].getCostoTotalSinPromociones());
		}
}
