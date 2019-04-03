package br.usp.ime.util;

import java.util.Comparator;

import br.usp.ime.ganimedes.model.Aluno;

public class OrdenadorAlunoNome implements Comparator<Aluno> {

	@Override
	public int compare(Aluno a1, Aluno a2) {
		return a1.getNompes().compareTo(a2.getNompes());
	}
}
