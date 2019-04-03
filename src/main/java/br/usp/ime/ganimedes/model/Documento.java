package br.usp.ime.ganimedes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author marcelo
 *
 */
@Entity
@Table(name = "DOCUMENTO")
public class Documento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Estagio estagio;

	private ETipoDoc tipo;

	@Column(name = "statusDoc", nullable = false)
	private EStatusDoc statusDoc;

	private EStatusAndamentoDoc statusAndamentoDoc;

	@Column(name = "dtaent", nullable = false)
	private Date dtaent;

	@Column(name = "dtaini", nullable = false)
	private Date dtaini;

	@Column(name = "dtafim", nullable = false)
	private Date dtafim;

	// armazena a data do ultimo andamento
	@Column(name = "dtaultand")
	private Date dtaultand;

	@Column(name = "cgahorsem")
	private Integer cgahorsem;

	public Documento() {

		if (this.getStatusDoc() == null) {
			this.setStatusDoc(EStatusDoc.SEM);
		}

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDtaini() {
		return dtaini;
	}

	public void setDtaini(Date dtaini) {
		this.dtaini = dtaini;
	}

	public Date getDtafim() {
		return dtafim;
	}

	public void setDtafim(Date dtafim) {
		this.dtafim = dtafim;
	}

	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

	public ETipoDoc getTipo() {
		return tipo;
	}

	public void setTipo(ETipoDoc tipo) {
		this.tipo = tipo;
	}

	public EStatusDoc getStatusDoc() {
		return statusDoc;
	}

	public void setStatusDoc(EStatusDoc statusDoc) {
		this.statusDoc = statusDoc;
	}

	public EStatusAndamentoDoc getStatusAndamentoDoc() {
		return statusAndamentoDoc;
	}

	public void setStatusAndamentoDoc(EStatusAndamentoDoc statusAndamentoDoc) {
		this.statusAndamentoDoc = statusAndamentoDoc;
	}

	public Integer getCgahorsem() {
		return cgahorsem;
	}

	public void setCgahorsem(Integer cgahorsem) {
		this.cgahorsem = cgahorsem;
	}

	public Date getDtaultand() {
		return dtaultand;
	}

	public void setDtaultand(Date dtaultand) {
		this.dtaultand = dtaultand;
	}

	public Date getDtaent() {
		return dtaent;
	}

	public void setDtaent(Date dtaent) {
		this.dtaent = dtaent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Documento other = (Documento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Documento [id=" + id + ", estagio=" + estagio + ", tipo=" + tipo + ", statusDoc=" + statusDoc + ", statusAndamentoDoc=" + statusAndamentoDoc + ", dtaent=" + dtaent + ", dtaini="
				+ dtaini + ", dtafim=" + dtafim + ", dtaultand=" + dtaultand + ", cgahorsem=" + cgahorsem + "]";
	}

}
