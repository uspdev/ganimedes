package br.usp.ime.ganimedes.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.usp.model.replicado.VinculoPessoaUsp;

@Entity
@Table(name = "ALUNO")
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer codpes;

	@OneToMany(mappedBy = "aluno", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<Estagio> estagios = new ArrayList<Estagio>();

	private String nompes;

	@Transient
	private String tipdocidf;
	@Transient
	private String numdocidf;
	@Transient
	private Date dtaexdidf;
	@Transient
	private String sglorgexdidf;
	@Transient
	private String sglest;
	@Transient
	private Character sexpes;
	@Transient
	private String numcpf;
	@Transient
	private Date dtanas;
	@Transient
	private String tipvin;
	@Transient
	private char sitatl;
	@Transient
	private Date dtainivin;
	@Transient
	private Date dtafimvin;
	@Transient
	private String tiping;
	@Transient
	private String sitoco;
	@Transient
	private int codcur;
	@Transient
	private short codhab;
	@Transient
	private String nomcur;
	@Transient
	private String nomhab;
	@Transient
	Short numsemidl;
	@Transient
	Integer codare;

	@Transient
	String observacao;

	@Transient
	String email;

	@Transient
	List<VinculoPessoaUsp> vinculos = new ArrayList<VinculoPessoaUsp>();

	/* construtor para aluno de graduacao */
	public Aluno(Integer codpes, String nompes, String tipdocidf, String numdocidf, Date dtaexdidf, String sglorgexdidf, String sglest,
			Character sexpes, String numcpf, Date dtanas, String tipvin, char sitatl, Date dtainivin, String tiping, String sitoco, int codcur,
			short codhab, Short numsemidl) {

		super();
		this.codpes = codpes;
		this.nompes = nompes;
		this.tipdocidf = tipdocidf;
		this.numdocidf = numdocidf;
		this.dtaexdidf = dtaexdidf;
		this.sglorgexdidf = sglorgexdidf;
		this.sglest = sglest;
		this.sexpes = sexpes;
		this.numcpf = numcpf;
		this.dtanas = dtanas;
		this.tipvin = tipvin;
		this.sitatl = sitatl;
		this.dtainivin = dtainivin;
		this.tiping = tiping;
		this.sitoco = sitoco;
		this.codcur = codcur;
		this.codhab = codhab;
		this.numsemidl = numsemidl;
	}

	/* construtor para aluno de pos */
	public Aluno(Integer codpes, String nompes, String tipdocidf, String numdocidf, Date dtaexdidf, String sglorgexdidf, String sglest,
			Character sexpes, String numcpf, Date dtanas, String tipvin, char sitatl, Date dtainivin, String tiping, String sitoco, int codcur,
			Integer codare, String nomcur) {

		super();
		this.codpes = codpes;
		this.nompes = nompes;
		this.tipdocidf = tipdocidf;
		this.numdocidf = numdocidf;
		this.dtaexdidf = dtaexdidf;
		this.sglorgexdidf = sglorgexdidf;
		this.sglest = sglest;
		this.sexpes = sexpes;
		this.numcpf = numcpf;
		this.dtanas = dtanas;
		this.tipvin = tipvin;
		this.sitatl = sitatl;
		this.dtainivin = dtainivin;
		this.tiping = tiping;
		this.sitoco = sitoco;
		this.codcur = codcur;
		this.codare = codare;
		this.nomcur = nomcur;
	}

	public String getCpfMask() {
		try {
			return numcpf.substring(0, 3) + "." + numcpf.substring(3, 6) + "." + numcpf.substring(6, 9) + "-" + numcpf.substring(9);
		} catch (NullPointerException e) {
			return null;
		}
	}

	public String getFoto() {
		String foto = "/resources/fotos/semfoto.jpg";
		// String foto = "/resources/fotos/" + this.getCodpes() + ".jpg";

		File f = new File(foto);
		if (!f.exists()) {
			return null;
		}

		return foto;
	}

	public Aluno(Integer codpes) {
		super();
		this.codpes = codpes;
	}

	public Aluno() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getCodpes() {
		return codpes;
	}

	public void setCodpes(Integer codpes) {
		this.codpes = codpes;
	}

	public String getNompes() {
		return nompes;
	}

	public void setNompes(String nompes) {
		this.nompes = nompes;
	}

	public String getTipdocidf() {
		return tipdocidf;
	}

	public void setTipdocidf(String tipdocidf) {
		this.tipdocidf = tipdocidf;
	}

	public String getNumdocidf() {
		return numdocidf;
	}

	public void setNumdocidf(String numdocidf) {
		this.numdocidf = numdocidf;
	}

	public Date getDtaexdidf() {
		return dtaexdidf;
	}

	public void setDtaexdidf(Date dtaexdidf) {
		this.dtaexdidf = dtaexdidf;
	}

	public String getSglorgexdidf() {
		return sglorgexdidf;
	}

	public void setSglorgexdidf(String sglorgexdidf) {
		this.sglorgexdidf = sglorgexdidf;
	}

	public String getSglest() {
		return sglest;
	}

	public void setSglest(String sglest) {
		this.sglest = sglest;
	}

	public Character getSexpes() {
		return sexpes;
	}

	public void setSexpes(Character sexpes) {
		this.sexpes = sexpes;
	}

	public String getNumcpf() {
		return numcpf;
	}

	public void setNumcpf(String numcpf) {
		this.numcpf = numcpf;
	}

	public Date getDtanas() {
		return dtanas;
	}

	public void setDtanas(Date dtanas) {
		this.dtanas = dtanas;
	}

	public String getTipvin() {
		return tipvin;
	}

	public void setTipvin(String tipvin) {
		this.tipvin = tipvin;
	}

	public char getSitatl() {
		return sitatl;
	}

	public void setSitatl(char sitatl) {
		this.sitatl = sitatl;
	}

	public Date getDtainivin() {
		return dtainivin;
	}

	public void setDtainivin(Date dtainivin) {
		this.dtainivin = dtainivin;
	}

	public Date getDtafimvin() {
		return dtafimvin;
	}

	public void setDtafimvin(Date dtafimvin) {
		this.dtafimvin = dtafimvin;
	}

	public String getTiping() {
		return tiping;
	}

	public void setTiping(String tiping) {
		this.tiping = tiping;
	}

	public String getSitoco() {
		return sitoco;
	}

	public void setSitoco(String sitoco) {
		this.sitoco = sitoco;
	}

	public int getCodcur() {
		return codcur;
	}

	public void setCodcur(int codcur) {
		this.codcur = codcur;
	}

	public short getCodhab() {
		return codhab;
	}

	public void setCodhab(short codhab) {
		this.codhab = codhab;
	}

	public String getNomcur() {
		return nomcur;
	}

	public void setNomcur(String nomcur) {
		this.nomcur = nomcur;
	}

	public String getNomhab() {
		return nomhab;
	}

	public void setNomhab(String nomhab) {
		this.nomhab = nomhab;
	}

	public List<Estagio> getEstagios() {
		return estagios;
	}

	public void setEstagios(List<Estagio> estagios) {
		this.estagios = estagios;
	}

	public Short getNumsemidl() {
		return numsemidl;
	}

	public void setNumsemidl(Short numsemidl) {
		this.numsemidl = numsemidl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<VinculoPessoaUsp> getVinculos() {
		return vinculos;
	}

	public void setVinculos(List<VinculoPessoaUsp> vinculos) {
		this.vinculos = vinculos;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codpes == null) ? 0 : codpes.hashCode());
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
		Aluno other = (Aluno) obj;
		if (codpes == null) {
			if (other.codpes != null)
				return false;
		} else if (!codpes.equals(other.codpes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aluno [codpes=" + codpes + ", nompes=" + nompes + "]";
	}

}