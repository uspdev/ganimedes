package br.usp.ime.ganimedes.mb;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import br.usp.ime.ganimedes.dao.DaoEsqueciMinhaSenha;
import br.usp.ime.ganimedes.dao.DaoMensagem;
import br.usp.ime.ganimedes.dao.DaoUsuario;
import br.usp.ime.ganimedes.mail.MessageFactory;
import br.usp.ime.ganimedes.model.EsqueciMinhaSenha;
import br.usp.ime.ganimedes.model.Mensagem;
import br.usp.ime.ganimedes.model.Usuario;
import br.usp.ime.ganimedes.view.MessageBean;
import br.usp.ime.util.DataUtility;
import br.usp.ime.util.PasswordGenerator;
import br.usp.ime.util.Recursos;

@ManagedBean(name = "mbEsqueciMinhaSenha")
@RequestScoped
public class MbEsqueciMinhaSenha implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{param.codigo}")
	private String codigoURL;

	@EJB
	DaoEsqueciMinhaSenha daoEsqueci;

	@EJB
	DaoUsuario daoUsuario;

	@EJB
	DaoMensagem daoMensagem;

	@Inject
	MessageBean mb;

	private String codlog;
	private String senhaNova;
	private String confirmacaoSenha;

	private static final Random random = new SecureRandom();

	public void gerarPedido(String codlog) {
		EsqueciMinhaSenha esqueciMinhaSenha = daoEsqueci.buscarEsqueciMinhaSenha(codlog);

		if (daoEsqueci.buscarEsqueciMinhaSenha(codlog) != null) {
			mb.addMessage("mer", "main", FacesMessage.SEVERITY_INFO);
			return;
		}

		esqueciMinhaSenha = new EsqueciMinhaSenha();
		esqueciMinhaSenha.setUsuario(daoUsuario.buscarUsuario(codlog));

		if (esqueciMinhaSenha.getUsuario() == null) {
			mb.addMessage("enecsc", "main", FacesMessage.SEVERITY_ERROR);
			return;
		}

		if (!esqueciMinhaSenha.getUsuario().isAtivado()) {
			mb.addMessage("una", "main", FacesMessage.SEVERITY_ERROR);
			return;
		}

		String codigoURL = new BigInteger(130, random).toString(32);
		esqueciMinhaSenha.setCodigoURL(codigoURL);

		Calendar validade = Calendar.getInstance();
		validade.add(Calendar.DATE, 7);
		esqueciMinhaSenha.setValidade(DataUtility.calendarToDate(validade));

		esqueciMinhaSenha = (EsqueciMinhaSenha) daoEsqueci.persist(esqueciMinhaSenha);

		// cria a mensagem para ser enviada
		MessageFactory mf = new MessageFactory();
		String msg = mf.getMsgRecuperarSenha(esqueciMinhaSenha.getCodigoURL());

		Recursos recursos = new Recursos();
		String de = recursos.getResourceValue("email");
		String appName = recursos.getResourceValue("email");

		Mensagem m = new Mensagem();
		m.setCriacao(new Date());
		m.setAssunto("[" + appName + " ] RECUPERAÇÃO DE SENHA");
		m.setDe(de);
		m.setMensagem(msg);
		m.setPara(esqueciMinhaSenha.getUsuario().getCodlog() + ", " + de);

		if (daoMensagem.persist(m) != null) {
			mb.addMessage("me", "main", FacesMessage.SEVERITY_INFO);
			this.setCodlog("");
		}
	}

	public boolean pedidoValido() {
		EsqueciMinhaSenha esqueciMinhaSenha = daoEsqueci.buscarEsqueciMinhaSenhaPorCodigoURL(this.getCodigoURL());
	
		if (esqueciMinhaSenha == null) {
			return false;
		} else {
			return true;
		}
	}

	public void salvarNovaSenha(String codigoURL) {
		if (!this.getSenhaNova().equals("")) {
			if (this.getSenhaNova().length() < 8) {
				mb.addCustomMessage("A nova senha deve ter no mínimo 8 caracteres.", "main", FacesMessage.SEVERITY_ERROR);
				return;
			}
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			Usuario usuario = daoEsqueci.buscarEsqueciMinhaSenhaPorCodigoURL(codigoURL).getUsuario();
			
			usuario.setSalt(Base64.encodeBase64String(PasswordGenerator.generateSalt()));
			usuario.setSenha(DigestUtils.sha256Hex(this.getSenhaNova() + usuario.getSalt()));

			daoUsuario.persist(usuario);
			mb.addCustomMessage("Senha alterada com sucesso.", "main", FacesMessage.SEVERITY_INFO);
			daoEsqueci.removerEsqueciMinhaSenha(usuario);
		} else {
			mb.addCustomMessage("Erro.", "main", FacesMessage.SEVERITY_ERROR);
		}
	}

	public String getCodigoURL() {
		return codigoURL;
	}

	public void setCodigoURL(String codigoURL) {
		this.codigoURL = codigoURL;
	}

	public String getCodlog() {
		return codlog;
	}

	public void setCodlog(String codlog) {
		this.codlog = codlog;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
}
