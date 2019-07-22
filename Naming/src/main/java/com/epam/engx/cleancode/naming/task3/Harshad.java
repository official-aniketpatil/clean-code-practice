package com.epam.engx.cleancode.naming.task3;

public class Harshad {

	final static long SEQUENCE_LIMIT = 1000;
	
	private static void printHarshad() {
		
		for (int i = 1; i <= SEQUENCE_LIMIT; i++) {
			if (i % getDigitSum(i) == 0) {
				System.out.println(i);
			}
		}
	}
	public static void main(String[] args) {
		printHarshad();
	}

	private static int getDigitSum(int num) {
		int sum = 0;
		while (num != 0) {
            sum += num % 10;
            num = num / 10;
        }
		return sum;
	}

}
