package br.usp.ime.ganimedes.model;

public enum ERegime {

	ESTAGIO("Estágio"), TRAINEE("Trainee (CLT)"), EFETIVO("Efetivo (CLT)"), FREELA("Freelancer");

	private final String valor;

	ERegime(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}

}
