package com.pkg.lg.scribbles;

import java.util.Arrays;

public class NextGreaterInt {
	
	private static int nextGreaterElement(int number) {
		
		int pointer = 0;
		
		char [] ch = Integer.toString(number).toCharArray();
	    
		for (int indx = ch.length-1; indx > 0 ; indx--) {
	        if(ch[indx]-'0' > ch[indx-1]-'0'){
	            pointer = indx;
	            break;
	        }
	    }
		
	    if(pointer == 0){
	        return -1;
	    }
	    
	    int [] reformedArray = new int[ch.length];
	    
	    for (int i = 0; i < ch.length; i++) {
	        reformedArray[i]=ch[i]-'0';
	    }
	    
	    
	    Arrays.sort(reformedArray,pointer,reformedArray.length);
	    
	    
	    for(int indx = pointer; indx < reformedArray.length; indx++){
	        if(reformedArray[indx] > reformedArray[pointer-1]){
	            int temp = reformedArray[pointer-1];
	            reformedArray[pointer-1] = reformedArray[indx];
	            reformedArray[indx] = temp;
	            break;
	        }
	    }
	    
	    int nextLNumber = 0;
	    
	    for (int indx = 0; indx < reformedArray.length; indx++) {
	        nextLNumber = nextLNumber * 10 + reformedArray[indx];
	    }
	    
	    if(number >= nextLNumber){
	    	return -1;
	    }
	    
	    return nextLNumber;
	}
	
	
	public static void main(String[] args) {
		System.out.println(nextGreaterElement(88798));
	}

}
