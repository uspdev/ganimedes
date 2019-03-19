package br.usp.ime.util;

import java.util.Comparator;

import br.usp.ime.ganimedes.model.Empresa;

public class OrdenadorEmpresaNome implements Comparator<Empresa> {

	@Override
	public int compare(Empresa e1, Empresa e2) {

		return e1.getNome().compareTo(e2.getNome());
	}
}
