package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;

public class MortgageInstallmentCalculator {

	private static void validateArguments(int principalAmount, int mortgageYears, double interestRatePercent) {
        if (principalAmount < 0 || mortgageYears <= 0 || interestRatePercent < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }
    }
    private static double convertPercentToDecimal(double annualInterestRate) {
    	return annualInterestRate / 100.0;
    }
    private static int convertToMonths(int year) {
    	return year*12;
    }
    private static double getMonthlyInterestRate(double annualInterestRate) {
    	return annualInterestRate / 12.0;
    }
    public static double getMonthlyPayment(int principleAmount, int mortgageMonths, double monthlyInterestRateInDecimal) {
    	
    	return (principleAmount * monthlyInterestRateInDecimal) / (1 - Math.pow(1 + monthlyInterestRateInDecimal, - mortgageMonths));
    }
    private static double calculateZeroRatedMonthlyPayment(int principalAmount, int mortgageMonths) {
    	return principalAmount / mortgageMonths;
    }
    public static double calculateMonthlyPayment(
        int principalAmount, int mortgageYears, double annualInterestRate) {

    	validateArguments(principalAmount, mortgageYears, annualInterestRate); 
        double annualInterestRateInDecimal = convertPercentToDecimal(annualInterestRate);

        int mortgageMonths = convertToMonths(mortgageYears);

        if(annualInterestRate == 0) {
            return  calculateZeroRatedMonthlyPayment(principalAmount, mortgageMonths);
        }

        double monthlyInterestRateInDecimal = getMonthlyInterestRate(annualInterestRateInDecimal);

        double monthlyPayment = getMonthlyPayment(principalAmount, mortgageMonths, monthlyInterestRateInDecimal);

        return monthlyPayment;
    }
}
