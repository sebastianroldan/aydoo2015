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
		this.setNombre(nombre);
		this.coordenadas = coordenadas;
		this.costoDeAtraccion = costo;
		this.tiempoNecesario = tiempo;
		this.cupoMaximo = cupo;
		this.setTipoAtraccion(tipo);
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
	
	public void setCostoDeAtraccion(double costoDeAtraccion) {
		this.costoDeAtraccion = costoDeAtraccion;
	}
	
	public Coordenada getCoordenadas() {
		return this.coordenadas;
	}

	public void setCoordenadas(Coordenada coordenadas) {
		this.coordenadas = coordenadas;
	}

	public int getCupoMaximo() {
		return this.cupoMaximo;
	}

	public void setCupoMaximo(int cupoMaximo) {
		this.cupoMaximo = cupoMaximo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoDeAtraccion getTipoAtraccion() {
		return tipoAtraccion;
	}

	public void setTipoAtraccion(TipoDeAtraccion tipoAtraccion) {
		this.tipoAtraccion = tipoAtraccion;
	}
	
}