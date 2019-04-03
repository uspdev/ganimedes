package br.usp.ime.ganimedes.view;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import br.usp.ime.ganimedes.model.ERegime;

@ManagedBean(name = "comboRegimesTrabalho")
@RequestScoped
public class ComboRegimesTrabalho {

	public List<SelectItem> getRegimes() {
		List<SelectItem> itens = new ArrayList<SelectItem>();

		itens.add(new SelectItem(ERegime.ESTAGIO, ERegime.ESTAGIO.getValor()));
		itens.add(new SelectItem(ERegime.TRAINEE, ERegime.TRAINEE.getValor()));
		itens.add(new SelectItem(ERegime.EFETIVO, ERegime.EFETIVO.getValor()));
		itens.add(new SelectItem(ERegime.FREELA, ERegime.FREELA.getValor()));

		return itens;

	}

}
