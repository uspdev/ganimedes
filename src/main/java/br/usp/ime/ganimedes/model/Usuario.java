package br.usp.ime.ganimedes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.scribe.model.Token;
import org.scribe.model.Verifier;

@Named(value = "UsuarioLogado")
@SessionScoped

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private boolean ativado = false;

	@Column(name = "codpes", unique = true, nullable = false)
	private Integer codpes;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	private String nompes;

	private String salt;

	private String senha;

	@Transient
	private String confirmacaoSenha;

	@Column(name = "codlog", unique = true)
	private String codlog;
	private String telefone;
	private String cpf;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro", nullable = false)
	private Date dataCadastro;

	// obtem os papeis antecipadamente por que usa na autorizacao
	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "USUARIO_PAPEL", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "papel_id", referencedColumnName = "id"))
	private List<Papel> papeis = new ArrayList<Papel>();

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "USUARIO_GRUPO", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "grupo_id", referencedColumnName = "id"))
	private List<Grupo> grupos = new ArrayList<Grupo>();

	@OneToMany
	private List<Anuncio> anuncios = new ArrayList<Anuncio>();

	@Transient
	private Token oauthToken;
	@Transient
	private Verifier oauthVerifier;
	@Transient
	private boolean isAutorizado;
	@Transient
	private boolean isAutenticado;

	public boolean isAdm() {
		for (Papel p : papeis) {

			if (p.getNome().equals("ADM")) {
				return true;
			}
		}
		return false;
	}

	public boolean isOpr() {
		for (Papel p : papeis) {

			if (p.getNome().equals("OPR")) {
				return true;
			}
		}
		return false;
	}

	public boolean isUsr() {
		for (Papel p : papeis) {

			if (p.getNome().equals("USR")) {
				return true;
			}
		}
		return false;
	}

	public boolean isAnunciante() {
		for (Papel p : papeis) {

			if (p.getNome().equals("ANUNCIANTE")) {
				return true;
			}
		}
		return false;
	}

	public boolean isAluno() {
		for (Papel p : papeis) {

			if (p.getNome().equals("ALUNO")) {
				return true;
			}
		}
		return false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isAtivado() {
		return ativado;
	}

	public void setAtivado(boolean ativado) {
		this.ativado = ativado;
	}

	public Integer getCodpes() {
		return codpes;
	}

	public void setCodpes(Integer codpes) {
		this.codpes = codpes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNompes() {
		return nompes;
	}

	public void setNompes(String nompes) {
		this.nompes = nompes;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Token getOauthToken() {
		return oauthToken;
	}

	public void setOauthToken(Token oauthToken) {
		this.oauthToken = oauthToken;
	}

	public Verifier getOauthVerifier() {
		return oauthVerifier;
	}

	public void setOauthVerifier(Verifier oauthVerifier) {
		this.oauthVerifier = oauthVerifier;
	}

	public boolean isAutorizado() {
		return isAutorizado;
	}

	public void setAutorizado(boolean isAutorizado) {
		this.isAutorizado = isAutorizado;
	}

	public boolean isAutenticado() {
		return isAutenticado;
	}

	public void setAutenticado(boolean isAutenticado) {
		this.isAutenticado = isAutenticado;
	}

	public String getCodlog() {
		return codlog;
	}

	public void setCodlog(String codlog) {
		this.codlog = codlog;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
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
		Usuario other = (Usuario) obj;
		if (codpes == null) {
			if (other.codpes != null)
				return false;
		} else if (!codpes.equals(other.codpes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", ativado=" + ativado + ", codpes=" + codpes + ", email=" + email + ", nompes=" + nompes + ", salt=" + salt
				+ ", senha=" + senha + ", papeis=" + papeis + ", grupos=" + grupos + ", oauthToken=" + oauthToken + ", oauthVerifier=" + oauthVerifier
				+ ", isAutorizado=" + isAutorizado + ", isAutenticado=" + isAutenticado + "]";
	}

}
