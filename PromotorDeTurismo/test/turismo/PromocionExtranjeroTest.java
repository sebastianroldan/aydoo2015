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
		public void aplicarPromocionExtranjeroTest(){
			
				Turista turista3 = new Turista("Jose", 2500, 1200, 40, TipoDeAtraccion.AVENTURA, new Coordenada(0,199),1);
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
			
				atraccionesDisponibles.add(moria);
				moria.setCoordenadas(new Coordenada(0, 50));
				atraccionesDisponibles.add(mordor);
				moria.setCoordenadas(new Coordenada(0, 80));
				atraccionesDisponibles.add(gondor);		
				moria.setCoordenadas(new Coordenada(0, 100));
				PromocionExtranjero promoExtranjero = new PromocionExtranjero();
		
				Sugerencia sugerencia = new Sugerencia();				
				
				sugerencia.generarSugerencia(atraccionesDisponibles, turista3);
				
				Assert.assertEquals(2000,sugerencia.getCostoFinal(),0);
				
				promoExtranjero.aplicarPromocion(sugerencia);
				
				Assert.assertEquals(1000,sugerencia.getCostoFinal(),0);
		}
		
		@Test
		public void noAplicarPromocionExtranjeroSiEstaAMenosDe200KMTest(){
				
				Turista turista3 = new Turista("Jose", 2500, 1200, 40, TipoDeAtraccion.AVENTURA, new Coordenada(0,150),1);
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
			
				atraccionesDisponibles.add(moria);
				moria.setCoordenadas(new Coordenada(0, 600));
				atraccionesDisponibles.add(mordor);
				moria.setCoordenadas(new Coordenada(0, 500));
				atraccionesDisponibles.add(gondor);		
				moria.setCoordenadas(new Coordenada(0, 10));
				PromocionExtranjero promoExtranjero = new PromocionExtranjero();
		
				Sugerencia sugerencia = new Sugerencia();				
				
				sugerencia.generarSugerencia(atraccionesDisponibles, turista3);			
				
				Assert.assertFalse(promoExtranjero.esAplicableLaPromocion(sugerencia, turista3));
		}
		
		@Test
		public void aplicarPromocionExtranjeroSiEstaAMasDe200KMTest(){
					
				Turista turista3 = new Turista("Jose", 2500, 1200, 40, TipoDeAtraccion.AVENTURA, new Coordenada(0,150),1);
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
				
				atraccionesDisponibles.add(moria);
				moria.setCoordenadas(new Coordenada(0, 600));
				atraccionesDisponibles.add(mordor);
				moria.setCoordenadas(new Coordenada(0, 500));
				atraccionesDisponibles.add(gondor);		
				moria.setCoordenadas(new Coordenada(0, 351));
				PromocionExtranjero promoExtranjero = new PromocionExtranjero();
		
				Sugerencia sugerencia = new Sugerencia();				
				
				sugerencia.generarSugerencia(atraccionesDisponibles, turista3);			
				
				Assert.assertTrue(promoExtranjero.esAplicableLaPromocion(sugerencia, turista3));
		}
		

}
