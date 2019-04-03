/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.model.replicado;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fabio
 */
@Embeddable
public class NomecursoPK implements Serializable {
	@Basic(optional = false)
	@NotNull
	@Column(name = "codcur")
	private int codcur;
	@Basic(optional = false)
	@NotNull
	@Column(name = "dtainicur")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtainicur;

	public NomecursoPK() {
	}

	public NomecursoPK(int codcur, Date dtainicur) {
		this.codcur = codcur;
		this.dtainicur = dtainicur;
	}

	public int getCodcur() {
		return codcur;
	}

	public void setCodcur(int codcur) {
		this.codcur = codcur;
	}

	public Date getDtainicur() {
		return dtainicur;
	}

	public void setDtainicur(Date dtainicur) {
		this.dtainicur = dtainicur;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) codcur;
		hash += (dtainicur != null ? dtainicur.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof NomecursoPK)) {
			return false;
		}
		NomecursoPK other = (NomecursoPK) object;
		if (this.codcur != other.codcur) {
			return false;
		}
		if ((this.dtainicur == null && other.dtainicur != null) || (this.dtainicur != null && !this.dtainicur.equals(other.dtainicur))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.usp.poli.model.apl08.NomecursoPK[ codcur=" + codcur + ", dtainicur=" + dtainicur + " ]";
	}

}
