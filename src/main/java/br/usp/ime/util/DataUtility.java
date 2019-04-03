package br.usp.ime.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DataUtility {

	/**
	 * Calcula o nÃºmero de dias entre duas datas do tipo Calendar independente do tipo de Calendar usado.
	 * 
	 * @param d1
	 *          Primeira data.
	 * @param d2
	 *          Segunda data.
	 * 
	 * @return NÃºmero de dias de diferenÃ§a entre as datas. Retorna zero se for a mesma data. A ordem das datas nÃ£o importa, sempre vai ser retornado
	 *         valor >= 0. Se os de d1 e d2 forem diferentes pode nÃ£o retornar um valor preciso.
	 */
	public static int daysBetween(java.util.Calendar d1, java.util.Calendar d2) {
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			java.util.Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(java.util.Calendar.DAY_OF_YEAR) - d1.get(java.util.Calendar.DAY_OF_YEAR);
		int y2 = d2.get(java.util.Calendar.YEAR);
		if (d1.get(java.util.Calendar.YEAR) != y2) {
			d1 = (java.util.Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
				d1.add(java.util.Calendar.YEAR, 1);
			} while (d1.get(java.util.Calendar.YEAR) != y2);
		}
		return days;
	}

	public long daysBetween(Date d1, Date d2) {
		long ONE_HOUR = 60 * 60 * 1000L;
		return ((d2.getTime() - d1.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
	}

	/**
	 * 
	 * 
	 */
	public static Calendar dateToCalendar(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return cal;
	}

	public static Date calendarToDate(Calendar cal) {
		Date data = new Date();
		data = cal.getTime();
		return data;
	}

	public static String dateToString(Date d) {
		Format formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String s = formatter.format(d);
		return s;
	}

	public static String dateToStringHour(Date d) {
		Format formatter = new SimpleDateFormat("HH:mm");
		String s = formatter.format(d);
		return s;
	}

	public static String dateToStringDate(Date d) {
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		String s = formatter.format(d);
		return s;
	}

	public static String getSemestre(Date d) {
		String semestre = "1";

		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		if (cal.get(Calendar.MONTH) > 5) {
			semestre = "2";
		}

		return semestre;
	}

	public static String getAnoSemestre(Date d) {
		String semestre = "1";

		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		if (cal.get(Calendar.MONTH) > 5) {
			semestre = "2";
		}

		String ano = String.valueOf(cal.get(Calendar.YEAR));

		return ano + semestre;

	}

	public static String getDiaSemana(Date d) {

		String ds = "";

		Calendar cal = Calendar.getInstance();

		cal.setTime(d);

		switch (cal.get(Calendar.DAY_OF_WEEK)) {

		case 1:
			ds = "DOM";
			break;
		case 2:
			ds = "SEG";
			break;
		case 3:
			ds = "TER";
			break;
		case 4:
			ds = "QUA";
			break;
		case 5:
			ds = "QUI";
			break;
		case 6:
			ds = "SEX";
			break;
		case 7:
			ds = "SAB";
			break;

		}

		return ds;
	}

	public static Date dataBrSimplesToDate(String sData) {
		// a data entra no formato DD/MM/YYYY

		Calendar cal = Calendar.getInstance();

		try {

			String dia = sData.substring(0, 2);
			String mes = sData.substring(3, 5);

			String ano = "";

			if (sData.length() == 10) {
				ano = sData.substring(6, 10);
			} else if (sData.length() == 8) {
				ano = "20" + sData.substring(6, 8);
			}

			cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(dia));
			cal.set(Calendar.MONTH, Integer.valueOf(mes) - 1);
			cal.set(Calendar.YEAR, Integer.valueOf(ano));
			cal.set(Calendar.HOUR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 1);
		} catch (NullPointerException | StringIndexOutOfBoundsException e) {
			return null;
		}

		return cal.getTime();

	}

	public String getDataBrSimples(Date d) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(d);
	}

	public String getDataBrCompleta(Date d) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return formato.format(d);
	}

	public static String getCodigo(Date d) {
		Format formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
		String s = formatter.format(d);
		return s;
	}
}
