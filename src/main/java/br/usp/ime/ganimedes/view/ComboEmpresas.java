package br.usp.ime.ganimedes.view;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.usp.ime.ganimedes.dao.DaoEmpresa;
import br.usp.ime.ganimedes.model.Empresa;

@ManagedBean(name = "comboEmpresas")
@SessionScoped
public class ComboEmpresas {


	@EJB
	DaoEmpresa daoEmpresa;



	public List<SelectItem> getEmpresas() {
		List<SelectItem> itens = new ArrayList<SelectItem>();

		for (Empresa e : daoEmpresa.buscarEmpresas()) {
			itens.add(new SelectItem(e, e.getNome() + " " + e.getCnpj()));
		}

		return itens;

	}

}
