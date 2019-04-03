package br.usp.ime.util;

import java.util.Comparator;

import br.usp.ime.ganimedes.model.Mensagem;

public class OrdenadorMensagem implements Comparator<Mensagem> {

	@Override
	public int compare(Mensagem m1, Mensagem m2) {
		return m2.getCriacao().compareTo(m1.getCriacao());
	}
}
