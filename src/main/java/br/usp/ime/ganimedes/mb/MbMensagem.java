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

import org.primefaces.context.RequestContext;

import br.usp.ime.ganimedes.dao.DaoMensagem;
import br.usp.ime.ganimedes.model.Mensagem;
import br.usp.ime.ganimedes.model.Usuario;
import br.usp.ime.ganimedes.view.MessageBean;
import br.usp.ime.ganimedes.view.PageTransitionBean;
import br.usp.ime.util.OrdenadorMensagem;

@ManagedBean(name = "mbMensagem")
@ViewScoped
public class MbMensagem implements Serializable {
	private static final long serialVersionUID = 1L;

	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();

	Logger log = Logger.getLogger(MbMensagem.class.getName());

	@EJB
	DaoMensagem daoMensagem;

	@Inject
	Usuario usuarioLogado;

	@Inject
	PageTransitionBean pt;

	@Inject
	MessageBean mb;

	public void show(Mensagem m) {
		mensagem = m;

		// somente para PF >= 6.2
		// PrimeFaces current = PrimeFaces.current();
		// current.executeScript("PF('dlgSelect').show();");

		// somente para PF >+ 4.x ate < 6.2
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgMensagem').show();");
	}

	List<Mensagem> mensagens = new ArrayList<Mensagem>();
	private Mensagem mensagem = new Mensagem();

	public void carregar() {
		this.mensagens = daoMensagem.findAll();
		Collections.sort(mensagens, new OrdenadorMensagem());
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

}