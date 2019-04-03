package br.usp.ime.ganimedes.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.persistence.Query;

import br.usp.ime.ganimedes.model.Aluno;
import br.usp.ime.ganimedes.model.CursoGr;
import br.usp.model.replicado.Histescolargr;
import br.usp.model.replicado.Programagr;
import br.usp.model.replicado.VinculoPessoaUsp;

@Remote
public interface DaoReplicadoInterface {

	public Aluno buscarAlunoGr(Integer codpes);

	public Aluno buscarAlunoPos(Integer codpes);

	public String buscarNomeCurso(int codcur);

	public String buscarNomeHabilitacao(int codcur, short codhab);

	public Short buscarNumSemIdl(int codpes);

	public List<CursoGr> buscarCursosGraduacao();

	public List<VinculoPessoaUsp> buscarVinculos(Integer codpes);

	public List<Programagr> buscarProgramaAtivo(Integer codpes);

	public List<Histescolargr> buscarMatriculasAtivas(Integer codpes);

	public String buscarNomePessoa(int codpes);

	public String buscarEmailPrincipal(Integer codpes);
	
	public boolean isAluno(Integer codpes);

}
