package br.usp.ime.ganimedes.view;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import br.usp.ime.ganimedes.model.EStatusDoc;

@ManagedBean(name = "comboStatusDoc")
@RequestScoped
public class ComboStatusDoc {

	public List<SelectItem> getStatusDoc() {
		List<SelectItem> itens = new ArrayList<SelectItem>();
		itens.add(new SelectItem("", ""));
		itens.add(new SelectItem(EStatusDoc.SEM, EStatusDoc.SEM.getValor()));
		itens.add(new SelectItem(EStatusDoc.DEFERIDO, EStatusDoc.DEFERIDO.getValor()));
		itens.add(new SelectItem(EStatusDoc.INDEFERIDO, EStatusDoc.INDEFERIDO.getValor()));

		return itens;

	}

}
