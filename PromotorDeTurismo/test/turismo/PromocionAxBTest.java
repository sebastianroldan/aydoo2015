package turismo;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PromocionAxBTest {
		
		private Promocion promoAxB;
		private Atraccion comarca;
		private Atraccion rohan;
		private Atraccion isengard;
		private Turista turista = new Turista("Pablo", 5000, 600, 15, TipoDeAtraccion.AVENTURA, new Domicilio(100,500),1);
	
	
		@Before
		public void ejecutarAntesDeCadaTest(){		
				comarca = new Atraccion("La Comarca",new Coordenada(100,500), 200, 100, 15, TipoDeAtraccion.DEGUSTACION);
				rohan = new Atraccion("Rohan", new Coordenada(450, 120), 400, 100, 100, TipoDeAtraccion.AVENTURA);			
				isengard = new Atraccion("Isengard", new Coordenada(300,500), 300, 100, 5, TipoDeAtraccion.AVENTURA);
				
				List<Atraccion> atraccionesAxB = new LinkedList<Atraccion>();
				atraccionesAxB.add(rohan);
				atraccionesAxB.add(isengard);
											
				promoAxB = new PromocionAxB(atraccionesAxB, comarca);
				
				
				
		}
		
		@Test
		public void alAplicarPromoAxBSeAgregaAtraccionComarcaTest(){				
				
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
			
				atraccionesDisponibles.add(rohan);
				atraccionesDisponibles.add(isengard);				
		
				Sugerencia sugerencia = new Sugerencia();
				sugerencia.generarSugerencia(atraccionesDisponibles, turista);				
				
				promoAxB.aplicarPromocion(sugerencia, turista);
				
				
				Assert.assertEquals(700,sugerencia.getCostoFinal(),0);
				Assert.assertEquals(3, sugerencia.getListaDeAtracciones().size());
				Assert.assertEquals(comarca, sugerencia.getListaDeAtracciones().get(2));
			
		}
		
		@Test
		public void alAplicarPromoAxBNoDebeAgregarComarcaPorqueYaestaEnLaSugerenciaTest(){
				
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
			
				atraccionesDisponibles.add(rohan);
				atraccionesDisponibles.add(isengard);
				atraccionesDisponibles.add(comarca);
		
				Sugerencia sugerencia = new Sugerencia();
				sugerencia.generarSugerencia(atraccionesDisponibles, turista);	
				promoAxB.aplicarPromocion(sugerencia, turista);
				
				Assert.assertEquals(700,sugerencia.getCostoFinal(),0);
				Assert.assertEquals(3, sugerencia.getListaDeAtracciones().size());
				Assert.assertEquals(comarca, sugerencia.getListaDeAtracciones().get(2));
			
		}
		
		@Test
		public void alAplicarPromoAxBNoDebeAgregarComarcaPorFaltaDeTiempoTest(){
			
				Turista turista2 = new Turista("Sergio", 1950, 290, 15, TipoDeAtraccion.AVENTURA, new Domicilio(100,500),1);
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
			
				atraccionesDisponibles.add(rohan);
				atraccionesDisponibles.add(isengard);		
		
				Sugerencia sugerencia = new Sugerencia();
				sugerencia.generarSugerencia(atraccionesDisponibles, turista2);	
				promoAxB.aplicarPromocion(sugerencia, turista2);
								
				Assert.assertEquals(2, sugerencia.getListaDeAtracciones().size());
				Assert.assertEquals(rohan, sugerencia.getListaDeAtracciones().get(0));
				Assert.assertEquals(isengard, sugerencia.getListaDeAtracciones().get(1));
			
		}
		
		@Test
		public void esAplicableSiEnLaSugerenciaEstanTodasLasAtraccionesDeLaPromocionTest(){
				
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
				
				atraccionesDisponibles.add(rohan);
				atraccionesDisponibles.add(isengard);		
		
				Sugerencia sugerencia = new Sugerencia();
				sugerencia.generarSugerencia(atraccionesDisponibles, turista);	
				
				Assert.assertTrue(promoAxB.esAplicableLaPromocion(sugerencia, turista));
		}
		
		@Test
		public void noEsAplicableSiEnLaSugerenciaNoEstanTodasLasAtraccionesDeLaPromocionTest(){
				
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
				
				atraccionesDisponibles.add(rohan);
		
				Sugerencia sugerencia = new Sugerencia();
				sugerencia.generarSugerencia(atraccionesDisponibles, turista);	
				
				Assert.assertFalse(promoAxB.esAplicableLaPromocion(sugerencia, turista));
		}
		
	
	
}
