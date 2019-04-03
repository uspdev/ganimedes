package br.usp.ime.util;


import java.util.Comparator;

import br.usp.ime.ganimedes.model.Papel;

public class OrdenadorPapelPorNome implements Comparator<Papel> {

	public int compare(Papel p1, Papel p2) {
		try {
			return p1.getNome().compareTo(p2.getNome());
		} catch (Exception e) {
			return 0;
		}

	}

}
