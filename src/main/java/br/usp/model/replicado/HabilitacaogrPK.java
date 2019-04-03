/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.usp.model.replicado;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class HabilitacaogrPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "codcur", nullable = false)
	private int codcur;
	@Basic(optional = false)
	@Column(name = "codhab", nullable = false)
	private short codhab;

	public HabilitacaogrPK() {
	}

	public HabilitacaogrPK(int codcur, short codhab) {
		this.codcur = codcur;
		this.codhab = codhab;
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) codcur;
		hash += (int) codhab;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof HabilitacaogrPK)) {
			return false;
		}
		HabilitacaogrPK other = (HabilitacaogrPK) object;
		if (this.codcur != other.codcur) {
			return false;
		}
		if (this.codhab != other.codhab) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.usp.poli.replicado.HabilitacaogrPK[codcur=" + codcur
				+ ", codhab=" + codhab + "]";
	}

}
