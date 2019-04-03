package br.usp.ime.util;

import java.util.Comparator;

import br.usp.ime.ganimedes.model.Grupo;

public class OrdenadorGruposPorNome implements Comparator<Grupo> {

		public int compare(Grupo g1, Grupo g2) {
			try {
				return g1.getNome().compareTo(g2.getNome());
			} catch (Exception e) {
				return 0;
			}

		}

	}
