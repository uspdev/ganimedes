package br.usp.ime.ganimedes.dao;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.hibernate.TransientObjectException;

import br.usp.ime.ganimedes.model.Anunciante;
import br.usp.ime.ganimedes.model.Anuncio;
import br.usp.ime.util.OrdenadorAnunciantePorNome;

@Stateless
public class DaoAnunciante extends Dao<Anunciante> {

	public DaoAnunciante() {
		super.setPersistentClass(Anunciante.class);
	}

	public List<Anunciante> getGrupos() {
		List<Anunciante> anunciantes = super.findAll();
		Collections.sort(anunciantes, new OrdenadorAnunciantePorNome());
		return anunciantes;

	}

	public List<Anunciante> buscarAnunciantes() {
		String q = "SELECT x FROM Anunciante x";
		Query query = em.createQuery(q);
		@SuppressWarnings("unchecked")
		List<Anunciante> anunciantes = (List<Anunciante>) query.getResultList();
		return anunciantes;
	}

	public Anunciante buscarAnunciante(Anuncio anuncio) {
		int anuncianteId = 0;

		Anunciante a = new Anunciante();

		String q = "SELECT anunciante_id FROM ANUNCIO A WHERE A.id  =:id";
		Query query = em.createNativeQuery(q);
		query.setParameter("id", anuncio.getId());

		try {
			anuncianteId = (int) query.getSingleResult();
		} catch (NullPointerException e) {
		}

		catch (TransientObjectException e) {
		}

		catch (Exception e) {
		}

		if (anuncianteId > 0) {
			String q1 = "SELECT x FROM Anunciante x WHERE x.id = :id";
			Query query1 = em.createQuery(q1);
			query1.setParameter("id", anuncianteId);
			@SuppressWarnings("unchecked")
			List<Anunciante> anunciantes = (List<Anunciante>) query1.getResultList();

			if (!anunciantes.isEmpty()) {
				a = anunciantes.get(0);
			}
		}

		return a;
	}

	public Anunciante buscarAnunciantePorCPF(String cpf) {
		String q = "SELECT x FROM Anunciante x WHERE x.cpf = :cpf";
		Query query = em.createQuery(q).setParameter("cpf", cpf);
		if (query.getResultList().isEmpty())
			return null;
		else
			return (Anunciante) query.getSingleResult();
	}

	public Anunciante buscarAnunciantePrimeiroAcesso(String codlog) {

		String q = "SELECT x FROM Anunciante x WHERE x.codlog = :codlog";
		Query query = em.createQuery(q).setParameter("codlog", codlog);
		if (!query.getResultList().isEmpty()) {
			return (Anunciante) query.getSingleResult();
		}
		return null;
	}

}
