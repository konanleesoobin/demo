package com.example.demo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	/**
	 * String을 format에 맞게 localDate로 변환
	 * @param date
	 * @param format
	 * @return
	 */
	public LocalDate parseStringToLocalDate(String date, String format) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate parseDate = LocalDate.parse(date, formatter);
        return parseDate;
	}
	
	/**
	 * DateTimeParseException에 길이문제 때문에 변환
	 * [TODO] d가 f보다 컸을떄 
	 * @param date
	 * @param format
	 * @param cmd
	 * @return
	 */
	public String parseDateFormat(String date, String format, String cmd) {
		String[] dates = date.split("/");
		String[] formats = format.split("/"); 
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < dates.length; i++) {
			String d = dates[i];
			String f = formats[i];
			if(d.length() != f.length()) {
				for(int j = 0; j < f.length() - d.length(); j++) {
					sb.append("0");
				}
			}
			sb.append(d);
			sb.append(cmd);
		}
		
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
