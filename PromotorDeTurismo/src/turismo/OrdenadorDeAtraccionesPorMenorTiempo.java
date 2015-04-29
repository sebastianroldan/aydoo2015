package turismo;

import java.util.Comparator;

public class OrdenadorDeAtraccionesPorMenorTiempo implements Comparator<Atraccion>{

			@Override
			public int compare(Atraccion atraccion1, Atraccion atraccion2){
				
				Double tiempo1 = atraccion1.getTiempoNecesario();
				Double tiempo2 = atraccion2.getTiempoNecesario();
				
				return tiempo1.compareTo(tiempo2);
			}
		
	}
