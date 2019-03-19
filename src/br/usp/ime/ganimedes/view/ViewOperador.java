package br.usp.ime.ganimedes.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlForm;

import br.usp.ime.ganimedes.model.Contato;
import br.usp.ime.ganimedes.model.Empresa;

public class ViewOperador {
	private HtmlForm frmEdicaoConveniosEmpresa = new HtmlForm();
	private HtmlForm frmEdicaoContatosEmpresa = new HtmlForm();

	private HtmlForm frmInclusao = new HtmlForm();

	private String criterio;

	private List<Empresa> empresas = new ArrayList<Empresa>();
	private List<Contato> contatos = new ArrayList<Contato>();

	Contato contato = new Contato();
	Empresa empresa = new Empresa();

	public HtmlForm getFrmInclusao() {
		return frmInclusao;
	}

	public void setFrmInclusao(HtmlForm frmInclusao) {
		this.frmInclusao = frmInclusao;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public HtmlForm getFrmEdicaoConveniosEmpresa() {
		return frmEdicaoConveniosEmpresa;
	}

	public void setFrmEdicaoConveniosEmpresa(HtmlForm frmEdicaoConveniosEmpresa) {
		this.frmEdicaoConveniosEmpresa = frmEdicaoConveniosEmpresa;
	}

	public HtmlForm getFrmEdicaoContatosEmpresa() {
		return frmEdicaoContatosEmpresa;
	}

	public void setFrmEdicaoContatosEmpresa(HtmlForm frmEdicaoContatosEmpresa) {
		this.frmEdicaoContatosEmpresa = frmEdicaoContatosEmpresa;
	}

}
