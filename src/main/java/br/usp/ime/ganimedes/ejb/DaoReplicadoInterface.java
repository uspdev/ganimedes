package br.usp.ime.ganimedes.ejb;

import java.util.List;

import javax.ejb.Remote;

import br.usp.ime.ganimedes.model.Aluno;
import br.usp.ime.ganimedes.model.CursoGr;
import br.usp.model.replicado.Histescolargr;
import br.usp.model.replicado.Programagr;
import br.usp.model.replicado.VinculoPessoaUsp;

@Remote
public interface DaoReplicadoInterface {
	/*
	 * public String buscarEmailPrincipal(Integer codpes);
	 *
	 * public List<Pessoa> buscarDocentesDepartamento(String departamento);
	 *
	 * public List<Pessoa> buscarDocentes(String nompes);
	 *
	 * public List<Pessoa> buscarFuncionarios(String nompes);
	 *
	 * public List<Pessoa> buscarFuncionarios();
	 *
	 * public List<Pessoa> buscarFuncionariosPorSetor(Short codset);
	 */

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

	public boolean temMatriculaAtiva(Integer codpes);

	public boolean temProgramaAtivo(Integer codpes);

}
