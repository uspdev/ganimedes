package br.usp.ime.ganimedes.view;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usp.ime.ganimedes.model.Usuario;

public class RestoreViewPhaseListener implements PhaseListener {
	private static final long serialVersionUID = 1L;

	@Inject
	Usuario usuarioLogado;

	public void afterPhase(PhaseEvent arg0) {

	}

	public void beforePhase(PhaseEvent arg0) {

		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();

		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String baseUrl = request.getServletContext().getContextPath() + "/";

		String viewId = fc.getViewRoot().getViewId();

		if (viewId.equalsIgnoreCase("/public/forbidden.xhtml") || viewId.equalsIgnoreCase("/public/login.xhtml")
				|| viewId.equalsIgnoreCase("/public/callback.xhtml") || viewId.equalsIgnoreCase("/public/requisicao_senha.xhtml")) {

			return;

		}

		if (viewId.equalsIgnoreCase("/empresa/login.xhtml") || viewId.equalsIgnoreCase("/empresa/cadastro.xhtml")
				|| viewId.equalsIgnoreCase("/empresa/email.xhtml") || viewId.equalsIgnoreCase("/empresa/formulario_usuario.xhtml")
				|| viewId.equalsIgnoreCase("/empresa/reenviar_email_confirmacao.xhtml") || viewId.equalsIgnoreCase("/empresa/recuperar_senha.xhtml")
				|| viewId.equalsIgnoreCase("/empresa/confirmacao.xhtml") || viewId.equalsIgnoreCase("/empresa/nova_senha.xhtml")) {
			return;
		} else if (!usuarioLogado.isAutorizado()) {

			try {
				ec.redirect(baseUrl + "public/login.xhtml");
			} catch (IOException e) {
				return;
			}
		}

	}

	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

}