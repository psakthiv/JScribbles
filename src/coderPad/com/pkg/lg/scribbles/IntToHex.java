package com.pkg.lg.scribbles;

public class IntToHex {
	
	private static String convertToHex(int intNum) {
		String digits = "0123456789abcdef";
	
		String hexString = "";
		if(intNum == 0) return "0";
		int digit = intNum;
		while (intNum != 0) {
			hexString = digits.charAt(digit&15) + hexString;
			intNum = intNum >>> 4;
		}
		
		return hexString;
	}
	
	public static void main(String[] args) {
		System.out.println(convertToHex(26));
	}

}
