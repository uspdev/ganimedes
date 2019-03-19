package br.usp.ime.util;

import java.util.Comparator;

import br.usp.ime.ganimedes.model.Remuneracao;

public class OrdenadorRemuneracaoData implements Comparator<Remuneracao> {

	@Override
	public int compare(Remuneracao r1, Remuneracao r2) {
		return r1.getDtaini().compareTo(r2.getDtaini());
	}
}
