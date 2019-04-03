package br.usp.ime.agendador;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import br.usp.ime.ganimedes.dao.DaoMensagem;
import br.usp.ime.ganimedes.interfaces.MailerRemoteInterface;
import br.usp.ime.ganimedes.model.Mensagem;

@Stateless
public class Agendador {

	@EJB
	DaoMensagem daoMensagem;

	@EJB
	MailerRemoteInterface mailer;

	// a cada 10 minutos
	@Schedule(minute = "*/10", hour = "*", persistent = false)
	public void agenda() {

		List<Mensagem> mensagens = daoMensagem.buscarUsuarioMensagensNaoEnviadas();

		for (Mensagem mensagem : mensagens) {
			System.out.println("Mensagem Enviada " + mensagem.getId());
			mensagem.setEnvio(new Date());
			daoMensagem.persist(mensagem);
			mailer.send(mensagem.getDe(), mensagem.getPara(), "", mensagem.getAssunto(), mensagem.getMensagem());
		}

	}
}