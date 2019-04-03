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

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import br.usp.ime.ganimedes.dao.DaoUsuario;
import br.usp.ime.ganimedes.model.Grupo;
import br.usp.ime.ganimedes.model.Papel;
import br.usp.ime.ganimedes.model.Usuario;
import br.usp.ime.ganimedes.view.MessageBean;
import br.usp.ime.util.PasswordGenerator;

@Named(value = "mbUsuarios")
@ViewScoped
public class MbUsuarios implements Serializable {
	private static final long serialVersionUID = 1L;

	Logger log = Logger.getLogger(MbUsuarios.class.getName());

	@EJB
	DaoUsuario daoUsuario;

	@Inject
	Usuario usuarioLogado;

	@Inject
	MessageBean mb;

	private HtmlForm frmList = new HtmlForm();
	private HtmlForm frmShow = new HtmlForm();
	private HtmlForm frmEdit = new HtmlForm();

	private Usuario usuario = new Usuario();

	private List<Grupo> grupos = new ArrayList<Grupo>();
	private List<Papel> papeis = new ArrayList<Papel>();

	public List<Usuario> getUsuarios() {
		return daoUsuario.getUsuarios();

	}

	public void showUser(Usuario u) {
		this.getFrmList().setRendered(false);
		this.getFrmShow().setRendered(true);
		this.usuario = u;
	}

	public void hideUser() {
		this.getFrmList().setRendered(true);
		this.getFrmShow().setRendered(false);
	}

	public void associarGrupos() {
		for (Grupo gs : this.getGrupos()) {
			if (!this.getUsuario().getGrupos().contains(gs)) {
				this.getUsuario().getGrupos().add(gs);
			}
		}

		daoUsuario.persist(this.getUsuario());

		this.getGrupos().clear();
	}

	public void desassociarGrupo(Grupo g) {
		this.getUsuario().getGrupos().remove(g);
		daoUsuario.persist(this.getUsuario());
	}

	public void associarPapeis() {
		for (Papel ps : this.getPapeis()) {
			if (!this.getUsuario().getPapeis().contains(ps)) {
				this.getUsuario().getPapeis().add(ps);
			}
		}

		daoUsuario.persist(this.getUsuario());

		this.getPapeis().clear();
	}

	public void desassociarPapel(Papel p) {
		this.getUsuario().getPapeis().remove(p);
		daoUsuario.persist(this.getUsuario());
	}

	public void delUser(Usuario u) {
		daoUsuario.delete(u);
		this.getFrmList().setRendered(true);
		this.getFrmShow().setRendered(false);
	}

	public void salvar() {
		daoUsuario.persist(this.getUsuario());
		mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
		this.usuario = new Usuario();
	}

	public void atualizar() {
		daoUsuario.persist(this.getUsuario());

		mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
		this.showUser(this.getUsuario());

	}

	public void criarUsuario() {
		Usuario u = this.getUsuario();
		String senhaGerada = PasswordGenerator.generatePassword();
		u.setSalt(Base64.encodeBase64String(PasswordGenerator.generateSalt()));
		u.setSenha(DigestUtils.sha256Hex(senhaGerada + u.getSalt()));
		daoUsuario.persist(u);
		mb.addCustomMessage("Usuário cadastrado: " + usuario.getCodpes() + " Senha: " + senhaGerada, "main", FacesMessage.SEVERITY_INFO);
		this.usuario = new Usuario();
	}

	public void changePassword(Usuario u) {
		String senhaGerada = PasswordGenerator.generatePassword();
		u.setSalt(Base64.encodeBase64String(PasswordGenerator.generateSalt()));
		u.setSenha(DigestUtils.sha256Hex(senhaGerada + u.getSalt()));
		daoUsuario.persist(u);
		mb.addCustomMessage("Nova senha cadastrada. Usuário: " + usuario.getCodpes() + " Senha: " + senhaGerada, "main", FacesMessage.SEVERITY_INFO);
		this.usuario = new Usuario();
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

}