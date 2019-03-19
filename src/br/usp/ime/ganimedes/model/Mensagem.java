package br.usp.ime.ganimedes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MENSAGEM")
public class Mensagem implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String assunto;
	private String de;
	private String para;

	@Lob
	private String mensagem;

	@Temporal(TemporalType.TIMESTAMP)
	private Date criacao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date envio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getCriacao() {
		return criacao;
	}

	public void setCriacao(Date criacao) {
		this.criacao = criacao;
	}

	public Date getEnvio() {
		return envio;
	}

	public void setEnvio(Date envio) {
		this.envio = envio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assunto == null) ? 0 : assunto.hashCode());
		result = prime * result + ((criacao == null) ? 0 : criacao.hashCode());
		result = prime * result + ((de == null) ? 0 : de.hashCode());
		result = prime * result + ((envio == null) ? 0 : envio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mensagem == null) ? 0 : mensagem.hashCode());
		result = prime * result + ((para == null) ? 0 : para.hashCode());
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
		Mensagem other = (Mensagem) obj;
		if (assunto == null) {
			if (other.assunto != null)
				return false;
		} else if (!assunto.equals(other.assunto))
			return false;
		if (criacao == null) {
			if (other.criacao != null)
				return false;
		} else if (!criacao.equals(other.criacao))
			return false;
		if (de == null) {
			if (other.de != null)
				return false;
		} else if (!de.equals(other.de))
			return false;
		if (envio == null) {
			if (other.envio != null)
				return false;
		} else if (!envio.equals(other.envio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mensagem == null) {
			if (other.mensagem != null)
				return false;
		} else if (!mensagem.equals(other.mensagem))
			return false;
		if (para == null) {
			if (other.para != null)
				return false;
		} else if (!para.equals(other.para))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mensagem [id=" + id + "]";
	}

}
