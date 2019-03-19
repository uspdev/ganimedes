package br.usp.ime.ganimedes.model;

public enum EStatusDoc {

	DEFERIDO("Deferido"), INDEFERIDO("Indeferido"), SEM("Sem Parecer");

	private final String valor;

	EStatusDoc(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}

}
