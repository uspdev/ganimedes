package br.usp.ime.ganimedes.dao;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.usp.ime.ganimedes.model.Token;
import br.usp.ime.ganimedes.model.Usuario;

@Stateless
public class DaoToken extends Dao<Token> {

	public DaoToken() {
		super.setPersistentClass(Token.class);
	}

	public Token buscarToken(String token) {
		String q = "SELECT x FROM Token x WHERE x.token= :token";
		Query query = em.createQuery(q).setParameter("token", token);
		if (query.getResultList().isEmpty())
			return null;
		else
			return (Token) query.getSingleResult();
	}

	public Token buscarTokenPorCodlog(String codlog) {
		Usuario usuario = this.buscarUsuario(codlog);
		String q = "SELECT x FROM Token x WHERE x.usuario = :usuario";
		Query query = em.createQuery(q).setParameter("usuario", usuario);
		if (query.getResultList().isEmpty())
			return null;
		else
			return (Token) query.getSingleResult();
	}

	/*
	 * public List<Token> buscarToken() { String q = "SELECT x FROM Token x"; Query query = em.createQuery(q);
	 *
	 * @SuppressWarnings("unchecked") List<Token> confirmacaoEmail = (List<Token>) query.getResultList(); if (query.getResultList().isEmpty()) return
	 * null; else return confirmacaoEmail; }
	 */

	public Usuario buscarUsuario(String codlog) {
		Usuario u = new Usuario();

		String q = "SELECT x FROM Usuario x WHERE x.codlog = :codlog";
		Query query = em.createQuery(q).setParameter("codlog", codlog);
		if (!query.getResultList().isEmpty()) {
			u = (Usuario) query.getSingleResult();
		} else {
			codlog = "estagios@ime.usp.br";
			String q1 = "SELECT x FROM Usuario x WHERE x.codlog = :codlog";
			Query query1 = em.createQuery(q1).setParameter("codlog", codlog);

			if (query1.getResultList().isEmpty()) {
				// TODO: cria anunciante e devolve (ENTENDER O MOTIVO DISSO)
				u.setCodlog("estagios@ime.usp.br");
				u.setCpf("000.000.000-00");
				u.setNompes("secretaria");
				// a = (Anunciante) this.salvar(a);
			} else {
				u = (Usuario) query1.getSingleResult();
			}
		}

		return u;

	}

	public void removerToken(Token token) {
		this.delete(token);
	}

}
