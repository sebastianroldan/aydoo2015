package turismoTest;


import org.junit.Assert;
import org.junit.Test;

import turismo.Coordenada;

public class CoordenadaTest {
	
		@Test
		public void calcularDistanciasEnElMismoPuntoTest(){
			
				Coordenada punto1 = new Coordenada(100, 200);
				Coordenada punto2 = new Coordenada(100, 200);
				
				double distancia = punto1.calcularDistanciaDesdeEstaCoordenada(punto2);
				
				Assert.assertEquals(0, distancia, 0);
		}
		
		@Test
		public void calcularDistanciaEntreDosPuntosSobreLaMismaYTest(){
			
				Coordenada punto1 = new Coordenada(100, 200);
				Coordenada punto2 = new Coordenada(200, 200);
				
				double distancia = Coordenada.calcularDistanciesEntre(punto1, punto2);
				
				Assert.assertEquals(100, distancia, 0);
		}
		
		@Test
		public void calcularDistanciasDeDosPuntosConDiferenteXeYTest(){
			
				Coordenada punto1 = new Coordenada(000, 000);
				Coordenada punto2 = new Coordenada(300, 400);
				
				double distancia = punto1.calcularDistanciaDesdeEstaCoordenada(punto2);
				
				Assert.assertEquals(500, distancia, 0);
		}
		
		@Test
		public void calcularDistanciaEntreDosPuntosSobreLaMismaXTest(){
				Coordenada punto1 = new Coordenada(100, 200);
				Coordenada punto2 = new Coordenada(100, 350.02);
				
				double distancia = Coordenada.calcularDistanciesEntre(punto1, punto2);
				
				Assert.assertEquals(150.02, distancia, 0.001);
		}
		
		@Test
		public void calcularDistanciaEntreDosPuntosSobreLaMismaXConSignoDistintoTest(){
				Coordenada punto1 = new Coordenada(100, 200);
				Coordenada punto2 = new Coordenada(100, -350.02);
				
				double distancia = Coordenada.calcularDistanciesEntre(punto1, punto2);
				
				Assert.assertEquals(550.020, distancia, 0.001);
		}
		
		@Test
		public void calcularDistanciaEntreDosPuntosConSignoNegativoTest(){
				Coordenada punto1 = new Coordenada(-100, -200);
				Coordenada punto2 = new Coordenada(-200, -300);
			
				double distancia = Coordenada.calcularDistanciesEntre(punto1, punto2);
			
				Assert.assertEquals(141.421, distancia, 0.001);
		}
	
}
