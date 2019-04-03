package br.usp.model.replicado;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PESSOA", catalog = "replicado", schema = "dbo")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 833644795952693402L;

	@Id
	@Column(name = "codpes", nullable = false)
	private Integer codpes;

	@Column(name = "nompes", nullable = false)
	private String nompes;

	@Column(name = "nommaepes")
	private String nommaepes;

	@Column(name = "dtanas")
	@Temporal(TemporalType.DATE)
	private Date dtanas;

	@Column(name = "tipdocidf")
	private String tipdocidf;

	@Column(name = "numdocidf")
	private String numdocidf;

	@Column(name = "dtaexdidf")
	@Temporal(TemporalType.DATE)
	private Date dtaexdidf;

	@Column(name = "sglorgexdidf")
	private String sglorgexdidf;

	@Column(name = "dtafimvalidf")
	@Temporal(TemporalType.DATE)
	private Date dtafimvalidf;

	@Column(name = "sexpes")
	private Character sexpes;

	@Column(name = "codloccer")
	private Integer codloccer;

	@Column(name = "sglest")
	private String sglest;

	@Column(name = "codpas")
	private Short codpas;

	@Column(name = "stabcocad")
	private Character stabcocad;

	@Column(name = "codpescad")
	private Integer codpescad;

	@Column(name = "dtacadpes")
	@Temporal(TemporalType.DATE)
	private Date dtacadpes;

	@Column(name = "codpesultalt")
	private Integer codpesultalt;

	@Column(name = "dtaultalt")
	@Temporal(TemporalType.DATE)
	private Date dtaultalt;

	@Column(name = "nompesfon")
	private String nompesfon;

	@Column(name = "nommaepesfon")
	private String nommaepesfon;

	@Column(name = "timestamp")
	private byte[] timestamp;

	@Column(name = "nomcnhpes")
	private String nomcnhpes;

	@Column(name = "numdocfmt")
	private String numdocfmt;

	@Column(name = "stafotcad")
	private Character stafotcad;

	@Column(name = "numcpf")
	private String numcpf;

	@Column(name = "epfbcoini")
	private String epfbcoini;

	// private List<VinculoPessoaUsp> vinculo = new
	// ArrayList<VinculoPessoaUsp>();

	/** Creates a new instance of Pessoa */
	public Pessoa() {
	}

	/**
	 * Cria uma nova instância de Pessoa com os valores especificados.
	 * 
	 * @param codpes
	 *          o codpes do Pessoa
	 */
	public Pessoa(Integer codpes) {
		this.codpes = codpes;
	}

	/**
	 * Cria uma nova instância de Pessoa com os valores especificados.
	 * 
	 * @param codpes
	 *          o codpes do Pessoa
	 * @param nompes
	 *          o nompes do Pessoa
	 */
	public Pessoa(Integer codpes, String nompes) {
		this.codpes = codpes;
		this.nompes = nompes;
	}

	public Integer getCodpes() {
		return this.codpes;
	}

	public String getNompes() {
		return this.nompes;
	}

	public String getNommaepes() {
		return this.nommaepes;
	}

	public Date getDtanas() {
		return this.dtanas;
	}

	public String getTipdocidf() {
		return this.tipdocidf;
	}

	public String getNumdocidf() {
		return this.numdocidf;
	}

	public Date getDtaexdidf() {
		return this.dtaexdidf;
	}

	public String getSglorgexdidf() {
		return this.sglorgexdidf;
	}

	public Date getDtafimvalidf() {
		return this.dtafimvalidf;
	}

	public Character getSexpes() {
		return this.sexpes;
	}

	public Integer getCodloccer() {
		return this.codloccer;
	}

	public String getSglest() {
		return this.sglest;
	}

	public Short getCodpas() {
		return this.codpas;
	}

	public Character getStabcocad() {
		return this.stabcocad;
	}

	public Integer getCodpescad() {
		return this.codpescad;
	}

	public Date getDtacadpes() {
		return this.dtacadpes;
	}

	public Integer getCodpesultalt() {
		return this.codpesultalt;
	}

	public Date getDtaultalt() {
		return this.dtaultalt;
	}

	public String getNompesfon() {
		return this.nompesfon;
	}

	public String getNommaepesfon() {
		return this.nommaepesfon;
	}

	public byte[] getTimestamp() {
		return this.timestamp;
	}

	public String getNomcnhpes() {
		return this.nomcnhpes;
	}

	public String getNumdocfmt() {
		return this.numdocfmt;
	}

	public Character getStafotcad() {
		return this.stafotcad;
	}

	public String getNumcpf() {
		return this.numcpf;
	}

	public String getEpfbcoini() {
		return this.epfbcoini;
	}

	/**
	 * Retorna um valor de código hash para o objeto. Esta implementação computa um valor de código hash baseado nos campos id deste objeto.
	 * 
	 * @return um valor de código hash para este objeto.
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (this.codpes != null ? this.codpes.hashCode() : 0);
		return hash;
	}

	/**
	 * Determina se outro objeto é igual a este Pessoa. O resultado é <code>true</code> se e somente se o argumento não for nulo e for um objeto Pessoa
	 * o qual tem o mesmo valor para o campo id como este objeto.
	 * 
	 * @param object
	 *          o objeto de referência com o qual comparar
	 * @return <code>true</code> se este objeto é o mesmo como o argumento; <code>false</code> caso contrário.
	 */

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Pessoa)) {
			return false;
		}
		Pessoa other = (Pessoa) object;
		if (this.codpes != other.codpes && (this.codpes == null || !this.codpes.equals(other.codpes)))
			return false;
		return true;
	}

	/**
	 * Retorna uma representação literal deste objeto. Esta implementação cria uma representação baseada nos campos id.
	 * 
	 * @return uma representação literal deste objeto.
	 */
	@Override
	public String toString() {
		return "br.usp.poli.icaro.pessoa.model.Pessoa[codpes=" + codpes + "]";
	}

}
