package turismo;

import java.util.Comparator;

public class OrdenadorDeAtraccionesPorCostoMenor implements Comparator<Atraccion>{

		@Override
		public int compare(Atraccion atraccion1, Atraccion atraccion2){
			
				Double costo1 = atraccion1.getCostoDeAtraccion();
				Double costo2 = atraccion2.getCostoDeAtraccion();
			
				return costo1.compareTo(costo2);	
		}
	
}
