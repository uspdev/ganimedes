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

import br.usp.ime.util.DataUtility;

@Entity
@Table(name = "CONVENIO")
public class Convenio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Empresa empresa;

	@Column(name = "dtaini", nullable = false)
	private Date dtaini;

	@Column(name = "dtafim", nullable = false)
	private Date dtafim;

	@Column(name = "numpro", nullable = false)
	private String numpro;

	public Convenio() {

	}

	public String getPeriodo() {
		try {
			return DataUtility.dateToStringDate(this.getDtaini()) + " a " + DataUtility.dateToStringDate(this.getDtafim());
		} catch (Exception e) {

		}
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
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

	public String getNumpro() {
		return numpro;
	}

	public void setNumpro(String numpro) {
		this.numpro = numpro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dtafim == null) ? 0 : dtafim.hashCode());
		result = prime * result + ((dtaini == null) ? 0 : dtaini.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numpro == null) ? 0 : numpro.hashCode());
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
		Convenio other = (Convenio) obj;
		if (dtafim == null) {
			if (other.dtafim != null)
				return false;
		} else if (!dtafim.equals(other.dtafim))
			return false;
		if (dtaini == null) {
			if (other.dtaini != null)
				return false;
		} else if (!dtaini.equals(other.dtaini))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numpro == null) {
			if (other.numpro != null)
				return false;
		} else if (!numpro.equals(other.numpro))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Convenio [id=" + id + ", empresa=" + empresa + ", dtaini=" + dtaini + ", dtafim=" + dtafim + ", numpro=" + numpro + "]";
	}

}
