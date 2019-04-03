package br.usp.ime.ganimedes.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CURSO")
public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private int codcur;
	
	private String nome;

	private ENivelCurso nivel;

	@ManyToMany
	private List<Anuncio> vagas;

	public Curso() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ENivelCurso getNivel() {
		return nivel;
	}

	public void setNivel(ENivelCurso nivel) {
		this.nivel = nivel;
	}

	public List<Anuncio> getVagas() {
		return vagas;
	}

	public void setVagas(List<Anuncio> vagas) {
		this.vagas = vagas;
	}
	
	

	public int getCodcur() {
		return codcur;
	}

	public void setCodcur(int codcur) {
		this.codcur = codcur;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Curso other = (Curso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nivel != other.nivel)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		
		return true;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nome=" + nome + ", nivel=" + nivel;
	}

}
