package br.usp.ime.ganimedes.ejb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.itextpdf.text.DocumentException;

import br.usp.ime.ganimedes.documentos.RelatorioEmpresa;
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
import br.usp.ime.util.OrdenadorEmpresaNome;
import br.usp.ime.util.OrdenadorEstagioNomeAluno;
import br.usp.model.replicado.Histescolargr;
import br.usp.model.replicado.Programagr;

@Stateless
public class GanimedesBean implements GanimedesInterface {

	@EJB
	DaoGanimedes daoGanimedes;

	@EJB
	DaoReplicadoInterface daoReplicado;

	public List<Empresa> buscarEmpresas() {

		List<Empresa> empresas = new ArrayList<Empresa>();
		empresas = daoGanimedes.buscarEmpresas();
		List<Convenio> convenios = new ArrayList<Convenio>();
		List<Contato> contatos = new ArrayList<Contato>();
		List<Estagio> estagios = new ArrayList<Estagio>();

		for (Empresa empresa : empresas) {
			contatos = daoGanimedes.buscarContatos(empresa);
			empresa.setContatos(contatos);

			convenios = daoGanimedes.buscarConvenios(empresa);
			empresa.setConvenios(convenios);

			estagios = daoGanimedes.buscarEstagios(empresa);
			empresa.setEstagios(estagios);

		}

		return empresas;
	}

	public List<Empresa> buscarEmpresa(String criterio) {

		List<Empresa> empresas = new ArrayList<Empresa>();

		empresas.addAll(daoGanimedes.buscarEmpresaPorCnpj(criterio));

		empresas.addAll(daoGanimedes.buscarEmpresaPorNome(criterio));

		List<Contato> contatos = new ArrayList<Contato>();
		List<Convenio> convenios = new ArrayList<Convenio>();
		List<Estagio> estagios = new ArrayList<Estagio>();

		for (Empresa empresa : empresas) {
			contatos = daoGanimedes.buscarContatos(empresa);
			empresa.setContatos(contatos);
			convenios = daoGanimedes.buscarConvenios(empresa);
			empresa.setConvenios(convenios);
			estagios = daoGanimedes.buscarEstagios(empresa);
			empresa.setEstagios(estagios);
		}

		return empresas;
	}

	public Empresa buscarEmpresaPorNome(String nome) {

		List<Empresa> empresas = daoGanimedes.buscarEmpresaPorNome(nome);

		if (!empresas.isEmpty()) {
			return empresas.get(0);
		}

		return null;
	}

	public Empresa buscarEmpresaPorId(Integer id) {
		return daoGanimedes.buscarEmpresaPorId(id);
	}

	public boolean temProgramaAtivo(Integer codpes) {
		boolean tem = false;

		List<Programagr> programas = daoReplicado.buscarProgramaAtivo(codpes);

		if (!programas.isEmpty()) {
			tem = true;
		}
		return tem;
	}

	public boolean temMatriculaAtiva(Integer codpes) {
		boolean tem = false;

		List<Histescolargr> historico = daoReplicado.buscarMatriculasAtivas(codpes);

		if (!historico.isEmpty()) {
			tem = true;
		}

		return tem;
	}

	public Object salvar(Object o) {
		return daoGanimedes.salvar(o);

	}

	public void deletar(Object o) {
		daoGanimedes.deletar(o);
	}

	public void deletarContato(Contato c) {
		daoGanimedes.deletarContato(c);

	}

	public void deletarConvenio(Convenio c) {
		daoGanimedes.deletarConvenio(c);

	}

	public int deletarDocumento(Documento documento) {
		return daoGanimedes.deletarDocumento(documento);
	}

	public int deletarRemuneracao(Remuneracao r) {
		return daoGanimedes.deletarRemuneracao(r);
	}

	public int deletarJornada(Jornada j) {
		return daoGanimedes.deletarJornada(j);
	}

	public int deletarNotificacao(Notificacao n) {
		return daoGanimedes.deletarNotificacao(n);
	}

	public int deletarSupervisor(Supervisor s) {
		return daoGanimedes.deletarSupervisor(s);
	}

	public void deletarEstagio(Estagio e) {
		daoGanimedes.deletarEstagio(e);
	}

	public Aluno buscarAluno(Integer codpes) {

		// primeiro busca aluno de graduação
		Aluno aluno = null;

		aluno = daoReplicado.buscarAlunoGr(codpes);

		if (aluno == null) {
			aluno = daoReplicado.buscarAlunoPos(codpes);
		}

		if (aluno != null) {
			aluno.setEstagios(daoGanimedes.buscarEstagios(aluno));
			aluno.setEmail(daoReplicado.buscarEmailPrincipal(aluno.getCodpes()));
		}

		return aluno;
	}


	public List<Documento> buscarDocumentosAtivosPeriodo(Date dtaini, Date dtafim) {

		List<Documento> documentos = daoGanimedes.buscarDocumentosAtivosPeriodo(dtaini, dtafim);

		for (Documento documento : documentos) {
			int codpes = documento.getEstagio().getAluno().getCodpes();
			Aluno a = this.buscarAluno(codpes); // daoReplicado.buscarAlunoGr(codpes);
			documento.getEstagio().setAluno(a);
		}

		return documentos;
	}

	public List<Estagio> buscarEstagiosAtivosPeriodo(Date dtaini, Date dtafim) {
		List<Estagio> estagios = daoGanimedes.buscarEstagiosAtivosPeriodo(dtaini, dtafim);
		List<Estagio> estagiosRemover = new ArrayList<Estagio>();
		for (Estagio estagio : estagios) {
			if (estagio.getDtafim().before(dtaini)) {
				estagiosRemover.add(estagio);
			}
			estagio.setAluno(daoReplicado.buscarAlunoGr(estagio.getAluno().getCodpes()));
		}
		estagios.removeAll(estagiosRemover);
		return estagios;
	}

	public List<Estagio> buscarEstagiosAtivosPeriodo(Date dtaini, Date dtafim, Empresa empresa) {

		Date hoje = new Date();
		List<Estagio> estagios = daoGanimedes.buscarEstagios(empresa);

		List<Estagio> estagiosR = new ArrayList<Estagio>();

		for (Estagio e : estagios) {

			if (e.getDtaini().before(hoje) && e.getDtafim().after(hoje)) {
				estagiosR.add(e);
				Aluno a = this.buscarAluno(e.getAluno().getCodpes());
				// daoReplicado.buscarAlunoGr(codpes);
				e.setAluno(a);
				a.getEstagios().add(e);
			}

		}

		return estagiosR;

	}

	public List<Estagio> buscarEstagios(Empresa empresa) {
		List<Estagio> estagios = daoGanimedes.buscarEstagios(empresa);

		for (Estagio estagio : estagios) {
			int codpes = estagio.getAluno().getCodpes();
			Aluno a = this.buscarAluno(codpes); // daoReplicado.buscarAlunoGr(codpes);
			estagio.setAluno(a);
			a.getEstagios().add(estagio);
		}

		Collections.sort(estagios, new OrdenadorEstagioNomeAluno());
		return estagios;
	}


	public List<Estagio> buscarEstagiosEncerrados() {
		List<Estagio> estagios = daoGanimedes.buscarEstagiosEncerrados();

		for (Estagio estagio : estagios) {
			estagio.setAluno(daoReplicado.buscarAlunoGr(estagio.getAluno().getCodpes()));
		}

		return estagios;
	}

	public List<Estagio> buscarEstagios() {
		List<Estagio> estagios = daoGanimedes.buscarEstagios();
		for (Estagio estagio : estagios) {

			try {
				estagio.setAluno(daoReplicado.buscarAlunoGr(estagio.getAluno().getCodpes()));
			} catch (NullPointerException e) {

			}

		}

		return estagios;

	}

	public Estagio buscarEstagio(Aluno a, Empresa e) {
		Estagio estagio = daoGanimedes.buscarEstagio(a, e);
		if (estagio != null) {
			Aluno aluno = daoReplicado.buscarAlunoGr(a.getCodpes());
			aluno.setEmail(daoReplicado.buscarEmailPrincipal(aluno.getCodpes()));
			estagio.setAluno(aluno);
			aluno.getEstagios().add(estagio);
		}
		return estagio;
	}

	public List<Curso> buscarCursos() {
		return daoGanimedes.buscarCursos();
	}


	public void gerarRelatorioEmpresasConveniadas(List<Empresa> empresas, Date dta) {

		RelatorioEmpresa doc = new RelatorioEmpresa();

		try {
			doc.gerarDocumento(empresas, dta);
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Empresa> buscarEmpresasConveniadas(Date dta) {

		List<Empresa> empresas = daoGanimedes.buscarEmpresasConveniadas(dta);

		Collections.sort(empresas, new OrdenadorEmpresaNome());

		return empresas;
	}

	public List<Empresa> buscarEmpresasConveniadasProximoEncerramento() {

		List<Empresa> empresas = daoGanimedes.buscarEmpresasConveniadasProximoEncerramento();

		Collections.sort(empresas, new OrdenadorEmpresaNome());

		return empresas;
	}



}