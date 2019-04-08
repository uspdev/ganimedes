package br.usp.ime.ganimedes.mb;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;

import br.usp.ime.ganimedes.dao.DaoContato;
import br.usp.ime.ganimedes.dao.DaoConvenio;
import br.usp.ime.ganimedes.dao.DaoEmpresa;
import br.usp.ime.ganimedes.dao.DaoUsuario;
import br.usp.ime.ganimedes.model.Contato;
import br.usp.ime.ganimedes.model.Convenio;
import br.usp.ime.ganimedes.model.Empresa;
import br.usp.ime.ganimedes.model.Usuario;
import br.usp.ime.ganimedes.view.MessageBean;
import br.usp.ime.ganimedes.view.PageTransitionBean;

@ManagedBean(name = "mbConvenio")
@ViewScoped
public class MbConvenio implements Serializable {
	private static final long serialVersionUID = 1L;

	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();

	Logger log = Logger.getLogger(MbConvenio.class.getName());

	@Inject
	Usuario usuarioLogado;

	@EJB
	DaoUsuario daoUsuario;

	@EJB
	DaoEmpresa daoEmpresa;

	@EJB
	DaoConvenio daoConvenio;

	@EJB
	DaoContato daoContato;

	@Inject
	MessageBean mb;

	@Inject
	PageTransitionBean pt;

	private Contato contato = new Contato();
	private Convenio convenio = new Convenio();
	private Empresa empresa = new Empresa();

	public void carregarEmpresa() {
		List<Empresa> empresas = daoEmpresa.buscarEmpresaPorCnpj(empresa.getCnpj());

		if (empresas.isEmpty()) {
			mb.addMessage("semreg", "main", FacesMessage.SEVERITY_INFO);
		} else {
			empresa = empresas.get(0);
		}

	}

	public void mostrarInclusaoEmpresa() {
		empresa = new Empresa();
		// somente para PF >= 6.2
		// PrimeFaces current = PrimeFaces.current();
		// current.executeScript("PF('dlgSelect').show();");

		// somente para PF >+ 4.x ate < 6.2
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgAddEmpresa').show();");

	}

	public void mostrarEdicaoConvenio(Convenio convenio) {
		this.convenio = convenio;
		// somente para PF >= 6.2
		// PrimeFaces current = PrimeFaces.current();
		// current.executeScript("PF('dlgSelect').show();");

		// somente para PF >+ 4.x ate < 6.2
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgEditConvenio').show();");

	}

	public void atualizarConvenio() {
		daoConvenio.persist(convenio);
	}

	public void mostrarEdicaoContato(Contato contato) {
		this.contato = contato;
		// somente para PF >= 6.2
		// PrimeFaces current = PrimeFaces.current();
		// current.executeScript("PF('dlgSelect').show();");

		// somente para PF >+ 4.x ate < 6.2
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgEditContato').show();");

	}

	public void atualizarContato() {
		daoContato.persist(contato);
	}

	public void criarEmpresa() {
		empresa = (Empresa) daoEmpresa.persist(empresa);
		if (empresa == null) {
			mb.addMessage("oprerr", "main", FacesMessage.SEVERITY_ERROR);
		} else {
			mb.addMessage("cadok", "main", FacesMessage.SEVERITY_INFO);
		}
	}

	public void salvarEmpresa() {
		for (Contato c : empresa.getContatos()) {
			c.setEmpresa(empresa);
		}

		for (Convenio c : empresa.getConvenios()) {
			c.setEmpresa(empresa);
		}

		if (daoEmpresa.persist(empresa) != null) {
			mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
		} else {
			mb.addMessage("erro_cadastro_empresa", "main", FacesMessage.SEVERITY_ERROR);
		}
	}

	public void atualizarEmpresa(Empresa e) {
		daoEmpresa.persist(e);
	}

	public void adicionarContato() {
		empresa.getContatos().add(contato);
		contato.setEmpresa(empresa);
		daoContato.persist(contato);
	}

	public void adicionarConvenio() {
		empresa.getConvenios().add(convenio);
		convenio.setEmpresa(empresa);
		daoConvenio.persist(convenio);
	}

	public void deletarEmpresa(Empresa e) {
		daoEmpresa.delete(empresa);
	}

	public void deletarContato(Contato c) {
		daoContato.delete(c);
		empresa.getContatos().remove(c);
	}

	public void deletarConvenio(Convenio c) {
		daoConvenio.delete(c);
		empresa.getConvenios().remove(c);
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
