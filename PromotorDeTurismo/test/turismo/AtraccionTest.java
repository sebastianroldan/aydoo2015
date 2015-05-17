package turismo;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import turismo.Atraccion;
import turismo.Coordenada;
import turismo.TipoDeAtraccion;

public class AtraccionTest {

		private Atraccion atraccion;
	
		@Before
		public void ejecutaAntesCadaTest() {
	
			 	Coordenada coordenadas = new Coordenada(10000,3000);
			 	double costo = 250.30;
			 	int tiempo = 60;
			 	int cupo = 15;
			 	this.atraccion = new Atraccion("Lago rojo", coordenadas, costo, tiempo, cupo, TipoDeAtraccion.PAISAJE);
	
		}
		
		@Test
	    public void crearAtraccionTest(){
	               
	        	Assert.assertNotNull(this.atraccion);
	
	    }
		
		@Test
		public void obtenerTiempoNecesarioParaRealizarlaTest(){
			
				double tiempo;
				tiempo = this.atraccion.getTiempoNecesario();
				
				Assert.assertEquals(60,tiempo,0);
		}
		
		@Test
		public void obtenerCosteDeAtraccionTest(){
			
				double costo;
				costo = this.atraccion.getCostoDeAtraccion();
			
				Assert.assertEquals(250.30,costo,0);
		}
	
		@Test
		public void obtenerCoordenadasTest(){
				
				Coordenada coordenadasXeY;
				coordenadasXeY = this.atraccion.getCoordenadas();
				
				Assert.assertEquals(10000,coordenadasXeY.getCoordenadaX(),0);
				Assert.assertEquals(3000,coordenadasXeY.getCoordenadaY(),0);
		}
		
		@Test
		public void obtenerCupoMaximoTest(){
			
				int cupo;
				cupo = this.atraccion.getCupoMaximo();
				Assert.assertEquals(15,cupo);
		}
		
		@Test
		public void agregarVisitantesTest(){
			
				int visitantes;
				visitantes = this.atraccion.getCantidadDeVisitantes();
				
				Assert.assertEquals(0, visitantes);
				
				this.atraccion.agregarVisitante();
				
				visitantes = this.atraccion.getCantidadDeVisitantes();
				
				Assert.assertEquals(1, visitantes);
				
		} 
		
		@Test
		public void agregarVisitanteConCupoLlenoTest(){
			
				for (int i=0; i < 15; i++){
						this.atraccion.agregarVisitante();
				}
				
				int cantidadDeVisitantes = this.atraccion.getCantidadDeVisitantes();
				
				Assert.assertEquals(15, cantidadDeVisitantes);
				
				this.atraccion.agregarVisitante();
				
				cantidadDeVisitantes = this.atraccion.getCantidadDeVisitantes();
				
				Assert.assertEquals(15, cantidadDeVisitantes);
		}
		
}
