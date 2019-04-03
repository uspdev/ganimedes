package br.usp.ime.ganimedes.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.usp.ime.ganimedes.ejb.GanimedesInterface;
import br.usp.ime.ganimedes.model.Contato;
import br.usp.ime.ganimedes.model.Convenio;
import br.usp.ime.ganimedes.model.Empresa;
import br.usp.ime.ganimedes.model.Usuario;
import br.usp.ime.ganimedes.view.MessageBean;
import br.usp.ime.ganimedes.view.PageTransitionBean;
import br.usp.ime.ganimedes.view.ViewOperador;

@ManagedBean(name = "mbOperador")
@ViewScoped
public class MbOperador implements Serializable {
	private static final long serialVersionUID = 1L;

	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();

	@Inject
	Usuario usuarioLogado;

	Logger log = Logger.getLogger(MbOperador.class.getName());

	@EJB
	GanimedesInterface ejb;

	private ViewOperador tela = new ViewOperador();

	PageTransitionBean pt = new PageTransitionBean();

	private MessageBean mb = new MessageBean();

	public ViewOperador getTela() {
		return tela;
	}

	public void setTela(ViewOperador tela) {
		this.tela = tela;
	}

	public void buscarEmpresas() {

		List<Empresa> empresas = ejb.buscarEmpresas();

		if (empresas.isEmpty()) {
			mb.addMessage("semreg", "main", FacesMessage.SEVERITY_INFO);
		} else {
			this.tela.setEmpresas(empresas);
			this.tela.getFrmEdicaoContatosEmpresa().setRendered(false);
			this.tela.getFrmEdicaoConveniosEmpresa().setRendered(false);
			this.tela.getFrmInclusao().setRendered(false);
		}

	}

	public void salvarEmpresa() {
		Empresa e = this.tela.getEmpresa();

		for (Contato c : e.getContatos()) {
			c.setEmpresa(e);
		}

		for (Convenio c : e.getConvenios()) {
			c.setEmpresa(e);
		}

		if (ejb.salvar(e) != null) {
			mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
			this.tela.getFrmInclusao().setRendered(false);
			this.tela.getFrmEdicaoContatosEmpresa().setRendered(false);
			this.tela.getFrmEdicaoConveniosEmpresa().setRendered(false);
			tela = new ViewOperador();
		} else {
			mb.addMessage("erro_cadastro_empresa", "main", FacesMessage.SEVERITY_ERROR);
		}

	}

	public void atualizarEmpresa(Empresa e) {
		ejb.salvar(e);
	}

	public void deletarEmpresa(Empresa e) {
		ejb.deletar(e);
		this.tela.getEmpresas().remove(e);
		mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
	}

	public void deletarContato(Contato c) {
		ejb.deletarContato(c);
		this.tela.getEmpresa().getContatos().remove(c);
	}

	public void deletarConvenio(Convenio c) {
		ejb.deletarConvenio(c);
		this.tela.getEmpresa().getConvenios().remove(c);
	}

}