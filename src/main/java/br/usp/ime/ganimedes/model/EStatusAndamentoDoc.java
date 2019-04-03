package br.usp.ime.ganimedes.model;

public enum EStatusAndamentoDoc {

	AAC("Coordenador"), AAE("Pendente"), ARA("Pronto"), DRA("Retirado"), ACG("CG");

	private final String valor;

	EStatusAndamentoDoc(String valorOpcao) {
		valor = valorOpcao;
	}

	public String getValor() {
		return valor;
	}

}
