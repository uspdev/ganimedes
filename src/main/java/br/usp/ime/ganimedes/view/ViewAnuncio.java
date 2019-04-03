package br.usp.ime.ganimedes.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlForm;

import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.primefaces.model.UploadedFile;

import br.usp.ime.ganimedes.model.Anuncio;

public class ViewAnuncio {

	private HtmlForm frmCadastro = new HtmlForm();
	private HtmlForm frmLista = new HtmlForm();
	private HtmlForm frmEdicao = new HtmlForm();
	private HtmlForm frmVisualizacao = new HtmlForm();

	private Anuncio anuncio = new Anuncio();

	private List<String> niveis;
	private List<String> cursos = new ArrayList<String>();

	private List<Anuncio> anuncios = new ArrayList<Anuncio>();

	private UploadedFile file;

	SelectOneRadio selRegimeTrabalho = new SelectOneRadio();
	SelectOneRadio selCargaHoraria = new SelectOneRadio();

	private String status = "";

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}

	public HtmlForm getFrmLista() {
		return frmLista;
	}

	public void setFrmLista(HtmlForm frmLista) {
		this.frmLista = frmLista;
	}

	public HtmlForm getFrmEdicao() {
		return frmEdicao;
	}

	public void setFrmEdicao(HtmlForm frmEdicao) {
		this.frmEdicao = frmEdicao;
	}

	public List<String> getNiveis() {
		return niveis;
	}

	public void setNiveis(List<String> niveis) {
		this.niveis = niveis;
	}

	public List<String> getCursos() {
		return cursos;
	}

	public void setCursos(List<String> cursos) {
		this.cursos = cursos;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public HtmlForm getFrmVisualizacao() {
		return frmVisualizacao;
	}

	public void setFrmVisualizacao(HtmlForm frmVisualizacao) {
		this.frmVisualizacao = frmVisualizacao;
	}

	public HtmlForm getFrmCadastro() {
		return frmCadastro;
	}

	public void setFrmCadastro(HtmlForm frmCadastro) {
		this.frmCadastro = frmCadastro;
	}

	public SelectOneRadio getSelRegimeTrabalho() {
		return selRegimeTrabalho;
	}

	public void setSelRegimeTrabalho(SelectOneRadio selRegimeTrabalho) {
		this.selRegimeTrabalho = selRegimeTrabalho;
	}

	public SelectOneRadio getSelCargaHoraria() {
		return selCargaHoraria;
	}

	public void setSelCargaHoraria(SelectOneRadio selCargaHoraria) {
		this.selCargaHoraria = selCargaHoraria;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
