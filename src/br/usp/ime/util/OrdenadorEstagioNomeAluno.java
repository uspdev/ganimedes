package br.usp.ime.util;

import java.util.Comparator;

import br.usp.ime.ganimedes.model.Estagio;

public class OrdenadorEstagioNomeAluno implements Comparator<Estagio> {

	@Override
	public int compare(Estagio e1, Estagio e2) {
		// ordem semestre, obrigatoriedade decrescente e disciplina
		// String per1 = (d1.getNumsemidl() == null ? "00" : d1.getNumsemidl() < 10 ? "0" + d1.getNumsemidl() : "" + d1.getNumsemidl());
		// String per2 = (d2.getNumsemidl() == null ? "00" : d2.getNumsemidl() < 10 ? "0" + d2.getNumsemidl() : "" + d2.getNumsemidl());

		try {
			return e1.getAluno().getNompes().compareTo(e2.getAluno().getNompes());
		} catch (NullPointerException ex) {
			return 0;
		}
	}
}
