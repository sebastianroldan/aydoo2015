package ejemploDeFactoryMethod;

public abstract class Auto {
	
	protected String color;
	protected String marca;
	protected String modelo;	
	
	public String getMarca(){
		return this.marca;
	}
	
	public String getModelo(){
		return this.modelo;
	}
	
	public String getColor(){
		return this.color;
	}

}
