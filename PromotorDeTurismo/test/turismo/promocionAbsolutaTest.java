package turismo;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class promocionAbsolutaTest {
	
	private Promocion promoAbsoluta;
	private Atraccion rohan;
	private Atraccion isengard;
	private Sugerencia sugerencia;
	private Turista turista = new Turista("Pablo", 1950, 600, 15, TipoDeAtraccion.AVENTURA, new Domicilio(100,500),1);
	
	@Before
	public void ejecutarAntesDeCadaTest(){		
		
			rohan = new Atraccion("Rohan", new Coordenada(450, 120), 400, 60, 10, TipoDeAtraccion.AVENTURA);			
			isengard = new Atraccion("Isengard", new Coordenada(300,500), 250, 120, 5, TipoDeAtraccion.AVENTURA);			
			
			List<Atraccion> atraccionesAbsolutas = new LinkedList<Atraccion>();
			atraccionesAbsolutas.add(rohan);
			atraccionesAbsolutas.add(isengard);			
									
			promoAbsoluta = new PromocionAbsoluta(atraccionesAbsolutas, 400);		
			
			List<Atraccion> atraccionesDisponibles = new LinkedList<Atraccion>();
			
			atraccionesDisponibles.add(rohan);
			atraccionesDisponibles.add(isengard);	
	
			sugerencia = new Sugerencia();
			sugerencia.generarSugerencia(atraccionesDisponibles, turista);	
			
	}
	
	@Test
	public void alAplicarPromoAbsolutaDebenCostar400RohaneIsengardTest(){
						
			Assert.assertEquals(650, sugerencia.getCostoFinal(),0);
			
			promoAbsoluta.aplicarPromocion(sugerencia, turista);
			
			Assert.assertEquals(400,sugerencia.getCostoFinal(),0);
		
	}
	
	@Test
	public void deberiaAplicarLaPromocionPorEstarTodasLasAtraccionesEnLaSugerenciaTest(){
			
			Assert.assertTrue(promoAbsoluta.esAplicableLaPromocion(sugerencia, turista));
	}
	
	@Test
	public void noDeberiaAplicarLaPromocionPorNoEstarTodasLasAtraccionesEnLaSugerenciaTest(){			
			
		Atraccion mordor = new Atraccion("Mordor", new Coordenada(200,100), 50, 2, 25, TipoDeAtraccion.AVENTURA);
		
		List<Atraccion> atraccionesAbsolutas = new LinkedList<Atraccion>();
		atraccionesAbsolutas.add(rohan);
		atraccionesAbsolutas.add(isengard);
		atraccionesAbsolutas.add(mordor);
								
		Promocion promoAbsoluta2 = new PromocionAbsoluta(atraccionesAbsolutas, 400);
					
		Assert.assertFalse(promoAbsoluta2.esAplicableLaPromocion(sugerencia, turista));
	}
}
