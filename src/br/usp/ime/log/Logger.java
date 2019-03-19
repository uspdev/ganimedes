package br.usp.ime.log;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.usp.ime.ganimedes.dao.DaoLog;
import br.usp.ime.ganimedes.model.Log;
import br.usp.ime.ganimedes.model.Usuario;

@Named(value = "logger")
@RequestScoped
public class Logger implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	Usuario usuario;

	@EJB
	DaoLog daoLog;

	@PostConstruct
	public void carregar() {

		// BUSCAR OS LOGS
		// List<Log> logs = ejbHelios.buscarLogs();

	}

	public void logar(String operacao, String status, String descricao, Usuario usuario) {
		Log log = new Log(operacao, status, descricao, usuario);
		daoLog.persist(log);
	}

}
