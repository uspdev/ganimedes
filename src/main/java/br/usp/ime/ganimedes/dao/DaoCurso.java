package br.usp.ime.ganimedes.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.usp.ime.ganimedes.model.Curso;

@Stateless
public class DaoCurso extends Dao<Curso> {

	public DaoCurso() {
		super.setPersistentClass(Curso.class);
	}

	public List<Curso> buscarCursos() {
		List<Curso> cursos = new ArrayList<Curso>();

		String q = "SELECT C FROM Curso C ORDER BY C.nivel, C.nome";
		Query query = em.createQuery(q);

		cursos = query.getResultList();

		return cursos;

	}

}
