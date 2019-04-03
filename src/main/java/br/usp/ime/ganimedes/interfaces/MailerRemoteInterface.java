package br.usp.ime.ganimedes.interfaces;

public interface MailerRemoteInterface {

	public void send(String from, String to, String cc, String subject, String msg);

}
