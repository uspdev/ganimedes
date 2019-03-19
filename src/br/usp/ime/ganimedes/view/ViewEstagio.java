package br.usp.ime.ganimedes.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlForm;

import br.usp.ime.ganimedes.model.Aluno;
import br.usp.ime.ganimedes.model.Documento;
import br.usp.ime.ganimedes.model.Empresa;
import br.usp.ime.ganimedes.model.Estagio;
import br.usp.ime.ganimedes.model.Jornada;
import br.usp.ime.ganimedes.model.Remuneracao;
import br.usp.ime.ganimedes.model.Supervisor;

public class ViewEstagio {

	private HtmlForm frmCadastro = new HtmlForm();
	private HtmlForm frmAluno = new HtmlForm();
	private HtmlForm frmCadastroDocumento = new HtmlForm();

	private HtmlForm frmLista = new HtmlForm();
	private HtmlForm frmBuscaAluno = new HtmlForm();
	private HtmlForm frmBuscaAlunoCadastro = new HtmlForm();

	private HtmlForm frmEstagio = new HtmlForm();
	private HtmlForm frmEstagios = new HtmlForm();

	private String criterio;

	private String codpes;

	private Empresa empresa = new Empresa();
	private List<Empresa> empresas = new ArrayList<Empresa>();

	private Jornada jornada = new Jornada();
	private Supervisor supervisor = new Supervisor();
	private Remuneracao remuneracao = new Remuneracao();

	private List<Estagio> estagios = new ArrayList<Estagio>();

	private Aluno aluno = new Aluno();
	private Estagio estagio = new Estagio();

	private Documento documento = new Documento();

	private List<Documento> documentos = new ArrayList<Documento>();

	List<Aluno> alunos = new ArrayList<Aluno>();

	private String[] verificacoesSelecionadas;

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

	public HtmlForm getFrmCadastro() {
		return frmCadastro;
	}

	public void setFrmCadastro(HtmlForm frmCadastro) {
		this.frmCadastro = frmCadastro;
	}

	public HtmlForm getFrmAluno() {
		return frmAluno;
	}

	public void setFrmAluno(HtmlForm frmAluno) {
		this.frmAluno = frmAluno;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public HtmlForm getFrmLista() {
		return frmLista;
	}

	public void setFrmLista(HtmlForm frmLista) {
		this.frmLista = frmLista;
	}

	public List<Estagio> getEstagios() {
		return estagios;
	}

	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
	}

	public HtmlForm getFrmBuscaAluno() {
		return frmBuscaAluno;
	}

	public void setFrmBuscaAluno(HtmlForm frmBuscaAluno) {
		this.frmBuscaAluno = frmBuscaAluno;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public HtmlForm getFrmBuscaAlunoCadastro() {
		return frmBuscaAlunoCadastro;
	}

	public void setFrmBuscaAlunoCadastro(HtmlForm frmBuscaAlunoCadastro) {
		this.frmBuscaAlunoCadastro = frmBuscaAlunoCadastro;
	}

	public HtmlForm getFrmEstagio() {
		return frmEstagio;
	}

	public void setFrmEstagio(HtmlForm frmEstagio) {
		this.frmEstagio = frmEstagio;
	}

	public String getCodpes() {
		return codpes;
	}

	public void setCodpes(String codpes) {
		this.codpes = codpes;
	}

	public HtmlForm getFrmEstagios() {
		return frmEstagios;
	}

	public void setFrmEstagios(HtmlForm frmEstagios) {
		this.frmEstagios = frmEstagios;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	public Remuneracao getRemuneracao() {
		return remuneracao;
	}

	public void setRemuneracao(Remuneracao remuneracao) {
		this.remuneracao = remuneracao;
	}

	public String[] getVerificacoesSelecionadas() {
		return verificacoesSelecionadas;
	}

	public void setVerificacoesSelecionadas(String[] verificacoesSelecionadas) {
		this.verificacoesSelecionadas = verificacoesSelecionadas;
	}

	public HtmlForm getFrmCadastroDocumento() {
		return frmCadastroDocumento;
	}

	public void setFrmCadastroDocumento(HtmlForm frmCadastroDocumento) {
		this.frmCadastroDocumento = frmCadastroDocumento;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

}
