package turismo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class PromotorDeTurismo {

	private List<Atraccion> listaDeAtracciones;
	private Set<Promocion> promociones;
	
	public PromotorDeTurismo(){
		this.listaDeAtracciones = new LinkedList<Atraccion>();
		this.promociones = new HashSet<Promocion>();
	}
	
	public Sugerencia[] sugerirListaDeItinerarios(Turista turista) {
		Sugerencia[] sugerencias = new Sugerencia[5];
		for (int i=0; i < 5; i++){
			sugerencias[i] = generarSugerencia(turista, i);
			aplicarPromocion(sugerencias[i]);
		}
		return sugerencias;
	}

	private void aplicarPromocion(Sugerencia recorrido) {
		for (Promocion promo: this.promociones){
			if (promo.esAplicableLaPromocion(recorrido)){
				promo.aplicarPromocion(recorrido);
			}
		}
	}

	private Sugerencia generarSugerencia(Turista turista, int i) {
		Sugerencia recorridoSugerido = new Sugerencia(turista);
		switch (i) {
			case 0: recorridoSugerido.generarRecorridoConAtraccionesPorMenorCosto(this.listaDeAtracciones);
			break;
			case 1: recorridoSugerido.generarRecorridoConAtraccionesPorTiempoDisponible(this.listaDeAtracciones);
			break;			
			case 2: recorridoSugerido.generarRecorridoConAtraccionesPorPreferencia(this.listaDeAtracciones);
			break;			
			case 3: recorridoSugerido.generarRecorridoConAtraccionesMasCostosas(this.listaDeAtracciones);
			break;			
			default: recorridoSugerido.generarRecorrido(this.listaDeAtracciones);
			break;			
		}
			
		return recorridoSugerido;
	}

}
