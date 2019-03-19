package br.usp.ime.ganimedes.view;

import javax.faces.component.html.HtmlForm;

public class ViewConfirmacaoEmail {

	private HtmlForm frmConfirmacao = new HtmlForm();
	private HtmlForm frmLinkInvalido = new HtmlForm();

	public HtmlForm getFrmConfirmacao() {
		return frmConfirmacao;
	}

	public void setFrmConfirmacao(HtmlForm frmConfirmacao) {
		this.frmConfirmacao = frmConfirmacao;
	}

	public HtmlForm getFrmLinkInvalido() {
		return frmLinkInvalido;
	}

	public void setFrmLinkInvalido(HtmlForm frmLinkInvalido) {
		this.frmLinkInvalido = frmLinkInvalido;
	}

}
