/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.usp.model.replicado;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "HISTESCOLARGR", catalog = "replicado", schema = "dbo")
public class Histescolargr implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HistescolargrPK histescolargrPK;
    @Basic(optional = false)
    @Column(name = "dtacrihst", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtacrihst;
    @Column(name = "notfim", precision = 4, scale = 2)
    private BigDecimal notfim;
    @Column(name = "notfim2", precision = 4, scale = 2)
    private BigDecimal notfim2;
    @Column(name = "frqfim", precision = 5, scale = 2)
    private BigDecimal frqfim;
    @Column(name = "rstfim", length = 2)
    private String rstfim;
    @Column(name = "dtavalfim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtavalfim;
    @Column(name = "dtavalfim2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtavalfim2;
    @Basic(optional = false)
    @Column(name = "stamtr", nullable = false)
    private char stamtr;
    @Column(name = "discrl")
    private Character discrl;
    @Column(name = "primtralu")
    private Short primtralu;
    @Column(name = "stacrihstesc")
    private Character stacrihstesc;
    @Column(name = "dtaultalt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaultalt;
    @Column(name = "codpesalt")
    private Integer codpesalt;
  //  @Lob
    @Column(name = "timestamp")
    private byte[] timestamp;
    @Column(name = "aplori")
    private Character aplori;
    @Column(name = "numseqtur")
    private Short numseqtur;
    @Column(name = "aplorifrqnot")
    private Character aplorifrqnot;
    @Column(name = "dtlstamtr")
    private Character dtlstamtr;
    @Column(name = "primtr")
    private Short primtr;
    @Column(name = "clsesmalutur")
    private Short clsesmalutur;

    public Histescolargr() {
    }

    public Histescolargr(HistescolargrPK histescolargrPK) {
        this.histescolargrPK = histescolargrPK;
    }

    public Histescolargr(HistescolargrPK histescolargrPK, Date dtacrihst, char stamtr) {
        this.histescolargrPK = histescolargrPK;
        this.dtacrihst = dtacrihst;
        this.stamtr = stamtr;
    }

    public Histescolargr(int codpes, short codpgm, String coddis, short verdis, String codtur) {
        this.histescolargrPK = new HistescolargrPK(codpes, codpgm, coddis, verdis, codtur);
    }

    public HistescolargrPK getHistescolargrPK() {
        return histescolargrPK;
    }

    public void setHistescolargrPK(HistescolargrPK histescolargrPK) {
        this.histescolargrPK = histescolargrPK;
    }

    public Date getDtacrihst() {
        return dtacrihst;
    }

    public void setDtacrihst(Date dtacrihst) {
        this.dtacrihst = dtacrihst;
    }

    public BigDecimal getNotfim() {
        return notfim;
    }

    public void setNotfim(BigDecimal notfim) {
        this.notfim = notfim;
    }

    public BigDecimal getNotfim2() {
        return notfim2;
    }

    public void setNotfim2(BigDecimal notfim2) {
        this.notfim2 = notfim2;
    }

    public BigDecimal getFrqfim() {
        return frqfim;
    }

    public void setFrqfim(BigDecimal frqfim) {
        this.frqfim = frqfim;
    }

    public String getRstfim() {
        return rstfim;
    }

    public void setRstfim(String rstfim) {
        this.rstfim = rstfim;
    }

    public Date getDtavalfim() {
        return dtavalfim;
    }

    public void setDtavalfim(Date dtavalfim) {
        this.dtavalfim = dtavalfim;
    }

    public Date getDtavalfim2() {
        return dtavalfim2;
    }

    public void setDtavalfim2(Date dtavalfim2) {
        this.dtavalfim2 = dtavalfim2;
    }

    public char getStamtr() {
        return stamtr;
    }

    public void setStamtr(char stamtr) {
        this.stamtr = stamtr;
    }

    public Character getDiscrl() {
        return discrl;
    }

    public void setDiscrl(Character discrl) {
        this.discrl = discrl;
    }

    public Short getPrimtralu() {
        return primtralu;
    }

    public void setPrimtralu(Short primtralu) {
        this.primtralu = primtralu;
    }

    public Character getStacrihstesc() {
        return stacrihstesc;
    }

    public void setStacrihstesc(Character stacrihstesc) {
        this.stacrihstesc = stacrihstesc;
    }

    public Date getDtaultalt() {
        return dtaultalt;
    }

    public void setDtaultalt(Date dtaultalt) {
        this.dtaultalt = dtaultalt;
    }

    public Integer getCodpesalt() {
        return codpesalt;
    }

    public void setCodpesalt(Integer codpesalt) {
        this.codpesalt = codpesalt;
    }

    public byte[] getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(byte[] timestamp) {
        this.timestamp = timestamp;
    }

    public Character getAplori() {
        return aplori;
    }

    public void setAplori(Character aplori) {
        this.aplori = aplori;
    }

    public Short getNumseqtur() {
        return numseqtur;
    }

    public void setNumseqtur(Short numseqtur) {
        this.numseqtur = numseqtur;
    }

    public Character getAplorifrqnot() {
        return aplorifrqnot;
    }

    public void setAplorifrqnot(Character aplorifrqnot) {
        this.aplorifrqnot = aplorifrqnot;
    }

    public Character getDtlstamtr() {
        return dtlstamtr;
    }

    public void setDtlstamtr(Character dtlstamtr) {
        this.dtlstamtr = dtlstamtr;
    }

    public Short getPrimtr() {
        return primtr;
    }

    public void setPrimtr(Short primtr) {
        this.primtr = primtr;
    }

    public Short getClsesmalutur() {
        return clsesmalutur;
    }

    public void setClsesmalutur(Short clsesmalutur) {
        this.clsesmalutur = clsesmalutur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (histescolargrPK != null ? histescolargrPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Histescolargr)) {
            return false;
        }
        Histescolargr other = (Histescolargr) object;
        if ((this.histescolargrPK == null && other.histescolargrPK != null) || (this.histescolargrPK != null && !this.histescolargrPK.equals(other.histescolargrPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.usp.poli.replicado.Histescolargr[histescolargrPK=" + histescolargrPK + "]";
    }
    
    public int getCreditos(){
    	return 4;
    }
    
    public double getNotaMaxima(){
    	BigDecimal nota1 = new BigDecimal(0);
    	BigDecimal nota2 = new BigDecimal(0);
    	if (this.getNotfim() != null)
			nota1 = this.getNotfim();
		
		if ( this.getNotfim2() != null)
			nota2 = this.getNotfim2();
		
		return Math.max(nota1.doubleValue(), nota2.doubleValue());
    }

}
