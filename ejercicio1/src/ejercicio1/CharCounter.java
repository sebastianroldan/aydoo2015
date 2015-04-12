package ejercicio1;

public class CharCounter {

	private String stringToCount;
	
	public CharCounter(String stringToCount){
		this.stringToCount = stringToCount;
	}
	
	public int howMany(char character){
		int count = 0;
		for (int i = 0; i < stringToCount.length(); i++) {
			
			if (stringToCount.charAt(i) == character) {
				count++;
			}
		}
		return count;		
	}
}
