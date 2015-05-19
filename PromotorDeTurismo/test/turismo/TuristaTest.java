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
				
				this.seba = new Turista("Sebastian", presupuesto, tiempoDisponible, velocidadDeTraslado, preferencia, new Coordenada(100,500),1);
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
}