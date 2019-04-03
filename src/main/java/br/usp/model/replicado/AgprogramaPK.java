/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.usp.model.replicado;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fabio
 */
@Embeddable
public class AgprogramaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "codare")
    private int codare;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codpes")
    private int codpes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numseqpgm")
    private short numseqpgm;

    public AgprogramaPK() {
    }

    public AgprogramaPK(int codare, int codpes, short numseqpgm) {
        this.codare = codare;
        this.codpes = codpes;
        this.numseqpgm = numseqpgm;
    }

    public int getCodare() {
        return codare;
    }

    public void setCodare(int codare) {
        this.codare = codare;
    }

    public int getCodpes() {
        return codpes;
    }

    public void setCodpes(int codpes) {
        this.codpes = codpes;
    }

    public short getNumseqpgm() {
        return numseqpgm;
    }

    public void setNumseqpgm(short numseqpgm) {
        this.numseqpgm = numseqpgm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codare;
        hash += (int) codpes;
        hash += (int) numseqpgm;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AgprogramaPK)) {
            return false;
        }
        AgprogramaPK other = (AgprogramaPK) object;
        if (this.codare != other.codare) {
            return false;
        }
        if (this.codpes != other.codpes) {
            return false;
        }
        if (this.numseqpgm != other.numseqpgm) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.usp.poli.model.apl08.AgprogramaPK[ codare=" + codare + ", codpes=" + codpes + ", numseqpgm=" + numseqpgm + " ]";
    }
    
}
