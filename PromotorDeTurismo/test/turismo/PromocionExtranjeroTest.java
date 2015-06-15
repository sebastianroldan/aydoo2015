package turismo;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PromocionExtranjeroTest {
	
		private Atraccion mordor;
		private Atraccion gondor;
		private Atraccion moria;		
		
		@Before
		public void ejecutarAntesDeCadaTest(){		

				mordor = new Atraccion("Mordor",new Coordenada(700,-800), 1200, 60, 5, TipoDeAtraccion.AVENTURA);
				gondor = new Atraccion("Gondor",new Coordenada(500,-650), 300, 180, 10, TipoDeAtraccion.PAISAJE);
				moria = new Atraccion("Minas de Moria", new Coordenada(150,200), 500, 45, 5, TipoDeAtraccion.AVENTURA);
		}
		
		@Test
		public void deberiaDescontar50PorcientoAlAplicarLaPromocionTest(){
			
				Turista turista3 = new Turista("Jose", 2500, 1200, 40, TipoDeAtraccion.AVENTURA, new Domicilio(0,199),1);
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
			
				
				moria.setCoordenadas(new Coordenada(0, 50));
				atraccionesDisponibles.add(moria);
				mordor.setCoordenadas(new Coordenada(0, 80));
				atraccionesDisponibles.add(mordor);		
				gondor.setCoordenadas(new Coordenada(0, 100));
				atraccionesDisponibles.add(gondor);
				
				Promocion promoExtranjero = new PromocionExtranjero();
		
				Sugerencia sugerencia = new Sugerencia();				
				
				sugerencia.generarSugerencia(atraccionesDisponibles, turista3);
				
				Assert.assertEquals(2000,sugerencia.getCostoFinal(),0);
				
				promoExtranjero.aplicarPromocion(sugerencia, turista3);
				
				Assert.assertEquals(1000,sugerencia.getCostoFinal(),0);
		}
		
		@Test
		public void noDeberiaSerAplicablePromocionExtranjeroSiEstaAMenosDe200KMTest(){
				
				Turista turista3 = new Turista("Jose", 2500, 1200, 40, TipoDeAtraccion.AVENTURA, new Domicilio(0,150),1);
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
				
				moria.setCoordenadas(new Coordenada(0, 10));
				atraccionesDisponibles.add(moria);
				mordor.setCoordenadas(new Coordenada(0, 600));
				atraccionesDisponibles.add(mordor);
				gondor.setCoordenadas(new Coordenada(0, 500));
				atraccionesDisponibles.add(gondor);		
				
				
				Promocion promoExtranjero = new PromocionExtranjero();
		
				Sugerencia sugerencia = new Sugerencia();				
				
				sugerencia.generarSugerencia(atraccionesDisponibles, turista3);			
				
				Assert.assertFalse(promoExtranjero.esAplicableLaPromocion(sugerencia, turista3));
		}
		
		@Test
		public void deberiaSeraplicablePromocionExtranjeroSiEstaAMasDe200KMTest(){
					
				Turista turista3 = new Turista("Jose", 2500, 1200, 40, TipoDeAtraccion.AVENTURA, new Domicilio(0,150),1);
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
				
				moria.setCoordenadas(new Coordenada(0, 351));
				atraccionesDisponibles.add(moria);
				mordor.setCoordenadas(new Coordenada(0, 600));
				atraccionesDisponibles.add(mordor);
				gondor.setCoordenadas(new Coordenada(0, 500));
				atraccionesDisponibles.add(gondor);		
				
				Promocion promoExtranjero = new PromocionExtranjero();
		
				Sugerencia sugerencia = new Sugerencia();				
				
				sugerencia.generarSugerencia(atraccionesDisponibles, turista3);			
				
				Assert.assertTrue(promoExtranjero.esAplicableLaPromocion(sugerencia, turista3));
		}
		
		@Test
		public void noDeberiaSerAplicableSiLaSugerenciaNoTieneAtraccionesTest(){
			
			Sugerencia sugerencia = new Sugerencia();				
			
			Turista turista3 = new Turista("Jose", 2500, 1200, 40, TipoDeAtraccion.AVENTURA, new Domicilio(0,150),1);
			
			Promocion promoExtranjero = new PromocionExtranjero();
			
			Assert.assertFalse(promoExtranjero.esAplicableLaPromocion(sugerencia, turista3));
			
		} 

}
