package turismo;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import turismo.Atraccion;
import turismo.Coordenada;
import turismo.Promocion;
import turismo.PromocionPorcentual;
import turismo.Sugerencia;
import turismo.TipoDeAtraccion;
import turismo.Turista;

public class PromocionPorcentualTest {

		private Promocion promoPorcentual;
		private Atraccion mordor;
		private Atraccion gondor;
		private Atraccion moria;		
				
		@Before
		public void ejecutarAntesDeCadaTest(){		
		
				mordor = new Atraccion("Mordor",new Coordenada(700,-800), 1200, 60, 5, TipoDeAtraccion.AVENTURA);
				gondor = new Atraccion("Gondor",new Coordenada(500,-650), 300, 180, 10, TipoDeAtraccion.PAISAJE);
				moria = new Atraccion("Minas de Moria", new Coordenada(150,200), 500, 45, 5, TipoDeAtraccion.AVENTURA);
				
				List<Atraccion> atraccionesPorcentual = new LinkedList<Atraccion>();
				atraccionesPorcentual.add(moria);
				atraccionesPorcentual.add(mordor);
				atraccionesPorcentual.add(gondor);		
								
				promoPorcentual = new PromocionPorcentual(atraccionesPorcentual, 30);		
				
		}
		
		
		@Test
		public void debeAplicarSiSugerenciaContieneTodasLasAtraccionesDePromocionTest(){
				
				Turista turista = new Turista("Juan", 2300, 600, 15, TipoDeAtraccion.AVENTURA, new Domicilio(100,500),1);			
				Sugerencia sugerencia = new Sugerencia();
				
				List<Atraccion> atracciones = new LinkedList<Atraccion>();
				atracciones.add(moria);
				atracciones.add(mordor);
				atracciones.add(gondor);
				
				sugerencia.generarSugerencia(atracciones, turista);
				
				Assert.assertTrue(promoPorcentual.esAplicableLaPromocion(sugerencia, turista));
		}
		
		@Test
		public void noDebeAplicarSiSugerenciaNoContieneTodasLasAtraccionesDePromocionTest(){
				
				Turista turista = new Turista("Juan", 2300, 600, 15, TipoDeAtraccion.AVENTURA, new Domicilio(100,500),1);			
				Sugerencia sugerencia = new Sugerencia();
				
				List<Atraccion> atracciones = new LinkedList<Atraccion>();
				atracciones.add(moria);
				atracciones.add(mordor);				
				
				sugerencia.generarSugerencia(atracciones, turista);
				
				Assert.assertFalse(promoPorcentual.esAplicableLaPromocion(sugerencia, turista));
		}
		
		
		@Test
		public void alAplicarPromoPorcentualDebeDescontar600Test(){
				Turista turista = new Turista("Juan", 2300, 600, 15, TipoDeAtraccion.AVENTURA, new Domicilio(100,500),1);
				List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
			
				atraccionesDisponibles.add(moria);
				atraccionesDisponibles.add(mordor);
				atraccionesDisponibles.add(gondor);		
		
				Sugerencia sugerencia = new Sugerencia();				
				
				sugerencia.generarSugerencia(atraccionesDisponibles, turista);
				
				Assert.assertEquals(2000,sugerencia.getCostoFinal(),0);
				
				promoPorcentual.aplicarPromocion(sugerencia, turista);
				
				Assert.assertEquals(1400,sugerencia.getCostoFinal(),0);
		}				
}