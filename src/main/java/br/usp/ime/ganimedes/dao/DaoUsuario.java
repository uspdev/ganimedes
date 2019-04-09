package br.usp.ime.ganimedes.dao;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.usp.ime.ganimedes.model.Grupo;
import br.usp.ime.ganimedes.model.Papel;
import br.usp.ime.ganimedes.model.Usuario;

@Stateless
public class DaoUsuario extends Dao<Usuario> {

	public DaoUsuario() {
		super.setPersistentClass(Usuario.class);
	}

	public Usuario buscarUsuario(Integer codpes) {
		Query query = em.createQuery("SELECT U FROM Usuario U WHERE codpes = :codpes");
		query.setParameter("codpes", codpes);
		List<Usuario> usuarios = query.getResultList();
		if (usuarios.isEmpty()) {
			return null;
		}

		Usuario usuario = usuarios.get(0);
		return usuario;
	}

	public Usuario buscarUsuario(String codlog) {
		Query query = em.createQuery("SELECT U FROM Usuario U WHERE codlog = :codlog");
		query.setParameter("codlog", codlog);

		List<Usuario> usuarios = query.getResultList();
		if (usuarios.isEmpty()) {
			return null;

		}

		Usuario usuario = usuarios.get(0);
		return usuario;

		/*
		 * else { codlog = "estagios@ime.usp.br"; String q1 = "SELECT x FROM Usuario x WHERE x.codlog = :codlog"; Query query1 =
		 * em.createQuery(q1).setParameter("codlog", codlog);
		 *
		 * if (query1.getResultList().isEmpty()) { // TODO: cria anunciante e devolve (ENTENDER O MOTIVO DISSO) u.setCodlog("estagios@ime.usp.br");
		 * u.setCpf("000.000.000-00"); u.setNompes("secretaria"); // a = (Anunciante) this.salvar(a); } else { u = (Usuario) query1.getSingleResult(); } }
		 *
		 */

	}

	public List<Usuario> getUsuarios() {
		Query query = em.createQuery("SELECT U FROM Usuario U ORDER BY U.nompes");
		return (List<Usuario>) query.getResultList();

	}

	public List<Grupo> getGrupos() {
		Query query = em.createQuery("SELECT G FROM Grupo G ORDER BY G.nome");
		return (List<Grupo>) query.getResultList();

	}

	public List<Papel> getPapeis() {
		Query query = em.createQuery("SELECT P FROM Papel P ORDER BY P.nome");
		return (List<Papel>) query.getResultList();

	}

	public Papel buscarPapel(String nome) {
		Query query = em.createQuery("SELECT P FROM Papel P WHERE nome = :nome");
		query.setParameter("nome", nome);
		Papel papel = (Papel) query.getSingleResult();

		return papel;
	}

	public Grupo buscarGrupo(String nome) {
		Query query = em.createQuery("SELECT G FROM Grupo G WHERE nome = :nome");
		query.setParameter("nome", nome);
		Grupo grupo = (Grupo) query.getSingleResult();
		return grupo;
	}

	// métodos especificos do ganimedes
	public Usuario buscarUsuarioPorCPF(String cpf) {
		String q = "SELECT x FROM Usuario x WHERE x.cpf = :cpf";
		Query query = em.createQuery(q).setParameter("cpf", cpf);
		if (query.getResultList().isEmpty())
			return null;
		else
			return (Usuario) query.getSingleResult();
	}

	public Usuario buscarUsuarioPrimeiroAcesso(String codlog) {

		String q = "SELECT U FROM Usuario U WHERE U.codlog = :codlog";
		Query query = em.createQuery(q).setParameter("codlog", codlog);
		if (!query.getResultList().isEmpty()) {
			return (Usuario) query.getSingleResult();
		}
		return null;
	}

	public boolean existeUsuarioAdm() {
		String query = "SELECT count(*) FROM USUARIO AS U, USUARIO_PAPEL AS UP, PAPEL AS P "
				+ "	WHERE U.id = UP.usuario_id AND UP.papel_id = P.id AND P.nome = 'ADM'";

		Query q = em.createNativeQuery(query);
		BigInteger count = (BigInteger) q.setMaxResults(1).getSingleResult();

		if (count.doubleValue() > 0) {
			return true;
		}

		return false;

	}

}
