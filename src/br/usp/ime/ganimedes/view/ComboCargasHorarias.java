package br.usp.ime.ganimedes.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.usp.ime.ganimedes.mb.MbAnuncio;
import br.usp.ime.ganimedes.model.ERegime;

@ManagedBean(name = "comboCargaHoraria")
@RequestScoped
public class ComboCargasHorarias {

	public List<SelectItem> getCargasHorarias() {
		List<SelectItem> itens = new ArrayList<SelectItem>();

		FacesContext fc = FacesContext.getCurrentInstance();
		MbAnuncio mbAnuncio = (MbAnuncio) fc.getApplication().getVariableResolver().resolveVariable(fc, "mbAnuncio");

		itens.add(new SelectItem("20", "20"));
		itens.add(new SelectItem("25", "25"));
		itens.add(new SelectItem("30", "30"));

		try {
			if (!mbAnuncio.getTela().getSelRegimeTrabalho().getValue().equals(ERegime.ESTAGIO)) {
				itens.add(new SelectItem("40", "40"));
				itens.add(new SelectItem("0", "Outros"));
				
			}
		} catch (NullPointerException e) {
			// nada pra fazer aqui...
		}

		return itens;

	}
}
