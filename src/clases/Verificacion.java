package clases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.regex.Pattern;

public class Verificacion {

	public boolean dniValido(String dni) {
		if(Pattern.matches("[a-zA-Z]+", dni)==true||dni.length()!=8||Integer.parseInt(dni)<0) {
			return false;
		}
		return true;
	}
	public boolean telValido(String tel) {

		if(Pattern.matches("[a-zA-Z]+", tel)==true||tel.length()<6||tel.length()>8||Integer.parseInt(tel)<0) {
			return false;
		}
		return true;
	}
	
	public boolean fechaValida(String f) {
		try {
			LocalDate.parse(f);
		}catch(DateTimeParseException e) {
			return false;
		}
		return true;
	}
	
	public boolean notaValida(String nota) {
		if(Pattern.matches("[a-zA-Z]+", nota)!=true) {
			if(Float.parseFloat(nota)<=10&&Float.parseFloat(nota)>=1) {
				return true;
			}
		}
		return false;
	}

	
}
