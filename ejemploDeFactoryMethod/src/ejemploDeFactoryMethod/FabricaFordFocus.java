package ejemploDeFactoryMethod;

public class FabricaFordFocus extends FabricaFord{

	@Override
	public Auto factoryMethod() {
		
		return new FordFocus("gris", "Ford", "Focus","1.8 16V");
	}
}
