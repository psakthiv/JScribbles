package com.pkg.lg.scribbles;

import java.util.Arrays;

public class RunningSum {
	
	public static int[] runningSum(int[] nums) {
        
        int runningSum[] = new int[nums.length];
        int sum = 0;
        for(int indx = 0; indx < nums.length; indx++){
        	sum += nums[indx];
            runningSum[indx] = sum;
        }
       return runningSum; 
    }
	
	public static void main(String[] args) {
		int nums[] = {1,2,3,4};
		int runningSum[] = runningSum(nums);
		System.out.println(Arrays.toString(runningSum));

	}

}
