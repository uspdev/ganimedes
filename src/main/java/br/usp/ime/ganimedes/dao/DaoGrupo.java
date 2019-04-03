package br.usp.ime.ganimedes.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.usp.ime.ganimedes.model.Grupo;
import br.usp.ime.util.OrdenadorGruposPorNome;

@Stateless
public class DaoGrupo extends Dao<Grupo> {

	public DaoGrupo() {
		super.setPersistentClass(Grupo.class);
	}

	public List<Grupo> getGrupos() {
		List<Grupo> grupos = super.findAll();
		Collections.sort(grupos, new OrdenadorGruposPorNome());
		return grupos;

	}

	public Grupo buscarGrupo(String nome) {
		Query query = em.createQuery("SELECT G FROM Grupo G WHERE nome = :nome");
		query.setParameter("nome", nome);
		Grupo grupo = (Grupo) query.getSingleResult();
		return grupo;
	}

}
