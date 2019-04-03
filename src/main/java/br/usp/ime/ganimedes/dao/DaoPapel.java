package br.usp.ime.ganimedes.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.usp.ime.ganimedes.model.Papel;
import br.usp.ime.util.OrdenadorPapelPorNome;

@Stateless
public class DaoPapel extends Dao<Papel> {

	public DaoPapel() {
		super.setPersistentClass(Papel.class);
	}

	public List<Papel> getPapeis() {
		List<Papel> papeis = super.findAll();
		Collections.sort(papeis, new OrdenadorPapelPorNome());
		return papeis;
	}

	public Papel buscarPapel(String nome) {
		Query query = em.createQuery("SELECT P FROM Papel P WHERE P.nome = :nome ORDER BY P.nome");
		query.setParameter("nome", nome);

		List<Papel> papeis = query.getResultList();

		if (papeis.isEmpty()) {
			return null;
		}
		return papeis.get(0);
	}

}
