package ejemploDeFactoryMethod;

public class FordFiesta extends Auto {	
	
	private boolean packElectrico; 
	
	public FordFiesta(String color, String marca, String modelo, boolean pack){
		this.color = color;
		this.marca = marca;
		this.modelo = modelo;
		this.packElectrico = pack;
	}	
	
	public boolean tienePackElectrico(){
		return this.packElectrico;
	}
	
	
}
