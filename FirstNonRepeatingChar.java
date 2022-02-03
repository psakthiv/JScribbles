package coderPad;

public class FirstNonRepeatingChar {

	public static void main(String[] args) {
		
		String str = "PunithaSakthivel";
		
		for(int indx = 0; indx < str.length(); indx++) {
			if(str.indexOf(str.charAt(indx)) == str.lastIndexOf(str.charAt(indx))) {
				System.out.println(str.charAt(indx) + " ..this is the last index..");
				break;
			}
		}
	}
	
}
