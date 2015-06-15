package turismo;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PromocionPaqueteFamiliarTest {
		
	
		@Test
		public void debeSerAplicableSiElGrupoFamiliarEs4oMasTest(){
			
				Turista turista = new Turista("Juan", 5000, 460, 80, TipoDeAtraccion.AVENTURA, new Domicilio(100,500),4);
				Sugerencia sugerencia = new Sugerencia();			
				Promocion promoFamiliar = new PromocionPaqueteFamiliar();
				
				Assert.assertTrue(promoFamiliar.esAplicableLaPromocion(sugerencia, turista));
				
		}
		
		@Test
		public void noDebeSerAplicableSiElGrupoFamiliarEsMenorDe4Test(){
			
				Turista turista = new Turista("Juan", 5000, 460, 80, TipoDeAtraccion.AVENTURA, new Domicilio(100,500),3);
				Sugerencia sugerencia = new Sugerencia();			
				Promocion promoFamiliar = new PromocionPaqueteFamiliar();
				
				Assert.assertFalse(promoFamiliar.esAplicableLaPromocion(sugerencia, turista));
			
		}
		
		@Test
		public void deberiaAplicarUn10PorcientoDeDescuentoTest(){
				
			Turista turista = new Turista("Juan", 5000, 460, 80, TipoDeAtraccion.AVENTURA, new Domicilio(100,500),4);
			Sugerencia sugerencia = new Sugerencia();			
			Promocion promoFamiliar = new PromocionPaqueteFamiliar();
			Atraccion mordor = new Atraccion("Mordor",new Coordenada(100,500),500,20,20,TipoDeAtraccion.AVENTURA);
			Atraccion gondor = new Atraccion("Gondor",new Coordenada(100,450),500,20,20,TipoDeAtraccion.AVENTURA);
			List<Atraccion> atracciones = new LinkedList<Atraccion>();
			
			atracciones.add(mordor);
			atracciones.add(gondor);
			
			sugerencia.generarSugerencia(atracciones, turista);
			
			Assert.assertEquals(4000,sugerencia.getCostoFinal(),0);
			Assert.assertTrue(promoFamiliar.esAplicableLaPromocion(sugerencia, turista));
			
			promoFamiliar.aplicarPromocion(sugerencia, turista);
			
			Assert.assertEquals(3600,sugerencia.getCostoFinal(),0);
		}
		
		@Test
		public void deberiaAplicarUn10yAdemas30PorcientoDeUnaPersonaDeDescuentoTest(){
				
			Turista turista = new Turista("Juan", 5000, 460, 80, TipoDeAtraccion.AVENTURA, new Domicilio(100,500),5);
			Sugerencia sugerencia = new Sugerencia();			
			Promocion promoFamiliar = new PromocionPaqueteFamiliar();
			Atraccion mordor = new Atraccion("Mordor",new Coordenada(100,500),500,20,20,TipoDeAtraccion.AVENTURA);
			Atraccion gondor = new Atraccion("Gondor",new Coordenada(100,450),500,20,20,TipoDeAtraccion.AVENTURA);
			List<Atraccion> atracciones = new LinkedList<Atraccion>();
			
			atracciones.add(mordor);
			atracciones.add(gondor);
			
			sugerencia.generarSugerencia(atracciones, turista);
			
			Assert.assertEquals(5000,sugerencia.getCostoFinal(),0);
			Assert.assertTrue(promoFamiliar.esAplicableLaPromocion(sugerencia, turista));			
			
			promoFamiliar.aplicarPromocion(sugerencia, turista);			
			
			Assert.assertEquals(4300,sugerencia.getCostoFinal(),0);
		}
		
		@Test
		public void deberiaAplicarUn10yAdemas30PorcientoA2PersonasDeDescuentoTest(){
				
			Turista turista = new Turista("Juan", 6000, 460, 80, TipoDeAtraccion.AVENTURA, new Domicilio(100,500),6);
			Sugerencia sugerencia = new Sugerencia();			
			Promocion promoFamiliar = new PromocionPaqueteFamiliar();
			Atraccion mordor = new Atraccion("Mordor",new Coordenada(100,500),500,20,20,TipoDeAtraccion.AVENTURA);
			Atraccion gondor = new Atraccion("Gondor",new Coordenada(100,450),500,20,20,TipoDeAtraccion.AVENTURA);
			List<Atraccion> atracciones = new LinkedList<Atraccion>();
			
			atracciones.add(mordor);
			atracciones.add(gondor);
			
			sugerencia.generarSugerencia(atracciones, turista);
			
			Assert.assertEquals(6000,sugerencia.getCostoFinal(),0);
			Assert.assertTrue(promoFamiliar.esAplicableLaPromocion(sugerencia, turista));			
			
			promoFamiliar.aplicarPromocion(sugerencia, turista);			
			
			Assert.assertEquals(5000,sugerencia.getCostoFinal(),0);
		}
}

