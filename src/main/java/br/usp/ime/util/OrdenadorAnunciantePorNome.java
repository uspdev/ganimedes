package br.usp.ime.util;

import java.util.Comparator;

import br.usp.ime.ganimedes.model.Anunciante;

public class OrdenadorAnunciantePorNome implements Comparator<Anunciante> {

	@Override
	public int compare(Anunciante a1, Anunciante a2) {
		return a1.getNome().compareTo(a2.getNome());
	}
}
