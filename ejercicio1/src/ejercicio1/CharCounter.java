package ejercicio1;

import java.util.HashMap; 

public class CharCounter {

	private String stringToCount;
	
	public CharCounter(String stringToCount){
		this.stringToCount = stringToCount;
	}
	
	public int howMany(char character){
		int count = 0;
		
		switch (character){

		case '$': count = -1; break;
		case '@': count = -2; break;
		default:
			for (int i = 0; i < stringToCount.length(); i++) {
				
				if (stringToCount.charAt(i) == character) {
					count++;
				}
			}
			break;
		}
		
		return count;		
	}
	
	public HashMap<Character,Integer> countAll(){
		HashMap charMap = new HashMap<Character,Integer>();
		Integer count = 0;
		for (char letter='a'; letter <= 'z'; ++letter) { 
			count = this.howMany(letter); 
40 			charMap.put(letter, count); 
		}
		return charMap;
	}
}
