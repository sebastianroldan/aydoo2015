package turismo;

public class Coordenada {
	
		private double coordenadaX;
		private double coordenadaY;
	
		public Coordenada(double x, double y) {
				this.coordenadaX = x;
				this.coordenadaY = y;
		}
	
		public double getCoordenadaX() {
				return coordenadaX;
		}
	
		public double getCoordenadaY() {
				return coordenadaY;
		}
	
		public double calcularDistanciaDesdeEstaCoordenada(Coordenada punto){
				double restaCoordenadasX = this.coordenadaX-punto.getCoordenadaX();
				double restaCoordenadasY = this.coordenadaY-punto.getCoordenadaY();
				double Xcuadrado = restaCoordenadasX*restaCoordenadasX;
				double Ycuadrado = restaCoordenadasY*restaCoordenadasY;
				double distancia = Math.sqrt(Xcuadrado + Ycuadrado);
				return distancia; 
		}
		
		public static double calcularDistanciesEntre(Coordenada punto1, Coordenada punto2){		
				return punto1.calcularDistanciaDesdeEstaCoordenada(punto2);
		}
	
}
