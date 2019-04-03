package br.usp.ime.ganimedes.view;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.usp.ime.ganimedes.ejb.DaoGanimedes;
import br.usp.ime.ganimedes.model.Aluno;

@ManagedBean(name = "comboAlunos")
@SessionScoped
public class ComboAlunos {

	@EJB
	DaoGanimedes daoGanimedes;

	public List<SelectItem> getAlunos() {
		List<SelectItem> itens = new ArrayList<SelectItem>();

		for (Aluno a : daoGanimedes.buscarAlunos()) {
			itens.add(new SelectItem(a, a.getNompes()));
		}

		return itens;

	}

}
