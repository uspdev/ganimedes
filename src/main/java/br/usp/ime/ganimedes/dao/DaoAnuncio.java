package br.usp.ime.ganimedes.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.usp.ime.ganimedes.model.Anuncio;
import br.usp.ime.ganimedes.model.Usuario;

@Stateless
public class DaoAnuncio extends Dao<Anuncio> {

	public DaoAnuncio() {
		super.setPersistentClass(Anuncio.class);
	}

	public List<Anuncio> buscarAnuncios(Usuario usuario) {
		List<Anuncio> anuncios = new ArrayList<Anuncio>();
		String q = "SELECT A FROM Anuncio A WHERE A.usuario.id = :id ORDER BY A.dtaCriacao DESC";
		Query query = em.createQuery(q);
		query.setParameter("id", usuario.getId());
		anuncios = query.getResultList();
		return anuncios;
	}

	public List<Anuncio> buscarAnunciosNovos() {
		Calendar c = Calendar.getInstance();
		List<Anuncio> anuncios = new ArrayList<Anuncio>();
		String q = "SELECT A FROM Anuncio A WHERE A.status = 0 ORDER BY A.dtaCriacao, A.status ASC, A.nomeEmpresa ASC";
		Query query = em.createQuery(q);
		anuncios = query.getResultList();
		return anuncios;
	}

	public List<Anuncio> buscarAnunciosAprovados() {
		Calendar c = Calendar.getInstance();
		List<Anuncio> anuncios = new ArrayList<Anuncio>();
		String q = "SELECT A FROM Anuncio A WHERE A.status = 1 AND :hoje <= A.dtafimdiv  ORDER BY A.dtaCriacao, A.status ASC, A.nomeEmpresa ASC";
		Query query = em.createQuery(q);
		query.setParameter("hoje", c.getTime());
		anuncios = query.getResultList();
		return anuncios;
	}

	public List<Anuncio> buscarAnunciosRejeitados() {
		Calendar c = Calendar.getInstance();
		List<Anuncio> anuncios = new ArrayList<Anuncio>();
		String q = "SELECT A FROM Anuncio A WHERE A.status = 2 ORDER BY A.dtaCriacao, A.status ASC, A.nomeEmpresa ASC";
		Query query = em.createQuery(q);
		anuncios = query.getResultList();
		return anuncios;
	}

	public List<Anuncio> buscarAnunciosEncerrados() {
		Calendar c = Calendar.getInstance();
		List<Anuncio> anuncios = new ArrayList<Anuncio>();
		String q = "SELECT A FROM Anuncio A WHERE A.status = 3 OR A.dtafimdiv < :hoje ORDER BY A.dtaCriacao, A.status ASC, A.nomeEmpresa ASC";
		Query query = em.createQuery(q);
		query.setParameter("hoje", c.getTime());
		anuncios = query.getResultList();
		return anuncios;

	}

	public Usuario buscarAnunciante(Anuncio anuncio) {
		Query query = em.createQuery("SELECT U FROM Usuario U WHERE codpes = :codpes");
		query.setParameter("codpes", anuncio.getUsuario().getCodpes());
		List<Usuario> usuarios = query.getResultList();
		if (usuarios.isEmpty()) {
			return null;
		}
		Usuario usuario = usuarios.get(0);
		return usuario;
	}
}