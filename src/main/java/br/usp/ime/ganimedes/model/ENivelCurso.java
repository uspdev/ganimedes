package br.usp.ime.ganimedes.model;

public enum ENivelCurso {

	GRADUACAO("Graduação"), POS("Pós-Graduação"), ESPECIALIZACAO("Especialização"), MESTRADO("Mestrado"), MP("Mestrado Profissionalizante"), DOUTORADO(
			"Doutorado");

	private final String valor;

	ENivelCurso(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}

}
