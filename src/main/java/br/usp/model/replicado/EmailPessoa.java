package br.usp.model.replicado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe de entidade Emailpessoa
 * 
 * @author marcelo.modesto
 */
@Entity
@Table(name = "EMAILPESSOA", catalog = "replicado", schema = "dbo")
public class EmailPessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6556818505163343118L;

	/**
	 * Campo de chave primária embutido
	 */
	@EmbeddedId
	protected EmailPessoaPK EmailPessoaPK;

	@Column(name = "codema", nullable = false)
	private String codema;

	@Column(name = "stamtr")
	private Character stamtr;

	
	@Column(name = "timestamp")
	private byte[] timestamp;

	@Column(name = "staatzdvg")
	private Character staatzdvg;

	/** Creates a new instance of EmailPessoa */
	public EmailPessoa() {
	}

	/**
	 * Cria uma nova instância de EmailPessoa com os valores especificados.
	 * 
	 * @param EmailPessoaPK
	 *            o EmailPessoaPK do EmailPessoa
	 */
	public EmailPessoa(EmailPessoaPK EmailPessoaPK) {
		this.EmailPessoaPK = EmailPessoaPK;
	}

	/**
	 * Cria uma nova instância de EmailPessoa com os valores especificados.
	 * 
	 * @param EmailPessoaPK
	 *            o EmailPessoaPK do EmailPessoa
	 * @param codema
	 *            o codema do EmailPessoa
	 */
	public EmailPessoa(EmailPessoaPK EmailPessoaPK, String codema) {
		this.EmailPessoaPK = EmailPessoaPK;
		this.codema = codema;
	}

	/**
	 * Cria uma nova instância de EmailPessoaPK com os valores especificados.
	 * 
	 * @param numseqema
	 *            o numseqema do EmailPessoaPK
	 * @param codpes
	 *            o codpes do EmailPessoaPK
	 */
	public EmailPessoa(short numseqema, int codpes) {
		this.EmailPessoaPK = new EmailPessoaPK(numseqema, codpes);
	}

	/**
	 * Define o EmailPessoaPK deste EmailPessoa.
	 * 
	 * @return o EmailPessoaPK
	 */
	public EmailPessoaPK getEmailPessoaPK() {
		return this.EmailPessoaPK;
	}

	/**
	 * Define o EmailPessoaPK deste EmailPessoa para o valor especificado.
	 * 
	 * @param EmailPessoaPK
	 *            o novo EmailPessoaPK
	 */
	public void setEmailPessoaPK(EmailPessoaPK EmailPessoaPK) {
		this.EmailPessoaPK = EmailPessoaPK;
	}

	/**
	 * Define o codema deste EmailPessoa.
	 * 
	 * @return o codema
	 */
	public String getCodema() {
		return this.codema;
	}

	/**
	 * Define o codema deste EmailPessoa para o valor especificado.
	 * 
	 * @param codema
	 *            o novo codema
	 */
	public void setCodema(String codema) {
		this.codema = codema;
	}

	/**
	 * Define o stamtr deste EmailPessoa.
	 * 
	 * @return o stamtr
	 */
	public Character getStamtr() {
		return this.stamtr;
	}

	/**
	 * Define o stamtr deste EmailPessoa para o valor especificado.
	 * 
	 * @param stamtr
	 *            o novo stamtr
	 */
	public void setStamtr(Character stamtr) {
		this.stamtr = stamtr;
	}

	/**
	 * Define o timestamp deste EmailPessoa.
	 * 
	 * @return o timestamp
	 */
	public byte[] getTimestamp() {
		return this.timestamp;
	}

	/**
	 * Define o timestamp deste EmailPessoa para o valor especificado.
	 * 
	 * @param timestamp
	 *            o novo timestamp
	 */
	public void setTimestamp(byte[] timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Define o staatzdvg deste EmailPessoa.
	 * 
	 * @return o staatzdvg
	 */
	public Character getStaatzdvg() {
		return this.staatzdvg;
	}

	/**
	 * Define o staatzdvg deste EmailPessoa para o valor especificado.
	 * 
	 * @param staatzdvg
	 *            o novo staatzdvg
	 */
	public void setStaatzdvg(Character staatzdvg) {
		this.staatzdvg = staatzdvg;
	}

	/**
	 * Retorna um valor de código hash para o objeto. Esta implementação computa
	 * um valor de código hash baseado nos campos id deste objeto.
	 * 
	 * @return um valor de código hash para este objeto.
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (this.EmailPessoaPK != null ? this.EmailPessoaPK.hashCode() : 0);
		return hash;
	}

	/**
	 * Determina se outro objeto é igual a este EmailPessoa. O resultado é
	 * <code>true</code> se e somente se o argumento não for nulo e for um
	 * objeto EmailPessoa o qual tem o mesmo valor para o campo id como este
	 * objeto.
	 * 
	 * @param object
	 *            o objeto de referência com o qual comparar
	 * @return <code>true</code> se este objeto é o mesmo como o argumento;
	 *         <code>false</code> caso contrário.
	 */
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof EmailPessoa)) {
			return false;
		}
		EmailPessoa other = (EmailPessoa) object;
		if (this.EmailPessoaPK != other.EmailPessoaPK
				&& (this.EmailPessoaPK == null || !this.EmailPessoaPK.equals(other.EmailPessoaPK)))
			return false;
		return true;
	}

	/**
	 * Retorna uma representação literal deste objeto. Esta implementação cria
	 * uma representação baseada nos campos id.
	 * 
	 * @return uma representação literal deste objeto.
	 */
	@Override
	public String toString() {
		return "br.usp.poli.icaro.model.EmailPessoa[EmailPessoaPK=" + EmailPessoaPK + "]";
	}

}
