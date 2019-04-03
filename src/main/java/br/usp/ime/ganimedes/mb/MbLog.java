package br.usp.ime.ganimedes.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.usp.ime.ganimedes.dao.DaoLog;
import br.usp.ime.ganimedes.dao.DaoMensagem;
import br.usp.ime.ganimedes.model.Log;
import br.usp.ime.ganimedes.model.Usuario;
import br.usp.ime.ganimedes.view.MessageBean;
import br.usp.ime.ganimedes.view.PageTransitionBean;
import br.usp.ime.util.OrdenadorLog;

@ManagedBean(name = "mbLog")
@ViewScoped
public class MbLog implements Serializable {
	private static final long serialVersionUID = 1L;

	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();

	Logger log = Logger.getLogger(MbLog.class.getName());

	@EJB
	DaoMensagem daoMensagem;

	@EJB
	DaoLog daoLog;

	@Inject
	Usuario usuarioLogado;

	@Inject
	PageTransitionBean pt;

	@Inject
	MessageBean mb;

	List<Log> logs = new ArrayList<Log>();

	public void carregar() {
		this.logs = daoLog.findAll();
		Collections.sort(logs, new OrdenadorLog());
	}

	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}

}