package com.projeto.web.service.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DataUtils {

	public static String formatar(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return localDateTime.format(formatter);
	}

	public static String formatar(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return localDate.format(formatter);
	}

	public static LocalDate convertTo(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if (!data.isEmpty()) {
			return LocalDate.parse(data, formatter);
		}
		return null;
	}
		
	public static LocalDateTime convertToDateTime(String data) {
		if (!data.isEmpty()) {
			return LocalDateTime.parse(data);
		}
		return null;
	}

	public static LocalDateTime convertDateTime(String data) {
		if (!data.isEmpty()) {
			data = formataDataConversao(data);
			return LocalDateTime.parse(data);
		}
		return null;
	}

	private static String formataDataConversao(String data) {

		data = data.replace("/", "");

		Integer year = Integer.parseInt(data.substring(4, 8));
		Integer month = Integer.parseInt(data.substring(2, 4));
		Integer day = Integer.parseInt(data.substring(0, 2));

		StringBuilder sb = new StringBuilder();
		sb.append((year.toString().length() < 2) ? "20" + year.toString() : year.toString());
		sb.append((month < 10) ? "-0" + month.toString() : "-" + month.toString());
		sb.append((day < 10) ? "-0" + day.toString() : "-" + day.toString());
		sb.append("T00:00");

		return sb.toString();
	}

	public static LocalDate convertToDate(String data) {
		if (!data.isEmpty()) {
			return LocalDate.parse(data.substring(0, 10));
		}
		return null;
	}

	public static Calendar converterStringCalendar(String dataHora) {

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sdf.parse(dataHora));
			return calendar;
		} catch (ParseException e) {
		}

		return null;
	}

	public static boolean equals(Calendar data1, Calendar data2) {
		return data1.get(Calendar.DAY_OF_MONTH) == data2.get(Calendar.DAY_OF_MONTH)
				&& data1.get(Calendar.MONTH) == data2.get(Calendar.MONTH)
				&& data1.get(Calendar.YEAR) == data2.get(Calendar.YEAR);
	}

	public static String retornaDataFormatada(LocalDateTime localDateTime) {
		StringBuilder sb = new StringBuilder();
		Integer dia = localDateTime.getDayOfMonth();
		Integer mes = localDateTime.getMonthValue();
		Integer ano = localDateTime.getYear();

		sb.append((dia < 10) ? "0" + dia.toString() : dia.toString());
		sb.append((mes < 10) ? "/0" + mes.toString() : "/" + mes.toString());
		sb.append((ano.toString().length() < 2) ? "/20" + ano.toString() : "/" + ano.toString());

		return sb.toString();
	}

	public static String retornaDataHoraFormatada(LocalDateTime dtAtual) {
		StringBuilder sb = new StringBuilder();
		Integer dia = dtAtual.getDayOfMonth();
		Integer mes = dtAtual.getMonthValue();
		Integer ano = dtAtual.getYear();
		Integer hora = dtAtual.getHour();
		Integer min = dtAtual.getMinute();
		Integer seg = dtAtual.getSecond();

		sb.append((dia < 10) ? "0" + dia.toString() : dia.toString());
		sb.append((mes < 10) ? "/0" + mes.toString() : "/" + mes.toString());
		sb.append((ano.toString().length() < 2) ? "/20" + ano.toString() : "/" + ano.toString());
		sb.append((hora < 10) ? " 0" + hora.toString() : " " + hora.toString());
		sb.append((min < 10) ? ":0" + min.toString() : ":" + min.toString());
		sb.append((seg < 10) ? ":0" + seg.toString() : ":" + seg.toString());

		return sb.toString();

	}
	
	public static LocalDate convertToDateAnoMesDia(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    if (!data.isEmpty()) {
	        return LocalDate.parse(data, formatter);
	    }
		return null;
	}
	
	public static LocalDate convertToDateTimeAnoMesDia(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
	    if (!data.isEmpty()) {
	        return LocalDate.parse(data, formatter);
	    }
		return null;
	}

}
