package br.usp.model.replicado;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PROGRAMAGR", catalog = "replicado", schema = "dbo")
public class Programagr implements Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected ProgramagrPK programagrPK;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "tiping")
	private String tiping;
	@Size(max = 40)
	@Column(name = "tipencpgm")
	private String tipencpgm;
	@Column(name = "codpestrf")
	private Integer codpestrf;
	@Column(name = "codpgmtrf")
	private Short codpgmtrf;
	@Column(name = "dtaing")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaing;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 2)
	@Column(name = "stapgm")
	private String stapgm;
	@Column(name = "stalinptg")
	private Character stalinptg;
	@Column(name = "clsing")
	private Short clsing;
	@Column(name = "dtamaxccl")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtamaxccl;
	@Column(name = "dtaini")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaini;
	@Column(name = "clsfim")
	private Short clsfim;
	@Size(max = 2)
	@Column(name = "nomturing")
	private String nomturing;
	@Size(max = 2)
	@Column(name = "vinpgm")
	private String vinpgm;
	@Column(name = "numsemidl")
	private Short numsemidl;
	@Column(name = "codcvn")
	private Integer codcvn;
	@Column(name = "codpestut")
	private Integer codpestut;
	@Column(name = "numoptpmt")
	private Short numoptpmt;
	// @Lob
	@Column(name = "timestamp")
	private byte[] timestamp;
	@Column(name = "numopcing")
	private Short numopcing;
	@Column(name = "numecrpmt")
	private Short numecrpmt;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "notingifm")
	private BigDecimal notingifm;
	@Column(name = "ptoingifm")
	private BigDecimal ptoingifm;
	@Column(name = "numoptpmtlre")
	private Short numoptpmtlre;
	@Column(name = "codclgcad")
	private Short codclgcad;
	@Size(max = 12)
	@Column(name = "sglclgcad")
	private String sglclgcad;
	@Size(max = 50)
	@Column(name = "rpaqtnfuv")
	private String rpaqtnfuv;
	@Column(name = "staincusp")
	private Character staincusp;
	@Column(name = "codpascvn")
	private Short codpascvn;

	public Programagr() {
	}

	public Programagr(ProgramagrPK programagrPK) {
		this.programagrPK = programagrPK;
	}

	public Programagr(ProgramagrPK programagrPK, String tiping, String stapgm) {
		this.programagrPK = programagrPK;
		this.tiping = tiping;
		this.stapgm = stapgm;
	}

	public Programagr(int codpes, short codpgm) {
		this.programagrPK = new ProgramagrPK(codpes, codpgm);
	}

	public ProgramagrPK getProgramagrPK() {
		return programagrPK;
	}

	public void setProgramagrPK(ProgramagrPK programagrPK) {
		this.programagrPK = programagrPK;
	}

	public String getTiping() {
		return tiping;
	}

	public void setTiping(String tiping) {
		this.tiping = tiping;
	}

	public String getTipencpgm() {
		return tipencpgm;
	}

	public void setTipencpgm(String tipencpgm) {
		this.tipencpgm = tipencpgm;
	}

	public Integer getCodpestrf() {
		return codpestrf;
	}

	public void setCodpestrf(Integer codpestrf) {
		this.codpestrf = codpestrf;
	}

	public Short getCodpgmtrf() {
		return codpgmtrf;
	}

	public void setCodpgmtrf(Short codpgmtrf) {
		this.codpgmtrf = codpgmtrf;
	}

	public Date getDtaing() {
		return dtaing;
	}

	public void setDtaing(Date dtaing) {
		this.dtaing = dtaing;
	}

	public String getStapgm() {
		return stapgm;
	}

	public void setStapgm(String stapgm) {
		this.stapgm = stapgm;
	}

	public Character getStalinptg() {
		return stalinptg;
	}

	public void setStalinptg(Character stalinptg) {
		this.stalinptg = stalinptg;
	}

	public Short getClsing() {
		return clsing;
	}

	public void setClsing(Short clsing) {
		this.clsing = clsing;
	}

	public Date getDtamaxccl() {
		return dtamaxccl;
	}

	public void setDtamaxccl(Date dtamaxccl) {
		this.dtamaxccl = dtamaxccl;
	}

	public Date getDtaini() {
		return dtaini;
	}

	public void setDtaini(Date dtaini) {
		this.dtaini = dtaini;
	}

	public Short getClsfim() {
		return clsfim;
	}

	public void setClsfim(Short clsfim) {
		this.clsfim = clsfim;
	}

	public String getNomturing() {
		return nomturing;
	}

	public void setNomturing(String nomturing) {
		this.nomturing = nomturing;
	}

	public String getVinpgm() {
		return vinpgm;
	}

	public void setVinpgm(String vinpgm) {
		this.vinpgm = vinpgm;
	}

	public Short getNumsemidl() {
		return numsemidl;
	}

	public void setNumsemidl(Short numsemidl) {
		this.numsemidl = numsemidl;
	}

	public Integer getCodcvn() {
		return codcvn;
	}

	public void setCodcvn(Integer codcvn) {
		this.codcvn = codcvn;
	}

	public Integer getCodpestut() {
		return codpestut;
	}

	public void setCodpestut(Integer codpestut) {
		this.codpestut = codpestut;
	}

	public Short getNumoptpmt() {
		return numoptpmt;
	}

	public void setNumoptpmt(Short numoptpmt) {
		this.numoptpmt = numoptpmt;
	}

	public byte[] getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(byte[] timestamp) {
		this.timestamp = timestamp;
	}

	public Short getNumopcing() {
		return numopcing;
	}

	public void setNumopcing(Short numopcing) {
		this.numopcing = numopcing;
	}

	public Short getNumecrpmt() {
		return numecrpmt;
	}

	public void setNumecrpmt(Short numecrpmt) {
		this.numecrpmt = numecrpmt;
	}

	public BigDecimal getNotingifm() {
		return notingifm;
	}

	public void setNotingifm(BigDecimal notingifm) {
		this.notingifm = notingifm;
	}

	public BigDecimal getPtoingifm() {
		return ptoingifm;
	}

	public void setPtoingifm(BigDecimal ptoingifm) {
		this.ptoingifm = ptoingifm;
	}

	public Short getNumoptpmtlre() {
		return numoptpmtlre;
	}

	public void setNumoptpmtlre(Short numoptpmtlre) {
		this.numoptpmtlre = numoptpmtlre;
	}

	public Short getCodclgcad() {
		return codclgcad;
	}

	public void setCodclgcad(Short codclgcad) {
		this.codclgcad = codclgcad;
	}

	public String getSglclgcad() {
		return sglclgcad;
	}

	public void setSglclgcad(String sglclgcad) {
		this.sglclgcad = sglclgcad;
	}

	public String getRpaqtnfuv() {
		return rpaqtnfuv;
	}

	public void setRpaqtnfuv(String rpaqtnfuv) {
		this.rpaqtnfuv = rpaqtnfuv;
	}

	public Character getStaincusp() {
		return staincusp;
	}

	public void setStaincusp(Character staincusp) {
		this.staincusp = staincusp;
	}

	public Short getCodpascvn() {
		return codpascvn;
	}

	public void setCodpascvn(Short codpascvn) {
		this.codpascvn = codpascvn;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (programagrPK != null ? programagrPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Programagr)) {
			return false;
		}
		Programagr other = (Programagr) object;
		if ((this.programagrPK == null && other.programagrPK != null) || (this.programagrPK != null && !this.programagrPK.equals(other.programagrPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.usp.model.replicado.Programagr[ programagrPK=" + programagrPK + " ]";
	}

}
