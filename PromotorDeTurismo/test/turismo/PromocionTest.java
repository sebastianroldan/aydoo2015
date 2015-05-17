package turismo;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import turismo.Atraccion;
import turismo.Coordenada;
import turismo.Promocion;
import turismo.PromocionAbsoluta;
import turismo.PromocionAxB;
import turismo.PromocionPorcentual;
import turismo.Sugerencia;
import turismo.TipoDeAtraccion;
import turismo.Turista;

public class PromocionTest {

		private Promocion promoAbsoluta;
		private Promocion promoPorcentual;
		private Promocion promoAxB;
		private Atraccion mordor;
		private Atraccion gondor;
		private Atraccion moria;
		private Atraccion comarca;
		private Atraccion rohan;
		private Atraccion isengard;
		private Turista turista = new Turista("Pablo", 1950, 600, 15, TipoDeAtraccion.AVENTURA, new Coordenada(100,500));
		
		
		@Before
		public void ejecutarAntesDeCadaTest(){		
				comarca = new Atraccion("La Comarca",new Coordenada(100,500), 450, 120, 15, TipoDeAtraccion.DEGUSTACION);
				mordor = new Atraccion("Mordor",new Coordenada(700,-800), 1200, 60, 5, TipoDeAtraccion.AVENTURA);
				gondor = new Atraccion("Gondor",new Coordenada(500,-650), 300, 180, 10, TipoDeAtraccion.PAISAJE);
				rohan = new Atraccion("Rohan", new Coordenada(450, 120), 400, 60, 10, TipoDeAtraccion.AVENTURA);
				moria = new Atraccion("Minas de Moria", new Coordenada(150,200), 500, 45, 5, TipoDeAtraccion.AVENTURA);
				isengard = new Atraccion("Isengard", new Coordenada(300,500), 30, 120, 5, TipoDeAtraccion.AVENTURA);
				
				List<Atraccion> atraccionesPorcentual = new LinkedList<Atraccion>();
				atraccionesPorcentual.add(moria);
				atraccionesPorcentual.add(mordor);
				atraccionesPorcentual.add(gondor);
				
				List<Atraccion> atraccionesAbsolutas = new LinkedList<Atraccion>();
				atraccionesAbsolutas.add(rohan);
				atraccionesAbsolutas.add(isengard);
				
				List<Atraccion> atraccionesAxB = new LinkedList<Atraccion>();
				atraccionesAxB.add(rohan);
				atraccionesAxB.add(isengard);
								
				promoPorcentual = new PromocionPorcentual(atraccionesPorcentual, 30);
				promoAbsoluta = new PromocionAbsoluta(atraccionesAbsolutas, 410);
				promoAxB = new PromocionAxB(atraccionesAxB, comarca);
				
		}
		
		@Test
		public void crearPromocionAbsolutaTest(){		
				
				List<Atraccion> lista = promoAbsoluta.getAtracciones();
				
				Assert.assertEquals(2,lista.size());
				Assert.assertEquals(rohan, lista.get(0));
				Assert.assertEquals(isengard, lista.get(1));		
		}
		
		@Test
		public void alAplicarPromoAbsolutaDebenCostar410RohaneIsengardTest(){
				
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
			
				atraccionesDisponibles.add(rohan);
				atraccionesDisponibles.add(isengard);
				atraccionesDisponibles.add(gondor);		
		
				Sugerencia sugerencia = new Sugerencia();
				sugerencia.generarSugerencia(atraccionesDisponibles, turista);		
				
				Assert.assertEquals(730, sugerencia.getCostoFinal(),0);
				
				promoAbsoluta.aplicarPromocion(sugerencia, turista);
				
				Assert.assertEquals(710,sugerencia.getCostoFinal(),0);
			
		}
		
		@Test
		public void alAplicarPromoAxBSeAgregaAtraccionComarcaTest(){				
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
			
				atraccionesDisponibles.add(rohan);
				atraccionesDisponibles.add(isengard);				
		
				Sugerencia sugerencia = new Sugerencia();
				sugerencia.generarSugerencia(atraccionesDisponibles, turista);				
				promoAxB.aplicarPromocion(sugerencia, turista);
				
				
				Assert.assertEquals(430,sugerencia.getCostoFinal(),0);
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
				
				Assert.assertEquals(430,sugerencia.getCostoFinal(),0);
				Assert.assertEquals(3, sugerencia.getListaDeAtracciones().size());
				Assert.assertEquals(comarca, sugerencia.getListaDeAtracciones().get(2));
			
		}
		
		@Test
		public void alAplicarPromoAxBNoDebeAgregarComarcaPorFaltaDeTiempoTest(){
				Turista turista2 = new Turista("Sergio", 1950, 270, 15, TipoDeAtraccion.AVENTURA, new Coordenada(100,500));
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
		public void alAplicarPromoPorcentualDebeDescontar600Test(){
				Turista turista3 = new Turista("Juan", 2300, 600, 15, TipoDeAtraccion.AVENTURA, new Coordenada(100,500));
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
			
				atraccionesDisponibles.add(moria);
				atraccionesDisponibles.add(mordor);
				atraccionesDisponibles.add(gondor);		
		
				Sugerencia sugerencia = new Sugerencia();				
				
				sugerencia.generarSugerencia(atraccionesDisponibles, turista3);
				
				Assert.assertEquals(2000,sugerencia.getCostoFinal(),0);
				
				promoPorcentual.aplicarPromocion(sugerencia, turista3);
				
				Assert.assertEquals(1400,sugerencia.getCostoFinal(),0);
		}				
}