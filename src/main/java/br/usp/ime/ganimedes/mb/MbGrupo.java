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

import br.usp.ime.ganimedes.dao.DaoGrupo;
import br.usp.ime.ganimedes.dao.DaoUsuario;
import br.usp.ime.ganimedes.model.Grupo;
import br.usp.ime.ganimedes.model.Usuario;
import br.usp.ime.ganimedes.view.MessageBean;

@Named(value = "mbGrupo")
@ViewScoped
public class MbGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger(MbGrupo.class.getName());

	@EJB
	DaoGrupo daoGrupo;

	@EJB
	DaoUsuario daoUsuario;

	@Inject
	Usuario usuarioLogado;

	@Inject
	MessageBean mb;

	@Inject
	MbUsuario mbUsuario;

	private Grupo grupo = new Grupo();
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	private List<Usuario> usuariosSelecionados = new ArrayList<Usuario>();

	private HtmlForm frmList = new HtmlForm();
	private HtmlForm frmShow = new HtmlForm();
	private HtmlForm frmEdit = new HtmlForm();

	public List<Grupo> getGrupos() {
		return daoGrupo.getGrupos();

	}

	public void salvar() {
		daoGrupo.persist(this.getGrupo());
		mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);

		this.grupo = new Grupo();
	}

	public void deletar() {

		if (grupo.getUsuarios().isEmpty()) {

			if (daoGrupo.delete(grupo)) {
				this.getGrupos().remove(grupo);
				mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
				this.getFrmShow().setRendered(false);
				this.getFrmList().setRendered(true);
			} else {
				mb.addMessage("oprerr", "main", FacesMessage.SEVERITY_ERROR);
			}
		} else {
			mb.addMessage("grupo_contem_membro", "main", FacesMessage.SEVERITY_ERROR);
		}
	}

	public void mostrarGrupo(Grupo g) {
		this.getFrmList().setRendered(false);
		this.getFrmShow().setRendered(true);
		this.grupo = g;
	}

	public void mostrarUsuario(Usuario u) {
		mbUsuario.mostrarUsuario(u);
	}

	public void associarUsuariosAGrupo() {
		for (Usuario u : this.getUsuarios()) {
			if (!u.getGrupos().contains(this.getGrupo())) {
				u.getGrupos().add(this.getGrupo());
				this.getGrupo().getUsuarios().add(u);
			}
			if (null != daoUsuario.persist(u)) {
				mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);

			} else {
				mb.addMessage("oprerr", "main", FacesMessage.SEVERITY_ERROR);
			}

		}
	}

	public void desassociarUsuariosAGrupo() {

		if (usuariosSelecionados.isEmpty()) {
			mb.addMessage("nis", "show", FacesMessage.SEVERITY_ERROR);
			return;
		}

		for (Usuario usuario : usuariosSelecionados) {
			this.getGrupo().getUsuarios().remove(usuario);
			usuario.getGrupos().remove(this.getGrupo());
			daoUsuario.update(usuario);
		}

	}

	public void atualizar() {

		if (null != daoGrupo.update(this.getGrupo())) {
			mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
			this.mostrarGrupo(this.getGrupo());
		} else {
			mb.addMessage("oprerr", "main", FacesMessage.SEVERITY_ERROR);
		}

	}

	public HtmlForm getFrmList() {
		return frmList;
	}

	public void setFrmList(HtmlForm frmList) {
		this.frmList = frmList;
	}

	public HtmlForm getFrmEdit() {
		return frmEdit;
	}

	public void setFrmEdit(HtmlForm frmEdit) {
		this.frmEdit = frmEdit;
	}

	public HtmlForm getFrmShow() {
		return frmShow;
	}

	public void setFrmShow(HtmlForm frmShow) {
		this.frmShow = frmShow;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuariosSelecionados() {
		return usuariosSelecionados;
	}

	public void setUsuariosSelecionados(List<Usuario> usuariosSelecionados) {
		this.usuariosSelecionados = usuariosSelecionados;
	}

}