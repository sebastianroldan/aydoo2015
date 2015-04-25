package turismo;

public class Atraccion {
	
	private String nombre;
	private Coordenada coordenadas;
	private double costoDeAtraccion;
	private int tiempoNecesario;
	private int cupoMaximo;
	private TipoDeAtraccion tipoAtraccion;

	public Atraccion(String nombre, Coordenada coordenadas, double costo,
			int tiempo, int cupo, TipoDeAtraccion tipo) {
		this.nombre = nombre;
		this.coordenadas = coordenadas;
		this.costoDeAtraccion = costo;
		this.tiempoNecesario = tiempo;
		this.cupoMaximo = cupo;
		this.tipoAtraccion = tipo;
	}

	public int getTiempoNecesario() {
		
		return this.tiempoNecesario;
	}
	
	public void setTiempoNecesario(int tiempo) {
		
		this.tiempoNecesario = tiempo;
	}

	public double getCostoDeAtraccion() {
		
		return this.costoDeAtraccion;
	}

	public Coordenada getCoordenadas() {
		
		return this.coordenadas;
	}

	public int getCupoMaximo() {
		
		return this.cupoMaximo;
	}
	
}
