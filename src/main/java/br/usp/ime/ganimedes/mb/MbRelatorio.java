package br.usp.ime.ganimedes.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.itextpdf.text.DocumentException;

import br.usp.ime.ganimedes.documentos.RelatorioEstagio;
import br.usp.ime.ganimedes.documentos.RelatorioEstagioPorCurso;
import br.usp.ime.ganimedes.ejb.GanimedesInterface;
import br.usp.ime.ganimedes.model.CursoGr;
import br.usp.ime.ganimedes.model.Empresa;
import br.usp.ime.ganimedes.model.Estagio;
import br.usp.ime.ganimedes.model.Usuario;
import br.usp.ime.ganimedes.view.MessageBean;
import br.usp.ime.ganimedes.view.PageTransitionBean;
import br.usp.ime.ganimedes.view.ViewRelatorio;
import br.usp.ime.util.OrdenadorEstagioNomeAluno;

@ManagedBean(name = "mbRelatorio")
@ViewScoped
public class MbRelatorio implements Serializable {
	private static final long serialVersionUID = 1L;

	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();

	@Inject
	Usuario usuarioLogado;

	Logger log = Logger.getLogger(MbRelatorio.class.getName());

	@EJB
	GanimedesInterface ejb;

	private ViewRelatorio tela = new ViewRelatorio();

	PageTransitionBean pt = new PageTransitionBean();

	private MessageBean mb = new MessageBean();

	public ViewRelatorio getTela() {
		return tela;
	}

	public void setTela(ViewRelatorio tela) {
		this.tela = tela;
	}

	public void gerarRelatorioEstagio() {
		Date di = this.tela.getDtaini();
		Date df = this.tela.getDtafim();

		if (di.after(df)) {
			mb.addMessage("dipdf", "main", FacesMessage.SEVERITY_ERROR);
			return;
		}

		Empresa empresa = this.tela.getEstagio().getEmpresa();

		List<Estagio> estagios = new ArrayList<Estagio>();

		if (empresa == null) {

			estagios = ejb.buscarEstagiosAtivosPeriodo(di, df);
		} else {
			estagios = ejb.buscarEstagiosAtivosPeriodo(di, df, empresa);
		}

		if (estagios.isEmpty()) {
			mb.addMessage("semreg", "main", FacesMessage.SEVERITY_ERROR);
			return;
		}

		if (this.tela.getCursosGraduacao().size() == 0) {

			Collections.sort(estagios, new OrdenadorEstagioNomeAluno());

			RelatorioEstagio doc = new RelatorioEstagio();

			try {
				doc.gerarDocumento(estagios, di, df);
			} catch (DocumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if (this.tela.getCursosGraduacao().size() > 0) {

			gerarRelatorioEstagiosPorCurso(di, df, estagios);
		}

	}

	private void gerarRelatorioEstagiosPorCurso(Date di, Date df, List<Estagio> estagios) {

		HashMap<CursoGr, List<Estagio>> mapaRelatorio = new HashMap<CursoGr, List<Estagio>>();

		for (CursoGr c : this.tela.getCursosGraduacao()) {
			List<Estagio> estagiosRelatorio = new ArrayList<Estagio>();

			for (Estagio e : estagios) {

				if (c.getCodcur() == e.getAluno().getCodcur() && c.getCodhab() == e.getAluno().getCodhab()) {
					estagiosRelatorio.add(e);
				}

			}
			Collections.sort(estagiosRelatorio, new OrdenadorEstagioNomeAluno());
			mapaRelatorio.put(c, estagiosRelatorio);
		}

		RelatorioEstagioPorCurso doc = new RelatorioEstagioPorCurso();

		try {
			doc.gerarDocumento(mapaRelatorio, di, df);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
	}

	public void gerarRelatorioConvenio() {

		List<Empresa> empresas = ejb.buscarEmpresasConveniadas(this.tela.getDta());

		if (empresas.isEmpty()) {
			mb.addMessage("semreg", "main", FacesMessage.SEVERITY_INFO);
		} else {
			ejb.gerarRelatorioEmpresasConveniadas(empresas, this.tela.getDta());
		}

	}

	public void selecionarTodosCursos() {

		this.tela.setCursos(ejb.buscarCursos());

	}

	public void cancelarSelecaoCursos() {
		this.tela.getCursos().clear();
	}

}