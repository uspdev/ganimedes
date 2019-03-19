package br.usp.ime.util;

import java.util.Comparator;

import br.usp.ime.ganimedes.model.Supervisor;

public class OrdenadorSupervisorData implements Comparator<Supervisor> {

	@Override
	public int compare(Supervisor s1, Supervisor s2) {
		return s1.getDtaini().compareTo(s2.getDtaini());
	}
}
