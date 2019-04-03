package br.usp.model.replicado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Classe de Chave Primária VinculopessoauspPK para classe de entidade Vinculopessoausp
 * 
 * @author marcelo.modesto
 */
@Embeddable
public class VinculoPessoaUspPK implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 3572969067287054187L;

	@Column(name = "codpes", nullable = false)
	private int codpes;

	@Column(name = "numseqpes", nullable = false)
	private short numseqpes;

	@Column(name = "tipvin", nullable = false)
	private String tipvin;

	/** Creates a new instance of VinculopessoauspPK */
	public VinculoPessoaUspPK() {
	}

	/**
	 * Cria uma nova instância de VinculopessoauspPK com os valores especificados.
	 * 
	 * @param tipvin
	 *          o tipvin do VinculopessoauspPK
	 * @param numseqpes
	 *          o numseqpes do VinculopessoauspPK
	 * @param codpes
	 *          o codpes do VinculopessoauspPK
	 */
	public VinculoPessoaUspPK(String tipvin, short numseqpes, int codpes) {
		this.tipvin = tipvin;
		this.numseqpes = numseqpes;
		this.codpes = codpes;
	}

	/**
	 * Define o codpes deste VinculopessoauspPK.
	 * 
	 * @return o codpes
	 */
	public int getCodpes() {
		return this.codpes;
	}

	/**
	 * Define o codpes deste VinculopessoauspPK para o valor especificado.
	 * 
	 * @param codpes
	 *          o novo codpes
	 */
	public void setCodpes(int codpes) {
		this.codpes = codpes;
	}

	/**
	 * Define o numseqpes deste VinculopessoauspPK.
	 * 
	 * @return o numseqpes
	 */
	public short getNumseqpes() {
		return this.numseqpes;
	}

	/**
	 * Define o numseqpes deste VinculopessoauspPK para o valor especificado.
	 * 
	 * @param numseqpes
	 *          o novo numseqpes
	 */
	public void setNumseqpes(short numseqpes) {
		this.numseqpes = numseqpes;
	}

	/**
	 * Define o tipvin deste VinculopessoauspPK.
	 * 
	 * @return o tipvin
	 */
	public String getTipvin() {
		return this.tipvin;
	}

	/**
	 * Define o tipvin deste VinculopessoauspPK para o valor especificado.
	 * 
	 * @param tipvin
	 *          o novo tipvin
	 */
	public void setTipvin(String tipvin) {
		this.tipvin = tipvin;
	}

	/**
	 * Retorna um valor de código hash para o objeto. Esta implementação computa um valor de código hash baseado nos campos id deste objeto.
	 * 
	 * @return um valor de código hash para este objeto.
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (this.tipvin != null ? this.tipvin.hashCode() : 0);
		hash += (int) numseqpes;
		hash += (int) codpes;
		return hash;
	}

	/**
	 * Determina se outro objeto é igual a este VinculopessoauspPK. O resultado é <code>true</code> se e somente se o argumento não for nulo e for um
	 * objeto VinculopessoauspPK o qual tem o mesmo valor para o campo id como este objeto.
	 * 
	 * @param object
	 *          o objeto de referência com o qual comparar
	 * @return <code>true</code> se este objeto é o mesmo como o argumento; <code>false</code> caso contrário.
	 */
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof VinculoPessoaUspPK)) {
			return false;
		}
		VinculoPessoaUspPK other = (VinculoPessoaUspPK) object;
		if (this.tipvin != other.tipvin && (this.tipvin == null || !this.tipvin.equals(other.tipvin)))
			return false;
		if (this.numseqpes != other.numseqpes)
			return false;
		if (this.codpes != other.codpes)
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
		return "br.usp.poli.icaro.model.VinculopessoauspPK[tipvin=" + tipvin + ", numseqpes=" + numseqpes + ", codpes=" + codpes + "]";
	}

}
