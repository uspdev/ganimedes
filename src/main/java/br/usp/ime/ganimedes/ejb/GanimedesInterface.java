package br.usp.ime.ganimedes.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import br.usp.ime.ganimedes.model.Aluno;
import br.usp.ime.ganimedes.model.Contato;
import br.usp.ime.ganimedes.model.Convenio;
import br.usp.ime.ganimedes.model.Curso;
import br.usp.ime.ganimedes.model.Documento;
import br.usp.ime.ganimedes.model.Empresa;
import br.usp.ime.ganimedes.model.Estagio;
import br.usp.ime.ganimedes.model.Jornada;
import br.usp.ime.ganimedes.model.Notificacao;
import br.usp.ime.ganimedes.model.Remuneracao;
import br.usp.ime.ganimedes.model.Supervisor;

@Remote
public interface GanimedesInterface {

	public List<Empresa> buscarEmpresas();

	public List<Empresa> buscarEmpresa(String criterio);

	public Empresa buscarEmpresaPorId(Integer id);

	public Empresa buscarEmpresaPorNome(String nome);

	public Object salvar(Object o);

	public void deletar(Object o);

	public void deletarContato(Contato c);

	public void deletarConvenio(Convenio c);

	public int deletarRemuneracao(Remuneracao r);

	public int deletarJornada(Jornada j);

	public int deletarSupervisor(Supervisor s);

	public int deletarNotificacao(Notificacao n);

	public Aluno buscarAluno(Integer codpes);

	public int deletarDocumento(Documento documento);

	public List<Documento> buscarDocumentosAtivosPeriodo(Date dtaini, Date dtafim);

	public List<Estagio> buscarEstagiosAtivosPeriodo(Date dtaini, Date dtafim);

	public List<Estagio> buscarEstagiosAtivosPeriodo(Date dtaini, Date dtafim, Empresa empresa);

	public Estagio buscarEstagio(Aluno a, Empresa e);

	public List<Estagio> buscarEstagios();

	public List<Estagio> buscarEstagios(Empresa empresa);

	public List<Estagio> buscarEstagiosEncerrados();

	public void deletarEstagio(Estagio e);

	public List<Curso> buscarCursos();

	public List<Empresa> buscarEmpresasConveniadas(Date dta);

	public void gerarRelatorioEmpresasConveniadas(List<Empresa> empresas, Date dta);

	public boolean temProgramaAtivo(Integer codpes);

	public boolean temMatriculaAtiva(Integer codpes);

	public List<Empresa> buscarEmpresasConveniadasProximoEncerramento();

}