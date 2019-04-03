package br.usp.ime.ganimedes.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ANUNCIO")
public class Anuncio implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private EStatusAnuncio status;

	@Column(nullable = false)
	private String nomeEmpresa;

	@Lob
	@Column(columnDefinition = "TEXT (4000)")
	private String descricaoEmpresa;

	@Column(nullable = false)
	private String areaAtuacao;

	@Column(nullable = false)
	private String nompes;

	@Column(nullable = false)
	private String numtel;

	@Column(nullable = false)
	private String urlweb;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String loctrb;

	@Column(nullable = false)
	private String titvag;

	@Lob
	@Column(columnDefinition = "TEXT (4000)")
	private String desvag;

	private ERegime regimeTrabalho;

	@Column(nullable = false)
	private Integer horsem;

	@Lob
	@Column(columnDefinition = "TEXT (4000)")
	private String benofr;

	private Double salmes;

	@Lob
	@Column(columnDefinition = "TEXT (4000)")
	private String perfilCandidato;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Curso> cursos = new ArrayList<Curso>();

	@Temporal(TemporalType.DATE)
	private Date dtainidiv;

	@Temporal(TemporalType.DATE)
	private Date dtafimdiv;

	private String emailInscricao;

	private String numtelInscricao;

	private boolean repchk;

	private boolean repcod;

	private boolean reppwd;

	private String ip;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtarepchk;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaCriacao;

	private boolean aprovado;

	private boolean aprovadoPor;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaAprovacao;

	@Lob
	private byte[] logotipo;

	@Lob
	private byte[] cartaz;

	@ManyToOne
	private Usuario usuario;

	public Anuncio() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	public String getNompes() {
		return nompes;
	}

	public void setNompes(String nompes) {
		this.nompes = nompes;
	}

	public String getNumtel() {
		return numtel;
	}

	public void setNumtel(String numtel) {
		this.numtel = numtel;
	}

	public String getUrlweb() {
		return urlweb;
	}

	public void setUrlweb(String urlweb) {
		this.urlweb = urlweb;
	}

	public String getLoctrb() {
		return loctrb;
	}

	public void setLoctrb(String loctrb) {
		this.loctrb = loctrb;
	}

	public String getTitvag() {
		return titvag;
	}

	public void setTitvag(String titvag) {
		this.titvag = titvag;
	}

	public String getDesvag() {
		return desvag;
	}

	public void setDesvag(String desvag) {
		this.desvag = desvag;
	}

	public ERegime getRegimeTrabalho() {
		return regimeTrabalho;
	}

	public void setRegimeTrabalho(ERegime regimeTrabalho) {
		this.regimeTrabalho = regimeTrabalho;
	}

	public Integer getHorsem() {
		return horsem;
	}

	public void setHorsem(Integer horsem) {
		this.horsem = horsem;
	}

	public String getBenofr() {
		return benofr;
	}

	public void setBenofr(String benofr) {
		this.benofr = benofr;
	}

	public Double getSalmes() {
		return salmes;
	}
	
	public String getDtaCriacaoFormatado() {
		DateFormat df = DateFormat.getDateInstance(1, new Locale("pt", "BR"));
		String data = df.format(this.getDtaCriacao());
		return data ;
	}

	public String getSalmesFormatado() {
		String salmes = "A combinar";

		try {
			if (this.getSalmes() > 0) {
				NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

				salmes = nf.format(this.getSalmes());
			}
		} catch (NullPointerException | IllegalArgumentException e) {
			salmes = "A combinar";
		}

		return salmes;
	}

	public void setSalmes(Double salmes) {
		this.salmes = salmes;
	}

	public String getPerfilCandidato() {
		return perfilCandidato;
	}

	public void setPerfilCandidato(String perfilCandidato) {
		this.perfilCandidato = perfilCandidato;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public Date getDtainidiv() {
		return dtainidiv;
	}

	public void setDtainidiv(Date dtainidiv) {
		this.dtainidiv = dtainidiv;
	}

	public Date getDtafimdiv() {
		return dtafimdiv;
	}

	public void setDtafimdiv(Date dtafimdiv) {
		this.dtafimdiv = dtafimdiv;
	}

	public boolean isRepchk() {
		return repchk;
	}

	public void setRepchk(boolean repchk) {
		this.repchk = repchk;
	}

	public boolean isRepcod() {
		return repcod;
	}

	public void setRepcod(boolean repcod) {
		this.repcod = repcod;
	}

	public boolean isReppwd() {
		return reppwd;
	}

	public void setReppwd(boolean reppwd) {
		this.reppwd = reppwd;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getDtarepchk() {
		return dtarepchk;
	}

	public void setDtarepchk(Date dtarepchk) {
		this.dtarepchk = dtarepchk;
	}

	public Date getDtaCriacao() {
		return dtaCriacao;
	}

	public void setDtaCriacao(Date dtaCriacao) {
		this.dtaCriacao = dtaCriacao;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public boolean isAprovadoPor() {
		return aprovadoPor;
	}

	public void setAprovadoPor(boolean aprovadoPor) {
		this.aprovadoPor = aprovadoPor;
	}

	public Date getDtaAprovacao() {
		return dtaAprovacao;
	}

	public void setDtaAprovacao(Date dtaAprovacao) {
		this.dtaAprovacao = dtaAprovacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailInscricao() {
		return emailInscricao;
	}

	public void setEmailInscricao(String emailInscricao) {
		this.emailInscricao = emailInscricao;
	}

	public String getNumtelInscricao() {
		return numtelInscricao;
	}

	public void setNumtelInscricao(String numtelInscricao) {
		this.numtelInscricao = numtelInscricao;
	}

	public byte[] getLogotipo() {
		return logotipo;
	}


	public void setLogotipo(byte[] logotipo) {
		this.logotipo = logotipo;
	}

	public byte[] getCartaz() {
		return cartaz;
	}

	public void setCartaz(byte[] cartaz) {
		this.cartaz = cartaz;
	}

	public EStatusAnuncio getStatus() {
		return status;
	}

	public void setStatus(EStatusAnuncio status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}

	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + (aprovado ? 1231 : 1237);
		result = prime * result + (aprovadoPor ? 1231 : 1237);
		result = prime * result + ((areaAtuacao == null) ? 0 : areaAtuacao.hashCode());
		result = prime * result + ((benofr == null) ? 0 : benofr.hashCode());
		result = prime * result + Arrays.hashCode(cartaz);
		result = prime * result + ((cursos == null) ? 0 : cursos.hashCode());
		result = prime * result + ((desvag == null) ? 0 : desvag.hashCode());
		result = prime * result + ((dtaAprovacao == null) ? 0 : dtaAprovacao.hashCode());
		result = prime * result + ((dtaCriacao == null) ? 0 : dtaCriacao.hashCode());
		result = prime * result + ((dtafimdiv == null) ? 0 : dtafimdiv.hashCode());
		result = prime * result + ((dtainidiv == null) ? 0 : dtainidiv.hashCode());
		result = prime * result + ((dtarepchk == null) ? 0 : dtarepchk.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((emailInscricao == null) ? 0 : emailInscricao.hashCode());
		result = prime * result + ((horsem == null) ? 0 : horsem.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((loctrb == null) ? 0 : loctrb.hashCode());
		result = prime * result + Arrays.hashCode(logotipo);
		result = prime * result + ((nomeEmpresa == null) ? 0 : nomeEmpresa.hashCode());
		result = prime * result + ((nompes == null) ? 0 : nompes.hashCode());
		result = prime * result + ((numtel == null) ? 0 : numtel.hashCode());
		result = prime * result + ((numtelInscricao == null) ? 0 : numtelInscricao.hashCode());
		result = prime * result + ((perfilCandidato == null) ? 0 : perfilCandidato.hashCode());
		result = prime * result + ((regimeTrabalho == null) ? 0 : regimeTrabalho.hashCode());
		result = prime * result + (repchk ? 1231 : 1237);
		result = prime * result + (repcod ? 1231 : 1237);
		result = prime * result + (reppwd ? 1231 : 1237);
		result = prime * result + ((salmes == null) ? 0 : salmes.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((titvag == null) ? 0 : titvag.hashCode());
		result = prime * result + ((urlweb == null) ? 0 : urlweb.hashCode());
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
		Anuncio other = (Anuncio) obj;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (aprovado != other.aprovado)
			return false;
		if (aprovadoPor != other.aprovadoPor)
			return false;
		if (areaAtuacao == null) {
			if (other.areaAtuacao != null)
				return false;
		} else if (!areaAtuacao.equals(other.areaAtuacao))
			return false;
		if (benofr == null) {
			if (other.benofr != null)
				return false;
		} else if (!benofr.equals(other.benofr))
			return false;
		if (!Arrays.equals(cartaz, other.cartaz))
			return false;
		if (cursos == null) {
			if (other.cursos != null)
				return false;
		} else if (!cursos.equals(other.cursos))
			return false;
		if (desvag == null) {
			if (other.desvag != null)
				return false;
		} else if (!desvag.equals(other.desvag))
			return false;
		if (dtaAprovacao == null) {
			if (other.dtaAprovacao != null)
				return false;
		} else if (!dtaAprovacao.equals(other.dtaAprovacao))
			return false;
		if (dtaCriacao == null) {
			if (other.dtaCriacao != null)
				return false;
		} else if (!dtaCriacao.equals(other.dtaCriacao))
			return false;
		if (dtafimdiv == null) {
			if (other.dtafimdiv != null)
				return false;
		} else if (!dtafimdiv.equals(other.dtafimdiv))
			return false;
		if (dtainidiv == null) {
			if (other.dtainidiv != null)
				return false;
		} else if (!dtainidiv.equals(other.dtainidiv))
			return false;
		if (dtarepchk == null) {
			if (other.dtarepchk != null)
				return false;
		} else if (!dtarepchk.equals(other.dtarepchk))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emailInscricao == null) {
			if (other.emailInscricao != null)
				return false;
		} else if (!emailInscricao.equals(other.emailInscricao))
			return false;
		if (horsem == null) {
			if (other.horsem != null)
				return false;
		} else if (!horsem.equals(other.horsem))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (loctrb == null) {
			if (other.loctrb != null)
				return false;
		} else if (!loctrb.equals(other.loctrb))
			return false;
		if (!Arrays.equals(logotipo, other.logotipo))
			return false;
		if (nomeEmpresa == null) {
			if (other.nomeEmpresa != null)
				return false;
		} else if (!nomeEmpresa.equals(other.nomeEmpresa))
			return false;
		if (nompes == null) {
			if (other.nompes != null)
				return false;
		} else if (!nompes.equals(other.nompes))
			return false;
		if (numtel == null) {
			if (other.numtel != null)
				return false;
		} else if (!numtel.equals(other.numtel))
			return false;
		if (numtelInscricao == null) {
			if (other.numtelInscricao != null)
				return false;
		} else if (!numtelInscricao.equals(other.numtelInscricao))
			return false;
		if (perfilCandidato == null) {
			if (other.perfilCandidato != null)
				return false;
		} else if (!perfilCandidato.equals(other.perfilCandidato))
			return false;
		if (regimeTrabalho != other.regimeTrabalho)
			return false;
		if (repchk != other.repchk)
			return false;
		if (repcod != other.repcod)
			return false;
		if (reppwd != other.reppwd)
			return false;
		if (salmes == null) {
			if (other.salmes != null)
				return false;
		} else if (!salmes.equals(other.salmes))
			return false;
		if (status != other.status)
			return false;
		if (titvag == null) {
			if (other.titvag != null)
				return false;
		} else if (!titvag.equals(other.titvag))
			return false;
		if (urlweb == null) {
			if (other.urlweb != null)
				return false;
		} else if (!urlweb.equals(other.urlweb))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Anuncio [id=" + id + ", status=" + status + ", nomeEmpresa=" + nomeEmpresa + ", areaAtuacao=" + areaAtuacao + ", nompes=" + nompes
				+ ", numtel=" + numtel + ", urlweb=" + urlweb + ", email=" + email + ", loctrb=" + loctrb + ", titvag=" + titvag + ", desvag=" + desvag
				+ ", regimeTrabalho=" + regimeTrabalho + ", horsem=" + horsem + ", benofr=" + benofr + ", salmes=" + salmes + ", perfilCandidato="
				+ perfilCandidato + ", cursos=" + cursos + ", dtainidiv=" + dtainidiv + ", dtafimdiv=" + dtafimdiv + ", emailInscricao=" + emailInscricao
				+ ", numtelInscricao=" + numtelInscricao + ", repchk=" + repchk + ", repcod=" + repcod + ", reppwd=" + reppwd + ", ip=" + ip + ", dtarepchk="
				+ dtarepchk + ", dtaCriacao=" + dtaCriacao + ", aprovado=" + aprovado + ", aprovadoPor=" + aprovadoPor + ", dtaAprovacao=" + dtaAprovacao
				+ ", logotipo=" + Arrays.toString(logotipo) + ", cartaz=" + Arrays.toString(cartaz) + "]";
	}

}
