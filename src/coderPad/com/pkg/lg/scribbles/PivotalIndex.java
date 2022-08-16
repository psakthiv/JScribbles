package com.pkg.lg.scribbles;

public class PivotalIndex {
	
	private static int pivotalIndex(int[] nums) {
		int indxSum = 0;
		int jIndxSum = 0;
		
		for(int indx = 0; indx < nums.length; indx++) {
			if(indx != 0) {
				indxSum += nums[indx-1];
			}
			jIndxSum = 0;
			for(int jIndx = indx+1; jIndx < nums.length; jIndx++ ) {
				jIndxSum += nums[jIndx];
			}
			if(indxSum == jIndxSum) {
				return indx;
			}
		}
		return -1;
	}

	
	public static void main(String[] args) {
		int[] nums = {-1,-1,-1,-1,-1,-1};//{-1,-1,0,1,1,0};//{-1};//{2,1,-1};//{2,1,-1};//{1,2,3};//{1,7,3,6,5,6};
		System.out.println(pivotalIndex(nums));
	}
}
