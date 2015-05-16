package turismo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class PromotorDeTurismo {

	private List<Atraccion> atracciones;
	private Set<Promocion> promociones;
	private List<Turista> turistas;
	
	public PromotorDeTurismo(){
		this.atracciones = new LinkedList<Atraccion>();
		this.promociones = new HashSet<Promocion>();
		this.turistas = new LinkedList<Turista>();
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
			case 0: recorridoSugerido.generarRecorridoConAtraccionesPorMenorCosto(this.atracciones);
			break;
			case 1: recorridoSugerido.generarRecorridoConAtraccionesPorTiempoDisponible(this.atracciones);
			break;			
			case 2: recorridoSugerido.generarRecorridoConAtraccionesPorPreferencia(this.atracciones);
			break;			
			case 3: recorridoSugerido.generarRecorridoConAtraccionesMasCostosas(this.atracciones);
			break;			
			default: recorridoSugerido.generarRecorrido(this.atracciones);
			break;			
		}
			
		return recorridoSugerido;
	}	
	
	public void agregarAtraccion(Atraccion atraccion){
		this.atracciones.add(atraccion);
	}
	
	public void agregarTurista(Turista turista){
		this.turistas.add(turista);
	}
		
	public void agregarPromocion(Promocion promocion){
		this.promociones.add(promocion);
	}
	
	public Sugerencia[] sugerirListaDeItinerarios(Turista turista) {
		Sugerencia[] sugerencias = new Sugerencia[5];
		for (int i=0; i < 5; i++){
			sugerencias[i] = generarSugerencia(turista, i);
			aplicarPromocion(sugerencias[i]);
		}
		return sugerencias;
	}

}
