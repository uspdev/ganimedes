package br.usp.ime.ganimedes.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import br.usp.ime.ganimedes.model.Aluno;
import br.usp.ime.ganimedes.model.CursoGr;
import br.usp.model.replicado.EmailPessoa;
import br.usp.model.replicado.Histescolargr;
import br.usp.model.replicado.Programagr;
import br.usp.model.replicado.VinculoPessoaUsp;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class DaoReplicado implements DaoReplicadoInterface {

	@PersistenceContext(unitName = "replicado")
	EntityManager replicado;

	@Resource
	UserTransaction utx;

	@SuppressWarnings("unchecked")
	public Aluno buscarAlunoGr(Integer codpes) {

		try {
			utx.begin();
		} catch (NotSupportedException | SystemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Aluno a = null;

		String query = "SELECT NEW br.usp.ime.ganimedes.model.Aluno"
				+ "(P.codpes, P.nompes, P.tipdocidf, P.numdocidf, P.dtaexdidf, P.sglorgexdidf, P.sglest, P.sexpes, P.numcpf, P.dtanas, V.VinculoPessoaUspPK.tipvin, V.sitatl, V.dtainivin, V.tiping, V.sitoco, H.vincsathabilitacaogrPK.codcur, H.vincsathabilitacaogrPK.codhab, PR.numsemidl) "
				+ "FROM br.usp.model.replicado.Pessoa P, br.usp.model.replicado.VinculoPessoaUsp V, br.usp.model.replicado.Vincsathabilitacaogr H, br.usp.model.replicado.Programagr PR "
				+ "WHERE P.codpes = :codpes AND P.codpes = V.VinculoPessoaUspPK.codpes AND P.codpes = H.vincsathabilitacaogrPK.codpes AND V.VinculoPessoaUspPK.tipvin= :tipvin AND P.codpes = PR.programagrPK.codpes";

		Query q = replicado.createQuery(query);
		q.setParameter("codpes", codpes);
		q.setParameter("tipvin", "ALUNOGR");

		List<Aluno> alunos = q.getResultList();

		try {
			utx.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException
				| SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!alunos.isEmpty()) {
			a = alunos.get(0);
			a.setNomcur(this.buscarNomeCurso(a.getCodcur()));
			a.setNomhab(this.buscarNomeHabilitacao(a.getCodcur(), a.getCodhab()));
			// a.setNumsemidl(this.buscarNumSemIdl(a.getCodpes()));

			for (Aluno aluno : alunos) {
				aluno.setVinculos(this.buscarVinculos(aluno.getCodpes()));
			}

		}

		return a;
	}

	@SuppressWarnings("unchecked")
	public Aluno buscarAlunoPos(Integer codpes) {

		try {
			utx.begin();
		} catch (NotSupportedException | SystemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Aluno a = null;

		String query = "SELECT NEW br.usp.ime.ganimedes.model.Aluno"
				+ "(P.codpes, P.nompes, P.tipdocidf, P.numdocidf, P.dtaexdidf, P.sglorgexdidf, P.sglest, P.sexpes, P.numcpf, P.dtanas, V.VinculoPessoaUspPK.tipvin, V.sitatl, V.dtainivin, V.tiping, V.sitoco, AR.codcur , AR.codare , NC.nomcur) "
				+ "FROM br.usp.model.replicado.Pessoa P, " + "br.usp.model.replicado.VinculoPessoaUsp V, " + "br.usp.model.replicado.Agprograma AG, "
				+ "br.usp.model.replicado.Area AR, " + "br.usp.model.replicado.Nomecurso NC " + "WHERE P.codpes = :codpes "
				+ "AND V.VinculoPessoaUspPK.tipvin= :tipvin " + "AND P.codpes = V.VinculoPessoaUspPK.codpes " + "AND P.codpes = AG.agprogramaPK.codpes "
				+ "AND AG.agprogramaPK.codare = AR.codare " + "AND AR.codcur = NC.nomecursoPK.codcur ";

		Query q = replicado.createQuery(query);
		q.setParameter("codpes", codpes);
		q.setParameter("tipvin", "ALUNOPOS");

		List<Aluno> alunos = q.getResultList();

		try {
			utx.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException
				| SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!alunos.isEmpty()) {
			a = alunos.get(0);
		}

		return a;
	}

	public String buscarNomeCurso(int codcur) {

		try {
			utx.begin();
		} catch (NotSupportedException | SystemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query = "SELECT C.nomcur FROM br.usp.model.replicado.Cursogr C WHERE C.codcur = :codcur";

		Query q = replicado.createQuery(query);
		q.setParameter("codcur", codcur);

		String nomcur = (String) q.setMaxResults(1).getSingleResult();

		try {
			utx.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException
				| SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nomcur;

	}

	public String buscarNomeHabilitacao(int codcur, short codhab) {

		try {
			utx.begin();
		} catch (NotSupportedException | SystemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query = "SELECT H.nomhab FROM dbo.HABILITACAOGR AS H WHERE H.codcur = :codcur AND H.codhab = :codhab";

		Query q = replicado.createNativeQuery(query);
		q.setParameter("codcur", codcur);
		q.setParameter("codhab", codhab);

		String nomhab = (String) q.setMaxResults(1).getSingleResult();

		try {
			utx.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException
				| SystemException e) {
			e.printStackTrace();
		}

		return nomhab;

	}

	public Short buscarNumSemIdl(int codpes) {

		Short numsemidl = 0;

		try {
			utx.begin();
		} catch (NotSupportedException | SystemException e1) {
			e1.printStackTrace();
		}

		String query = "SELECT P FROM br.usp.model.replicado.Programagr P WHERE P.programagrPK = :codpes";

		Query q = replicado.createQuery(query);
		q.setParameter("codpes", codpes);

		Programagr programa = (Programagr) q.setMaxResults(1).getSingleResult();
		numsemidl = programa.getNumsemidl();

		try {
			utx.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException
				| SystemException e) {
			e.printStackTrace();
		}

		return numsemidl;

	}

	@SuppressWarnings("unchecked")
	public List<CursoGr> buscarCursosGraduacao() {
		List<CursoGr> cursos = new ArrayList<CursoGr>();

		String query = "SELECT NEW br.usp.ime.ganimedes.model.CursoGr(C.codcur, H.habilitacaogrPK.codhab, C.nomcur, H.nomhab) "
				+ "FROM br.usp.model.replicado.Cursogr C, br.usp.model.replicado.Habilitacaogr H " + "WHERE C.codcur = H.habilitacaogrPK.codcur "
				+ "AND C.codclg=45 " + "AND C.dtadtvcur IS NULL " + "AND H.dtadtvhab IS NULL " + "ORDER BY C.codcur, H.habilitacaogrPK.codhab";

		Query q = replicado.createQuery(query);
		cursos = q.getResultList();
		return cursos;
	}

	public List<VinculoPessoaUsp> buscarVinculos(Integer codpes) {
		List<String> resposta = new ArrayList<String>();
		String query = "SELECT V FROM br.usp.model.replicado.VinculoPessoaUsp V WHERE V.VinculoPessoaUspPK.codpes = :codpes  ORDER BY V.sitatl ASC, V.dtainivin DESC";
		Query q = replicado.createQuery(query);
		q.setParameter("codpes", codpes);

		List<VinculoPessoaUsp> vinculos = q.getResultList();
		return vinculos;
	}

	public List<Programagr> buscarProgramaAtivo(Integer codpes) {
		String query = "SELECT P FROM br.usp.model.replicado.Programagr P WHERE P.programagrPK.codpes = :codpes AND P.stapgm = :ativo OR P.stapgm = :reativado";
		Query q = replicado.createQuery(query);
		q.setParameter("codpes", codpes);
		q.setParameter("ativo", "A");
		q.setParameter("reativado", "R");

		List<Programagr> programas = q.getResultList();
		return programas;
	}

	public List<Histescolargr> buscarMatriculasAtivas(Integer codpes) {
		String query = "SELECT H FROM br.usp.model.replicado.Histescolargr H WHERE H.histescolargrPK.codpes = :codpes AND H.rstfim IS NULL AND H.stamtr = :M";
		Query q = replicado.createQuery(query);
		q.setParameter("codpes", codpes);
		q.setParameter("M", 'M'); // matriculado

		List<Histescolargr> historico = q.getResultList();
		return historico;
	}

	public String buscarNomePessoa(int codpes) {

		try {
			utx.begin();
		} catch (NotSupportedException | SystemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String query = "SELECT P.nompes FROM br.usp.model.replicado.Pessoa P WHERE P.codpes = :codpes";

		Query q = replicado.createQuery(query);
		q.setParameter("codpes", codpes);

		String nompes = "";

		List<String> result = q.getResultList();
		if (!result.isEmpty()) {
			nompes = result.get(0);
		}

		try {
			utx.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException | HeuristicRollbackException
				| SystemException e) {
			e.printStackTrace();
		}

		return nompes;

	}

	public String buscarEmailPrincipal(Integer codpes) {
		String query = "SELECT E FROM br.usp.model.replicado.EmailPessoa E WHERE E.EmailPessoaPK.codpes = :codpes AND E.stamtr= :ativo";
		Query q = replicado.createQuery(query);
		q.setParameter("codpes", codpes);
		q.setParameter("ativo", 'S');

		List<EmailPessoa> emails = q.getResultList();

		String email = "";
		if (!emails.isEmpty()) {
			email = emails.get(0).getCodema();
		}

		return email;

	}

	public boolean isAluno(Integer codpes) {

		String query = "SELECT * FROM VINCULOPESSOAUSP WHERE codpes = :codpes AND sitatl = 'A' and codclg = 45";

		Query q = replicado.createNativeQuery(query);
		q.setParameter("codpes", codpes);

		List<Object> result = q.getResultList();

		if (result.isEmpty()) {
			return false;
		}

		return true;
	}

}