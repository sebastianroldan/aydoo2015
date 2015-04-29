package turismo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Sugerencia {
	
	private List<Atraccion> recorrido;
	
	public Sugerencia(){
		recorrido = new LinkedList<Atraccion>();
	}
	
	
	public double getCostoTotal(){
		Iterator<Atraccion> iteradorDeAtracciones = recorrido.iterator();
		double costoTotal = 0;
		while (iteradorDeAtracciones.hasNext()){
			Atraccion atraccionActual = iteradorDeAtracciones.next();
			costoTotal = costoTotal + atraccionActual.getCostoDeAtraccion();
		}
		return costoTotal; 
	}
	
	public double getTiempoTotal(int velocidadDeTraslado){
		Iterator<Atraccion> iteradorDeAtracciones = recorrido.iterator();
		double tiempoTotal = 0;
		Coordenada puntoActual = new Coordenada(100,500);
		double tiempoDeTraslado = 0;
		double distanciaDeTraslado = 0;
		
		while (iteradorDeAtracciones.hasNext()){
			Atraccion atraccionProxima = iteradorDeAtracciones.next();
			
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
		atracciones.sort(ordenadorPorCosto);
		return atracciones;
	}

	private List<Atraccion> ordenarAtraccionesPorMenorTiempo(
			List<Atraccion> atraccionesDisponibles) {
		OrdenadorDeAtraccionesPorMenorTiempo ordenadorPorTiempo = new OrdenadorDeAtraccionesPorMenorTiempo();
		atraccionesDisponibles.sort(ordenadorPorTiempo);
		return atraccionesDisponibles;
	}
}
