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
@Table(name = "JORNADA")
public class Jornada implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Estagio estagio;

	@Column(name = "dtaini", nullable = false)
	private Date dtaini;

	@Column(name = "jornada", precision = 2)
	private Integer jornada;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Estagio getEstagio() {
		return estagio;
	}

	public void setEstagio(Estagio estagio) {
		this.estagio = estagio;
	}

	public Date getDtaini() {
		return dtaini;
	}

	public void setDtaini(Date dtaini) {
		this.dtaini = dtaini;
	}

	public Integer getJornada() {
		return jornada;
	}

	public void setJornada(Integer jornada) {
		this.jornada = jornada;
	}

}
