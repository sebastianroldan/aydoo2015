package turismo;

import java.util.LinkedList;
import java.util.List;

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
				
				Turista turista = new Turista("Marcos", 3000, 300, 15, TipoDeAtraccion.DEGUSTACION, new Coordenada(100,500),1);
				
				Sugerencia[] sugerenciasParaTurista = new Sugerencia[5];
				sugerenciasParaTurista = promotor.sugerirListaDeItinerarios(turista);		
				Assert.assertEquals(5, sugerenciasParaTurista.length);
		}
		
		
		@Test
		public void generarSugerenciasQueNoSuperenPresupuestoDelTuristaTest(){		
				
				promotor = new PromotorDeTurismo();
				promotor.agregarAtraccion(comarca);
				promotor.agregarAtraccion(mordor);
				promotor.agregarAtraccion(gondor);
				
				Turista turista = new Turista("Marcos", 3000, 430, 30, TipoDeAtraccion.DEGUSTACION, new Coordenada(100,500),1);
				Sugerencia[] sugerenciasParaTurista = new Sugerencia[5];
				
				sugerenciasParaTurista = promotor.sugerirListaDeItinerarios(turista);
				double maximoCosto = turista.getPresupuesto();						
				
				Assert.assertTrue(maximoCosto >= sugerenciasParaTurista[0].getCostoFinal());
				Assert.assertTrue(maximoCosto >= sugerenciasParaTurista[1].getCostoFinal());
				Assert.assertTrue(maximoCosto >= sugerenciasParaTurista[2].getCostoFinal());
				Assert.assertTrue(maximoCosto >= sugerenciasParaTurista[3].getCostoFinal());
				Assert.assertTrue(maximoCosto >= sugerenciasParaTurista[4].getCostoFinal());
		}
		
		@Test
		public void alAplicarPromocionExtranjeroNoDebeAplicarPromocionAxBTest(){		
				
				Atraccion comarca = new Atraccion("La Comarca",new Coordenada(0,100), 600, 120, 15, TipoDeAtraccion.AVENTURA);
				Atraccion mordor = new Atraccion("Mordor",new Coordenada(0,50), 400, 60, 5, TipoDeAtraccion.AVENTURA);
				Atraccion gondor = new Atraccion("Gondor",new Coordenada(0,150), 580, 180, 10, TipoDeAtraccion.PAISAJE);		
				
				promotor = new PromotorDeTurismo();
				promotor.agregarAtraccion(comarca);
				promotor.agregarAtraccion(mordor);				
				promotor.agregarAtraccion(gondor);
				
				List<Atraccion> atracciones = new LinkedList<Atraccion>();
				atracciones.add(comarca);
				atracciones.add(mordor);
				
				Turista turista = new Turista("Marcos", 3000, 5000, 30, TipoDeAtraccion.AVENTURA, new Coordenada(0,500),1);								
				Sugerencia[] sugerenciasParaTurista = new Sugerencia[5];
				
				Promocion promoAxB = new PromocionAxB(atracciones, gondor);
				promotor.agregarPromocion(promoAxB);
				
				sugerenciasParaTurista = promotor.sugerirListaDeItinerarios(turista);
				
				Sugerencia sugerenciaPorPreferencia = sugerenciasParaTurista[2];
				
				Assert.assertEquals(2, sugerenciaPorPreferencia.getListaDeAtracciones().size());
				Assert.assertEquals(mordor, sugerenciaPorPreferencia.getListaDeAtracciones().get(0));
				Assert.assertEquals(comarca, sugerenciaPorPreferencia.getListaDeAtracciones().get(1));
				Assert.assertEquals(500, sugerenciaPorPreferencia.getCostoFinal(), 0);
		}
		
		@Test
		public void sugerenciaConGrupoFamiliarDe3NoAplicaPaqueteFamiliarTest(){		
			
				
				Atraccion comarca = new Atraccion("La Comarca",new Coordenada(0,100), 600, 120, 15, TipoDeAtraccion.AVENTURA);
				Atraccion mordor = new Atraccion("Mordor",new Coordenada(0,50), 400, 60, 3, TipoDeAtraccion.AVENTURA);
				Atraccion gondor = new Atraccion("Gondor",new Coordenada(0,150), 580, 180, 10, TipoDeAtraccion.PAISAJE);
						
				promotor = new PromotorDeTurismo();
				promotor.agregarAtraccion(comarca);
				promotor.agregarAtraccion(mordor);				
				promotor.agregarAtraccion(gondor);
				
				List<Atraccion> atracciones = new LinkedList<Atraccion>();
				atracciones.add(comarca);
				atracciones.add(mordor);
				
				Turista turista = new Turista("Marcos", 5000, 5000, 50, TipoDeAtraccion.AVENTURA, new Coordenada(0,100),3);								
				Sugerencia[] sugerenciasParaTurista = new Sugerencia[5];
				
				Promocion promoAxB = new PromocionAxB(atracciones, gondor);
				promotor.agregarPromocion(promoAxB);
				
				sugerenciasParaTurista = promotor.sugerirListaDeItinerarios(turista);
				
				Sugerencia sugerenciaPorPreferencia = sugerenciasParaTurista[2];
				
				Assert.assertEquals(3, sugerenciaPorPreferencia.getListaDeAtracciones().size());
				Assert.assertEquals(mordor, sugerenciaPorPreferencia.getListaDeAtracciones().get(0));
				Assert.assertEquals(comarca, sugerenciaPorPreferencia.getListaDeAtracciones().get(1));
				Assert.assertEquals(gondor, sugerenciaPorPreferencia.getListaDeAtracciones().get(2));
				Assert.assertEquals(3000, sugerenciaPorPreferencia.getCostoFinal(), 0);
		}
		
		@Test
		public void sugerenciaConGrupoFamiliarDe4AplicaPaqueteFamiliar10DeDescuentoTest(){		
			
				
				Atraccion comarca = new Atraccion("La Comarca",new Coordenada(0,100), 600, 120, 15, TipoDeAtraccion.AVENTURA);
				Atraccion mordor = new Atraccion("Mordor",new Coordenada(0,50), 400, 60, 4, TipoDeAtraccion.AVENTURA);
				Atraccion gondor = new Atraccion("Gondor",new Coordenada(0,150), 1000, 180, 10, TipoDeAtraccion.PAISAJE);
						
				promotor = new PromotorDeTurismo();
				promotor.agregarAtraccion(comarca);
				promotor.agregarAtraccion(mordor);				
				promotor.agregarAtraccion(gondor);
				
				List<Atraccion> atracciones = new LinkedList<Atraccion>();
				atracciones.add(comarca);
				atracciones.add(mordor);
				
				Turista turista = new Turista("Marcos", 4000, 5000, 50, TipoDeAtraccion.AVENTURA, new Coordenada(0,100),4);								
				Sugerencia[] sugerenciasParaTurista = new Sugerencia[5];
				
				Promocion promoAxB = new PromocionAxB(atracciones, gondor);
				promotor.agregarPromocion(promoAxB);
				
				sugerenciasParaTurista = promotor.sugerirListaDeItinerarios(turista);
				
				Sugerencia sugerenciaPorPreferencia = sugerenciasParaTurista[2];
				
				Assert.assertEquals(3, sugerenciaPorPreferencia.getListaDeAtracciones().size());
				Assert.assertEquals(mordor, sugerenciaPorPreferencia.getListaDeAtracciones().get(0));
				Assert.assertEquals(comarca, sugerenciaPorPreferencia.getListaDeAtracciones().get(1));
				Assert.assertEquals(gondor, sugerenciaPorPreferencia.getListaDeAtracciones().get(2));
				Assert.assertEquals(3600, sugerenciaPorPreferencia.getCostoFinal(), 0);
		}
		
		@Test
		public void sugerenciaConGrupoFamiliarDe6Aplica10Y30DeDescuentoTest(){		
			
				
				Atraccion comarca = new Atraccion("La Comarca",new Coordenada(0,100), 600, 120, 15, TipoDeAtraccion.AVENTURA);
				Atraccion mordor = new Atraccion("Mordor",new Coordenada(0,50), 400, 60, 6, TipoDeAtraccion.AVENTURA);
				Atraccion gondor = new Atraccion("Gondor",new Coordenada(0,150), 1000, 180, 10, TipoDeAtraccion.PAISAJE);
						
				promotor = new PromotorDeTurismo();
				promotor.agregarAtraccion(comarca);
				promotor.agregarAtraccion(mordor);				
				promotor.agregarAtraccion(gondor);
				
				List<Atraccion> atracciones = new LinkedList<Atraccion>();
				atracciones.add(comarca);
				atracciones.add(mordor);
				
				Turista turista = new Turista("Marcos", 6000, 5000, 50, TipoDeAtraccion.AVENTURA, new Coordenada(0,100),6);								
				Sugerencia[] sugerenciasParaTurista = new Sugerencia[5];
				
				Promocion promoAxB = new PromocionAxB(atracciones, gondor);
				promotor.agregarPromocion(promoAxB);
				
				sugerenciasParaTurista = promotor.sugerirListaDeItinerarios(turista);
				
				Sugerencia sugerenciaPorPreferencia = sugerenciasParaTurista[2];
				
				Assert.assertEquals(3, sugerenciaPorPreferencia.getListaDeAtracciones().size());
				Assert.assertEquals(mordor, sugerenciaPorPreferencia.getListaDeAtracciones().get(0));
				Assert.assertEquals(comarca, sugerenciaPorPreferencia.getListaDeAtracciones().get(1));
				Assert.assertEquals(gondor, sugerenciaPorPreferencia.getListaDeAtracciones().get(2));
				Assert.assertEquals(5000, sugerenciaPorPreferencia.getCostoFinal(), 0);
		}
		
		@Test
		public void sugerenciaConGrupoFamiliarDe6Aplica10Y30DeDescuentoMasPromoAbsolutaTest(){		
			
				
				Atraccion comarca = new Atraccion("La Comarca",new Coordenada(0,100), 600, 120, 15, TipoDeAtraccion.AVENTURA);
				Atraccion mordor = new Atraccion("Mordor",new Coordenada(0,50), 400, 60, 6, TipoDeAtraccion.AVENTURA);
				Atraccion gondor = new Atraccion("Gondor",new Coordenada(0,150), 1000, 180, 10, TipoDeAtraccion.PAISAJE);
						
				promotor = new PromotorDeTurismo();
				promotor.agregarAtraccion(comarca);
				promotor.agregarAtraccion(mordor);				
				promotor.agregarAtraccion(gondor);
				
				List<Atraccion> atracciones = new LinkedList<Atraccion>();
				atracciones.add(comarca);
				atracciones.add(mordor);
				
				Turista turista = new Turista("Marcos", 7200, 5000, 50, TipoDeAtraccion.AVENTURA, new Coordenada(0,100),6);								
				Sugerencia[] sugerenciasParaTurista = new Sugerencia[5];
				
				Promocion promoAbsoluta = new PromocionAbsoluta(atracciones, 950);
				promotor.agregarPromocion(promoAbsoluta);
				
				sugerenciasParaTurista = promotor.sugerirListaDeItinerarios(turista);
				
				Sugerencia sugerenciaPorPreferencia = sugerenciasParaTurista[2];
				
				Assert.assertEquals(2, sugerenciaPorPreferencia.getListaDeAtracciones().size());
				Assert.assertEquals(mordor, sugerenciaPorPreferencia.getListaDeAtracciones().get(0));
				Assert.assertEquals(comarca, sugerenciaPorPreferencia.getListaDeAtracciones().get(1));				
				Assert.assertEquals(4700, sugerenciaPorPreferencia.getCostoFinal(), 0);
		}
}
