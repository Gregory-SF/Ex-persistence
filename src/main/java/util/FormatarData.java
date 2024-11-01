package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatarData {

	/**
	 * Método para formatar data no formato yyyy-MM-dd
	 * @param Date 
	 * @return String
	 * **/
	public static String formatarAnoMesDia(Date data) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(data);
	}
	
	/**
	 * Método para formatar data no formato yyyy-MM
	 * @param Date 
	 * @return String
	 * **/
	public static String formatarAnoMes(Date data) {
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		return format.format(data);
	}
	
	/**
	 * Método para formatar data e horário no formato yyyy-MM-dd HH:mm
	 * @param Date
	 * @return String
	 * **/
	public static String formatarAnoMesDiaHoraMinuto(Date data) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return format.format(data);
	}
	
	/**
	 * Método para formatar horário no formato HH
	 * @param Date
	 * @return int
	 * **/
	public static int formatarHora(Date data) {
		DateFormat format = new SimpleDateFormat("HH");
		return (int) (Integer.valueOf(format.format(data)));
	}
	
	/**
	 * Método para formatar String no formato yyyy-MM-dd para Date
	 * @param String 
	 * @return Date
	 * @throws ParseException 
	 * **/
	public static Date formatarStringDate(String data) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dataFormatada = format.parse(data);
		return dataFormatada;
	}
	
}
