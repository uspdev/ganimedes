package br.usp.ime.ganimedes.mb;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.usp.ime.ganimedes.dao.DaoAnuncio;
import br.usp.ime.ganimedes.dao.DaoUsuario;
import br.usp.ime.ganimedes.model.Anuncio;
import br.usp.ime.ganimedes.view.MessageBean;
import br.usp.ime.ganimedes.view.PageTransitionBean;
import br.usp.ime.util.OrdenadorAnuncioDataReversa;

@ManagedBean(name = "mbAluno")
@ViewScoped
public class MbAluno implements Serializable {

	private static final long serialVersionUID = 1L;

	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

	@EJB
	DaoAnuncio daoAnuncio;

	@EJB
	DaoUsuario daoUsuario;

	@Inject
	MessageBean mb;

	@Inject
	PageTransitionBean pt;

	private Anuncio anuncio = new Anuncio();

	private HtmlForm frmLista = new HtmlForm();
	private HtmlForm frmDetalhe = new HtmlForm();

	public MbAluno() {
	}


	public List<Anuncio> getAnuncios() {
		List<Anuncio> anuncios = daoAnuncio.buscarAnunciosAprovados();
		Collections.sort(anuncios, new OrdenadorAnuncioDataReversa());
		return anuncios;
	}

	public void abrirAnuncio() {
		this.getFrmDetalhe().setRendered(true);
		this.getFrmLista().setRendered(false);
	}

	public void fecharAnuncio() {
		this.getFrmDetalhe().setRendered(false);
		this.getFrmLista().setRendered(true);
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	public HtmlForm getFrmLista() {
		return frmLista;
	}

	public void setFrmLista(HtmlForm frmLista) {
		this.frmLista = frmLista;
	}

	public HtmlForm getFrmDetalhe() {
		return frmDetalhe;
	}

	public void setFrmDetalhe(HtmlForm frmDetalhe) {
		this.frmDetalhe = frmDetalhe;
	}

}