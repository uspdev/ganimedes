/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.model.replicado;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fabio
 */
@Entity
@Table(name = "AGPROGRAMA", catalog = "replicado", schema = "dbo")
public class Agprograma implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AgprogramaPK agprogramaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dtaselpgm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaselpgm;
    @Column(name = "dtactaprzpgm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtactaprzpgm;
    @Column(name = "dtalimpgm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtalimpgm;
    @Size(max = 2)
    @Column(name = "nivpgm")
    private String nivpgm;
    @Column(name = "starcopgm")
    private Character starcopgm;
    @Column(name = "starmtpgm")
    private Character starmtpgm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "vinalupgm")
    private String vinalupgm;
    @Column(name = "dtadpopgm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtadpopgm;
    @Column(name = "dtaaprbantrb")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaaprbantrb;
    @Column(name = "stacsldfatrb")
    private Character stacsldfatrb;
    @Column(name = "dtadfapgm")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtadfapgm;
    @Column(name = "dtahomtrbcpg")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtahomtrbcpg;
    @Size(max = 30)
    @Column(name = "nomlocdfatrb")
    private String nomlocdfatrb;
    @Size(max = 18)
    @Column(name = "menccdpgm")
    private String menccdpgm;
    @Column(name = "codsubare")
    private Short codsubare;
    @Column(name = "numseqnor")
    private Short numseqnor;
    @Column(name = "numseqrgl")
    private Short numseqrgl;
    @Column(name = "numviahst")
    private Short numviahst;
    @Column(name = "dtaemiviahst")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaemiviahst;
    @Lob
    @Column(name = "timestamp")
    private byte[] timestamp;

    public Agprograma() {
    }

    public Agprograma(AgprogramaPK agprogramaPK) {
        this.agprogramaPK = agprogramaPK;
    }

    public Agprograma(AgprogramaPK agprogramaPK, Date dtaselpgm, String vinalupgm) {
        this.agprogramaPK = agprogramaPK;
        this.dtaselpgm = dtaselpgm;
        this.vinalupgm = vinalupgm;
    }

    public Agprograma(int codare, int codpes, short numseqpgm) {
        this.agprogramaPK = new AgprogramaPK(codare, codpes, numseqpgm);
    }

    public AgprogramaPK getAgprogramaPK() {
        return agprogramaPK;
    }

    public void setAgprogramaPK(AgprogramaPK agprogramaPK) {
        this.agprogramaPK = agprogramaPK;
    }

    public Date getDtaselpgm() {
        return dtaselpgm;
    }

    public void setDtaselpgm(Date dtaselpgm) {
        this.dtaselpgm = dtaselpgm;
    }

    public Date getDtactaprzpgm() {
        return dtactaprzpgm;
    }

    public void setDtactaprzpgm(Date dtactaprzpgm) {
        this.dtactaprzpgm = dtactaprzpgm;
    }

    public Date getDtalimpgm() {
        return dtalimpgm;
    }

    public void setDtalimpgm(Date dtalimpgm) {
        this.dtalimpgm = dtalimpgm;
    }

    public String getNivpgm() {
        return nivpgm;
    }

    public void setNivpgm(String nivpgm) {
        this.nivpgm = nivpgm;
    }

    public Character getStarcopgm() {
        return starcopgm;
    }

    public void setStarcopgm(Character starcopgm) {
        this.starcopgm = starcopgm;
    }

    public Character getStarmtpgm() {
        return starmtpgm;
    }

    public void setStarmtpgm(Character starmtpgm) {
        this.starmtpgm = starmtpgm;
    }

    public String getVinalupgm() {
        return vinalupgm;
    }

    public void setVinalupgm(String vinalupgm) {
        this.vinalupgm = vinalupgm;
    }

    public Date getDtadpopgm() {
        return dtadpopgm;
    }

    public void setDtadpopgm(Date dtadpopgm) {
        this.dtadpopgm = dtadpopgm;
    }

    public Date getDtaaprbantrb() {
        return dtaaprbantrb;
    }

    public void setDtaaprbantrb(Date dtaaprbantrb) {
        this.dtaaprbantrb = dtaaprbantrb;
    }

    public Character getStacsldfatrb() {
        return stacsldfatrb;
    }

    public void setStacsldfatrb(Character stacsldfatrb) {
        this.stacsldfatrb = stacsldfatrb;
    }

    public Date getDtadfapgm() {
        return dtadfapgm;
    }

    public void setDtadfapgm(Date dtadfapgm) {
        this.dtadfapgm = dtadfapgm;
    }

    public Date getDtahomtrbcpg() {
        return dtahomtrbcpg;
    }

    public void setDtahomtrbcpg(Date dtahomtrbcpg) {
        this.dtahomtrbcpg = dtahomtrbcpg;
    }

    public String getNomlocdfatrb() {
        return nomlocdfatrb;
    }

    public void setNomlocdfatrb(String nomlocdfatrb) {
        this.nomlocdfatrb = nomlocdfatrb;
    }

    public String getMenccdpgm() {
        return menccdpgm;
    }

    public void setMenccdpgm(String menccdpgm) {
        this.menccdpgm = menccdpgm;
    }

    public Short getCodsubare() {
        return codsubare;
    }

    public void setCodsubare(Short codsubare) {
        this.codsubare = codsubare;
    }

    public Short getNumseqnor() {
        return numseqnor;
    }

    public void setNumseqnor(Short numseqnor) {
        this.numseqnor = numseqnor;
    }

    public Short getNumseqrgl() {
        return numseqrgl;
    }

    public void setNumseqrgl(Short numseqrgl) {
        this.numseqrgl = numseqrgl;
    }

    public Short getNumviahst() {
        return numviahst;
    }

    public void setNumviahst(Short numviahst) {
        this.numviahst = numviahst;
    }

    public Date getDtaemiviahst() {
        return dtaemiviahst;
    }

    public void setDtaemiviahst(Date dtaemiviahst) {
        this.dtaemiviahst = dtaemiviahst;
    }

    public byte[] getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(byte[] timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (agprogramaPK != null ? agprogramaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agprograma)) {
            return false;
        }
        Agprograma other = (Agprograma) object;
        if ((this.agprogramaPK == null && other.agprogramaPK != null) || (this.agprogramaPK != null && !this.agprogramaPK.equals(other.agprogramaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.usp.poli.model.apl08.Agprograma[ agprogramaPK=" + agprogramaPK + " ]";
    }
    
}
