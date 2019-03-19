package br.usp.ime.util;

import java.util.Comparator;

import br.usp.ime.ganimedes.model.Jornada;

public class OrdenadorJornadaData implements Comparator<Jornada> {

	@Override
	public int compare(Jornada j1, Jornada j2) {
		return j1.getDtaini().compareTo(j2.getDtaini());
	}
}
