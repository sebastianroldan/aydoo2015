package turismoTest;

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
	
	@Before
	public void ejecutarAntesDeTest(){
		
		sugerencia = new Sugerencia();
		turista = new Turista("Pablo", 1950, 300, 15, TipoDeAtraccion.AVENTURA);
		atraccionesDisponibles = new LinkedList<Atraccion>();
		Coordenada coordenadaComarca = new Coordenada(100,500);
		Coordenada coordenadaMordor = new Coordenada(700,-800);
		Coordenada coordenadaGondor = new Coordenada(500,-650);
		Atraccion comarca = new Atraccion("La Comarca",coordenadaComarca, 450, 120, 15, TipoDeAtraccion.DEGUSTACION);
		Atraccion mordor = new Atraccion("Mordor",coordenadaMordor, 1200, 60, 5, TipoDeAtraccion.AVENTURA);
		Atraccion gondor = new Atraccion("Gondor",coordenadaGondor, 300, 180, 10, TipoDeAtraccion.PAISAJE);
		atraccionesDisponibles.add(comarca);
		atraccionesDisponibles.add(mordor);
		atraccionesDisponibles.add(gondor);
	}
	
	@Test
	public void obtenerCostoTotalTest(){
		
		sugerencia.generarRecorridoConAtraccionesPorPresupuesto(turista, atraccionesDisponibles);
		
		Assert.assertEquals(300, sugerencia.getCostoTotal(), 0);
	}
	
	@Test
	public void obtenerTiempoTotalTest(){
		
		sugerencia.generarRecorridoConAtraccionesPorPresupuesto(turista, atraccionesDisponibles);
		
		Assert.assertEquals(261.17, sugerencia.getTiempoTotal(turista.getVelocidadDeTraslado()), 0.01);
	}
	
	@Test
	public void obtenerItinerarioPorPresupuestoTest(){
		
		sugerencia.generarRecorridoConAtraccionesPorPresupuesto(turista, atraccionesDisponibles);
		
		Assert.assertEquals("Gondor", sugerencia.getListaDeAtracciones().get(0).getNombre());
		Assert.assertEquals(1, sugerencia.getListaDeAtracciones().size());
	}
	
	@Test
	public void restringirPorCupoTest(){
		turista.setPresupuesto(2500);
		turista.setTiempoDisponible(500);
		turista.setVelocidadDeTraslado(100);
		atraccionesDisponibles.get(1).setCupoMaximo(0);
		
		sugerencia.generarRecorridoConAtraccionesPorPresupuesto(turista, atraccionesDisponibles);
		
		Assert.assertEquals(2, sugerencia.getListaDeAtracciones().size());
		Assert.assertEquals("Gondor",sugerencia.getListaDeAtracciones().get(0).getNombre());
		Assert.assertEquals("La Comarca",sugerencia.getListaDeAtracciones().get(1).getNombre());
	}
	
	@Test
	public void obtenerItinerarioPorTiempoDisponibleTest(){
		
		turista.setTiempoDisponible(380);		
		
		sugerencia.generarRecorridoConAtraccionesPorTiempoDisponible(turista, atraccionesDisponibles);
		
		Assert.assertEquals("Mordor", sugerencia.getListaDeAtracciones().get(0).getNombre());		
		Assert.assertEquals("La Comarca", sugerencia.getListaDeAtracciones().get(1).getNombre());
		Assert.assertEquals(2, sugerencia.getListaDeAtracciones().size());
	}
}
