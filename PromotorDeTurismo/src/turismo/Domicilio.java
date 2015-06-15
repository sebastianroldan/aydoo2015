package turismo;

public class Domicilio {
	
	private String direccion;
	private int numero;
	private Coordenada coordenada;
	
	public Domicilio(int coordenadaX, int coordenadaY) {
			this.coordenada = new Coordenada(coordenadaX,coordenadaY);
	}
	
	public int getNumero() {
			return numero;
	}
	
	public void setNumero(int numero) {
			this.numero = numero;
	}
	
	public Coordenada getCoordenada() {
			return coordenada;
	}

	public String getDireccion() {
			return direccion;
	}
	
	public void setDireccion(String direccion) {
			this.direccion = direccion;
	}
	
	
}
