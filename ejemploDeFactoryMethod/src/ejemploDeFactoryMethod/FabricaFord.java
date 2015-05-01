package ejemploDeFactoryMethod;

import java.util.LinkedList;
import java.util.List;

public abstract class FabricaFord {
	
	private List<Auto> autosFabricados;
	
	public FabricaFord(){
		this.autosFabricados = new LinkedList<Auto>();
	}
	
	public abstract Auto factoryMethod();
		
	public void producirAuto(){
		Auto nuevoAuto = this.factoryMethod();
		this.autosFabricados.add(nuevoAuto);
	}
	
	public List<Auto> getAutos(){
		return this.autosFabricados;
	}
	
}
