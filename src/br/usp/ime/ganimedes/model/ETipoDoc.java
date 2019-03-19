package br.usp.ime.ganimedes.model;

public enum ETipoDoc {

	COM("Termo de Compromisso"), ADI("Termo Aditivo"), REA("Termo de Realização"), RES("Termo de Rescisão"), ATV ("Relatório de Atividades");

	private final String valor;

	ETipoDoc(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}

}
