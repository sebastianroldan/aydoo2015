package turismo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Sugerencia {
	
		private List<Atraccion> sugerencia;
		private double costoFinal;
		
		public Sugerencia(){
				this.sugerencia = new LinkedList<Atraccion>();				
				this.costoFinal = 0;
		}
		
		private boolean puedeHacer(Atraccion proximaAtraccion, Turista turista) {
			
			return (alcanzaPresupuestoPara(proximaAtraccion, turista) 
						&& (alcanzaTiempoPara(proximaAtraccion, turista) 
								&& (proximaAtraccion.hayCupo())));
		}
	
	
		private boolean alcanzaTiempoPara(Atraccion atraccion, Turista turista) {
			
				double tiempoDeTraslado = calcularTiempoDeViajeHastaLaProximaAtraccion(
												turista.getVelocidadDeTraslado(),atraccion.getCoordenadas(), turista.getCoordenada());
				
				double tiempoTotalTranscurrido = this.getTiempoTotal(turista.getVelocidadDeTraslado());
				
				return tiempoDeTraslado+atraccion.getTiempoNecesario()+tiempoTotalTranscurrido 
												<= turista.getTiempoDisponible();
		}
	
	
		private double calcularTiempoDeViajeHastaLaProximaAtraccion(int velocidadDeTraslado,  
													Coordenada proximoPunto, Coordenada ubicacionDelTurista) {
			
				double tiempo = 0; 
				double distancia = 0;
				Coordenada ultimoPuntoRecorrido = ubicacionDelTurista;
			
				if (!this.sugerencia.isEmpty()){
			
						ultimoPuntoRecorrido = this.sugerencia.get(this.sugerencia.size()-1).getCoordenadas();
				}
			
				distancia = proximoPunto.calcularDistanciaDesdeEstaCoordenada(ultimoPuntoRecorrido);
				tiempo = distancia/velocidadDeTraslado;
				return tiempo;
		}
		
		private boolean alcanzaPresupuestoPara(Atraccion atraccion, Turista turista){
			
				return atraccion.getCostoDeAtraccion() + this.getCostoTotalSinPromociones()
										<= turista.getPresupuesto();
		}
		
		public void setCostoFinal(double montoAdescontar){
				this.costoFinal = this.getCostoTotalSinPromociones() - montoAdescontar;
		}
		
		public double getCostoFinal(){
				return costoFinal;
		}
		
		private double getCostoTotalSinPromociones(){
				double costoTotal = 0;
				
				for (Atraccion atraccionActual: sugerencia){
					
						costoTotal = costoTotal + atraccionActual.getCostoDeAtraccion();
				}
				return costoTotal; 
		}
		
		
		
		public double getTiempoTotal(int velocidadDeTraslado){		
				double tiempoTotal = 0;
				Coordenada puntoActual = new Coordenada(100,500);
				double tiempoDeTraslado = 0;
				double distanciaDeTraslado = 0;
			
				for (Atraccion atraccionProxima: sugerencia){			
						distanciaDeTraslado = puntoActual.calcularDistanciaDesdeEstaCoordenada(
																atraccionProxima.getCoordenadas());
						
						tiempoDeTraslado = distanciaDeTraslado/velocidadDeTraslado;
						tiempoTotal = tiempoTotal + tiempoDeTraslado + atraccionProxima.getTiempoNecesario();
						puntoActual = atraccionProxima.getCoordenadas();			
				}		
				return tiempoTotal;
		}
	
		public void generarSugerenciaConAtraccionesPorMenorCosto(
									List<Atraccion> atraccionesDisponibles, Turista turista){
			
				Collections.sort(atraccionesDisponibles, new OrdenadorDeAtraccionesPorCostoMenor());				
				this.generarSugerencia(atraccionesDisponibles, turista);				
		}
		
		public void generarSugerenciaConAtraccionesPorTiempoDisponible(
									List<Atraccion> atraccionesDisponibles, Turista turista) {
			
				Collections.sort(atraccionesDisponibles, new OrdenadorDeAtraccionesPorMenorTiempo());
				this.generarSugerencia(atraccionesDisponibles, turista);
		}
		
		public void generarSugerenciaConAtraccionesPorPreferencia(
									List<Atraccion> atraccionesDisponibles, Turista turista) {
				for (Atraccion proximaAtraccion: atraccionesDisponibles){			
						if (puedeHacer(proximaAtraccion, turista) && (proximaAtraccion.getTipoAtraccion()==turista.getPreferencia()) ){				
								proximaAtraccion.agregarVisitante();				
								sugerencia.add(proximaAtraccion);									
						}
				}
				this.setCostoFinal(0);

		}
		
		public void generarSugerenciaConAtraccionesMasCostosas(
						List<Atraccion> listaDeAtracciones, Turista turista) {
			
				Collections.sort(listaDeAtracciones, new OrdenadorDeAtraccionesPorCostoMayor());
				this.generarSugerencia(listaDeAtracciones, turista);		
		}
		
		public void generarSugerencia(List<Atraccion> atraccionesDisponibles, Turista turista){
			
				for (Atraccion proximaAtraccion : atraccionesDisponibles){
						if (puedeHacer(proximaAtraccion, turista)){
								proximaAtraccion.agregarVisitante();
								sugerencia.add(proximaAtraccion);										
						}
				}				
				this.setCostoFinal(0);
		}
		
		public List<Atraccion> getListaDeAtracciones(){
				return this.sugerencia;
		}
		
		public void agregarAtraccionExtra(Atraccion atraccionExtra, Turista turista){
				if (puedeHacer(atraccionExtra, turista)){
						sugerencia.add(atraccionExtra);
				}
		}
	
}
