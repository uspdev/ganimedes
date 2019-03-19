package br.usp.model.replicado;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "HABILITACAOGR", catalog = "replicado", schema = "dbo")
public class Habilitacaogr implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected HabilitacaogrPK habilitacaogrPK;
	@Basic(optional = false)
	@Column(name = "dtaatvhab", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaatvhab;
	@Column(name = "dtadtvhab")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtadtvhab;
	@Column(name = "nomhab", length = 200)
	private String nomhab;
	@Basic(optional = false)
	@Column(name = "tiphab", nullable = false)
	private char tiphab;
	@Column(name = "perhab", length = 10)
	private String perhab;
	@Lob
	@Column(name = "objhab")
	private String objhab;
	@Column(name = "numsemopc")
	private Integer numsemopc;
	@Column(name = "numcreopc")
	private Integer numcreopc;
	@Basic(optional = false)
	@Column(name = "numvaghab", nullable = false)
	private short numvaghab;
	@Column(name = "numvaghabcpl")
	private Short numvaghabcpl;
	@Column(name = "numvaghabcvn")
	private Short numvaghabcvn;
	@Column(name = "titccd", length = 255)
	private String titccd;
	//@Lob
	@Column(name = "timestamp")
	private byte[] timestamp;
	@Column(name = "staaltcrl")
	private Character staaltcrl;
	@Column(name = "tipper")
	private Character tipper;

	// @Column(name = "stautlfuv")
	// private Character stautlfuv;
	@Column(name = "staofemst")
	private Character staofemst;
	@Column(name = "titccdfem", length = 255)
	private String titccdfem;

	public Habilitacaogr() {
	}

	public Habilitacaogr(HabilitacaogrPK habilitacaogrPK) {
		this.habilitacaogrPK = habilitacaogrPK;
	}

	public Habilitacaogr(HabilitacaogrPK habilitacaogrPK, Date dtaatvhab, char tiphab, short numvaghab) {
		this.habilitacaogrPK = habilitacaogrPK;
		this.dtaatvhab = dtaatvhab;
		this.tiphab = tiphab;
		this.numvaghab = numvaghab;
	}

	public Habilitacaogr(int codcur, short codhab) {
		this.habilitacaogrPK = new HabilitacaogrPK(codcur, codhab);
	}

	public HabilitacaogrPK getHabilitacaogrPK() {
		return habilitacaogrPK;
	}

	public void setHabilitacaogrPK(HabilitacaogrPK habilitacaogrPK) {
		this.habilitacaogrPK = habilitacaogrPK;
	}

	public Date getDtaatvhab() {
		return dtaatvhab;
	}

	public void setDtaatvhab(Date dtaatvhab) {
		this.dtaatvhab = dtaatvhab;
	}

	public Date getDtadtvhab() {
		return dtadtvhab;
	}

	public void setDtadtvhab(Date dtadtvhab) {
		this.dtadtvhab = dtadtvhab;
	}

	public String getNomhab() {
		return nomhab;
	}

	public void setNomhab(String nomhab) {
		this.nomhab = nomhab;
	}

	public char getTiphab() {
		return tiphab;
	}

	public void setTiphab(char tiphab) {
		this.tiphab = tiphab;
	}

	public String getPerhab() {
		return perhab;
	}

	public void setPerhab(String perhab) {
		this.perhab = perhab;
	}

	public String getObjhab() {
		return objhab;
	}

	public void setObjhab(String objhab) {
		this.objhab = objhab;
	}

	public Integer getNumsemopc() {
		return numsemopc;
	}

	public void setNumsemopc(Integer numsemopc) {
		this.numsemopc = numsemopc;
	}

	public Integer getNumcreopc() {
		return numcreopc;
	}

	public void setNumcreopc(Integer numcreopc) {
		this.numcreopc = numcreopc;
	}

	public short getNumvaghab() {
		return numvaghab;
	}

	public void setNumvaghab(short numvaghab) {
		this.numvaghab = numvaghab;
	}

	public Short getNumvaghabcpl() {
		return numvaghabcpl;
	}

	public void setNumvaghabcpl(Short numvaghabcpl) {
		this.numvaghabcpl = numvaghabcpl;
	}

	public Short getNumvaghabcvn() {
		return numvaghabcvn;
	}

	public void setNumvaghabcvn(Short numvaghabcvn) {
		this.numvaghabcvn = numvaghabcvn;
	}

	public String getTitccd() {
		return titccd;
	}

	public void setTitccd(String titccd) {
		this.titccd = titccd;
	}

	public byte[] getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(byte[] timestamp) {
		this.timestamp = timestamp;
	}

	public Character getStaaltcrl() {
		return staaltcrl;
	}

	public void setStaaltcrl(Character staaltcrl) {
		this.staaltcrl = staaltcrl;
	}

	public Character getTipper() {
		return tipper;
	}

	public void setTipper(Character tipper) {
		this.tipper = tipper;
	}

	public Character getStaofemst() {
		return staofemst;
	}

	public void setStaofemst(Character staofemst) {
		this.staofemst = staofemst;
	}

	public String getTitccdfem() {
		return titccdfem;
	}

	public void setTitccdfem(String titccdfem) {
		this.titccdfem = titccdfem;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (habilitacaogrPK != null ? habilitacaogrPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Habilitacaogr)) {
			return false;
		}
		Habilitacaogr other = (Habilitacaogr) object;
		if ((this.habilitacaogrPK == null && other.habilitacaogrPK != null)
				|| (this.habilitacaogrPK != null && !this.habilitacaogrPK.equals(other.habilitacaogrPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.usp.poli.replicado.Habilitacaogr[habilitacaogrPK=" + habilitacaogrPK + "]";
	}

}
