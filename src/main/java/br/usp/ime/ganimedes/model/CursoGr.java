package br.usp.ime.ganimedes.model;

import java.io.Serializable;
import java.util.Date;

/*
 * @autor marcelom
 * Essa classe é utilizada para designar os cursos de graduação 
 * que vem do replicado
 * 
 * A classe curso deste mesmo pacote é mais genérica e atende apenas
 * aos formularios de empresas que cadastram vagas
 * 
 * 
 */

public class CursoGr implements Serializable {

	private static final long serialVersionUID = 1L;
	private int codcur;
	private short codhab;
	private String nomcur;
	private String nomhab;
	private String nomcurso;
	private Date dtainivin; // data do ingresso do aluno

	public CursoGr() {
		super();
	}

	public CursoGr(int codcur, short codhab, String nomcur, String nomhab) {
		super();
		this.codcur = codcur;
		this.codhab = codhab;
		this.nomcur = nomcur.trim();
		this.nomhab = nomhab.trim();
		this.nomcurso = this.nomcur.equalsIgnoreCase(this.nomhab) ? this.nomhab : this.nomcur + " - " + this.nomhab;
	}

	public int getCodcur() {
		return codcur;
	}

	public void setCodcur(int codcur) {
		this.codcur = codcur;
	}

	public short getCodhab() {
		return codhab;
	}

	public void setCodhab(short codhab) {
		this.codhab = codhab;
	}

	public String getNomcurso() {
		return nomcurso;
	}

	public void setNomcurso(String nomcurso) {
		this.nomcurso = nomcurso;
	}

	public Date getDtainivin() {
		return dtainivin;
	}

	public void setDtainivin(Date dtainivin) {
		this.dtainivin = dtainivin;
	}

	public String getNomcur() {
		return nomcur;
	}

	public void setNomcur(String nomcur) {
		this.nomcur = nomcur;
	}

	public String getNomhab() {
		return nomhab;
	}

	public void setNomhab(String nomhab) {
		this.nomhab = nomhab;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codcur;
		result = prime * result + codhab;
		result = prime * result + ((dtainivin == null) ? 0 : dtainivin.hashCode());
		result = prime * result + ((nomcur == null) ? 0 : nomcur.hashCode());
		result = prime * result + ((nomcurso == null) ? 0 : nomcurso.hashCode());
		result = prime * result + ((nomhab == null) ? 0 : nomhab.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CursoGr other = (CursoGr) obj;
		if (codcur != other.codcur)
			return false;
		if (codhab != other.codhab)
			return false;
		if (dtainivin == null) {
			if (other.dtainivin != null)
				return false;
		} else if (!dtainivin.equals(other.dtainivin))
			return false;
		if (nomcur == null) {
			if (other.nomcur != null)
				return false;
		} else if (!nomcur.equals(other.nomcur))
			return false;
		if (nomcurso == null) {
			if (other.nomcurso != null)
				return false;
		} else if (!nomcurso.equals(other.nomcurso))
			return false;
		if (nomhab == null) {
			if (other.nomhab != null)
				return false;
		} else if (!nomhab.equals(other.nomhab))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CursoGr [codcur=" + codcur + ", codhab=" + codhab + ", nomcur=" + nomcur + ", nomhab=" + nomhab + ", nomcurso=" + nomcurso + ", dtainivin=" + dtainivin + "]";
	}

}