package br.usp.ime.util;

import java.util.Comparator;

import br.usp.ime.ganimedes.model.Log;

public class OrdenadorLog implements Comparator<Log> {

	@Override
	public int compare(Log t1, Log t2) {
		return t2.getTimestamp().compareTo(t1.getTimestamp());
	}
}
