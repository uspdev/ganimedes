package br.usp.ime.ganimedes.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.usp.ime.ganimedes.model.Curso;
import br.usp.ime.ganimedes.model.CursoGr;
import br.usp.ime.ganimedes.model.Estagio;

public class ViewRelatorio {

	private Date dtaini;
	private Date dtafim;
	private Date dta;
	private String sAno;

	private String txtStatus;

	private List<Curso> cursos = new ArrayList<Curso>();

	private List<CursoGr> cursosGraduacao = new ArrayList<CursoGr>();

	private Estagio estagio = new Estagio();

	public Date getDtaini() {
		return dtaini;
	}

	public void setDtaini(Date dtaini) {
		this.dtaini = dtaini;
	}

	public Date getDtafim() {
		return dtafim;
	}

	public void setDtafim(Date dtafim) {
		this.dtafim = dtafim;
	}

	public String getTxtStatus() {
		return txtStatus;
	}

	public void setTxtStatus(String txtStatus) {
		this.txtStatus = txtStatus;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public String getsAno() {
		return sAno;
	}

	public void setsAno(String sAno) {
		this.sAno = sAno;
	}

	public Date getDta() {
		return dta;
	}

	public void setDta(Date dta) {
		this.dta = dta;
	}

	public List<CursoGr> getCursosGraduacao() {
		return cursosGraduacao;
	}

	public void setCursosGraduacao(List<CursoGr> cursosGraduacao) {
		this.cursosGraduacao = cursosGraduacao;
	}

	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

}
