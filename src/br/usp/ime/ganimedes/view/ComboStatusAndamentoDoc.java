package br.usp.ime.ganimedes.view;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import br.usp.ime.ganimedes.model.EStatusAndamentoDoc;

@ManagedBean(name = "comboStatusAndamentoDoc")
@RequestScoped
public class ComboStatusAndamentoDoc {

	public List<SelectItem> getStatusAndamentoDoc() {
		List<SelectItem> itens = new ArrayList<SelectItem>();
		itens.add(new SelectItem("", ""));
		itens.add(new SelectItem(EStatusAndamentoDoc.AAC, EStatusAndamentoDoc.AAC.getValor()));
		itens.add(new SelectItem(EStatusAndamentoDoc.ACG, EStatusAndamentoDoc.ACG.getValor()));
		itens.add(new SelectItem(EStatusAndamentoDoc.AAE, EStatusAndamentoDoc.AAE.getValor()));
		itens.add(new SelectItem(EStatusAndamentoDoc.ARA, EStatusAndamentoDoc.ARA.getValor()));
		itens.add(new SelectItem(EStatusAndamentoDoc.DRA, EStatusAndamentoDoc.DRA.getValor()));
		

		return itens;

	}

}
