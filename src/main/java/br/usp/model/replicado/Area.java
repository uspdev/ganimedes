/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.model.replicado;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fabio
 */
@Entity
@Table(name = "AREA", catalog = "replicado", schema = "dbo")
public class Area implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codare")
    private Integer codare;
    @Size(max = 11)
    @Column(name = "numprocri")
    private String numprocri;
    @Size(max = 11)
    @Column(name = "numprocfe")
    private String numprocfe;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codcur")
    private int codcur;
    @Column(name = "codcpe")
    private Integer codcpe;
    @Lob
    @Column(name = "timestamp")
    private byte[] timestamp;

    public Area() {
    }

    public Area(Integer codare) {
        this.codare = codare;
    }

    public Area(Integer codare, int codcur) {
        this.codare = codare;
        this.codcur = codcur;
    }

    public Integer getCodare() {
        return codare;
    }

    public void setCodare(Integer codare) {
        this.codare = codare;
    }

    public String getNumprocri() {
        return numprocri;
    }

    public void setNumprocri(String numprocri) {
        this.numprocri = numprocri;
    }

    public String getNumprocfe() {
        return numprocfe;
    }

    public void setNumprocfe(String numprocfe) {
        this.numprocfe = numprocfe;
    }

    public int getCodcur() {
        return codcur;
    }

    public void setCodcur(int codcur) {
        this.codcur = codcur;
    }

    public Integer getCodcpe() {
        return codcpe;
    }

    public void setCodcpe(Integer codcpe) {
        this.codcpe = codcpe;
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
        hash += (codare != null ? codare.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.codare == null && other.codare != null) || (this.codare != null && !this.codare.equals(other.codare))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.usp.poli.model.apl08.Area[ codare=" + codare + " ]";
    }
    
}
