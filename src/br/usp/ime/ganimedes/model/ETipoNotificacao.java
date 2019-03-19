package br.usp.ime.ganimedes.model;

public enum ETipoNotificacao {

	AVISO_RECEBIMENTO("Recebimento de Documento"), AVISO_RETIRADA("Retirada de Documento");

	private final String valor;

	ETipoNotificacao(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}

}
