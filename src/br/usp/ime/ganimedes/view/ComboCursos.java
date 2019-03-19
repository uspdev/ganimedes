package br.usp.ime.ganimedes.view;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.usp.ime.ganimedes.ejb.GanimedesInterface;
import br.usp.ime.ganimedes.mb.MbAnuncio;
import br.usp.ime.ganimedes.model.Curso;
import br.usp.ime.ganimedes.model.ENivelCurso;
import br.usp.ime.ganimedes.model.ERegime;

@ManagedBean(name = "comboCursos")
@RequestScoped
public class ComboCursos {

	@EJB
	GanimedesInterface ejb;

	public List<SelectItem> getCursos() {
		List<SelectItem> itens = new ArrayList<SelectItem>();

		FacesContext fc = FacesContext.getCurrentInstance();
		MbAnuncio mbAnuncio = (MbAnuncio) fc.getApplication().getVariableResolver().resolveVariable(fc, "mbAnuncio");

		try {
			if (!mbAnuncio.getTela().getSelRegimeTrabalho().getValue().equals(ERegime.ESTAGIO)) {

				for (Curso c : ejb.buscarCursos()) {
					itens.add(new SelectItem(c, c.getNivel().getValor() + " - " + c.getNome()));

				}
			} else {
				for (Curso c : ejb.buscarCursos()) {

					if (c.getNivel().equals(ENivelCurso.GRADUACAO)) {
						itens.add(new SelectItem(c, c.getNivel().getValor() + " - " + c.getNome()));
					}

				}

			}
		}

		catch (Exception e) {
			for (Curso c : ejb.buscarCursos()) {

				if (c.getNivel().equals(ENivelCurso.GRADUACAO)) {
					itens.add(new SelectItem(c, c.getNivel().getValor() + " - " + c.getNome()));
				}

			}

		}

		return itens;

	}

/*
	public List<SelectItem> getAll() {
		List<SelectItem> itens = new ArrayList<SelectItem>();

		FacesContext fc = FacesContext.getCurrentInstance();
		MbAnuncio mbAnuncio = (MbAnuncio) fc.getApplication().getVariableResolver().resolveVariable(fc, "mbAnuncio");

		for (Curso c : ejb.buscarCursos()) {
			itens.add(new SelectItem(c, c.getNivel().getValor() + " - " + c.getNome()));

		}

		return itens;

	}
	*/

}
