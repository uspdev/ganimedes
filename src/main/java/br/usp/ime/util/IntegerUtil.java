package br.usp.ime.util;

public class IntegerUtil {
	private IntegerUtil() {
	}

	public static boolean isInt(String s) {
		if (s == null || s.length() == 0) {
			return false;
		}
		
		for (int i = 0; i < s.length(); ++i) {
			if (Character.isDigit(s.charAt(i)) == false) {
				return false;
			}
		}
		
		return true;
	}
}
