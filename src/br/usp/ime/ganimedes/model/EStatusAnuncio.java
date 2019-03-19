package br.usp.ime.ganimedes.model;

public enum EStatusAnuncio {

	AGUARDANDO_APROVACAO("Aguardando aprovação"), APROVADO("Aprovado"), REJEITADO("Rejeitado"), ENCERRADO("Encerrado");

	private final String valor;

	EStatusAnuncio(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}

}
