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
	 * Método para retornar o primeiro dia do próximo mês em Date 
	 * @param Date
	 * @return Date|
	 * @throws ParseException 
	 * **/
	public static Date formatarMesPosterior(Date data) throws ParseException {
		DateFormat anoFormat = new SimpleDateFormat("yyyy");
		DateFormat mesFormat = new SimpleDateFormat("MM");	
		if ((int) (Integer.valueOf(mesFormat.format(data)))+1==13) {
			int ano = (int) (Integer.valueOf(anoFormat.format(data))+1);
			return formatarStringDate(ano+"-01-01");
		}
		int mes = (int) (Integer.valueOf(mesFormat.format(data))+1);
		return formatarStringDate(anoFormat.format(data)+"-"+mes+"-01");
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
	 * Método para formatar mês no formato MM
	 * @param Date
	 * @return int
	 * **/
	public static int formatarMes(Date data) {
		DateFormat format = new SimpleDateFormat("MM");
		return (int) (Integer.valueOf(format.format(data)));
	}
	
	/**
	 * Método para formatar retornar uma string do mês anterior no formato yyyy-MM
	 * @param Date
	 * @return String
	 * **/
	public static String formatarMesAnterior(Date data) {
		DateFormat anoFormat = new SimpleDateFormat("yyyy");
		DateFormat mesFormat = new SimpleDateFormat("MM");	
		if ((int) (Integer.valueOf(mesFormat.format(data)))-1==0) {
			int ano = (int) (Integer.valueOf(anoFormat.format(data))-1);
			return (ano+"-12");
		}
		int mes = (int) (Integer.valueOf(mesFormat.format(data))-1);
		return (anoFormat.format(data)+"-"+mes);
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
