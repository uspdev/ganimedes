package br.usp.ime.ganimedes.dao;

import javax.ejb.Stateless;

import br.usp.ime.ganimedes.model.Aluno;

@Stateless
public class DaoAluno extends Dao<Aluno> {

	public DaoAluno() {
		super.setPersistentClass(Aluno.class);
	}

}
