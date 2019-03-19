package br.usp.ime.ganimedes.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

import com.itextpdf.text.DocumentException;

import br.usp.ime.ganimedes.dao.DaoAnuncio;
import br.usp.ime.ganimedes.dao.DaoUsuario;
import br.usp.ime.ganimedes.documentos.DocumentoAnuncio;
import br.usp.ime.ganimedes.documentos.DocumentoCartaz;
import br.usp.ime.ganimedes.model.Anuncio;
import br.usp.ime.ganimedes.model.ERegime;
import br.usp.ime.ganimedes.model.EStatusAnuncio;
import br.usp.ime.ganimedes.model.Usuario;
import br.usp.ime.ganimedes.view.MessageBean;
import br.usp.ime.ganimedes.view.PageTransitionBean;
import br.usp.ime.ganimedes.view.ViewAnuncio;
import br.usp.ime.util.OrdenadorAnuncioDataReversa;

@ManagedBean(name = "mbAnuncio")
@ViewScoped
public class MbAnuncio implements Serializable {
	private static final long serialVersionUID = 1L;

	FacesContext fc = FacesContext.getCurrentInstance();
	HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();

	@Inject
	Usuario usuarioLogado;

	Logger log = Logger.getLogger(MbAnuncio.class.getName());

	@EJB
	DaoAnuncio daoAnuncio;

	@EJB
	DaoUsuario daoUsuario;

	private ViewAnuncio tela = new ViewAnuncio();

	PageTransitionBean pt = new PageTransitionBean();

	private MessageBean mb = new MessageBean();

	private String status;

	public void atualizarAnuncio() {
		Anuncio anuncio = this.tela.getAnuncio();
		daoAnuncio.persist(anuncio);
		mb.addMessage("updateok", "main", FacesMessage.SEVERITY_INFO);
		this.tela.getFrmCadastro().setRendered(true);
		this.tela.getFrmLista().setRendered(false);
	}

	public void salvarFechar() {
		Anuncio anuncio = this.tela.getAnuncio();
		daoAnuncio.persist(anuncio);
		mb.addMessage("updateok", "main", FacesMessage.SEVERITY_INFO);
		this.tela.getFrmCadastro().setRendered(false);
		this.tela.getFrmLista().setRendered(true);
	}

	public void aprovarAnuncio(Anuncio a) {
		a.setStatus(EStatusAnuncio.APROVADO);
		daoAnuncio.persist(a);
	}

	public void buscarAnuncios() {

		switch (this.getTela().getStatus()) {
		case "":
			this.tela.setAnuncios(daoAnuncio.buscarAnunciosNovos());
			break;

		case "AGUARDANDO":
			this.tela.setAnuncios(daoAnuncio.buscarAnunciosNovos());
			break;

		case "APROVADOS":
			this.tela.setAnuncios(daoAnuncio.buscarAnunciosAprovados());
			break;

		case "ENCERRADOS":
			this.tela.setAnuncios(daoAnuncio.buscarAnunciosEncerrados());
			break;

		case "REJEITADOS":
			this.tela.setAnuncios(daoAnuncio.buscarAnunciosRejeitados());
			break;

			
		case "POR_ANUNCIANTE":
			this.tela.setAnuncios(daoAnuncio.buscarAnuncios(this.tela.getAnuncio().getUsuario()));
			break;

			
		default:
			this.tela.setAnuncios(daoAnuncio.buscarAnunciosNovos());
			break;

		}

		Collections.sort(this.tela.getAnuncios(), new OrdenadorAnuncioDataReversa());

	}
	
	public void buscarAnunciosPorAnunciante(Usuario u) {
		//this.tela.setAnuncios(daoAnuncio.buscarAnuncios(u));
		List<Anuncio> anuncios = daoAnuncio.buscarAnuncios(u);
		for (Anuncio anuncio : anuncios) {
			System.out.println(anuncio.getId() + " " + anuncio.getNomeEmpresa() + " " + anuncio.getTitvag() );
		}
		System.out.println("Total de anuncios = " + anuncios.size());
		this.tela.getAnuncios().clear();
	}

	/** esse metodo serve para o download do anuncio que a propria empresa criou **/
	public void downloadAnuncio(Anuncio a) {

		DocumentoCartaz doc = new DocumentoCartaz();

		try {
			doc.downloadAnuncio(a);
		} catch (IOException e) {
			mb.addMessage("erro_geracao_cartaz", "main", FacesMessage.SEVERITY_ERROR);
		}

	}

	public void gerarCartaz(Anuncio a) {

		if (a == null) {
			a = this.tela.getAnuncio();
		}

		DocumentoAnuncio doc = new DocumentoAnuncio();
		try {
			doc.gerarDocumento(a);
		} catch (DocumentException | IOException e) {
			mb.addMessage("erro_geracao_cartaz", "main", FacesMessage.SEVERITY_ERROR);
		}

	}

	public void encerrarAnuncio(Anuncio a) {
		a.setStatus(EStatusAnuncio.ENCERRADO);
		daoAnuncio.persist(a);
	}

	public ViewAnuncio getTela() {
		return tela;
	}

	public Date getHoje() {
		return new Date();
	}

	public List<Anuncio> getMeusAnuncios() {
		return daoAnuncio.buscarAnuncios(usuarioLogado);
	}

	public void editar() {
		this.tela.getFrmLista().setRendered(false);
		this.tela.getFrmCadastro().setRendered(true);
	}

	public void mudarDataMaximaDivulgacao(SelectEvent e) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.tela.getAnuncio().getDtainidiv());
		cal.add(Calendar.MONTH, 1);
		// this.tela.getAnuncio().setDtafimdiv(this.tela.getAnuncio().getDtainidiv());
		this.tela.getAnuncio().setDtafimdiv(cal.getTime());
	}

	public void rejeitarAnuncio(Anuncio a) {
		a.setStatus(EStatusAnuncio.REJEITADO);
		daoAnuncio.persist(a);
	}

	public void salvarAnuncio() {

		Anuncio anuncio = this.tela.getAnuncio();

		if (anuncio.getHorsem() == null) {
			anuncio.setHorsem(0);
		}

		if (anuncio.getSalmes() == null) {
			anuncio.setSalmes(0.00);
		}

		if (anuncio.getRegimeTrabalho().equals(ERegime.ESTAGIO) && ((anuncio.getHorsem() > 30))) {
			mb.addMessage("che", "main", FacesMessage.SEVERITY_ERROR);

		} else {

			anuncio.setStatus(EStatusAnuncio.AGUARDANDO_APROVACAO);
			anuncio.setDtaCriacao(new Date());
			anuncio.setIp(request.getRemoteAddr());

			Usuario usuario = (Usuario) daoUsuario.findById(usuarioLogado.getId());
			usuario.setAnuncios(daoAnuncio.buscarAnuncios(usuario));
			usuario.getAnuncios().add(anuncio);
			anuncio.setUsuario(usuario);
			anuncio = (Anuncio) daoAnuncio.persist(anuncio);

			if (anuncio == null) {
				mb.addMessage("erro_cadastro_anuncio", "cadastro", FacesMessage.SEVERITY_ERROR);
				return;
			}
			this.tela.setAnuncio(anuncio);

			// this.tela.getFrmVisualizacao().setRendered(true);
			this.tela.getFrmCadastro().setRendered(false);
			this.tela.getFrmEdicao().setRendered(false);
			this.tela.getFrmLista().setRendered(true);
			mb.addMessage("cadok", "main", FacesMessage.SEVERITY_INFO);

		}
	}

	public void setTela(ViewAnuncio tela) {
		this.tela = tela;
	}

	public void uploadLogo(FileUploadEvent event) {
		FacesMessage message = new FacesMessage("O arquivo ", event.getFile().getFileName() + " foi carregado.");
		FacesContext.getCurrentInstance().addMessage("messages", message);
		this.tela.getAnuncio().setLogotipo(event.getFile().getContents());
	}

	public void uploadCartaz(FileUploadEvent event) {
		FacesMessage message = new FacesMessage("O arquivo ", event.getFile().getFileName() + " foi carregado.");
		FacesContext.getCurrentInstance().addMessage("messages", message);
		this.tela.getAnuncio().setCartaz(event.getFile().getContents());
	}

	public void visualizarAnuncio(Anuncio a) {
		this.tela.setAnuncio(a);
		this.tela.getFrmLista().setRendered(false);
		this.tela.getFrmVisualizacao().setRendered(true);
	}

	public void fecharVisualizacao() {
		this.tela.getFrmVisualizacao().setRendered(false);
		this.tela.getFrmLista().setRendered(true);
	}

	public void abrirCadastro() {
		this.tela.setAnuncio(new Anuncio());
		this.tela.getFrmCadastro().setRendered(true);
		this.tela.getFrmLista().setRendered(false);
	}

	public void fecharEdicao() {
		this.tela.getFrmEdicao().setRendered(false);
		this.tela.getFrmCadastro().setRendered(false);
		this.tela.getFrmLista().setRendered(true);
	}

	public List<Anuncio> getAnunciosAlunos() {
		List<Anuncio> anuncios = daoAnuncio.buscarAnunciosAprovados();
		return anuncios;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}