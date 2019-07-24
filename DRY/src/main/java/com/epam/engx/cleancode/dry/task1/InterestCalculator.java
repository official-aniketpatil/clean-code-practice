package com.epam.engx.cleancode.dry.task1;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InterestCalculator {

    private static final int AGE = 60;
    private static final double INTEREST_PERCENT = 4.5d;
    private static final double SENIOR_PERCENT = 5.5d;
    private static final int BONUS_AGE = 13;


    public BigDecimal calculateInterest(AccountDetails accountDetails) {
        if (isUserEligibleForBonus(accountDetails)) {
            return interest(accountDetails);
        } else {
            return BigDecimal.ZERO;
        }
    }

    private boolean isUserEligibleForBonus(AccountDetails accountDetails) {
        return durationBetweenDatesInYears(accountDetails.getBirth(), accountDetails.getStartDate()) > BONUS_AGE;
    }
    
    private Calendar getCalendarFrom(Date from) {
    	Calendar calendar = new GregorianCalendar();
        calendar.setTime(from);
        return calendar;
    }
    private int durationBetweenDatesInYears(Date from, Date to) {
        Calendar startCalendar = getCalendarFrom(from);
        Calendar endCalendar =   getCalendarFrom(to);

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        if (endCalendar.get(Calendar.DAY_OF_YEAR) < startCalendar.get(Calendar.DAY_OF_YEAR))
            return diffYear - 1;
        return diffYear;
    }
    private boolean isSeniorCitizen(int age) {
    	if(age > AGE) {
    		return true;
    	}
    	return false;
    }
    private double calculatePrincipleAmount(AccountDetails accountDetails) {
    	return accountDetails.getBalance().doubleValue()* durationSinceStartDateInYears(accountDetails.getStartDate());
    }
    private BigDecimal interest(AccountDetails accountDetails) {
        double interest = 0;
        if (isSeniorCitizen(accountDetails.getAge())) {
            interest = calculatePrincipleAmount(accountDetails) * SENIOR_PERCENT / 100;
        }else {
            interest = calculatePrincipleAmount(accountDetails) * INTEREST_PERCENT / 100;
        }
        return BigDecimal.valueOf(interest);
    }

    private int durationSinceStartDateInYears(Date startDate) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(startDate);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(new Date());

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        if (endCalendar.get(Calendar.DAY_OF_YEAR) < startCalendar.get(Calendar.DAY_OF_YEAR))
            return diffYear - 1;
        return diffYear;

    }
}
