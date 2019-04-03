package br.usp.ime.ganimedes.view;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import br.usp.ime.ganimedes.model.ETipoDoc;

@ManagedBean(name = "comboTiposDocumento")
@RequestScoped
public class ComboTiposDocumento {

	public List<SelectItem> getTiposDocumento() {
		List<SelectItem> itens = new ArrayList<SelectItem>();
		itens.add(new SelectItem("",""));
		itens.add(new SelectItem(ETipoDoc.ADI, ETipoDoc.ADI.getValor()));
		itens.add(new SelectItem(ETipoDoc.COM, ETipoDoc.COM.getValor()));
		itens.add(new SelectItem(ETipoDoc.REA, ETipoDoc.REA.getValor()));
		itens.add(new SelectItem(ETipoDoc.RES, ETipoDoc.RES.getValor()));
		itens.add(new SelectItem(ETipoDoc.ATV, ETipoDoc.ATV.getValor()));
		return itens;

	}

	
	public List<SelectItem> getTiposDocumentoAditivos() {
		List<SelectItem> itens = new ArrayList<SelectItem>();
		itens.add(new SelectItem("",""));
		itens.add(new SelectItem(ETipoDoc.ADI, ETipoDoc.ADI.getValor()));
		itens.add(new SelectItem(ETipoDoc.REA, ETipoDoc.REA.getValor()));
		itens.add(new SelectItem(ETipoDoc.RES, ETipoDoc.RES.getValor()));
		itens.add(new SelectItem(ETipoDoc.ATV, ETipoDoc.ATV.getValor()));
		return itens;

	}
	
	
	public List<SelectItem> getTermoCompromisso() {
		List<SelectItem> itens = new ArrayList<SelectItem>();
		itens.add(new SelectItem(ETipoDoc.COM, ETipoDoc.COM.getValor()));
		return itens;
	}
	
	
	
}
