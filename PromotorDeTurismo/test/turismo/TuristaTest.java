package turismo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import turismo.TipoDeAtraccion;
import turismo.Turista;

public class TuristaTest {

		private Turista seba;
		
		@Before
		public void ejecutaAntesCadaTest(){
				
				double presupuesto = 1200;
				int tiempoDisponible = 180;
				int velocidadDeTraslado = 80;
				TipoDeAtraccion preferencia = TipoDeAtraccion.AVENTURA;
				
				this.seba = new Turista("Sebastian", presupuesto, tiempoDisponible, velocidadDeTraslado, preferencia, new Domicilio(100,500),1);
		}
		
		@Test
		public void crearTuristaTest(){
			
				Assert.assertNotNull(this.seba);
			
		}
		
		@Test
		public void obtenerPresupuestoDelTuristaTest(){
			
				double presupuesto = this.seba.getPresupuesto();
			
				Assert.assertEquals(1200, presupuesto,0);
			
		}
		
		@Test
		public void obtenerTiempoDisponibleTest(){
			
				int tiempo = this.seba.getTiempoDisponible();
			
				Assert.assertEquals(180, tiempo);
		}
		
		@Test
		public void obtenerPreferenciaTest(){
				
				Assert.assertTrue(TipoDeAtraccion.AVENTURA == this.seba.getPreferencia());		
		}
		
		@Test
		public void obtenerVelocidadDeTrasladoTest(){
				
				int velocidad = this.seba.getVelocidadDeTraslado();
				
				Assert.assertEquals(80, velocidad);
		}
		
		@Test
		public void cantidadDeIntegrantesDeberiaSer5(){
			this.seba.setCantidadDeIntegrantesGrupoFamiliar(5);
			int cantidad = this.seba.getCantidadDeIntegrantesGrupoFamiliar();			
			Assert.assertEquals(5, cantidad);
		}
		
		@Test
		public void getNombreDeberiaDevolverSebastian(){
			
			String nombre = this.seba.getNombre();			
			Assert.assertEquals("Sebastian", nombre);
		}
		
		@Test
		public void alcanzaPresupuestoDeberiaDevolverTrue(){
			
			Sugerencia sugerencia = new Sugerencia();
			Atraccion mordor = new Atraccion("Mordor",new Coordenada(100,500), 1000, 60, 5, TipoDeAtraccion.AVENTURA);
			
			Assert.assertTrue(seba.alcanzaPresupuestoPara(mordor,sugerencia));
			
		}
		
		@Test
		public void alcanzaPresupuestoDeberiaDevolverFalse(){
			
			Sugerencia sugerencia = new Sugerencia();
			Atraccion mordor = new Atraccion("Mordor",new Coordenada(100,500), 1500, 60, 5, TipoDeAtraccion.AVENTURA);
			
			Assert.assertFalse(seba.alcanzaPresupuestoPara(mordor,sugerencia));
			
		}
		
		@Test
		public void puedeHacerDeberiaDevolverTruePorqueAlcanzaElPresupuestoElTiempoYHayCupo(){
			Sugerencia sugerencia = new Sugerencia();
			Atraccion mordor = new Atraccion("Mordor",new Coordenada(100,500), 1000, 60, 5, TipoDeAtraccion.AVENTURA);
			
			Assert.assertTrue(seba.puedeHacer(mordor,sugerencia));
		}
		
		@Test
		public void puedeHacerDeberiaDevolverFalsePorqueNoAlcanzaElPresupuesto(){
			Sugerencia sugerencia = new Sugerencia();
			Atraccion mordor = new Atraccion("Mordor",new Coordenada(100,500), 1500, 60, 5, TipoDeAtraccion.AVENTURA);
			
			Assert.assertFalse(seba.puedeHacer(mordor,sugerencia));
		}
		
		@Test
		public void puedeHacerDeberiaDevolverFalsePorqueNoAlcanzaElTiempo(){
			Sugerencia sugerencia = new Sugerencia();
			Atraccion mordor = new Atraccion("Mordor",new Coordenada(100,500), 1000, 200, 5, TipoDeAtraccion.AVENTURA);
			
			Assert.assertFalse(seba.puedeHacer(mordor,sugerencia));
		}
		
		@Test
		public void puedeHacerDeberiaDevolverFalsePorqueNoHayCupo(){
			Sugerencia sugerencia = new Sugerencia();
			Atraccion mordor = new Atraccion("Mordor",new Coordenada(100,500), 1000, 50, 0, TipoDeAtraccion.AVENTURA);
			
			Assert.assertFalse(seba.puedeHacer(mordor,sugerencia));
		}
		
}