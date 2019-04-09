package br.usp.ime.ganimedes.mb;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.usp.ime.ganimedes.dao.DaoUsuario;
import br.usp.ime.ganimedes.model.Usuario;

@ManagedBean(name = "mbConfiguracao")
@ViewScoped
public class MbConfiguracao implements Serializable {
	private static final long serialVersionUID = 1L;

	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();

	@Inject
	Usuario usuarioLogado;

	@EJB
	DaoUsuario daoUsuario;

	Logger log = Logger.getLogger(MbConfiguracao.class.getName());

	public boolean existeUsuarioAdm() {
		return daoUsuario.existeUsuarioAdm();
	}

}