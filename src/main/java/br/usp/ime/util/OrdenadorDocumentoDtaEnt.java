package br.usp.ime.util;

import java.util.Comparator;

import br.usp.ime.ganimedes.model.Documento;

public class OrdenadorDocumentoDtaEnt implements Comparator<Documento> {

	@Override
	public int compare(Documento d1, Documento d2) {
		try {
			return d1.getDtaent().compareTo(d2.getDtaent());
		} catch (Exception e) {
			return 0;
		}
	}
}
