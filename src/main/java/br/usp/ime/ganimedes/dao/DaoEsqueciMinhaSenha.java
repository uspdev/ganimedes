package br.usp.ime.ganimedes.dao;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.usp.ime.ganimedes.model.EsqueciMinhaSenha;
import br.usp.ime.ganimedes.model.Usuario;

@Stateless
public class DaoEsqueciMinhaSenha extends Dao<EsqueciMinhaSenha> {

	public DaoEsqueciMinhaSenha() {
		super.setPersistentClass(EsqueciMinhaSenha.class);
	}

	public EsqueciMinhaSenha buscarEsqueciMinhaSenha(String codlog) {

		String q = "SELECT E FROM EsqueciMinhaSenha E WHERE E.usuario.codlog = :codlog";
		Query query = em.createQuery(q).setParameter("codlog", codlog);
		if (query.getResultList().isEmpty())
			return null;
		else
			return (EsqueciMinhaSenha) query.getSingleResult();
	}

	public EsqueciMinhaSenha buscarEsqueciMinhaSenhaPorCodigoURL(String codigoURL) {
		String q = "SELECT E FROM EsqueciMinhaSenha E WHERE E.codigoURL= :codigoURL";
		Query query = em.createQuery(q).setParameter("codigoURL", codigoURL);
		if (query.getResultList().isEmpty())
			return null;
		else
			return (EsqueciMinhaSenha) query.getSingleResult();
	}

	public void removerEsqueciMinhaSenha(Usuario usuario) {
		String q = "SELECT E FROM EsqueciMinhaSenha E WHERE E.usuario = :usuario";
		Query query = em.createQuery(q).setParameter("usuario", usuario);
		if (!query.getResultList().isEmpty())
			this.delete((EsqueciMinhaSenha) query.getSingleResult());
	}

}
