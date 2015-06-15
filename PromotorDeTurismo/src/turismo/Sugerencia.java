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
		
		public double getCostoFinal(){
			return costoFinal;
		}
		
		public double getCostoTotalSinPromociones(){
				double costoTotal = 0;
				
				for (Atraccion atraccionActual: sugerencia){
					
						costoTotal = costoTotal + atraccionActual.getCostoDeAtraccion();
				}
				return costoTotal; 
		}
			
		public void setCostoFinal(double costo){
			this.costoFinal = costo;
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
		
		public double calcularTiempoDeViajeHastaLaProximaAtraccion(int velocidadDeTraslado,  
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
				
		public void aplicarDescuentoACostoFinal(double montoAdescontar){
				this.costoFinal = this.costoFinal - montoAdescontar;
		}
	
		public void generarSugerenciaConAtraccionesPorPreferencia(
									List<Atraccion> atraccionesDisponibles, Turista turista) {			
				for (Atraccion proximaAtraccion: atraccionesDisponibles){			
						if (turista.puedeHacer(proximaAtraccion, this) && (proximaAtraccion.getTipoAtraccion()==turista.getPreferencia()) ){				
								sugerencia.add(proximaAtraccion);	
						}
				}
				this.costoFinal = this.getCostoTotalSinPromociones()*turista.getCantidadDeIntegrantesGrupoFamiliar();

		}
		
		public void generarSugerencia(List<Atraccion> atraccionesDisponibles, Turista turista){
			
				for (Atraccion proximaAtraccion : atraccionesDisponibles){
						if (turista.puedeHacer(proximaAtraccion, this)){						
								sugerencia.add(proximaAtraccion);										
						}
				}				
				this.costoFinal = this.getCostoTotalSinPromociones()*turista.getCantidadDeIntegrantesGrupoFamiliar();
		}
		
		public void generarSugerenciaConAtraccionesMasCostosas(
						List<Atraccion> listaDeAtracciones, Turista turista) {
	
				Collections.sort(listaDeAtracciones, new OrdenadorDeAtraccionesPorCostoMayor());
				this.generarSugerencia(listaDeAtracciones, turista);		
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
		
		public List<Atraccion> getListaDeAtracciones(){
				return this.sugerencia;
		}
		
		public void agregarAtraccionExtra(Atraccion atraccionExtra, Turista turista){
				if (turista.alcanzaTiempoPara(atraccionExtra, this) && atraccionExtra.hayCupo(turista.getCantidadDeIntegrantesGrupoFamiliar())){
						sugerencia.add(atraccionExtra);
				}
		}
	
}
