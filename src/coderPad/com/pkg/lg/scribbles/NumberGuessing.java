package com.pkg.lg.scribbles;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessing {
	
	private static void guessNum() {
		Random random = new Random();
		int guess = random.nextInt(10);
		System.out.println(guess);
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter a number- ");
		int num = scan.nextInt();
		
		while(num != guess) {
			if(guess > num) {
				System.out.println("Too Low!!");
				num = scan.nextInt();
			}
			else if(guess < num) {
				System.out.println("Too High!!");
				num = scan.nextInt();
			}
			else {
				scan.close();
			}
		}
		System.out.println("You guessed it right!!");		
	}
	
	public static void main(String[] args) {
		guessNum();
		
	}

}
