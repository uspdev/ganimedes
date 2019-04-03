package br.usp.model.replicado;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Classe de entidade Vinculopessoausp
 * 
 * @author marcelo.modesto
 */
@Entity
@Table(name = "VINCULOPESSOAUSP", catalog = "replicado", schema = "dbo")
public class VinculoPessoaUsp implements Serializable {
	public String getTipo() {
		return this.getVinculoPessoaUspPK().getTipvin().trim();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6677140178843009758L;

	/**
	 * Campo de chave primária embutido
	 */
	@EmbeddedId
	protected VinculoPessoaUspPK VinculoPessoaUspPK;

	@Column(name = "dtainivin", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtainivin;

	@Column(name = "dtafimvin")
	@Temporal(TemporalType.DATE)
	private Date dtafimvin;

	@Column(name = "sitatl", nullable = false)
	private char sitatl;

	@Column(name = "dtainisitatl", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtainisitatl;

	@Column(name = "codund")
	private Short codund;

	@Column(name = "codset")
	private Short codset;

	@Column(name = "codclg")
	private Short codclg;

	@Column(name = "sglclg")
	private String sglclg;

	@Column(name = "codfusclgund")
	private Short codfusclgund;

	@Column(name = "stabcocadvin", nullable = false)
	private char stabcocadvin;

	@Column(name = "numviactiusp")
	private Short numviactiusp;

	@Column(name = "codctlemi")
	private String codctlemi;

	@Column(name = "dtaemicti")
	@Temporal(TemporalType.DATE)
	private Date dtaemicti;

	@Column(name = "codfncsrvhpt")
	private Integer codfncsrvhpt;

	@Column(name = "tiping")
	private String tiping;

	@Column(name = "sitoco")
	private String sitoco;

	@Column(name = "dtainisitoco")
	@Temporal(TemporalType.DATE)
	private Date dtainisitoco;

	@Column(name = "dtafimsitoco")
	@Temporal(TemporalType.DATE)
	private Date dtafimsitoco;

	@Column(name = "nivesc")
	private Short nivesc;

	@Column(name = "codesc")
	private Short codesc;

	@Column(name = "grufor")
	private Short grufor;

	@Column(name = "stavrfsat")
	private Short stavrfsat;

	@Column(name = "codcurgrd")
	private Integer codcurgrd;

	@Column(name = "codhab")
	private Short codhab;

	@Column(name = "codare")
	private Integer codare;

	@Column(name = "nivpgm")
	private String nivpgm;

	@Column(name = "numseqpgm")
	private Short numseqpgm;

	@Column(name = "codsel")
	private Integer codsel;

	@Column(name = "nomcaa")
	private String nomcaa;

	@Column(name = "codcla")
	private Integer codcla;

	@Column(name = "nomabvcla")
	private String nomabvcla;

	@Column(name = "nivgrupvm")
	private Character nivgrupvm;

	@Column(name = "codfnc")
	private Integer codfnc;

	@Column(name = "nomabvfnc")
	private String nomabvfnc;

	@Column(name = "tipfnc")
	private String tipfnc;

	@Column(name = "dtainisitfun")
	@Temporal(TemporalType.DATE)
	private Date dtainisitfun;

	@Column(name = "tipjor")
	private String tipjor;

	@Column(name = "tipmer")
	private String tipmer;

	@Column(name = "tipcon")
	private String tipcon;

	@Column(name = "dtainidctati")
	@Temporal(TemporalType.DATE)
	private Date dtainidctati;

	@Column(name = "dtafimdctati")
	@Temporal(TemporalType.DATE)
	private Date dtafimdctati;

	@Column(name = "codpestem")
	private Integer codpestem;

	@Column(name = "gruprt")
	private String gruprt;

	@Column(name = "stadpdfol")
	private Character stadpdfol;

	@Column(name = "stadpdpenalm")
	private Character stadpdpenalm;

	@Column(name = "stadpd_hu")
	private Character stadpdHu;

	@Column(name = "stadpdcch")
	private Character stadpdcch;

	@Column(name = "dtainicms")
	@Temporal(TemporalType.DATE)
	private Date dtainicms;

	@Column(name = "dtafimcms")
	@Temporal(TemporalType.DATE)
	private Date dtafimcms;

	@Column(name = "idforicms")
	private Character idforicms;

	@Column(name = "codorgcms")
	private Integer codorgcms;

	@Column(name = "stapsuacu")
	private Character stapsuacu;

	@Column(name = "codorgacu")
	private Integer codorgacu;

	@Column(name = "dtaultalt")
	@Temporal(TemporalType.DATE)
	private Date dtaultalt;

	@Column(name = "timestamp")
	private byte[] timestamp;

	@Column(name = "dtavalctiusp")
	@Temporal(TemporalType.DATE)
	private Date dtavalctiusp;

	@Column(name = "nompes")
	private String nompes;

	@Column(name = "nompesfon")
	private String nompesfon;

	@Transient
	private String local;

	/** Creates a new instance of VinculoPessoaUsp */
	public VinculoPessoaUsp() {
	}

	/**
	 * Cria uma nova instância de VinculoPessoaUsp com os valores especificados.
	 * 
	 * @param VinculoPessoaUspPK
	 *          o VinculoPessoaUspPK do VinculoPessoaUsp
	 */
	public VinculoPessoaUsp(VinculoPessoaUspPK VinculoPessoaUspPK) {
		this.VinculoPessoaUspPK = VinculoPessoaUspPK;
	}

	/**
	 * Cria uma nova instância de VinculoPessoaUsp com os valores especificados.
	 * 
	 * @param VinculoPessoaUspPK
	 *          o VinculoPessoaUspPK do VinculoPessoaUsp
	 * @param dtainivin
	 *          o dtainivin do VinculoPessoaUsp
	 * @param sitatl
	 *          o sitatl do VinculoPessoaUsp
	 * @param dtainisitatl
	 *          o dtainisitatl do VinculoPessoaUsp
	 * @param stabcocadvin
	 *          o stabcocadvin do VinculoPessoaUsp
	 */
	public VinculoPessoaUsp(VinculoPessoaUspPK VinculoPessoaUspPK, Date dtainivin, char sitatl, Date dtainisitatl, char stabcocadvin) {
		this.VinculoPessoaUspPK = VinculoPessoaUspPK;
		this.dtainivin = dtainivin;
		this.sitatl = sitatl;
		this.dtainisitatl = dtainisitatl;
		this.stabcocadvin = stabcocadvin;
	}

	/**
	 * Cria uma nova instância de VinculoPessoaUspPK com os valores especificados.
	 * 
	 * @param tipvin
	 *          o tipvin do VinculoPessoaUspPK
	 * @param numseqpes
	 *          o numseqpes do VinculoPessoaUspPK
	 * @param codpes
	 *          o codpes do VinculoPessoaUspPK
	 */
	public VinculoPessoaUsp(String tipvin, short numseqpes, int codpes) {
		this.VinculoPessoaUspPK = new VinculoPessoaUspPK(tipvin, numseqpes, codpes);
	}

	/**
	 * Define o VinculoPessoaUspPK deste VinculoPessoaUsp.
	 * 
	 * @return o VinculoPessoaUspPK
	 */
	public VinculoPessoaUspPK getVinculoPessoaUspPK() {
		return this.VinculoPessoaUspPK;
	}

	/**
	 * Define o VinculoPessoaUspPK deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param VinculoPessoaUspPK
	 *          o novo VinculoPessoaUspPK
	 */
	public void setVinculoPessoaUspPK(VinculoPessoaUspPK VinculoPessoaUspPK) {
		this.VinculoPessoaUspPK = VinculoPessoaUspPK;
	}

	/**
	 * Define o dtainivin deste VinculoPessoaUsp.
	 * 
	 * @return o dtainivin
	 */
	public Date getDtainivin() {
		return this.dtainivin;
	}

	/**
	 * Define o dtainivin deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param dtainivin
	 *          o novo dtainivin
	 */
	public void setDtainivin(Date dtainivin) {
		this.dtainivin = dtainivin;
	}

	/**
	 * Define o dtafimvin deste VinculoPessoaUsp.
	 * 
	 * @return o dtafimvin
	 */
	public Date getDtafimvin() {
		return this.dtafimvin;
	}

	/**
	 * Define o dtafimvin deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param dtafimvin
	 *          o novo dtafimvin
	 */
	public void setDtafimvin(Date dtafimvin) {
		this.dtafimvin = dtafimvin;
	}

	/**
	 * Define o sitatl deste VinculoPessoaUsp.
	 * 
	 * @return o sitatl
	 */
	public char getSitatl() {
		return this.sitatl;
	}

	/**
	 * Define o sitatl deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param sitatl
	 *          o novo sitatl
	 */
	public void setSitatl(char sitatl) {
		this.sitatl = sitatl;
	}

	/**
	 * Define o dtainisitatl deste VinculoPessoaUsp.
	 * 
	 * @return o dtainisitatl
	 */
	public Date getDtainisitatl() {
		return this.dtainisitatl;
	}

	/**
	 * Define o dtainisitatl deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param dtainisitatl
	 *          o novo dtainisitatl
	 */
	public void setDtainisitatl(Date dtainisitatl) {
		this.dtainisitatl = dtainisitatl;
	}

	/**
	 * Define o codund deste VinculoPessoaUsp.
	 * 
	 * @return o codund
	 */
	public Short getCodund() {
		return this.codund;
	}

	/**
	 * Define o codund deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param codund
	 *          o novo codund
	 */
	public void setCodund(Short codund) {
		this.codund = codund;
	}

	/**
	 * Define o codset deste VinculoPessoaUsp.
	 * 
	 * @return o codset
	 */
	public Short getCodset() {
		return this.codset;
	}

	/**
	 * Define o codset deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param codset
	 *          o novo codset
	 */
	public void setCodset(Short codset) {
		this.codset = codset;
	}

	/**
	 * Define o codclg deste VinculoPessoaUsp.
	 * 
	 * @return o codclg
	 */
	public Short getCodclg() {
		return this.codclg;
	}

	/**
	 * Define o codclg deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param codclg
	 *          o novo codclg
	 */
	public void setCodclg(Short codclg) {
		this.codclg = codclg;
	}

	/**
	 * Define o sglclg deste VinculoPessoaUsp.
	 * 
	 * @return o sglclg
	 */
	public String getSglclg() {
		return this.sglclg;
	}

	/**
	 * Define o sglclg deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param sglclg
	 *          o novo sglclg
	 */
	public void setSglclg(String sglclg) {
		this.sglclg = sglclg;
	}

	/**
	 * Define o codfusclgund deste VinculoPessoaUsp.
	 * 
	 * @return o codfusclgund
	 */
	public Short getCodfusclgund() {
		return this.codfusclgund;
	}

	/**
	 * Define o codfusclgund deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param codfusclgund
	 *          o novo codfusclgund
	 */
	public void setCodfusclgund(Short codfusclgund) {
		this.codfusclgund = codfusclgund;
	}

	/**
	 * Define o stabcocadvin deste VinculoPessoaUsp.
	 * 
	 * @return o stabcocadvin
	 */
	public char getStabcocadvin() {
		return this.stabcocadvin;
	}

	/**
	 * Define o stabcocadvin deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param stabcocadvin
	 *          o novo stabcocadvin
	 */
	public void setStabcocadvin(char stabcocadvin) {
		this.stabcocadvin = stabcocadvin;
	}

	/**
	 * Define o numviactiusp deste VinculoPessoaUsp.
	 * 
	 * @return o numviactiusp
	 */
	public Short getNumviactiusp() {
		return this.numviactiusp;
	}

	/**
	 * Define o numviactiusp deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param numviactiusp
	 *          o novo numviactiusp
	 */
	public void setNumviactiusp(Short numviactiusp) {
		this.numviactiusp = numviactiusp;
	}

	/**
	 * Define o codctlemi deste VinculoPessoaUsp.
	 * 
	 * @return o codctlemi
	 */
	public String getCodctlemi() {
		return this.codctlemi;
	}

	/**
	 * Define o codctlemi deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param codctlemi
	 *          o novo codctlemi
	 */
	public void setCodctlemi(String codctlemi) {
		this.codctlemi = codctlemi;
	}

	/**
	 * Define o dtaemicti deste VinculoPessoaUsp.
	 * 
	 * @return o dtaemicti
	 */
	public Date getDtaemicti() {
		return this.dtaemicti;
	}

	/**
	 * Define o dtaemicti deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param dtaemicti
	 *          o novo dtaemicti
	 */
	public void setDtaemicti(Date dtaemicti) {
		this.dtaemicti = dtaemicti;
	}

	/**
	 * Define o codfncsrvhpt deste VinculoPessoaUsp.
	 * 
	 * @return o codfncsrvhpt
	 */
	public Integer getCodfncsrvhpt() {
		return this.codfncsrvhpt;
	}

	/**
	 * Define o codfncsrvhpt deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param codfncsrvhpt
	 *          o novo codfncsrvhpt
	 */
	public void setCodfncsrvhpt(Integer codfncsrvhpt) {
		this.codfncsrvhpt = codfncsrvhpt;
	}

	/**
	 * Define o tiping deste VinculoPessoaUsp.
	 * 
	 * @return o tiping
	 */
	public String getTiping() {
		return this.tiping;
	}

	/**
	 * Define o tiping deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param tiping
	 *          o novo tiping
	 */
	public void setTiping(String tiping) {
		this.tiping = tiping;
	}

	/**
	 * Define o sitoco deste VinculoPessoaUsp.
	 * 
	 * @return o sitoco
	 */
	public String getSitoco() {
		return this.sitoco;
	}

	/**
	 * Define o sitoco deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param sitoco
	 *          o novo sitoco
	 */
	public void setSitoco(String sitoco) {
		this.sitoco = sitoco;
	}

	/**
	 * Define o dtainisitoco deste VinculoPessoaUsp.
	 * 
	 * @return o dtainisitoco
	 */
	public Date getDtainisitoco() {
		return this.dtainisitoco;
	}

	/**
	 * Define o dtainisitoco deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param dtainisitoco
	 *          o novo dtainisitoco
	 */
	public void setDtainisitoco(Date dtainisitoco) {
		this.dtainisitoco = dtainisitoco;
	}

	/**
	 * Define o dtafimsitoco deste VinculoPessoaUsp.
	 * 
	 * @return o dtafimsitoco
	 */
	public Date getDtafimsitoco() {
		return this.dtafimsitoco;
	}

	/**
	 * Define o dtafimsitoco deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param dtafimsitoco
	 *          o novo dtafimsitoco
	 */
	public void setDtafimsitoco(Date dtafimsitoco) {
		this.dtafimsitoco = dtafimsitoco;
	}

	/**
	 * Define o nivesc deste VinculoPessoaUsp.
	 * 
	 * @return o nivesc
	 */
	public Short getNivesc() {
		return this.nivesc;
	}

	/**
	 * Define o nivesc deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param nivesc
	 *          o novo nivesc
	 */
	public void setNivesc(Short nivesc) {
		this.nivesc = nivesc;
	}

	/**
	 * Define o codesc deste VinculoPessoaUsp.
	 * 
	 * @return o codesc
	 */
	public Short getCodesc() {
		return this.codesc;
	}

	/**
	 * Define o codesc deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param codesc
	 *          o novo codesc
	 */
	public void setCodesc(Short codesc) {
		this.codesc = codesc;
	}

	/**
	 * Define o grufor deste VinculoPessoaUsp.
	 * 
	 * @return o grufor
	 */
	public Short getGrufor() {
		return this.grufor;
	}

	/**
	 * Define o grufor deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param grufor
	 *          o novo grufor
	 */
	public void setGrufor(Short grufor) {
		this.grufor = grufor;
	}

	/**
	 * Define o stavrfsat deste VinculoPessoaUsp.
	 * 
	 * @return o stavrfsat
	 */
	public Short getStavrfsat() {
		return this.stavrfsat;
	}

	/**
	 * Define o stavrfsat deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param stavrfsat
	 *          o novo stavrfsat
	 */
	public void setStavrfsat(Short stavrfsat) {
		this.stavrfsat = stavrfsat;
	}

	/**
	 * Define o codcurgrd deste VinculoPessoaUsp.
	 * 
	 * @return o codcurgrd
	 */
	public Integer getCodcurgrd() {
		return this.codcurgrd;
	}

	/**
	 * Define o codcurgrd deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param codcurgrd
	 *          o novo codcurgrd
	 */
	public void setCodcurgrd(Integer codcurgrd) {
		this.codcurgrd = codcurgrd;
	}

	/**
	 * Define o codhab deste VinculoPessoaUsp.
	 * 
	 * @return o codhab
	 */
	public Short getCodhab() {
		return this.codhab;
	}

	/**
	 * Define o codhab deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param codhab
	 *          o novo codhab
	 */
	public void setCodhab(Short codhab) {
		this.codhab = codhab;
	}

	/**
	 * Define o codare deste VinculoPessoaUsp.
	 * 
	 * @return o codare
	 */
	public Integer getCodare() {
		return this.codare;
	}

	/**
	 * Define o codare deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param codare
	 *          o novo codare
	 */
	public void setCodare(Integer codare) {
		this.codare = codare;
	}

	/**
	 * Define o nivpgm deste VinculoPessoaUsp.
	 * 
	 * @return o nivpgm
	 */
	public String getNivpgm() {
		return this.nivpgm;
	}

	/**
	 * Define o nivpgm deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param nivpgm
	 *          o novo nivpgm
	 */
	public void setNivpgm(String nivpgm) {
		this.nivpgm = nivpgm;
	}

	/**
	 * Define o numseqpgm deste VinculoPessoaUsp.
	 * 
	 * @return o numseqpgm
	 */
	public Short getNumseqpgm() {
		return this.numseqpgm;
	}

	/**
	 * Define o numseqpgm deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param numseqpgm
	 *          o novo numseqpgm
	 */
	public void setNumseqpgm(Short numseqpgm) {
		this.numseqpgm = numseqpgm;
	}

	/**
	 * Define o codsel deste VinculoPessoaUsp.
	 * 
	 * @return o codsel
	 */
	public Integer getCodsel() {
		return this.codsel;
	}

	/**
	 * Define o codsel deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param codsel
	 *          o novo codsel
	 */
	public void setCodsel(Integer codsel) {
		this.codsel = codsel;
	}

	/**
	 * Define o nomcaa deste VinculoPessoaUsp.
	 * 
	 * @return o nomcaa
	 */
	public String getNomcaa() {
		return this.nomcaa;
	}

	/**
	 * Define o nomcaa deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param nomcaa
	 *          o novo nomcaa
	 */
	public void setNomcaa(String nomcaa) {
		this.nomcaa = nomcaa;
	}

	/**
	 * Define o codcla deste VinculoPessoaUsp.
	 * 
	 * @return o codcla
	 */
	public Integer getCodcla() {
		return this.codcla;
	}

	/**
	 * Define o codcla deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param codcla
	 *          o novo codcla
	 */
	public void setCodcla(Integer codcla) {
		this.codcla = codcla;
	}

	/**
	 * Define o nomabvcla deste VinculoPessoaUsp.
	 * 
	 * @return o nomabvcla
	 */
	public String getNomabvcla() {
		return this.nomabvcla;
	}

	/**
	 * Define o nomabvcla deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param nomabvcla
	 *          o novo nomabvcla
	 */
	public void setNomabvcla(String nomabvcla) {
		this.nomabvcla = nomabvcla;
	}

	/**
	 * Define o nivgrupvm deste VinculoPessoaUsp.
	 * 
	 * @return o nivgrupvm
	 */
	public Character getNivgrupvm() {
		return this.nivgrupvm;
	}

	/**
	 * Define o nivgrupvm deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param nivgrupvm
	 *          o novo nivgrupvm
	 */
	public void setNivgrupvm(Character nivgrupvm) {
		this.nivgrupvm = nivgrupvm;
	}

	/**
	 * Define o codfnc deste VinculoPessoaUsp.
	 * 
	 * @return o codfnc
	 */
	public Integer getCodfnc() {
		return this.codfnc;
	}

	/**
	 * Define o codfnc deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param codfnc
	 *          o novo codfnc
	 */
	public void setCodfnc(Integer codfnc) {
		this.codfnc = codfnc;
	}

	/**
	 * Define o nomabvfnc deste VinculoPessoaUsp.
	 * 
	 * @return o nomabvfnc
	 */
	public String getNomabvfnc() {
		return this.nomabvfnc;
	}

	/**
	 * Define o nomabvfnc deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param nomabvfnc
	 *          o novo nomabvfnc
	 */
	public void setNomabvfnc(String nomabvfnc) {
		this.nomabvfnc = nomabvfnc;
	}

	/**
	 * Define o tipfnc deste VinculoPessoaUsp.
	 * 
	 * @return o tipfnc
	 */
	public String getTipfnc() {
		return this.tipfnc;
	}

	/**
	 * Define o tipfnc deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param tipfnc
	 *          o novo tipfnc
	 */
	public void setTipfnc(String tipfnc) {
		this.tipfnc = tipfnc;
	}

	/**
	 * Define o dtainisitfun deste VinculoPessoaUsp.
	 * 
	 * @return o dtainisitfun
	 */
	public Date getDtainisitfun() {
		return this.dtainisitfun;
	}

	/**
	 * Define o dtainisitfun deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param dtainisitfun
	 *          o novo dtainisitfun
	 */
	public void setDtainisitfun(Date dtainisitfun) {
		this.dtainisitfun = dtainisitfun;
	}

	/**
	 * Define o tipjor deste VinculoPessoaUsp.
	 * 
	 * @return o tipjor
	 */
	public String getTipjor() {
		return this.tipjor;
	}

	/**
	 * Define o tipjor deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param tipjor
	 *          o novo tipjor
	 */
	public void setTipjor(String tipjor) {
		this.tipjor = tipjor;
	}

	/**
	 * Define o tipmer deste VinculoPessoaUsp.
	 * 
	 * @return o tipmer
	 */
	public String getTipmer() {
		return this.tipmer;
	}

	/**
	 * Define o tipmer deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param tipmer
	 *          o novo tipmer
	 */
	public void setTipmer(String tipmer) {
		this.tipmer = tipmer;
	}

	/**
	 * Define o tipcon deste VinculoPessoaUsp.
	 * 
	 * @return o tipcon
	 */
	public String getTipcon() {
		return this.tipcon;
	}

	/**
	 * Define o tipcon deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param tipcon
	 *          o novo tipcon
	 */
	public void setTipcon(String tipcon) {
		this.tipcon = tipcon;
	}

	/**
	 * Define o dtainidctati deste VinculoPessoaUsp.
	 * 
	 * @return o dtainidctati
	 */
	public Date getDtainidctati() {
		return this.dtainidctati;
	}

	/**
	 * Define o dtainidctati deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param dtainidctati
	 *          o novo dtainidctati
	 */
	public void setDtainidctati(Date dtainidctati) {
		this.dtainidctati = dtainidctati;
	}

	/**
	 * Define o dtafimdctati deste VinculoPessoaUsp.
	 * 
	 * @return o dtafimdctati
	 */
	public Date getDtafimdctati() {
		return this.dtafimdctati;
	}

	/**
	 * Define o dtafimdctati deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param dtafimdctati
	 *          o novo dtafimdctati
	 */
	public void setDtafimdctati(Date dtafimdctati) {
		this.dtafimdctati = dtafimdctati;
	}

	/**
	 * Define o codpestem deste VinculoPessoaUsp.
	 * 
	 * @return o codpestem
	 */
	public Integer getCodpestem() {
		return this.codpestem;
	}

	/**
	 * Define o codpestem deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param codpestem
	 *          o novo codpestem
	 */
	public void setCodpestem(Integer codpestem) {
		this.codpestem = codpestem;
	}

	/**
	 * Define o gruprt deste VinculoPessoaUsp.
	 * 
	 * @return o gruprt
	 */
	public String getGruprt() {
		return this.gruprt;
	}

	/**
	 * Define o gruprt deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param gruprt
	 *          o novo gruprt
	 */
	public void setGruprt(String gruprt) {
		this.gruprt = gruprt;
	}

	/**
	 * Define o stadpdfol deste VinculoPessoaUsp.
	 * 
	 * @return o stadpdfol
	 */
	public Character getStadpdfol() {
		return this.stadpdfol;
	}

	/**
	 * Define o stadpdfol deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param stadpdfol
	 *          o novo stadpdfol
	 */
	public void setStadpdfol(Character stadpdfol) {
		this.stadpdfol = stadpdfol;
	}

	/**
	 * Define o stadpdpenalm deste VinculoPessoaUsp.
	 * 
	 * @return o stadpdpenalm
	 */
	public Character getStadpdpenalm() {
		return this.stadpdpenalm;
	}

	/**
	 * Define o stadpdpenalm deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param stadpdpenalm
	 *          o novo stadpdpenalm
	 */
	public void setStadpdpenalm(Character stadpdpenalm) {
		this.stadpdpenalm = stadpdpenalm;
	}

	/**
	 * Define o stadpdHu deste VinculoPessoaUsp.
	 * 
	 * @return o stadpdHu
	 */
	public Character getStadpdHu() {
		return this.stadpdHu;
	}

	/**
	 * Define o stadpdHu deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param stadpdHu
	 *          o novo stadpdHu
	 */
	public void setStadpdHu(Character stadpdHu) {
		this.stadpdHu = stadpdHu;
	}

	/**
	 * Define o stadpdcch deste VinculoPessoaUsp.
	 * 
	 * @return o stadpdcch
	 */
	public Character getStadpdcch() {
		return this.stadpdcch;
	}

	/**
	 * Define o stadpdcch deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param stadpdcch
	 *          o novo stadpdcch
	 */
	public void setStadpdcch(Character stadpdcch) {
		this.stadpdcch = stadpdcch;
	}

	/**
	 * Define o dtainicms deste VinculoPessoaUsp.
	 * 
	 * @return o dtainicms
	 */
	public Date getDtainicms() {
		return this.dtainicms;
	}

	/**
	 * Define o dtainicms deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param dtainicms
	 *          o novo dtainicms
	 */
	public void setDtainicms(Date dtainicms) {
		this.dtainicms = dtainicms;
	}

	/**
	 * Define o dtafimcms deste VinculoPessoaUsp.
	 * 
	 * @return o dtafimcms
	 */
	public Date getDtafimcms() {
		return this.dtafimcms;
	}

	/**
	 * Define o dtafimcms deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param dtafimcms
	 *          o novo dtafimcms
	 */
	public void setDtafimcms(Date dtafimcms) {
		this.dtafimcms = dtafimcms;
	}

	/**
	 * Define o idforicms deste VinculoPessoaUsp.
	 * 
	 * @return o idforicms
	 */
	public Character getIdforicms() {
		return this.idforicms;
	}

	/**
	 * Define o idforicms deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param idforicms
	 *          o novo idforicms
	 */
	public void setIdforicms(Character idforicms) {
		this.idforicms = idforicms;
	}

	/**
	 * Define o codorgcms deste VinculoPessoaUsp.
	 * 
	 * @return o codorgcms
	 */
	public Integer getCodorgcms() {
		return this.codorgcms;
	}

	/**
	 * Define o codorgcms deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param codorgcms
	 *          o novo codorgcms
	 */
	public void setCodorgcms(Integer codorgcms) {
		this.codorgcms = codorgcms;
	}

	/**
	 * Define o stapsuacu deste VinculoPessoaUsp.
	 * 
	 * @return o stapsuacu
	 */
	public Character getStapsuacu() {
		return this.stapsuacu;
	}

	/**
	 * Define o stapsuacu deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param stapsuacu
	 *          o novo stapsuacu
	 */
	public void setStapsuacu(Character stapsuacu) {
		this.stapsuacu = stapsuacu;
	}

	/**
	 * Define o codorgacu deste VinculoPessoaUsp.
	 * 
	 * @return o codorgacu
	 */
	public Integer getCodorgacu() {
		return this.codorgacu;
	}

	/**
	 * Define o codorgacu deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param codorgacu
	 *          o novo codorgacu
	 */
	public void setCodorgacu(Integer codorgacu) {
		this.codorgacu = codorgacu;
	}

	/**
	 * Define o dtaultalt deste VinculoPessoaUsp.
	 * 
	 * @return o dtaultalt
	 */
	public Date getDtaultalt() {
		return this.dtaultalt;
	}

	/**
	 * Define o dtaultalt deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param dtaultalt
	 *          o novo dtaultalt
	 */
	public void setDtaultalt(Date dtaultalt) {
		this.dtaultalt = dtaultalt;
	}

	/**
	 * Define o timestamp deste VinculoPessoaUsp.
	 * 
	 * @return o timestamp
	 */
	public byte[] getTimestamp() {
		return this.timestamp;
	}

	/**
	 * Define o timestamp deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param timestamp
	 *          o novo timestamp
	 */
	public void setTimestamp(byte[] timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Define o dtavalctiusp deste VinculoPessoaUsp.
	 * 
	 * @return o dtavalctiusp
	 */
	public Date getDtavalctiusp() {
		return this.dtavalctiusp;
	}

	/**
	 * Define o dtavalctiusp deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param dtavalctiusp
	 *          o novo dtavalctiusp
	 */
	public void setDtavalctiusp(Date dtavalctiusp) {
		this.dtavalctiusp = dtavalctiusp;
	}

	/**
	 * Define o nompes deste VinculoPessoaUsp.
	 * 
	 * @return o nompes
	 */
	public String getNompes() {
		return this.nompes;
	}

	/**
	 * Define o nompes deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param nompes
	 *          o novo nompes
	 */
	public void setNompes(String nompes) {
		this.nompes = nompes;
	}

	/**
	 * Define o nompesfon deste VinculoPessoaUsp.
	 * 
	 * @return o nompesfon
	 */
	public String getNompesfon() {
		return this.nompesfon;
	}

	/**
	 * Define o nompesfon deste VinculoPessoaUsp para o valor especificado.
	 * 
	 * @param nompesfon
	 *          o novo nompesfon
	 */
	public void setNompesfon(String nompesfon) {
		this.nompesfon = nompesfon;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	/**
	 * Retorna um valor de código hash para o objeto. Esta implementação computa um valor de código hash baseado nos campos id deste objeto.
	 * 
	 * @return um valor de código hash para este objeto.
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (this.VinculoPessoaUspPK != null ? this.VinculoPessoaUspPK.hashCode() : 0);
		return hash;
	}

	/**
	 * Determina se outro objeto é igual a este VinculoPessoaUsp. O resultado é <code>true</code> se e somente se o argumento não for nulo e for um
	 * objeto VinculoPessoaUsp o qual tem o mesmo valor para o campo id como este objeto.
	 * 
	 * @param object
	 *          o objeto de referência com o qual comparar
	 * @return <code>true</code> se este objeto é o mesmo como o argumento; <code>false</code> caso contrário.
	 */
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof VinculoPessoaUsp)) {
			return false;
		}
		VinculoPessoaUsp other = (VinculoPessoaUsp) object;
		if (this.VinculoPessoaUspPK != other.VinculoPessoaUspPK
				&& (this.VinculoPessoaUspPK == null || !this.VinculoPessoaUspPK.equals(other.VinculoPessoaUspPK)))
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
		return "br.usp.poli.icaro.model.VinculoPessoaUsp[VinculoPessoaUspPK=" + VinculoPessoaUspPK + "]";
	}

}
