package turismo;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import turismo.Atraccion;
import turismo.Coordenada;
import turismo.Sugerencia;
import turismo.TipoDeAtraccion;
import turismo.Turista;

public class SugerenciaTest {
	
		private Sugerencia sugerencia;
		private Turista turista;
		private List<Atraccion> atraccionesDisponibles;
		private Atraccion comarca = new Atraccion("La Comarca",new Coordenada(100,500), 450, 120, 15, TipoDeAtraccion.DEGUSTACION);
		private Atraccion mordor = new Atraccion("Mordor",new Coordenada(700,-800), 1200, 60, 5, TipoDeAtraccion.AVENTURA);
		private Atraccion gondor = new Atraccion("Gondor",new Coordenada(500,-650), 300, 180, 10, TipoDeAtraccion.PAISAJE);
		
		@Before
		public void ejecutarAntesDeTest(){
			
				turista = new Turista("Pablo", 1950, 300, 15, TipoDeAtraccion.AVENTURA);
				sugerencia = new Sugerencia();
				atraccionesDisponibles = new LinkedList<Atraccion>();						
		
				atraccionesDisponibles.add(comarca);
				atraccionesDisponibles.add(mordor);
				atraccionesDisponibles.add(gondor);		
			
		}
		
		@Test
		public void getCostoFinalTest(){
				
				sugerencia.generarSugerenciaConAtraccionesPorMenorCosto(atraccionesDisponibles, turista);
								
				Assert.assertEquals(300, sugerencia.getCostoFinal(), 0);
		}
		
		@Test
		public void getTiempoTotalTest(){
				
				sugerencia.generarSugerenciaConAtraccionesPorMenorCosto(atraccionesDisponibles, turista);
				
				Assert.assertEquals(261.17, sugerencia.getTiempoTotal(turista.getVelocidadDeTraslado()), 0.01);
		}
		
		@Test
		public void generarSugerenciaConAtraccionesMenosCostosasTest(){
				
				turista.setPresupuesto(350);
				turista.setTiempoDisponible(10000);
				sugerencia.generarSugerenciaConAtraccionesPorMenorCosto(atraccionesDisponibles, turista);
				
				Assert.assertEquals(gondor, sugerencia.getListaDeAtracciones().get(0));
				Assert.assertEquals(1, sugerencia.getListaDeAtracciones().size());
		}
		
		@Test
		public void alSugerirNoDebeAgregarAtraccionesQueNoTenganCupoTest(){
				turista.setPresupuesto(3000);
				turista.setTiempoDisponible(1000);
				turista.setVelocidadDeTraslado(100);
				atraccionesDisponibles.get(1).setCupoMaximo(1);
				atraccionesDisponibles.get(1).agregarVisitante();
				
				sugerencia.generarSugerenciaConAtraccionesPorMenorCosto(atraccionesDisponibles, turista);
				
				Assert.assertEquals(2, sugerencia.getListaDeAtracciones().size());
				Assert.assertEquals(gondor,sugerencia.getListaDeAtracciones().get(0));
				Assert.assertEquals(comarca,sugerencia.getListaDeAtracciones().get(1));
		}
		
		@Test
		public void generarMayorCantidadDeSugerenciasSegunTiempoDisponibleTest(){
				
				turista.setTiempoDisponible(380);		
				
				sugerencia.generarSugerenciaConAtraccionesPorTiempoDisponible(atraccionesDisponibles, turista);
				
				Assert.assertEquals(mordor, sugerencia.getListaDeAtracciones().get(0));		
				Assert.assertEquals(comarca, sugerencia.getListaDeAtracciones().get(1));
				Assert.assertEquals(2, sugerencia.getListaDeAtracciones().size());
		}
		
		@Test
		public void generarSugerenciaConAtraccionesPorPreferenciaTest(){
				turista.setTiempoDisponible(480);
				turista.setPreferencia(TipoDeAtraccion.AVENTURA);	
				
				Atraccion rohan = new Atraccion("Rohan", new Coordenada(450, 120), 400, 60, 10, TipoDeAtraccion.AVENTURA);
				Atraccion moria = new Atraccion("Minas de Moria", new Coordenada(150,200), 500, 45, 5, TipoDeAtraccion.AVENTURA);
				Atraccion isengard = new Atraccion("Isengard", new Coordenada(300,500), 30, 120, 5, TipoDeAtraccion.DEGUSTACION);
				atraccionesDisponibles.add(rohan);
				atraccionesDisponibles.add(moria);
				atraccionesDisponibles.add(isengard);
				
				sugerencia.generarSugerenciaConAtraccionesPorPreferencia(atraccionesDisponibles, turista);
				
				Assert.assertEquals(mordor, sugerencia.getListaDeAtracciones().get(0));		
				Assert.assertEquals(rohan, sugerencia.getListaDeAtracciones().get(1));
				
				Assert.assertEquals(2, sugerencia.getListaDeAtracciones().size());
		}
		
		@Test
		public void generarSugerenciaConAtraccionesConMayorCostoTest(){
				turista.setTiempoDisponible(1000);
				turista.setPresupuesto(2300);
				
				Atraccion moria = new Atraccion("Minas de Moria", new Coordenada(150,200), 1000, 45, 5, TipoDeAtraccion.AVENTURA);
				atraccionesDisponibles.add(moria);
				sugerencia.generarSugerenciaConAtraccionesMasCostosas(atraccionesDisponibles, turista);
				
				Assert.assertEquals(mordor, sugerencia.getListaDeAtracciones().get(0));
				Assert.assertEquals(moria, sugerencia.getListaDeAtracciones().get(1));
				Assert.assertEquals(2, sugerencia.getListaDeAtracciones().size());
		}	
}
