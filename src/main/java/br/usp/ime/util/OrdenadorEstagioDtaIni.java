package br.usp.ime.util;

import java.util.Comparator;

import br.usp.ime.ganimedes.model.Estagio;

public class OrdenadorEstagioDtaIni implements Comparator<Estagio> {

	@Override
	public int compare(Estagio e1, Estagio e2) {
		int compare = 0;
		try {
			return e1.getDtaini().compareTo(e2.getDtaini());
		} catch (NullPointerException e) {
			return compare;
		}
	}
}
