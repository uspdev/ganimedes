package br.usp.ime.ganimedes.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.usp.ime.ganimedes.ejb.DaoReplicadoInterface;
import br.usp.ime.ganimedes.model.Aluno;

@Stateless
public class DaoAluno extends Dao<Aluno> {

	@EJB
	DaoReplicadoInterface daoReplicado;

	@EJB
	DaoEstagio daoEstagio;

	public DaoAluno() {
		super.setPersistentClass(Aluno.class);
	}

	public Aluno buscarAluno(Integer codpes) {

		// primeiro busca aluno de graduação
		Aluno aluno = null;

		aluno = daoReplicado.buscarAlunoGr(codpes);

		if (aluno == null) {
			aluno = daoReplicado.buscarAlunoPos(codpes);
		}

		if (aluno != null) {
			aluno.setEstagios(daoEstagio.buscarEstagios(aluno));
			aluno.setEmail(daoReplicado.buscarEmailPrincipal(aluno.getCodpes()));
		}

		return aluno;
	}

	public List<Aluno> buscarAlunos() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		String q = "SELECT A FROM Aluno A ORDER BY A.nompes ASC";
		Query query = em.createQuery(q);
		alunos = query.getResultList();
		return alunos;
	}

}
