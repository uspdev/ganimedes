package br.usp.ime.util;

import java.util.Comparator;

import br.usp.ime.ganimedes.model.Documento;

public class OrdenadorDocumentoNomeAluno implements Comparator<Documento> {

	@Override
	public int compare(Documento d1, Documento d2) {
		return d1.getEstagio().getAluno().getNompes().compareTo(d2.getEstagio().getAluno().getNompes());
	}
}
