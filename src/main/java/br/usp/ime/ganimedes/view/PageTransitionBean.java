package br.usp.ime.ganimedes.view;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ManagedBean(name = "PageTransition")
@RequestScoped
public class PageTransitionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();

	HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	String baseUrl = request.getServletContext().getContextPath() + "/";

	public void chamaHome() {

		try {
			ec.redirect(baseUrl + "index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void chamaHomeEmpresa() {

		try {
			ec.redirect(baseUrl + "empresa/index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void chamaHomeAluno() {

		try {
			ec.redirect(baseUrl + "aluno/index.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void chamaAjuda() {

		try {
			ec.redirect(baseUrl + "ajuda.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void chamaAjudaEmpresa() {

		try {
			ec.redirect(baseUrl + "empresa/ajuda.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void chamaAnuncio() {

		try {
			ec.redirect(baseUrl + "anuncio.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void chamaAnuncios() {

		try {
			ec.redirect(baseUrl + "anuncios.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void chamaConvenios() {

		try {
			ec.redirect(baseUrl + "convenios.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void chamaEstagios() {

		try {
			ec.redirect(baseUrl + "estagios.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void chamaAluno(String codpes) {

		try {
			ec.redirect(baseUrl + "aluno.xhtml?codpes=" + codpes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void chamaControleAnuncios() {

		try {
			ec.redirect(baseUrl + "controle_anuncios.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void chamaMeusAnuncios() {

		try {
			ec.redirect(baseUrl + "empresa/meus_anuncios.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void chamaLogin() {
		try {
			ec.redirect(baseUrl + "login.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void chamaLoginEmpresa() {
		try {
			ec.redirect(baseUrl + "/empresa/login.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void chamaLogout() {
		try {
			ec.redirect("http://uspdigital.usp.br/wsusuario/sair");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void chamaRecuperarSenha() {
		try {
			ec.redirect(baseUrl + "/empresa/recuperar_senha.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void chamaReenviarEmailConfirmacao() {
		try {
			ec.redirect(baseUrl + "empresa/reenviar_email_confirmacao.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void chamaCadastro() {

		try {
			ec.redirect("cadastro.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void chamaVerificacoes() {

		try {
			ec.redirect(baseUrl + "verificacoes.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void chamaRelatorios() {
		try {
			ec.redirect(baseUrl + "relatorios.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void chamaUsuario() {

		try {
			ec.redirect(baseUrl + "usuario.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void chamaGrupo() {

		try {
			ec.redirect(baseUrl + "grupo.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
