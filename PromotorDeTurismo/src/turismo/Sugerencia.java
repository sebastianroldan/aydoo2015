package turismo;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Sugerencia {
	
	private List<Atraccion> recorrido;
	
	public Sugerencia(){
		recorrido = new LinkedList<Atraccion>();
	}
	
	
	public double getCostoTotal(){
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

	public void generarRecorridoConAtraccionesPorPresupuesto(
			Turista turista, List<Atraccion> atraccionesDisponibles){
		atraccionesDisponibles = ordenarAtraccionesPorMenorPrecio(atraccionesDisponibles);
		this.generarRecorrido(turista, atraccionesDisponibles);				
	}
	
	public void generarRecorridoConAtraccionesPorTiempoDisponible(
			Turista turista, List<Atraccion> atraccionesDisponibles) {
		atraccionesDisponibles = ordenarAtraccionesPorMenorTiempo(atraccionesDisponibles);
		this.generarRecorrido(turista, atraccionesDisponibles);
	}
	
	
	
	private void generarRecorrido(
			Turista turista, List<Atraccion> atraccionesDisponibles){
		Iterator<Atraccion> iteradorDeAtracciones = atraccionesDisponibles.iterator();
		while (iteradorDeAtracciones.hasNext()){
			Atraccion proximaAtraccion = iteradorDeAtracciones.next();
			if (alcanzaPresupuestoPara(proximaAtraccion, turista) 
					&& (alcanzaTiempoPara(proximaAtraccion, turista) && (proximaAtraccion.hayCupo()))){
				proximaAtraccion.agregarVisitante();
				recorrido.add(proximaAtraccion);
			}
		}
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


	public List<Atraccion> getListaDeAtracciones(){
		return this.recorrido;
	}
	
	private boolean alcanzaPresupuestoPara(Atraccion atraccion, Turista turista){
		return atraccion.getCostoDeAtraccion()+this.getCostoTotal()<= turista.getPresupuesto();
	}
	
	private  List<Atraccion> ordenarAtraccionesPorMenorPrecio(List<Atraccion> atracciones){		
		OrdenadorDeAtraccionesPorCostoMenor ordenadorPorCosto = new OrdenadorDeAtraccionesPorCostoMenor();
		Collections.sort(atracciones,ordenadorPorCosto );		
		return atracciones;
	}

	private List<Atraccion> ordenarAtraccionesPorMenorTiempo(
			List<Atraccion> atraccionesDisponibles) {
		OrdenadorDeAtraccionesPorMenorTiempo ordenadorPorTiempo = new OrdenadorDeAtraccionesPorMenorTiempo();
		Collections.sort(atraccionesDisponibles,ordenadorPorTiempo);		
		return atraccionesDisponibles;
	}
}
