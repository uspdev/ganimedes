/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.usp.model.replicado;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "CURSOGR", catalog = "replicado", schema = "dbo")
public class Cursogr implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codcur")
    private Integer codcur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codclg")
    private short codclg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "sglclg")
    private String sglclg;
    @Size(max = 100)
    @Column(name = "nomcur")
    private String nomcur;
    @Size(max = 50)
    @Column(name = "nomabv")
    private String nomabv;
    @Column(name = "dtaprpcur")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaprpcur;
    @Column(name = "dtacricur")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtacricur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dtaatvcur")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaatvcur;
    @Column(name = "dtadtvcur")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtadtvcur;
    @Column(name = "dtaapr1")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaapr1;
    @Column(name = "dtaapr2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaapr2;
    @Column(name = "dtaapr3")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaapr3;
    @Column(name = "dtaapr4")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaapr4;
    @Column(name = "dtaapr5")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaapr5;
    @Column(name = "dtaaprcee")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaaprcee;
    @Size(max = 10)
    @Column(name = "bselglcee")
    private String bselglcee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipcur")
    private char tipcur;
    @Column(name = "dtaaprmec")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaaprmec;
    @Size(max = 10)
    @Column(name = "bselglmec")
    private String bselglmec;
    @Column(name = "staarecurbio")
    private Character staarecurbio;
    @Column(name = "staarecurhum")
    private Character staarecurhum;
    @Column(name = "staarecurexa")
    private Character staarecurexa;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "objcur")
    private String objcur;
    @Column(name = "totvag")
    private Short totvag;
    @Lob
    @Column(name = "timestamp")
    private byte[] timestamp;
    @Size(max = 100)
    @Column(name = "nomcurdpl")
    private String nomcurdpl;
    @Column(name = "dtainival")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtainival;
    @Column(name = "staensdtc")
    private Character staensdtc;
    @Size(max = 100)
    @Column(name = "nomcurigl")
    private String nomcurigl;
    @Size(max = 100)
    @Column(name = "nomcurepa")
    private String nomcurepa;
    @Size(max = 100)
    @Column(name = "nomcurfcs")
    private String nomcurfcs;
    @Size(max = 100)
    @Column(name = "nomcurdpligl")
    private String nomcurdpligl;
    @Size(max = 100)
    @Column(name = "nomcurdplepa")
    private String nomcurdplepa;
    @Size(max = 100)
    @Column(name = "nomcurdplfcs")
    private String nomcurdplfcs;

    public Cursogr() {
    }

    public Cursogr(Integer codcur) {
        this.codcur = codcur;
    }

    public Cursogr(Integer codcur, short codclg, String sglclg, Date dtaatvcur, char tipcur, String objcur) {
        this.codcur = codcur;
        this.codclg = codclg;
        this.sglclg = sglclg;
        this.dtaatvcur = dtaatvcur;
        this.tipcur = tipcur;
        this.objcur = objcur;
    }

    public Integer getCodcur() {
        return codcur;
    }

    public void setCodcur(Integer codcur) {
        this.codcur = codcur;
    }

    public short getCodclg() {
        return codclg;
    }

    public void setCodclg(short codclg) {
        this.codclg = codclg;
    }

    public String getSglclg() {
        return sglclg;
    }

    public void setSglclg(String sglclg) {
        this.sglclg = sglclg;
    }

    public String getNomcur() {
        return nomcur;
    }

    public void setNomcur(String nomcur) {
        this.nomcur = nomcur;
    }

    public String getNomabv() {
        return nomabv;
    }

    public void setNomabv(String nomabv) {
        this.nomabv = nomabv;
    }

    public Date getDtaprpcur() {
        return dtaprpcur;
    }

    public void setDtaprpcur(Date dtaprpcur) {
        this.dtaprpcur = dtaprpcur;
    }

    public Date getDtacricur() {
        return dtacricur;
    }

    public void setDtacricur(Date dtacricur) {
        this.dtacricur = dtacricur;
    }

    public Date getDtaatvcur() {
        return dtaatvcur;
    }

    public void setDtaatvcur(Date dtaatvcur) {
        this.dtaatvcur = dtaatvcur;
    }

    public Date getDtadtvcur() {
        return dtadtvcur;
    }

    public void setDtadtvcur(Date dtadtvcur) {
        this.dtadtvcur = dtadtvcur;
    }

    public Date getDtaapr1() {
        return dtaapr1;
    }

    public void setDtaapr1(Date dtaapr1) {
        this.dtaapr1 = dtaapr1;
    }

    public Date getDtaapr2() {
        return dtaapr2;
    }

    public void setDtaapr2(Date dtaapr2) {
        this.dtaapr2 = dtaapr2;
    }

    public Date getDtaapr3() {
        return dtaapr3;
    }

    public void setDtaapr3(Date dtaapr3) {
        this.dtaapr3 = dtaapr3;
    }

    public Date getDtaapr4() {
        return dtaapr4;
    }

    public void setDtaapr4(Date dtaapr4) {
        this.dtaapr4 = dtaapr4;
    }

    public Date getDtaapr5() {
        return dtaapr5;
    }

    public void setDtaapr5(Date dtaapr5) {
        this.dtaapr5 = dtaapr5;
    }

    public Date getDtaaprcee() {
        return dtaaprcee;
    }

    public void setDtaaprcee(Date dtaaprcee) {
        this.dtaaprcee = dtaaprcee;
    }

    public String getBselglcee() {
        return bselglcee;
    }

    public void setBselglcee(String bselglcee) {
        this.bselglcee = bselglcee;
    }

    public char getTipcur() {
        return tipcur;
    }

    public void setTipcur(char tipcur) {
        this.tipcur = tipcur;
    }

    public Date getDtaaprmec() {
        return dtaaprmec;
    }

    public void setDtaaprmec(Date dtaaprmec) {
        this.dtaaprmec = dtaaprmec;
    }

    public String getBselglmec() {
        return bselglmec;
    }

    public void setBselglmec(String bselglmec) {
        this.bselglmec = bselglmec;
    }

    public Character getStaarecurbio() {
        return staarecurbio;
    }

    public void setStaarecurbio(Character staarecurbio) {
        this.staarecurbio = staarecurbio;
    }

    public Character getStaarecurhum() {
        return staarecurhum;
    }

    public void setStaarecurhum(Character staarecurhum) {
        this.staarecurhum = staarecurhum;
    }

    public Character getStaarecurexa() {
        return staarecurexa;
    }

    public void setStaarecurexa(Character staarecurexa) {
        this.staarecurexa = staarecurexa;
    }

    public String getObjcur() {
        return objcur;
    }

    public void setObjcur(String objcur) {
        this.objcur = objcur;
    }

    public Short getTotvag() {
        return totvag;
    }

    public void setTotvag(Short totvag) {
        this.totvag = totvag;
    }

    public byte[] getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(byte[] timestamp) {
        this.timestamp = timestamp;
    }

    public String getNomcurdpl() {
        return nomcurdpl;
    }

    public void setNomcurdpl(String nomcurdpl) {
        this.nomcurdpl = nomcurdpl;
    }

    public Date getDtainival() {
        return dtainival;
    }

    public void setDtainival(Date dtainival) {
        this.dtainival = dtainival;
    }

    public Character getStaensdtc() {
        return staensdtc;
    }

    public void setStaensdtc(Character staensdtc) {
        this.staensdtc = staensdtc;
    }

    public String getNomcurigl() {
        return nomcurigl;
    }

    public void setNomcurigl(String nomcurigl) {
        this.nomcurigl = nomcurigl;
    }

    public String getNomcurepa() {
        return nomcurepa;
    }

    public void setNomcurepa(String nomcurepa) {
        this.nomcurepa = nomcurepa;
    }

    public String getNomcurfcs() {
        return nomcurfcs;
    }

    public void setNomcurfcs(String nomcurfcs) {
        this.nomcurfcs = nomcurfcs;
    }

    public String getNomcurdpligl() {
        return nomcurdpligl;
    }

    public void setNomcurdpligl(String nomcurdpligl) {
        this.nomcurdpligl = nomcurdpligl;
    }

    public String getNomcurdplepa() {
        return nomcurdplepa;
    }

    public void setNomcurdplepa(String nomcurdplepa) {
        this.nomcurdplepa = nomcurdplepa;
    }

    public String getNomcurdplfcs() {
        return nomcurdplfcs;
    }

    public void setNomcurdplfcs(String nomcurdplfcs) {
        this.nomcurdplfcs = nomcurdplfcs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codcur != null ? codcur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cursogr)) {
            return false;
        }
        Cursogr other = (Cursogr) object;
        if ((this.codcur == null && other.codcur != null) || (this.codcur != null && !this.codcur.equals(other.codcur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.usp.model.replicado.Cursogr[ codcur=" + codcur + " ]";
    }
    
}
