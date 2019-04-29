package br.usp.ime.ganimedes.mb;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.usp.ime.ganimedes.dao.DaoAluno;
import br.usp.ime.ganimedes.dao.DaoDocumento;
import br.usp.ime.ganimedes.dao.DaoEmpresa;
import br.usp.ime.ganimedes.dao.DaoEstagio;
import br.usp.ime.ganimedes.dao.DaoMensagem;
import br.usp.ime.ganimedes.ejb.DaoReplicadoInterface;
import br.usp.ime.ganimedes.mail.MessageFactory;
import br.usp.ime.ganimedes.model.Aluno;
import br.usp.ime.ganimedes.model.Documento;
import br.usp.ime.ganimedes.model.EStatusDoc;
import br.usp.ime.ganimedes.model.ETipoDoc;
import br.usp.ime.ganimedes.model.ETipoNotificacao;
import br.usp.ime.ganimedes.model.Empresa;
import br.usp.ime.ganimedes.model.Estagio;
import br.usp.ime.ganimedes.model.Jornada;
import br.usp.ime.ganimedes.model.Mensagem;
import br.usp.ime.ganimedes.model.Notificacao;
import br.usp.ime.ganimedes.model.Remuneracao;
import br.usp.ime.ganimedes.model.Supervisor;
import br.usp.ime.ganimedes.model.Usuario;
import br.usp.ime.ganimedes.view.MessageBean;
import br.usp.ime.ganimedes.view.PageTransitionBean;
import br.usp.ime.ganimedes.view.ViewEstagio;
import br.usp.ime.util.DataUtility;
import br.usp.ime.util.IntegerUtil;
import br.usp.ime.util.OrdenadorAlunoNome;
import br.usp.ime.util.OrdenadorEstagioDtaIni;
import br.usp.ime.util.Recursos;

@ManagedBean(name = "mbEstagio")
@ViewScoped
public class MbEstagio implements Serializable {
	private static final long serialVersionUID = 1L;

	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();

	@Inject
	Usuario usuarioLogado;

	Logger log = Logger.getLogger(MbEstagio.class.getName());

	@EJB
	DaoMensagem daoMensagem;

	@EJB
	DaoAluno daoAluno;

	@EJB
	DaoReplicadoInterface daoReplicado;

	@EJB
	DaoDocumento daoDocumento;

	@EJB
	DaoEstagio daoEstagio;

	@EJB
	DaoEmpresa daoEmpresa;

	private ViewEstagio tela = new ViewEstagio();

	PageTransitionBean pt = new PageTransitionBean();

	private MessageBean mb = new MessageBean();

	public MbEstagio() {

	}

	public ViewEstagio getTela() {
		return tela;
	}

	public void setTela(ViewEstagio tela) {
		this.tela = tela;
	}

	public void adicionarAluno() {
	Aluno a = null;
		Integer codpes = 0;

		if (this.tela.getCodpes().isEmpty()) {
			mb.addMessage("codpes_nao_informado", "main", FacesMessage.SEVERITY_ERROR);
			return;
		}

		if (IntegerUtil.isInt(this.tela.getCodpes())) {
			codpes = Integer.valueOf(this.tela.getCodpes());
		}

		a = daoAluno.findById(codpes);

		if (a == null) {
			a = daoAluno.buscarAluno(codpes);
		}

		if (a == null) {
			mb.addMessage("codpes_nao_encontrado", "main", FacesMessage.SEVERITY_ERROR);

		} else {
			daoAluno.persist(a);
			this.tela.getAlunos().add(a);
			Collections.sort(this.tela.getAlunos(), new OrdenadorAlunoNome());
		}


	}

	public List<Aluno> getAlunos() {
		List<Aluno> alunos = daoAluno.findAll();
		Collections.sort(alunos, new OrdenadorAlunoNome());
		return alunos;
	}

	public void carregarAluno() {

		this.tela.setAluno(daoAluno.buscarAluno(this.tela.getAluno().getCodpes()));

		if (this.tela.getAluno() == null) {
			mb.addMessage("ane", "main", FacesMessage.SEVERITY_ERROR);
			this.tela.setAluno(null);
			return;
		}

	}

	public void corrigeTabelaAluno() {

		for (Aluno aluno : daoAluno.buscarAlunos()) {

			try {
				Aluno a1 = daoAluno.buscarAluno(aluno.getCodpes());
				aluno.setNompes(a1.getNompes());
				daoAluno.persist(aluno);
			} catch (NullPointerException e) {
				System.out.println("null pointer " + aluno.getCodpes());
			}
		}

	}

	public void buscarAluno() {
		Aluno a = new Aluno();
		if (IntegerUtil.isInt(tela.getCriterio())) {

			Integer codpes = Integer.valueOf(this.tela.getCriterio());

			a = daoAluno.buscarAluno(codpes);

			if (a != null) {

				this.tela.setAluno(a);

			} else {
				mb.addMessage("ane", "main", FacesMessage.SEVERITY_ERROR);
				this.tela.setAluno(null);

			}

			pt.chamaAluno(tela.getCriterio());
		} else {

			mb.addMessage("ane", "main", FacesMessage.SEVERITY_ERROR);
		}

	}

	public void iniciarTelaAluno() {

		if (this.tela.getCodpes() != null) {
			Aluno a = daoAluno.buscarAluno(Integer.valueOf(this.tela.getCodpes()));
			this.tela.setAluno(a);
			this.tela.getFrmLista().setRendered(false);
			this.tela.getFrmAluno().setRendered(true);
			if (a == null) {
				mb.addMessage("ane", "main", FacesMessage.SEVERITY_ERROR);
			}
		}

	}

	public void encerrarTelaAluno() {
		pt.chamaHome();
	}

	public void mostrarFormBuscaAluno() {
		this.tela.getFrmBuscaAluno().setRendered(true);
		this.tela.getFrmBuscaAlunoCadastro().setRendered(false);
		this.tela.getFrmAluno().setRendered(false);
		this.tela.getFrmCadastro().setRendered(false);
		this.tela.getFrmLista().setRendered(false);
		this.tela.getFrmEstagio().setRendered(false);
	}

	public void mostrarFormBuscaAlunoCadastro() {
		this.tela.getFrmBuscaAluno().setRendered(false);
		this.tela.getFrmBuscaAlunoCadastro().setRendered(true);
		this.tela.getFrmAluno().setRendered(false);
		this.tela.getFrmCadastro().setRendered(false);
		this.tela.getFrmLista().setRendered(false);
		this.tela.getFrmEstagio().setRendered(false);
	}

	public void cadastrarEstagio() {

		Aluno aluno = this.tela.getAluno();
		Estagio estagio = this.tela.getEstagio();
		Documento documento = this.tela.getDocumento();
		Jornada jornada = this.tela.getJornada();
		Remuneracao remuneracao = this.tela.getRemuneracao();
		Supervisor supervisor = this.tela.getSupervisor();

		estagio.getDocumentos().add(documento);
		estagio.getJornadas().add(jornada);
		estagio.getSupervisores().add(supervisor);
		estagio.getRemuneracoes().add(remuneracao);

		documento.setEstagio(estagio);
		jornada.setEstagio(estagio);
		supervisor.setEstagio(estagio);
		remuneracao.setEstagio(estagio);

		jornada.setDtaini(documento.getDtaini());
		supervisor.setDtaini(documento.getDtaini());
		remuneracao.setDtaini(documento.getDtaini());

		aluno.getEstagios().add(estagio);
		estagio.setAluno(aluno);

		// estagio.getEmpresa().getEstagios().add(estagio);

		if (documento.getDtaultand() == null) {
			documento.setDtaultand(new Date());
		}

		if (null != daoAluno.persist(aluno)) {

			aluno = daoAluno.buscarAluno(aluno.getCodpes());

			Collections.sort(aluno.getEstagios(), new OrdenadorEstagioDtaIni());
			this.tela.setAluno(aluno);

			this.tela.getFrmAluno().setRendered(true);
			this.tela.getFrmEstagio().setRendered(false);
			this.tela.getFrmCadastro().setRendered(false);
			this.tela.getFrmEstagios().setRendered(true);

			mb.addMessage("cadok", "main", FacesMessage.SEVERITY_INFO);

		} else {
			mb.addMessage("erro_cadastro_estagio", "main", FacesMessage.SEVERITY_ERROR);
		}

	}

	public void cadastrarDocumento() {

		Documento documento = this.tela.getDocumento();

		Estagio estagio = this.tela.getEstagio();
		estagio.setAluno(this.tela.getAluno());
		estagio.getDocumentos().add(documento);
		documento.setEstagio(estagio);

		if (documento.getDtaultand() == null) {
			documento.setDtaultand(new Date());
		}

		documento = (Documento) daoDocumento.persist(documento);

		if (null != documento) {
			this.tela.setEstagio(daoEstagio.buscarEstagio(estagio.getAluno(), estagio.getEmpresa()));
			mb.addMessage("cadok", "main", FacesMessage.SEVERITY_INFO);
			this.tela.getFrmAluno().setRendered(true);
			this.tela.getFrmEstagio().setRendered(true);
			this.tela.getFrmCadastro().setRendered(false);
			this.tela.getFrmCadastroDocumento().setRendered(false);
			this.tela.getFrmEstagios().setRendered(false);

			this.tela.setDocumento(new Documento());

		} else {
			mb.addMessage("erro_cadastro_documento", "main", FacesMessage.SEVERITY_ERROR);
		}

	}

	public void abrirCadastroEstagio() {
		this.tela.setEstagio(new Estagio());
		this.tela.setDocumento(new Documento());
		this.tela.setSupervisor(new Supervisor());
		this.tela.setJornada(new Jornada());
		this.tela.setRemuneracao(new Remuneracao());

		// somente para PF >= 6.2
		// PrimeFaces current = PrimeFaces.current();
		// current.executeScript("PF('dlgSelect').show();");

		// somente para PF >+ 4.x ate < 6.2
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgAddEstagio').show();");

	}

	public void abrirCadastroDocumento() {

		this.tela.setDocumento(new Documento());
		this.tela.getFrmAluno().setRendered(true);
		this.tela.getFrmEstagio().setRendered(false);
		this.tela.getFrmEstagios().setRendered(false);
		this.tela.getFrmCadastro().setRendered(false);
		this.tela.getFrmCadastroDocumento().setRendered(true);
	}

	public void cancelarCadastroEstagio() {
		this.tela.getFrmEstagio().setRendered(false);
		this.tela.getFrmEstagios().setRendered(true);
		this.tela.getFrmCadastro().setRendered(false);
	}

	public void cancelarCadastroDocumento() {
		this.tela.getFrmEstagio().setRendered(true);
		this.tela.getFrmEstagios().setRendered(false);
		this.tela.getFrmCadastro().setRendered(false);
		this.tela.getFrmCadastroDocumento().setRendered(false);
	}

	public void atualizarEstagio() {
		this.tela.getFrmEstagio().setRendered(false);
		this.tela.getFrmEstagios().setRendered(true);
		this.tela.getFrmCadastro().setRendered(false);

	}

	public void deletarEstagio() {
		Estagio e = this.tela.getEstagio();

		for (Documento d : e.getDocumentos()) {
			daoEstagio.deletarDocumento(d);
		}

		for (Notificacao n : e.getNotificacoes()) {
			daoEstagio.deletarNotificacao(n);
		}

		for (Supervisor s : e.getSupervisores()) {
			daoEstagio.deletarSupervisor(s);
		}

		for (Remuneracao r : e.getRemuneracoes()) {
			daoEstagio.deletarRemuneracao(r);
		}

		for (Jornada j : e.getJornadas()) {
			daoEstagio.deletarJornada(j);
		}

		daoEstagio.deletarEstagio(e);

		this.tela.setEstagio(null);
		this.tela.getFrmEstagio().setRendered(false);
		this.tela.getFrmEstagios().setRendered(true);

		mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
	}

	public void deletarDocumento(Documento d) {

		int result = daoEstagio.deletarDocumento(d);
		if (result > 0) {
			mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
			this.tela.getEstagio().getDocumentos().remove(d);
		}
	}

	public void deletarRemuneracao(Remuneracao r) {

		if (r.getId() == null) {
			this.tela.getEstagio().getRemuneracoes().remove(r);
		} else {
			int result = daoEstagio.deletarRemuneracao(r);
			if (result > 0) {
				mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
				this.tela.getEstagio().getRemuneracoes().remove(r);
			}
		}
	}

	public void deletarSupervisor(Supervisor s) {
		if (s.getId() == null) {
			this.tela.getEstagio().getSupervisores().remove(s);
		} else {

			int result = daoEstagio.deletarSupervisor(s);
			if (result > 0) {
				mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
				this.tela.getEstagio().getSupervisores().remove(s);
			}
		}
	}

	public void deletarJornada(Jornada j) {
		if (j.getId() == null) {
			this.tela.getEstagio().getJornadas().remove(j);
		} else {

			int result = daoEstagio.deletarJornada(j);
			if (result > 0) {
				mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
				this.tela.getEstagio().getJornadas().remove(j);
			}
		}
	}

	public void salvarDocumento(Documento d) {

		Estagio e = this.tela.getEstagio();

		d.setEstagio(e);

		d = (Documento) daoDocumento.persist(d);

		if (d.getId() != null) {
			mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
		}
	}

	public void salvarEstagio() {
		Estagio e = this.tela.getEstagio();

		if (null != (Estagio) daoEstagio.persist(e)) {
			this.tela.setEstagio(daoEstagio.buscarEstagio(e.getAluno(), e.getEmpresa()));
		} else {
			mb.addMessage("erro_cadastro_estagio", "main", FacesMessage.SEVERITY_ERROR);
		}

	}

	public void salvarRemuneracao() {
		Estagio e = this.tela.getEstagio();

		for (Remuneracao r : e.getRemuneracoes()) {
			if (r.getDtaini().before(e.getDtaini()) || r.getDtaini().after(e.getDtafim())) {
				mb.addMessage("data_invalida", "main", FacesMessage.SEVERITY_ERROR);
				return;
			}
		}

		e = (Estagio) daoEstagio.persist(e);
		Estagio e1 = daoEstagio.buscarEstagio(e.getAluno(), e.getEmpresa());
		this.tela.setEstagio(e1);
		mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
	}

	public void salvarSupervisor() {
		Estagio e = this.tela.getEstagio();

		for (Supervisor s : e.getSupervisores()) {
			if (s.getDtaini().before(e.getDtaini()) || s.getDtaini().after(e.getDtafim())) {
				mb.addMessage("data_invalida", "main", FacesMessage.SEVERITY_ERROR);
				return;
			}
		}

		e = (Estagio) daoEstagio.persist(e);
		Estagio e1 = daoEstagio.buscarEstagio(e.getAluno(), e.getEmpresa());
		this.tela.setEstagio(e1);
		mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
	}

	public void salvarJornada() {
		Estagio e = this.tela.getEstagio();

		for (Jornada j : e.getJornadas()) {
			if (j.getDtaini().before(e.getDtaini()) || j.getDtaini().after(e.getDtafim())) {
				mb.addMessage("data_invalida", "main", FacesMessage.SEVERITY_ERROR);
				return;
			}
		}

		e = (Estagio) daoEstagio.persist(e);
		Estagio e1 = daoEstagio.buscarEstagio(e.getAluno(), e.getEmpresa());
		this.tela.setEstagio(e1);
		mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
	}

	public void adicionarNovoDocumento() {
		// somente para PF >= 6.2
		// PrimeFaces current = PrimeFaces.current();
		// current.executeScript("PF('dlgSelect').show();");

		// somente para PF >+ 4.x ate < 6.2
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgAddDocumento').show();");
	}

	public void adicionarNovaRemuneracao() {
		Remuneracao r = new Remuneracao();
		Estagio e = this.tela.getEstagio();
		r.setEstagio(e);
		r.setDtaini(e.getDtaini());
		e.getRemuneracoes().add(r);
		this.tela.setEstagio(e);
	}

	public void adicionarNovaJornada() {
		Jornada j = new Jornada();
		Estagio e = this.tela.getEstagio();
		j.setDtaini(e.getDtaini());
		j.setEstagio(e);
		e.getJornadas().add(j);
		this.tela.setEstagio(e);
	}

	public void adicionarNovoSupervisor() {
		Supervisor s = new Supervisor();
		Estagio e = this.tela.getEstagio();
		s.setDtaini(e.getDtaini());
		s.setEstagio(e);
		e.getSupervisores().add(s);
		this.tela.setEstagio(e);
	}

	public void deletarDocumentos(Estagio e) {

		if (this.tela.getDocumentos().isEmpty()) {
			mb.addMessage("nde", "main", FacesMessage.SEVERITY_INFO);
		} else {

			// excluir os documentos da tela
			for (Estagio e1 : this.tela.getAluno().getEstagios()) {
				if (e.getId() == e1.getId()) {
					e1.getDocumentos().removeAll(this.tela.getDocumentos());
				}
			}

			// excluir os documentos do banco de dados
			for (Documento d : this.tela.getDocumentos()) {
				daoEstagio.deletarDocumento(d);
			}

			int i = this.tela.getDocumentos().size();

			String msg = "";

			// excluir os documentos selecionados da tela
			this.tela.getDocumentos().clear();

			mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
		}
	}

	public void abrirEdicao(Estagio e) {
		this.tela.setEstagio(e);

		this.tela.getFrmEstagio().setRendered(true);
		this.tela.getFrmEstagios().setRendered(false);
	}

	public void abrirEdicaoDocumento(Documento d) {

		this.tela.setDocumento(d);

		// somente para PF >= 6.2
		// PrimeFaces current = PrimeFaces.current();
		// current.executeScript("PF('dlgSelect').show();");

		// somente para PF >+ 4.x ate < 6.2
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlgEditDocumento').show();");

	}

	public void fecharEdicao() {
		this.tela.getFrmAluno().setRendered(true);
		this.tela.getFrmEstagio().setRendered(false);
		this.tela.getFrmEstagios().setRendered(true);
	}

	public void mudarTipoDoc(ValueChangeEvent e) {
		HtmlSelectOneMenu select = (HtmlSelectOneMenu) e.getComponent();
		Documento documento = (Documento) (select.getAttributes().get("documento"));
		ETipoDoc tipo = (ETipoDoc) e.getNewValue();
		documento.setTipo(tipo);
		daoDocumento.persist(documento);
	}

	public void notificarRetiradaDocumento() {

		String destinatario = this.tela.getAluno().getEmail();

		if (destinatario == null) {
			mb.addMessage("email_nao_encontrado", "main", FacesMessage.SEVERITY_ERROR);
			return;
		}

		Date agora = new Date();

		Estagio e = this.tela.getEstagio();
		e.setAluno(this.tela.getAluno());

		Notificacao n = new Notificacao();
		n.setCodigo(DataUtility.getCodigo(agora));
		n.setData(agora);
		n.setEstagio(e);
		n.setTipo(ETipoNotificacao.AVISO_RETIRADA);
		e.getNotificacoes().add(n);

		e = (Estagio) daoEstagio.persist(e);

		if (e != null) {
			Estagio e1 = daoEstagio.buscarEstagio(e.getAluno(), e.getEmpresa());
			this.tela.setEstagio(e1);

			// cria a mensagem para ser enviada

			String msg = MessageFactory.getMsgRetiradaDocumento(e);

			Recursos recursos = new Recursos();
			String remetente = recursos.getResourceValue("email");
			String appName = recursos.getResourceValue("email");

			Mensagem m = new Mensagem();
			m.setCriacao(new Date());
			m.setAssunto("[" + appName + " ] DOCUMENTO PRONTO PARA RETIRADA #" + n.getCodigo());
			m.setDe(remetente);
			m.setMensagem(msg);
			m.setPara(destinatario + ", " + remetente);

			if (daoMensagem.persist(m) == null) {
				mb.addMessage("oprerr", "main", FacesMessage.SEVERITY_ERROR);
			} else {
				mb.addMessage("oprok", "main", FacesMessage.SEVERITY_INFO);
			}
		}
	}

	public void mudarStatusDoc(ValueChangeEvent e) {
		HtmlSelectOneMenu component = (HtmlSelectOneMenu) e.getComponent();
		Documento documento = (Documento) (component.getAttributes().get("documento"));
		EStatusDoc status = (EStatusDoc) e.getNewValue();
		documento.setStatusDoc(status);
		daoDocumento.persist(documento);
	}

	public void mudarDataInicio(SelectEvent e) {
		Calendar component = (Calendar) e.getComponent();
		Documento documento = (Documento) (component.getAttributes().get("documento"));
		Date data = (Date) e.getObject();
		documento.setDtaini(data);
		daoDocumento.persist(documento);
	}

	public void mudarDataFim(SelectEvent e) {
		Calendar component = (Calendar) e.getComponent();
		Documento documento = (Documento) (component.getAttributes().get("documento"));
		Date data = (Date) e.getObject();
		documento.setDtafim(data);
		daoDocumento.persist(documento);
	}

	public void mudarDataAndamento(SelectEvent e) {
		Calendar component = (Calendar) e.getComponent();
		Documento documento = (Documento) (component.getAttributes().get("documento"));
		Date data = (Date) e.getObject();
		documento.setDtaultand(data);
		daoDocumento.persist(documento);
	}

	public void mudarRemuneracao(ValueChangeEvent e) {
		HtmlInputText component = (HtmlInputText) e.getComponent();
		Documento documento = (Documento) (component.getAttributes().get("documento"));
		daoDocumento.persist(documento);
	}

	public void mudarCargaHoraria(ValueChangeEvent e) {
		HtmlInputText component = (HtmlInputText) e.getComponent();
		Documento documento = (Documento) (component.getAttributes().get("documento"));
		int txt = (int) e.getNewValue();

		documento.setCgahorsem(txt);
		daoDocumento.persist(documento);
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}

	public void verificarEstagios() {
		Date hoje = new Date();
		List<String> verificacoes = new ArrayList<String>(Arrays.asList(this.tela.getVerificacoesSelecionadas()));
		List<Empresa> conveniadas = daoEmpresa.buscarEmpresasConveniadas(hoje);
		List<Documento> documentos = daoDocumento.buscarDocumentosAtivosPeriodo(hoje, hoje);
		Set<Aluno> alunos = new HashSet<Aluno>();
		Set<Aluno> alunosDetectados = new HashSet<Aluno>();
		List<Aluno> alunosDetectados1 = new ArrayList<Aluno>();

		// extraio dos documentos os alunos com estagio ativo
		for (Documento d : documentos) {
			alunos.add(d.getEstagio().getAluno());
		}

		if (verificacoes.contains("CONVENIO_PROXIMO_ENCERRAMENTO")) {
			verificarConvenios();
		}

		for (Aluno a : alunos) {
			String problema = "";

			// Programa Encerrado
			if (verificacoes.contains("PROGRAMA_ENCERRADO")) {
				if (!daoReplicado.temProgramaAtivo(a.getCodpes())) {
					problema = problema + "<li>Programa encerrado ou trancado</li>";
				}
			}

			if (verificacoes.contains("SEM_MATRICULA")) {
				// Sem matricula
				if (!daoReplicado.temMatriculaAtiva(a.getCodpes())) {
					problema = problema + "<li>Aluno sem matrícula</li>";
				}
			}

			int ea = 0;
			for (Estagio e : a.getEstagios()) {

				if (e.isAtivo()) {
					ea++;
				}

				if (verificacoes.contains("ESTAGIOS_SIMULTANEOS")) {
					// Estágios Simultâneos
					if (ea > 1) {
						// a.setObservacao(a.getObservacao() + " Estágios simultâneos ");
						// this.tela.getAlunos().add(a);
						problema = problema + "<li>Aluno possui estágios simultâneos</li>";
					}
				}

				if (verificacoes.contains("RELATORIO_ATIVIDADES")) {
					// Devendo Relatório de Atividades
					if (e.deveRelatorioAtividade()) {
						problema = problema + "<li>Aluno deve relatório de atividades " + e.getEmpresa().getNome() + "</li>";
					}
				}

				if (verificacoes.contains("CONVENIO_ENCERRADO")) {

					// Convênio Encerrado
					if (!conveniadas.contains(e.getEmpresa())) {
						problema = problema + ("<li>Convênio empresa encerrado " + e.getEmpresa().getNome() + "</li>");

					}
				}

				if (problema != "") {
					a.setObservacao(problema);
					// this.tela.getAlunos().add(a);
					alunosDetectados.add(a);
				}

			}

		}

		if (verificacoes.contains("ESTAGIO_ENCERRADO_SEM_COMPROVACAO")) {

			System.out.println("Vai verificar estagio encerrado sem comprovacao");

			List<Estagio> estagios = daoEstagio.buscarEstagios();

			for (Estagio e : estagios) {

				System.out.println("Verificando se estagio foi encerrado sem comprovacao " + e.getId());
				String problema = "";

				if (e.getDtaini().before(hoje)) {
					// estagio encerrado sem comprovacao
					if (!e.isAtivo() && !e.temEncerramentoAprovado()) {

						problema = problema + ("<li>Aluno não entregou comprovante de encerramento de estágio " + e.getEmpresa().getNome() + "</li>");
					}
				}

				if (problema != "") {
					try {
						e.getAluno().setObservacao(problema);
						alunosDetectados1.add(e.getAluno());
					} catch (NullPointerException n) {
						// pula ...
					}
				}
			}
		}

		if (verificacoes.contains("ESTAGIO_PROXIMO_ENCERRAMENTO")) {

			List<Estagio> estagios = daoEstagio.buscarEstagios();

			for (Estagio e : estagios) {
				String problema = "";

				// estagio proximo ao vencimento
				if (e.getDtafim().after(hoje)) {
					if (e.proximoEncerramento()) {
						problema = problema
								+ ("<li>Estágio próximo do encerramento (" + DataUtility.dateToStringDate(e.getDtafim()) + ") " + e.getEmpresa().getNome() + "</li>");
					}
				}

				if (problema != "") {
					try {
						e.getAluno().setObservacao(problema);
						alunosDetectados1.add(e.getAluno());
					} catch (NullPointerException n) {
						// pula ...
					}
				}
			}
		}

		this.tela.getAlunos().clear();
		this.tela.getAlunos().addAll(alunosDetectados);
		this.tela.getAlunos().addAll(alunosDetectados1);

		Collections.sort(this.tela.getAlunos(), new OrdenadorAlunoNome());

		if (this.tela.getAlunos().isEmpty()) {
			mb.addMessage("semreg", "main", FacesMessage.SEVERITY_INFO);
		}
	}

	public void verificarConvenios() {
		List<Empresa> empresas = daoEmpresa.buscarEmpresasConveniadasProximoEncerramento();
		this.tela.setEmpresas(empresas);
		this.tela.getAlunos().clear();
	}

}
