package br.usp.ime.util;

import java.util.Comparator;

import br.usp.ime.ganimedes.model.Anuncio;

public class OrdenadorAnuncioDataReversa implements Comparator<Anuncio> {

	@Override
	public int compare(Anuncio a1, Anuncio a2) {
		return a2.getDtaCriacao().compareTo(a1.getDtaCriacao());
	}
}
