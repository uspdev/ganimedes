package br.usp.model.replicado;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ProgramagrPK implements Serializable {
	@Basic(optional = false)
	@NotNull
	@Column(name = "codpes")
	private int codpes;
	@Basic(optional = false)
	@NotNull
	@Column(name = "codpgm")
	private short codpgm;

	public ProgramagrPK() {
	}

	public ProgramagrPK(int codpes, short codpgm) {
		this.codpes = codpes;
		this.codpgm = codpgm;
	}

	public int getCodpes() {
		return codpes;
	}

	public void setCodpes(int codpes) {
		this.codpes = codpes;
	}

	public short getCodpgm() {
		return codpgm;
	}

	public void setCodpgm(short codpgm) {
		this.codpgm = codpgm;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) codpes;
		hash += (int) codpgm;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ProgramagrPK)) {
			return false;
		}
		ProgramagrPK other = (ProgramagrPK) object;
		if (this.codpes != other.codpes) {
			return false;
		}
		if (this.codpgm != other.codpgm) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.usp.model.replicado.ProgramagrPK[ codpes=" + codpes + ", codpgm=" + codpgm + " ]";
	}

}
