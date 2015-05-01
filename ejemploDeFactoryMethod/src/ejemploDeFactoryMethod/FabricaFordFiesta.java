package ejemploDeFactoryMethod;

public class FabricaFordFiesta extends FabricaFord{

	@Override
	public Auto factoryMethod() {
		
		return new FordFiesta("azul", "Ford", "Fiesta", true);
	}

}
