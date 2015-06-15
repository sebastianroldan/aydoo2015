package turismo;

import org.junit.Assert;
import org.junit.Test;

public class DireccionTest {
	
		@Test
		public void calleDeberiaSerFloridaTest(){
			
				Domicilio direccion = new Domicilio(0,2);
				direccion.setDireccion("Florida");
				
				Assert.assertEquals("Florida",direccion.getDireccion());
		}
		
		@Test
		public void numeroDeberiaSer1200Test(){
			
				Domicilio direccion = new Domicilio(0,2);
				direccion.setNumero(1200);
				
				Assert.assertEquals(1200,direccion.getNumero());
		}
		
		@Test
		public void coordenadaDeberiaSer0y2Test(){
			
				Domicilio direccion = new Domicilio(0,2);				
				
				Assert.assertEquals(0,direccion.getCoordenada().getCoordenadaX(),0);
				Assert.assertEquals(2,direccion.getCoordenada().getCoordenadaY(),0);
		}
		
}
