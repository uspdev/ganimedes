package br.usp.ime.ganimedes.mail;

import com.itextpdf.text.html.HtmlEncoder;

import br.usp.ime.ganimedes.model.Estagio;
import br.usp.ime.ganimedes.model.Token;
import br.usp.ime.util.Recursos;
import br.usp.ime.util.URLUtils;

public class MessageFactory {

	private String getCabecalho() {
		String msg = "<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>";
		msg = msg + "<html lang='pt-br' dir='ltr'>";

		msg = msg + "<head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>";
		msg = msg + "</head><body class='f-default' >";

		return msg;
	}

	public static String getMsgNovoToken(Token token) {
		Recursos recursos = new Recursos();
		String ou = HtmlEncoder.encode(recursos.getResourceValue("unidade-organizacional"));
		String instituicao = HtmlEncoder.encode(recursos.getResourceValue("instituicao"));
		String website = HtmlEncoder.encode(recursos.getResourceValue("website"));
		String fone = recursos.getResourceValue("fone");

		String url = URLUtils.getURL() + "/empresa/confirmacao.xhtml?token=" + token.getToken();

		String msg = "";
		msg += "<p><strong>Confirma&ccedil;&atilde;o de cadastro para acesso ao Sistema Ganimedes</strong>";
		msg += "<p>Para confirmar o seu cadastro, basta acessar o link:";
		msg += "<br><a href=" + url + ">" + url + "</a></p>";
		msg += "<br><br>Em caso de d&uacute;vida ou caso encontre algum problema para acessar o sistema, entre em contato conosco.";
		msg += "<br><br>---";
		msg += "<br>" + ou;
		msg += "<br>" + instituicao;
		msg += "<br><a href=" + website + ">" + website + "</a>";
		msg += "<br>" + fone;
		msg += "<br><br><em>Mensagem enviada pelo Sistema Ganimedes</em>";

		return msg;
	}

	public static String getMsgRetiradaDocumento(Estagio estagio) {
		Recursos recursos = new Recursos();
		String ou = HtmlEncoder.encode(recursos.getResourceValue("unidade-organizacional"));
		String instituicao = HtmlEncoder.encode(recursos.getResourceValue("instituicao"));
		String website = HtmlEncoder.encode(recursos.getResourceValue("website"));
		String fone = recursos.getResourceValue("fone");

		String msg = "";

		msg += "<p>Informamos que os documentos referentes ao est&aacute;gio abaixo est&atilde;o prontos para retirada</p>";
		msg += "<p>EST&Aacute;GIO</p>";
		msg += "<p>Aluno: " + HtmlEncoder.encode(estagio.getAluno().getNompes()) + "  " + estagio.getAluno().getCodpes();
		msg += "<br>Empresa: " + HtmlEncoder.encode(estagio.getEmpresa().getNome()) + "  " + estagio.getEmpresa().getCnpj();
		msg += "<p>INFORMA&Ccedil;&Otilde;ES PARA RETIRADA:";
		msg += "<br>Hor&aacute;rio de atendimento: 10 as 13 horas e das 17 as 20 horas em dias &uacute;teis";
		msg += "<br>Local: Se&ccedil;&atilde;o de Est&aacute;gios, Bloco B do IME-USP, sala 12";

		msg += "<br><br>---";
		msg += "<br>" + ou;
		msg += "<br>" + instituicao;
		msg += "<br><a href=" + website + ">" + website + "</a>";
		msg += "<br>" + fone;

		msg += "<br><br><em>Mensagem enviada pelo Sistema Ganimedes</em>";
		return msg;
	}

	public String getMsgRecuperarSenha(String codigo) {
		Recursos recursos = new Recursos();
		String ou = HtmlEncoder.encode(recursos.getResourceValue("unidade-organizacional"));
		String instituicao = HtmlEncoder.encode(recursos.getResourceValue("instituicao"));
		String website = HtmlEncoder.encode(recursos.getResourceValue("website"));
		String fone = recursos.getResourceValue("fone");

		String url = URLUtils.getURL() + "/empresa/nova_senha.xhtml?codigo=" + codigo;

		String msg = "";
		msg += "<p><strong>Recupera&ccedil;&atilde;o de senha para acesso ao Sistema Ganimedes</strong>";
		msg += "<p>Para recuperar a senha de acesso, basta acessar o link:";
		msg += "<br><a href=" + url + ">" + url + "</a></p>";
		msg += "<br><br>Em caso de d&uacute;vida ou caso encontre algum problema para acessar o sistema, entre em contato conosco.";
		msg += "<br><br>---";
		msg += "<br>" + ou;
		msg += "<br>" + instituicao;
		msg += "<br><a href=" + website + ">" + website + "</a>";
		msg += "<br>" + fone;

		msg += "<br><br><em>Mensagem enviada pelo Sistema Ganimedes</em>";
		return msg;

	}
}
