package turismo;

import org.junit.Assert;
import org.junit.Test;

import turismo.Atraccion;
import turismo.Coordenada;
import turismo.PromotorDeTurismo;
import turismo.Sugerencia;
import turismo.TipoDeAtraccion;
import turismo.Turista;

public class PromotorDeTurismoTest {
	
		private PromotorDeTurismo promotor;
		private Atraccion comarca = new Atraccion("La Comarca",new Coordenada(100,500), 450, 120, 15, TipoDeAtraccion.DEGUSTACION);
		private Atraccion mordor = new Atraccion("Mordor",new Coordenada(700,-800), 1200, 60, 5, TipoDeAtraccion.AVENTURA);
		private Atraccion gondor = new Atraccion("Gondor",new Coordenada(500,-650), 300, 180, 10, TipoDeAtraccion.PAISAJE);
	
		@Test
		public void sugerirCincoItinerariosPorCadaTuristaTest(){
				promotor = new PromotorDeTurismo();
				promotor.agregarAtraccion(comarca);
				promotor.agregarAtraccion(mordor);
				promotor.agregarAtraccion(gondor);
				
				Turista turista = new Turista("Marcos", 3000, 300, 15, TipoDeAtraccion.DEGUSTACION);
				
				Sugerencia[] sugerenciasParaTurista = new Sugerencia[5];
				sugerenciasParaTurista = promotor.sugerirListaDeItinerarios(turista);		
				Assert.assertEquals(5, sugerenciasParaTurista.length);
		}
		
		
		@Test
		public void generarSugerenciasQueNoSuperenPresupuestoDelTuristaTest(){		
				
				Atraccion comarca = new Atraccion("La Comarca",new Coordenada(100,500), 450, 120, 15, TipoDeAtraccion.DEGUSTACION);
				Atraccion mordor = new Atraccion("Mordor",new Coordenada(700,-800), 1200, 60, 5, TipoDeAtraccion.AVENTURA);
				Atraccion gondor = new Atraccion("Gondor",new Coordenada(500,-650), 300, 180, 10, TipoDeAtraccion.PAISAJE);		
				
				promotor = new PromotorDeTurismo();
				promotor.agregarAtraccion(comarca);
				promotor.agregarAtraccion(mordor);
				promotor.agregarAtraccion(gondor);
				
				Turista turista = new Turista("Marcos", 3000, 430, 30, TipoDeAtraccion.DEGUSTACION);
				Sugerencia[] sugerenciasParaTurista = new Sugerencia[5];
				
				sugerenciasParaTurista = promotor.sugerirListaDeItinerarios(turista);
				double maximoCosto = turista.getPresupuesto();						
				
				Assert.assertTrue(maximoCosto >= sugerenciasParaTurista[0].getCostoFinal());
				Assert.assertTrue(maximoCosto >= sugerenciasParaTurista[1].getCostoFinal());
				Assert.assertTrue(maximoCosto >= sugerenciasParaTurista[2].getCostoFinal());
				Assert.assertTrue(maximoCosto >= sugerenciasParaTurista[3].getCostoFinal());
				Assert.assertTrue(maximoCosto >= sugerenciasParaTurista[4].getCostoFinal());
		}
}
