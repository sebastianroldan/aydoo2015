package turismo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Sugerencia {
	
	private List<Atraccion> recorrido;
	private Turista turista;	
	private double costoFinal;
	
	public Sugerencia(Turista turista){
		recorrido = new LinkedList<Atraccion>();
		this.turista = turista;
	}
	
	private boolean puedeHacer(Atraccion proximaAtraccion) {
		
		return alcanzaPresupuestoPara(proximaAtraccion, turista) 
				&& (alcanzaTiempoPara(proximaAtraccion, turista) && (proximaAtraccion.hayCupo()));
	}


	private boolean alcanzaTiempoPara(Atraccion atraccion, Turista turista) {
		double tiempoDeTraslado = calcularTiempoDeViajeHastaLaProximaAtraccion(turista.getVelocidadDeTraslado(), atraccion.getCoordenadas());
		return tiempoDeTraslado+atraccion.getTiempoNecesario()+this.getTiempoTotal(turista.getVelocidadDeTraslado()) <= turista.getTiempoDisponible();
	}


	private double calcularTiempoDeViajeHastaLaProximaAtraccion(
			int velocidadDeTraslado,  Coordenada proximoPunto) {
		double tiempo = 0; 
		double distancia = 0;
		Coordenada ultimoPuntoRecorrido = new Coordenada(100,500);
		
		if (!this.recorrido.isEmpty()){
		
			ultimoPuntoRecorrido = this.recorrido.get(this.recorrido.size()-1).getCoordenadas();
		}
		
		distancia = proximoPunto.calcularDistanciaDesdeEstaCoordenada(ultimoPuntoRecorrido);
		tiempo = distancia/velocidadDeTraslado;
		return tiempo;
	}
	
	private boolean alcanzaPresupuestoPara(Atraccion atraccion, Turista turista){
		return atraccion.getCostoDeAtraccion()+this.getCostoTotalSinPromociones()<= turista.getPresupuesto();
	}
	
	public void setCostoFinal(double montoAdescontar){
		this.costoFinal = this.getCostoTotalSinPromociones() - montoAdescontar;
	}
	
	public double getCostoFinal(){
		return costoFinal;
	}
	
	public double getCostoTotalSinPromociones(){
		double costoTotal = 0;
		for (Atraccion atraccionActual: recorrido){			
			costoTotal = costoTotal + atraccionActual.getCostoDeAtraccion();
		}
		return costoTotal; 
	}
	
	
	
	public double getTiempoTotal(int velocidadDeTraslado){		
		double tiempoTotal = 0;
		Coordenada puntoActual = new Coordenada(100,500);
		double tiempoDeTraslado = 0;
		double distanciaDeTraslado = 0;
		
		for (Atraccion atraccionProxima: recorrido){			
			distanciaDeTraslado = puntoActual.calcularDistanciaDesdeEstaCoordenada(
					atraccionProxima.getCoordenadas());
			tiempoDeTraslado = distanciaDeTraslado/velocidadDeTraslado;
			tiempoTotal = tiempoTotal + tiempoDeTraslado + atraccionProxima.getTiempoNecesario();
			puntoActual = atraccionProxima.getCoordenadas();			
		}		
		return tiempoTotal;
	}

	public void generarRecorridoConAtraccionesPorMenorCosto(
			List<Atraccion> atraccionesDisponibles){
		Collections.sort(atraccionesDisponibles, new OrdenadorDeAtraccionesPorCostoMenor());
		this.generarRecorrido(atraccionesDisponibles);				
	}
	
	public void generarRecorridoConAtraccionesPorTiempoDisponible(
			 List<Atraccion> atraccionesDisponibles) {
		Collections.sort(atraccionesDisponibles, new OrdenadorDeAtraccionesPorMenorTiempo());
		this.generarRecorrido(atraccionesDisponibles);
	}
	
	public void generarRecorridoConAtraccionesPorPreferencia(
			 List<Atraccion> atraccionesDisponibles) {
		for (Atraccion proximaAtraccion: atraccionesDisponibles){			
			if (puedeHacer(proximaAtraccion) && (proximaAtraccion.getTipoAtraccion()==turista.getPreferencia()) ){				
				proximaAtraccion.agregarVisitante();				
				recorrido.add(proximaAtraccion);								
			}
		}
		
	}
	
	public void generarRecorridoConAtraccionesMasCostosas(
			List<Atraccion> listaDeAtracciones) {
		Collections.sort(listaDeAtracciones, new OrdenadorDeAtraccionesPorCostoMayor());
		this.generarRecorrido(listaDeAtracciones);		
	}
	
	public void generarRecorrido(List<Atraccion> atraccionesDisponibles){
		for (Atraccion proximaAtraccion : atraccionesDisponibles){
			if (puedeHacer(proximaAtraccion)){
				proximaAtraccion.agregarVisitante();
				recorrido.add(proximaAtraccion);		
			}
		}
		this.setCostoFinal(0);
	}
	
	public List<Atraccion> getListaDeAtracciones(){
		return this.recorrido;
	}
	
	public void agregarAtraccionExtra(Atraccion atraccionExtra){
			if (puedeHacer(atraccionExtra)){
				recorrido.add(atraccionExtra);
			}
	}
}
