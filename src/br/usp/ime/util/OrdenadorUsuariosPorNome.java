package br.usp.ime.util;

import java.util.Comparator;

import br.usp.ime.ganimedes.model.Usuario;

public class OrdenadorUsuariosPorNome implements Comparator<Usuario> {

	public int compare(Usuario u1, Usuario u2) {
		try {
			return u2.getNompes().compareTo(u2.getNompes());
		} catch (Exception e) {
			return 0;
		}

	}

}
