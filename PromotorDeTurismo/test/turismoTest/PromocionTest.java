package turismoTest;

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
				
				promoAxB = new PromocionAxB(atraccionesAxB, comarca);
				promoPorcentual = new PromocionPorcentual(atraccionesPorcentual, 30);
				promoAbsoluta = new PromocionAbsoluta(atraccionesAbsolutas, 410);
			
				
		}
		
		@Test
		public void crearPromocionAbsolutaTest(){		
				
				List<Atraccion> lista = promoAbsoluta.getAtracciones();
				
				Assert.assertEquals(2,lista.size());
				Assert.assertEquals("Rohan", lista.get(0).getNombre());
				Assert.assertEquals("Isengard", lista.get(1).getNombre());		
		}
		
		@Test
		public void aplicarPromoAbsolutaTest(){
				Turista turista = new Turista("Pablo", 1950, 600, 15, TipoDeAtraccion.AVENTURA);
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
			
				atraccionesDisponibles.add(rohan);
				atraccionesDisponibles.add(isengard);
				atraccionesDisponibles.add(gondor);		
		
				Sugerencia recorrido = new Sugerencia(turista);
				recorrido.generarRecorrido(atraccionesDisponibles);		
				promoAbsoluta.aplicarPromocion(recorrido);
				
				Assert.assertEquals(710,recorrido.getCostoFinal(),0);
			
		}
		
		@Test
		public void aplicarPromoAxBSinQueEsteEnElRcorridoTest(){
				Turista turista = new Turista("Pablo", 1950, 600, 15, TipoDeAtraccion.AVENTURA);
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
			
				atraccionesDisponibles.add(rohan);
				atraccionesDisponibles.add(isengard);		
		
				Sugerencia recorrido = new Sugerencia(turista);
				recorrido.generarRecorrido(atraccionesDisponibles);	
				promoAxB.aplicarPromocion(recorrido);
				
				Assert.assertEquals(430,recorrido.getCostoFinal(),0);
				Assert.assertEquals(3, recorrido.getListaDeAtracciones().size());
				Assert.assertEquals("La Comarca", recorrido.getListaDeAtracciones().get(2).getNombre());
			
		}
		
		@Test
		public void aplicarPromoAxBCuandoLaAtraccionYaEstaEnElRecorridoTest(){
				Turista turista = new Turista("Pablo", 1950, 600, 15, TipoDeAtraccion.AVENTURA);
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
			
				atraccionesDisponibles.add(rohan);
				atraccionesDisponibles.add(isengard);
				atraccionesDisponibles.add(comarca);
		
				Sugerencia recorrido = new Sugerencia(turista);
				recorrido.generarRecorrido(atraccionesDisponibles);	
				promoAxB.aplicarPromocion(recorrido);
				
				Assert.assertEquals(430,recorrido.getCostoFinal(),0);
				Assert.assertEquals(3, recorrido.getListaDeAtracciones().size());
				Assert.assertEquals("La Comarca", recorrido.getListaDeAtracciones().get(2).getNombre());
			
		}
		
		@Test
		public void aplicarPromoAxBCuandoNoPuedeRealizarlaPorTiempoTest(){
				Turista turista = new Turista("Pablo", 1950, 270, 15, TipoDeAtraccion.AVENTURA);
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
			
				atraccionesDisponibles.add(rohan);
				atraccionesDisponibles.add(isengard);		
		
				Sugerencia recorrido = new Sugerencia(turista);
				recorrido.generarRecorrido(atraccionesDisponibles);	
				promoAxB.aplicarPromocion(recorrido);
				
				Assert.assertEquals(430,recorrido.getCostoFinal(),0);
				Assert.assertEquals(2, recorrido.getListaDeAtracciones().size());
				Assert.assertEquals("Rohan", recorrido.getListaDeAtracciones().get(0).getNombre());
				Assert.assertEquals("Isengard", recorrido.getListaDeAtracciones().get(1).getNombre());
			
		}
		
		@Test
		public void aplicarPromoPorcentualTest(){
				Turista turista = new Turista("Pablo", 2300, 600, 15, TipoDeAtraccion.AVENTURA);
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
			
				atraccionesDisponibles.add(moria);
				atraccionesDisponibles.add(mordor);
				atraccionesDisponibles.add(gondor);		
		
				Sugerencia recorrido = new Sugerencia(turista);
				
				recorrido.generarRecorrido(atraccionesDisponibles);
						
				promoPorcentual.aplicarPromocion(recorrido);	
				
				Assert.assertEquals(1400,recorrido.getCostoFinal(),0);
		}
	

}
