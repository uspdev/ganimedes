package br.usp.ime.ganimedes.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlForm;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.usp.ime.ganimedes.dao.DaoPapel;
import br.usp.ime.ganimedes.dao.DaoUsuario;
import br.usp.ime.ganimedes.model.Papel;
import br.usp.ime.ganimedes.model.Usuario;
import br.usp.ime.ganimedes.view.MessageBean;

@Named(value = "mbPapel")
@ViewScoped
public class MbPapel implements Serializable {
	private static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger(MbPapel.class.getName());

	@EJB
	DaoPapel daoPapel;

	@EJB
	DaoUsuario daoUsuario;

	@Inject
	Usuario usuarioLogado;

	@Inject
	MessageBean mb;

	private Papel papel = new Papel();

	private HtmlForm frmList = new HtmlForm();
	private HtmlForm frmShow = new HtmlForm();
	private HtmlForm frmEdit = new HtmlForm();

	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private List<Usuario> usuariosSelecionados = new ArrayList<Usuario>();

	public List<Papel> getPapeis() {
		return daoPapel.getPapeis();

	}

	public void mostrarPapel(Papel p) {
		this.getFrmList().setRendered(false);
		this.getFrmShow().setRendered(true);
		this.papel = p;
	}

	public void salvar() {
		if (null != daoPapel.persist(this.getPapel())) {
			mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
			this.papel = new Papel();
		} else {
			mb.addMessage("oprerr", "main", FacesMessage.SEVERITY_ERROR);
		}

	}

	public void atualizar() {

		if (null != daoPapel.update(this.getPapel())) {
			mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
			this.mostrarPapel(this.getPapel());
		} else {
			mb.addMessage("oprerr", "main", FacesMessage.SEVERITY_ERROR);
		}

	}

	public void deletar(Papel p) {
		daoPapel.delete(p);
		this.getPapeis().remove(p);
		mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
	}

	public void associarUsuariosAPapel() {
		for (Usuario u : this.getUsuarios()) {
			if (!u.getPapeis().contains(this.getPapel())) {
				u.getPapeis().add(this.getPapel());
				this.getPapel().getUsuarios().add(u);
			}
			if (null != daoUsuario.persist(u)) {
				mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);

			} else {
				mb.addMessage("oprerr", "main", FacesMessage.SEVERITY_ERROR);
			}

		}
	}

	public void desassociarUsuariosAPapel() {

		if (usuariosSelecionados.isEmpty()) {
			mb.addMessage("nis", "show", FacesMessage.SEVERITY_ERROR);
			return;
		}

		for (Usuario usuario : usuariosSelecionados) {
			this.getPapel().getUsuarios().remove(usuario);
			usuario.getPapeis().remove(this.getPapel());
			daoUsuario.update(usuario);
		}

	}

	// GETTERS AND SETTERS
	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
	}

	public HtmlForm getFrmList() {
		return frmList;
	}

	public void setFrmList(HtmlForm frmList) {
		this.frmList = frmList;
	}

	public HtmlForm getFrmShow() {
		return frmShow;
	}

	public void setFrmShow(HtmlForm frmShow) {
		this.frmShow = frmShow;
	}

	public HtmlForm getFrmEdit() {
		return frmEdit;
	}

	public void setFrmEdit(HtmlForm frmEdit) {
		this.frmEdit = frmEdit;
	}

	public List<Usuario> getUsuariosSelecionados() {
		return usuariosSelecionados;
	}

	public void setUsuariosSelecionados(List<Usuario> usuariosSelecionados) {
		this.usuariosSelecionados = usuariosSelecionados;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}