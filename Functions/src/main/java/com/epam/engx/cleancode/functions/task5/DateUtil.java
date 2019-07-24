package com.epam.engx.cleancode.functions.task5;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
 
	public Date changeToMidnight(Date date, boolean dateShift) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		setShiftDate(calendar, dateShift);
		setMidnight(calendar);
		return calendar.getTime();
	}
	private void setMidnight(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}
	
	private void setShiftDate(Calendar calendar, boolean dateShift) {
		calendar.add(Calendar.DATE, dateShift ? 1 : -1);
	}

}
