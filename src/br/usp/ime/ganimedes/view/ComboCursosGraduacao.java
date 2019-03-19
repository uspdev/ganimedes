package br.usp.ime.ganimedes.view;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import br.usp.ime.ganimedes.ejb.DaoReplicadoInterface;
import br.usp.ime.ganimedes.model.CursoGr;

@ManagedBean(name = "comboCursosGraduacao")
@RequestScoped
public class ComboCursosGraduacao {

	@EJB
	DaoReplicadoInterface ejb;

	public List<SelectItem> getCursos() {
		List<SelectItem> itens = new ArrayList<SelectItem>();

		for (CursoGr c : ejb.buscarCursosGraduacao()) {

			itens.add(new SelectItem(c, c.getCodcur() + " " + c.getCodhab() + " " + c.getNomcurso()));

		}

		return itens;

	}

}
