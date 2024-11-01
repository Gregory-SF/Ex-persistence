package teste;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import util.FormatarData;

public class Teste {
	public static void main(String[] args) throws ParseException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String d1 = "2024-10-29";
		String d2 = "2024-10-02";
		Date aux = format.parse(d1);
		Date val = format.parse(d2);
		if(aux.after(val)) System.out.println("AAAAAAAAAAAAAA");
	}
}
