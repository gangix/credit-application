package com.credit.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converter {
	private static DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'00:00:01'Z'");
	
	public static String localDateToString(LocalDate localDate) {
		if(localDate==null) {
			return "";
		}
		
		return localDate.format(formatterDateTime);
	}
	
	public static LocalDate stringToLocalDate(String date) {
		return LocalDate.parse(date, formatterDateTime);
	}
	
}
