package coderPad;

public class CharToInt {
	
	public static int getInt(String str) {
		int indx = 0, result = 0;
		
		while(indx < str.length()) {
			result *= 10;
			result += ( str.charAt(indx++) - '0' );
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(getInt("4279878"));
	}
	
}
