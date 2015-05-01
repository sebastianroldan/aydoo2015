package ejemploDeFactoryMethod;

import org.junit.Assert;
import org.junit.Test;

public class FactoryMethodTest {
	
	@Test
	public void crearFordFiestaTest(){
		FabricaFord fabricaFord = new FabricaFordFiesta();
		Auto auto = fabricaFord.factoryMethod();
		
		Assert.assertEquals("Fiesta" , auto.getModelo());
		Assert.assertEquals("Ford" , auto.getMarca());
		Assert.assertEquals("azul", auto.getColor());		
		
	}
	
	@Test
	public void crearFordFocusTest(){
		FabricaFord fabricaFord = new FabricaFordFocus();
		Auto auto = fabricaFord.factoryMethod();
		
		Assert.assertEquals("Focus" , auto.getModelo());
		Assert.assertEquals("Ford" , auto.getMarca());
		Assert.assertEquals("gris", auto.getColor());
		
	}
	
	@Test
	public void producirAutoTest(){
		FabricaFord fabricaFord = new FabricaFordFocus();
		fabricaFord.producirAuto();
		
		Assert.assertEquals(1, fabricaFord.getAutos().size());
		Assert.assertEquals("Focus",fabricaFord.getAutos().get(0).modelo);
	}
	
}
