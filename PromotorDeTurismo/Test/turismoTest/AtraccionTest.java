package turismoTest;
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
	public void obtenerTiempoNecesarioParaRealizarla(){
		
		int tiempo;
		tiempo = this.atraccion.getTiempoNecesario();
		Assert.assertEquals(60,tiempo);
	}
	
	@Test
	public void obtenerCosteDeAtraccion(){
		
		double costo;
		costo = this.atraccion.getCostoDeAtraccion();
		Assert.assertEquals(250.30,costo,0);
	}

	@Test
	public void obtenerCoordenadas(){
		
		Coordenada coordenadasXeY;
		coordenadasXeY = this.atraccion.getCoordenadas();
		Assert.assertEquals(10000,coordenadasXeY.getCoordenadaX());
		Assert.assertEquals(3000,coordenadasXeY.getCoordenadaY());
	}
	
	@Test
	public void obtenerCupoMaximo(){
		
		int cupo;
		cupo = this.atraccion.getCupoMaximo();
		Assert.assertEquals(15,cupo);
	}
}
