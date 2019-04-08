package br.usp.ime.ganimedes.dao;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.usp.ime.ganimedes.model.Convenio;

@Stateless
public class DaoConvenio extends Dao<Convenio> {

	@EJB
	DaoEstagio daoConvenio;

	public DaoConvenio() {
		super.setPersistentClass(Convenio.class);
	}
}
