package com.barunsw.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {
	private static SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
	public static String currentDateTime() {
		return DATETIME_FORMAT.format(Calendar.getInstance().getTime());
	}
	
	public static String daysAgo(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1 * days);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		
		return DATETIME_FORMAT.format(cal.getTime());
		
	}
}
