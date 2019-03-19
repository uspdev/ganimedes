package br.usp.ime.ganimedes.model;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

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
@Table(name = "REMUNERACAO")
public class Remuneracao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Estagio estagio;

	@Column(name = "dtaini")
	private Date dtaini;

	@Column(name = "remuneracao", precision = 2)
	private double remuneracao;

	public String getRemuneracaoF() {
		Locale locale = new Locale("pt", "BR");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
		return formatter.format(remuneracao);

	}

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

	public double getRemuneracao() {
		return remuneracao;
	}

	public void setRemuneracao(double remuneracao) {
		this.remuneracao = remuneracao;
	}

}
