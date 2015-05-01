package ejemploDeFactoryMethod;

public class FordFocus extends Auto {	
		
		private String motor;
	
		public FordFocus(String color, String marca, String modelo, String motor){
			this.color = color;
			this.marca = marca;
			this.modelo = modelo;
			this.motor = motor;
		}	
		
		public  String getMotor(){
			return this.motor;
		}

}
