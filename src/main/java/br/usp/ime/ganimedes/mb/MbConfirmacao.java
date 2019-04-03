package br.usp.ime.ganimedes.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usp.ime.ganimedes.dao.DaoToken;
import br.usp.ime.ganimedes.dao.DaoUsuario;
import br.usp.ime.ganimedes.ejb.GanimedesInterface;
import br.usp.ime.ganimedes.model.Token;
import br.usp.ime.ganimedes.model.Usuario;
import br.usp.ime.ganimedes.view.MessageBean;

@ManagedBean(name = "mbConfirmacao")
@RequestScoped
public class MbConfirmacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{param.token}")
	private String sToken;

	@EJB
	GanimedesInterface ejb;

	@EJB
	DaoToken daoToken;

	@EJB
	DaoUsuario daoUsuario;

	@Inject
	MessageBean mb;

	private Usuario usuario;
	private String codlog;

	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
	HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

	private HtmlForm frmConfirmacao = new HtmlForm();
	private HtmlForm frmLinkInvalido = new HtmlForm();

	public MbConfirmacao() {

	}

	public void confirmarEmail() {
		Token token = daoToken.buscarToken(sToken);

		if (token == null) {
			this.getFrmLinkInvalido().setRendered(true);
			return;
		}
		token.getUsuario().setCodlog(token.getCodlog());

		usuario = daoUsuario.findById(token.getUsuario().getId());
		usuario.setAtivado(true);
		daoUsuario.persist(usuario);

		daoToken.delete(token);

		this.getFrmConfirmacao().setRendered(true);

	}

	public void reenviarEmail() {
		Usuario usuario = daoUsuario.buscarUsuario(this.getCodlog());
		if (usuario != null && usuario.isAtivado()) {
			mb.addMessage("cjc", "main", FacesMessage.SEVERITY_INFO);
			return;
		}
		Token token = daoToken.buscarTokenPorCodlog(this.getCodlog());
		if (token == null) {
			mb.addMessage("me", "main", FacesMessage.SEVERITY_ERROR);
			return;
		}
		mb.addMessage("me", "main", FacesMessage.SEVERITY_INFO);
	}

	public String getCodlog() {
		return codlog;
	}

	public void setCodlog(String codlog) {
		this.codlog = codlog;
	}

	public String getsToken() {
		return sToken;
	}

	public void setsToken(String sToken) {
		this.sToken = sToken;
	}

	public HtmlForm getFrmConfirmacao() {
		return frmConfirmacao;
	}

	public void setFrmConfirmacao(HtmlForm frmConfirmacao) {
		this.frmConfirmacao = frmConfirmacao;
	}

	public HtmlForm getFrmLinkInvalido() {
		return frmLinkInvalido;
	}

	public void setFrmLinkInvalido(HtmlForm frmLinkInvalido) {
		this.frmLinkInvalido = frmLinkInvalido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
