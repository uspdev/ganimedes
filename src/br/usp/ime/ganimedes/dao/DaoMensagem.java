package br.usp.ime.ganimedes.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.usp.ime.ganimedes.model.Mensagem;

@Stateless
public class DaoMensagem extends Dao<Mensagem> {

	public DaoMensagem() {
		super.setPersistentClass(Mensagem.class);
	}

	public List<Mensagem> buscarUsuarioMensagensNaoEnviadas() {
		Query query = em.createQuery("SELECT M FROM Mensagem M WHERE M.envio IS NULL");
		return query.getResultList();

	}

}
