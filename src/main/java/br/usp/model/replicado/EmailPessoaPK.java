package br.usp.model.replicado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Classe de Chave Primária EmailpessoaPK para classe de entidade Emailpessoa
 * 
 * @author marcelo.modesto
 */
@Embeddable
public class EmailPessoaPK implements Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2413776259246475037L;

	@Column(name = "codpes", nullable = false)
    private int codpes;

    @Column(name = "numseqema", nullable = false)
    private short numseqema;
    
    /** Creates a new instance of EmailpessoaPK */
    public EmailPessoaPK()
    {
    }

    /**
     * Cria uma nova instância de EmailpessoaPK com os valores especificados.
     * @param numseqema o numseqema do EmailpessoaPK
     * @param codpes o codpes do EmailpessoaPK
     */
    public EmailPessoaPK(short numseqema, int codpes)
    {
        this.numseqema = numseqema;
        this.codpes = codpes;
    }

    /**
     * Define o codpes deste EmailpessoaPK.
     * @return o codpes
     */
    public int getCodpes()
    {
        return this.codpes;
    }

    /**
     * Define o codpes deste EmailpessoaPK para o valor especificado.
     * @param codpes o novo codpes
     */
    public void setCodpes(int codpes)
    {
        this.codpes = codpes;
    }

    /**
     * Define o numseqema deste EmailpessoaPK.
     * @return o numseqema
     */
    public short getNumseqema()
    {
        return this.numseqema;
    }

    /**
     * Define o numseqema deste EmailpessoaPK para o valor especificado.
     * @param numseqema o novo numseqema
     */
    public void setNumseqema(short numseqema)
    {
        this.numseqema = numseqema;
    }

    /**
     * Retorna um valor de código hash para o objeto.  Esta implementação computa
     * um valor de código hash baseado nos campos id deste objeto.
     * @return um valor de código hash para este objeto.
     */
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int)numseqema;
        hash += (int)codpes;
        return hash;
    }

    /**
     * Determina se outro objeto é igual a este EmailpessoaPK.  O resultado é
     * <code>true</code> se e somente se o argumento não for nulo e for um objeto EmailpessoaPK o qual
     * tem o mesmo valor para o campo id como este objeto.
     * @param object o objeto de referência com o qual comparar
     * @return <code>true</code> se este objeto é o mesmo como o argumento;
     * <code>false</code> caso contrário.
     */
    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmailPessoaPK)) {
            return false;
        }
        EmailPessoaPK other = (EmailPessoaPK)object;
        if (this.numseqema != other.numseqema) return false;
        if (this.codpes != other.codpes) return false;
        return true;
    }

    /**
     * Retorna uma representação literal deste objeto.  Esta implementação cria
     * uma representação baseada nos campos id.
     * @return uma representação literal deste objeto.
     */
    @Override
    public String toString()
    {
        return "br.usp.poli.icaro.model.EmailpessoaPK[numseqema=" + numseqema + ", codpes=" + codpes + "]";
    }
    
}
