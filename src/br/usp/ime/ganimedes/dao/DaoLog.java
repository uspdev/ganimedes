package br.usp.ime.ganimedes.dao;

import javax.ejb.Stateless;

import br.usp.ime.ganimedes.model.Log;

@Stateless
public class DaoLog extends Dao<Log> {

	public DaoLog() {
		super.setPersistentClass(Log.class);
	}

}
