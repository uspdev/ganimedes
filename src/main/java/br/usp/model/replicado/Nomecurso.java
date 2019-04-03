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
@Table(name = "NOMECURSO", catalog = "replicado", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nomecurso.findAll", query = "SELECT n FROM Nomecurso n"),
    @NamedQuery(name = "Nomecurso.findByCodcur", query = "SELECT n FROM Nomecurso n WHERE n.nomecursoPK.codcur = :codcur"),
    @NamedQuery(name = "Nomecurso.findByDtainicur", query = "SELECT n FROM Nomecurso n WHERE n.nomecursoPK.dtainicur = :dtainicur"),
    @NamedQuery(name = "Nomecurso.findByDtafimcur", query = "SELECT n FROM Nomecurso n WHERE n.dtafimcur = :dtafimcur"),
    @NamedQuery(name = "Nomecurso.findByNomcur", query = "SELECT n FROM Nomecurso n WHERE n.nomcur = :nomcur")})
public class Nomecurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NomecursoPK nomecursoPK;
    @Column(name = "dtafimcur")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtafimcur;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 160)
    @Column(name = "nomcur")
    private String nomcur;
    @Lob
    @Column(name = "timestamp")
    private byte[] timestamp;

    public Nomecurso() {
    }

    public Nomecurso(NomecursoPK nomecursoPK) {
        this.nomecursoPK = nomecursoPK;
    }

    public Nomecurso(NomecursoPK nomecursoPK, String nomcur) {
        this.nomecursoPK = nomecursoPK;
        this.nomcur = nomcur;
    }

    public Nomecurso(int codcur, Date dtainicur) {
        this.nomecursoPK = new NomecursoPK(codcur, dtainicur);
    }

    public NomecursoPK getNomecursoPK() {
        return nomecursoPK;
    }

    public void setNomecursoPK(NomecursoPK nomecursoPK) {
        this.nomecursoPK = nomecursoPK;
    }

    public Date getDtafimcur() {
        return dtafimcur;
    }

    public void setDtafimcur(Date dtafimcur) {
        this.dtafimcur = dtafimcur;
    }

    public String getNomcur() {
        return nomcur;
    }

    public void setNomcur(String nomcur) {
        this.nomcur = nomcur;
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
        hash += (nomecursoPK != null ? nomecursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nomecurso)) {
            return false;
        }
        Nomecurso other = (Nomecurso) object;
        if ((this.nomecursoPK == null && other.nomecursoPK != null) || (this.nomecursoPK != null && !this.nomecursoPK.equals(other.nomecursoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.usp.poli.model.apl08.Nomecurso[ nomecursoPK=" + nomecursoPK + " ]";
    }
    
}
