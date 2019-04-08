package br.usp.ime.ganimedes.dao;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.usp.ime.ganimedes.model.Contato;

@Stateless
public class DaoContato extends Dao<Contato> {

	@EJB
	DaoEstagio daoContato;

	public DaoContato() {
		super.setPersistentClass(Contato.class);
	}
}
