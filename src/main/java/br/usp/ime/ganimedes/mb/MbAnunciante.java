package br.usp.ime.ganimedes.mb;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import br.usp.ime.ganimedes.dao.DaoMensagem;
import br.usp.ime.ganimedes.dao.DaoPapel;
import br.usp.ime.ganimedes.dao.DaoToken;
import br.usp.ime.ganimedes.dao.DaoUsuario;
import br.usp.ime.ganimedes.mail.MessageFactory;
import br.usp.ime.ganimedes.model.Mensagem;
import br.usp.ime.ganimedes.model.Papel;
import br.usp.ime.ganimedes.model.Token;
import br.usp.ime.ganimedes.model.Usuario;
import br.usp.ime.ganimedes.view.MessageBean;
import br.usp.ime.ganimedes.view.PageTransitionBean;
import br.usp.ime.util.Recursos;

@ManagedBean(name = "mbAnunciante")
@ViewScoped
public class MbAnunciante implements Serializable {

	private static final long serialVersionUID = 1L;

	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();


	@EJB
	DaoUsuario daoUsuario;

	@EJB
	DaoMensagem daoMensagem;

	@EJB
	DaoPapel daoPapel;

	@EJB
	DaoToken daoToken;

	@Inject
	MessageBean mb;

	@Inject
	PageTransitionBean pt;

	public MbAnunciante() {
		// TODO Auto-generated constructor stub
	}

	private Usuario usuario = new Usuario();
	private String codlog;
	private String codlogConfirmacao;
	private String senhaNova;
	private String senhaAtual;

	private boolean edicao = false;

	private HtmlForm formularioPagina = new HtmlForm();
	private HtmlForm formularioEdicao = new HtmlForm();
	private HtmlForm formularioListagem = new HtmlForm();
	private HtmlForm formularioInformacoes = new HtmlForm();

	private static final Random random = new SecureRandom();

	private HtmlForm frmCadastro = new HtmlForm();
	private HtmlForm frmConfirmacao = new HtmlForm();

	public void criarAnunciante() {

		if (daoUsuario.buscarUsuarioPorCPF(this.getUsuario().getCpf()) != null) {
			mb.addCustomMessage("Este CPF já está cadastrado no sistema.", "main", FacesMessage.SEVERITY_ERROR);
			return;
		}

		if (daoUsuario.buscarUsuarioPrimeiroAcesso(this.getUsuario().getCodlog()) != null
				|| daoToken.buscarTokenPorCodlog(this.getUsuario().getCodlog()) != null) {
			mb.addCustomMessage("Este e-mail já está em uso.", "main", FacesMessage.SEVERITY_ERROR);
			return;
		}

		if (!this.getUsuario().getCodlog().equals(this.getCodlogConfirmacao())) {
			mb.addCustomMessage("Emails não conferem.", "main", FacesMessage.SEVERITY_ERROR);
			return;
		}

		if (this.getSenhaNova().length() < 8) {
			mb.addCustomMessage("A senha deve ter no mínimo 8 caracteres.", "main", FacesMessage.SEVERITY_ERROR);
			return;
		}

		usuario.setDataCadastro(new Date());
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		usuario.setSalt(Base64.encodeBase64String(salt));
		usuario.setSenha(DigestUtils.sha256Hex(this.getSenhaNova() + this.usuario.getSalt()));
		usuario.setAtivado(false);


		Papel papel = daoPapel.buscarPapel("ANUNCIANTE");
		usuario.getPapeis().add(papel);

		usuario = (Usuario) daoUsuario.persist(usuario);

		if (usuario == null) {
			mb.addMessage("erro_cadastro_anunciante", "main", FacesMessage.SEVERITY_ERROR);
			return;
		}

		Token token = new Token(usuario);
		token = (Token) daoToken.persist(token);
		if (token == null) {
			mb.addMessage("erro_cadastro_token", "main", FacesMessage.SEVERITY_ERROR);
			return;
		}

		// cria a mensagem para ser enviada

		MessageFactory mf = new MessageFactory();

		String msg = mf.getMsgNovoToken(token);

		Recursos recursos = new Recursos();

		String de = recursos.getResourceValue("email");
		String appName = recursos.getResourceValue("email");

		Mensagem m = new Mensagem();
		m.setCriacao(new Date());
		m.setAssunto("[" + appName + " ] CADASTRO REALIZADO");
		m.setDe(de);
		m.setMensagem(msg);
		m.setPara(token.getCodlog() + ", " + de);

		daoMensagem.persist(m);

		if (daoUsuario.persist(usuario) != null) {
			this.getFrmCadastro().setRendered(false);
			this.getFrmConfirmacao().setRendered(true);
		} else {
			mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
		}

	}

	public void editarAnunciante(Usuario usuario) {
		this.getFormularioPagina().setRendered(false);
		this.getFormularioEdicao().setRendered(true);
		this.setEdicao(true);
		this.usuario = usuario;
	}

	public String getOu() {
		Recursos recursos = new Recursos();
		return recursos.getResourceValue("unidade-organizacional");
	}

	public String getInstituicao() {
		Recursos recursos = new Recursos();
		return recursos.getResourceValue("instituicao");
	}

	public String getWebsite() {
		Recursos recursos = new Recursos();
		return recursos.getResourceValue("website");
	}

	public String getFone() {
		Recursos recursos = new Recursos();
		return recursos.getResourceValue("fone");
	}

	public String getEmail() {
		Recursos recursos = new Recursos();
		return recursos.getResourceValue("email");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCodlog() {
		return codlog;
	}

	public void setCodlog(String codlog) {
		this.codlog = codlog;
	}

	public String getCodlogConfirmacao() {
		return codlogConfirmacao;
	}

	public void setCodlogConfirmacao(String codlogConfirmacao) {
		this.codlogConfirmacao = codlogConfirmacao;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public boolean isEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}

	public HtmlForm getFormularioPagina() {
		return formularioPagina;
	}

	public void setFormularioPagina(HtmlForm formularioPagina) {
		this.formularioPagina = formularioPagina;
	}

	public HtmlForm getFormularioEdicao() {
		return formularioEdicao;
	}

	public void setFormularioEdicao(HtmlForm formularioEdicao) {
		this.formularioEdicao = formularioEdicao;
	}

	public HtmlForm getFormularioListagem() {
		return formularioListagem;
	}

	public void setFormularioListagem(HtmlForm formularioListagem) {
		this.formularioListagem = formularioListagem;
	}

	public HtmlForm getFormularioInformacoes() {
		return formularioInformacoes;
	}

	public void setFormularioInformacoes(HtmlForm formularioInformacoes) {
		this.formularioInformacoes = formularioInformacoes;
	}

	public HtmlForm getFrmCadastro() {
		return frmCadastro;
	}

	public void setFrmCadastro(HtmlForm frmCadastro) {
		this.frmCadastro = frmCadastro;
	}

	public HtmlForm getFrmConfirmacao() {
		return frmConfirmacao;
	}

	public void setFrmConfirmacao(HtmlForm frmConfirmacao) {
		this.frmConfirmacao = frmConfirmacao;
	}

}
