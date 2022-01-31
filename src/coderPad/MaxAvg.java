package coderPad;

import java.util.HashMap;
import java.util.Map;

public class MaxAvg {

	
	public static int calculateMaxAvg(String[][] data) {
		
		
		  Map<String, int[]> map = new HashMap<>();
	        int best = -1;
	        
	        for(String[] d : data) {
	            String name = d[0];
	            int score = Integer.parseInt(d[1]);
	            
	            if(!map.containsKey(name)) { 
	            	map.put(name, new int[]{1, score});
	            }
	            else {
	                map.get(name)[0]++;
	                map.get(name)[1]+= score;
	            }
	        }
	        
	        for(int[] score : map.values()) {
	            best = Math.max(best, score[1]/score[0]);
	        }
	        
		return best;
	}
	
	public static void main(String[] args) {
		String[][] data = new String[][]{{"Bob","87"}, {"Mike", "305"},{"Bob", "52"}, {"Jason","35"}, {"Mike", "55"}, {"Jessica", "99"}};
		int best = calculateMaxAvg(data);
		System.out.println(best+ " This is the best score..");
		
	}
}
