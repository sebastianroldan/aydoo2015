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
		
		turista = new Turista("Pablo", 1950, 300, 15, TipoDeAtraccion.AVENTURA);
		sugerencia = new Sugerencia(turista);
		atraccionesDisponibles = new LinkedList<Atraccion>();
		
		Atraccion comarca = new Atraccion("La Comarca",new Coordenada(100,500), 450, 120, 15, TipoDeAtraccion.DEGUSTACION);
		Atraccion mordor = new Atraccion("Mordor",new Coordenada(700,-800), 1200, 60, 5, TipoDeAtraccion.AVENTURA);
		Atraccion gondor = new Atraccion("Gondor",new Coordenada(500,-650), 300, 180, 10, TipoDeAtraccion.PAISAJE);		

		atraccionesDisponibles.add(comarca);
		atraccionesDisponibles.add(mordor);
		atraccionesDisponibles.add(gondor);		
		
	}
	
	@Test
	public void obtenerCostoTotalTest(){
		
		sugerencia.generarRecorridoConAtraccionesPorMenorCosto(atraccionesDisponibles);
		
		Assert.assertEquals(300, sugerencia.getCostoTotalSinPromociones(), 0);
	}
	
	@Test
	public void obtenerTiempoTotalTest(){
		
		sugerencia.generarRecorridoConAtraccionesPorMenorCosto(atraccionesDisponibles);
		
		Assert.assertEquals(261.17, sugerencia.getTiempoTotal(turista.getVelocidadDeTraslado()), 0.01);
	}
	
	@Test
	public void obtenerItinerarioPorPresupuestoTest(){
		
		sugerencia.generarRecorridoConAtraccionesPorMenorCosto(atraccionesDisponibles);
		
		Assert.assertEquals("Gondor", sugerencia.getListaDeAtracciones().get(0).getNombre());
		Assert.assertEquals(1, sugerencia.getListaDeAtracciones().size());
	}
	
	@Test
	public void restringirPorCupoTest(){
		turista.setPresupuesto(2500);
		turista.setTiempoDisponible(500);
		turista.setVelocidadDeTraslado(100);
		atraccionesDisponibles.get(1).setCupoMaximo(0);
		
		sugerencia.generarRecorridoConAtraccionesPorMenorCosto(atraccionesDisponibles);
		
		Assert.assertEquals(2, sugerencia.getListaDeAtracciones().size());
		Assert.assertEquals("Gondor",sugerencia.getListaDeAtracciones().get(0).getNombre());
		Assert.assertEquals("La Comarca",sugerencia.getListaDeAtracciones().get(1).getNombre());
	}
	
	@Test
	public void obtenerItinerarioPorTiempoDisponibleTest(){
		
		turista.setTiempoDisponible(380);		
		
		sugerencia.generarRecorridoConAtraccionesPorTiempoDisponible(atraccionesDisponibles);
		
		Assert.assertEquals("Mordor", sugerencia.getListaDeAtracciones().get(0).getNombre());		
		Assert.assertEquals("La Comarca", sugerencia.getListaDeAtracciones().get(1).getNombre());
		Assert.assertEquals(2, sugerencia.getListaDeAtracciones().size());
	}
	
	@Test
	public void obtenerRecorridoConAtraccionesPorPreferenciaTest(){
		turista.setTiempoDisponible(480);
		turista.setPreferencia(TipoDeAtraccion.AVENTURA);	
		
		Atraccion rohan = new Atraccion("Rohan", new Coordenada(450, 120), 400, 60, 10, TipoDeAtraccion.AVENTURA);
		Atraccion moria = new Atraccion("Minas de Moria", new Coordenada(150,200), 500, 45, 5, TipoDeAtraccion.AVENTURA);
		Atraccion isengard = new Atraccion("Isengard", new Coordenada(300,500), 30, 120, 5, TipoDeAtraccion.AVENTURA);
		atraccionesDisponibles.add(rohan);
		atraccionesDisponibles.add(moria);
		atraccionesDisponibles.add(isengard);
		
		sugerencia.generarRecorridoConAtraccionesPorPreferencia(atraccionesDisponibles);
		
		Assert.assertEquals("Mordor", sugerencia.getListaDeAtracciones().get(0).getNombre());		
		Assert.assertEquals("Rohan", sugerencia.getListaDeAtracciones().get(1).getNombre());
		Assert.assertEquals("Isengard", sugerencia.getListaDeAtracciones().get(2).getNombre());
		Assert.assertEquals(3, sugerencia.getListaDeAtracciones().size());
	}
	
	@Test
	public void obtenerRecorridoConAtraccionesConMayorCostoTest(){
		turista.setTiempoDisponible(1000);
		turista.setPresupuesto(2300);
		
		Atraccion moria = new Atraccion("Minas de Moria", new Coordenada(150,200), 1000, 45, 5, TipoDeAtraccion.AVENTURA);
		atraccionesDisponibles.add(moria);
		sugerencia.generarRecorridoConAtraccionesMasCostosas(atraccionesDisponibles);
		
		Assert.assertEquals("Mordor", sugerencia.getListaDeAtracciones().get(0).getNombre());
		Assert.assertEquals("Minas de Moria", sugerencia.getListaDeAtracciones().get(1).getNombre());
		Assert.assertEquals(2, sugerencia.getListaDeAtracciones().size());
	}
	
	
	
}
