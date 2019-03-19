/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.usp.model.replicado;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author marcelo
 */
@Embeddable
public class HistescolargrPK implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@Column(name = "codpes", nullable = false)
	private int codpes;
	@Basic(optional = false)
	@Column(name = "codpgm", nullable = false)
	private short codpgm;
	@Basic(optional = false)
	@Column(name = "coddis", nullable = false, length = 7)
	private String coddis;
	@Basic(optional = false)
	@Column(name = "verdis", nullable = false)
	private short verdis;
	@Basic(optional = false)
	@Column(name = "codtur", nullable = false, length = 7)
	private String codtur;

	public HistescolargrPK() {
	}

	public HistescolargrPK(int codpes, short codpgm, String coddis, short verdis, String codtur) {
		this.codpes = codpes;
		this.codpgm = codpgm;
		this.coddis = coddis;
		this.verdis = verdis;
		this.codtur = codtur;
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

	public String getCoddis() {
		return coddis;
	}

	public void setCoddis(String coddis) {
		this.coddis = coddis;
	}

	public short getVerdis() {
		return verdis;
	}

	public void setVerdis(short verdis) {
		this.verdis = verdis;
	}

	public String getCodtur() {
		return codtur;
	}

	public void setCodtur(String codtur) {
		this.codtur = codtur;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) codpes;
		hash += (int) codpgm;
		hash += (coddis != null ? coddis.hashCode() : 0);
		hash += (int) verdis;
		hash += (codtur != null ? codtur.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof HistescolargrPK)) {
			return false;
		}
		HistescolargrPK other = (HistescolargrPK) object;
		if (this.codpes != other.codpes) {
			return false;
		}
		if (this.codpgm != other.codpgm) {
			return false;
		}
		if ((this.coddis == null && other.coddis != null) || (this.coddis != null && !this.coddis.equals(other.coddis))) {
			return false;
		}
		if (this.verdis != other.verdis) {
			return false;
		}
		if ((this.codtur == null && other.codtur != null) || (this.codtur != null && !this.codtur.equals(other.codtur))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.usp.poli.replicado.HistescolargrPK[codpes=" + codpes + ", codpgm=" + codpgm + ", coddis=" + coddis + ", verdis=" + verdis + ", codtur="
				+ codtur + "]";
	}

}
