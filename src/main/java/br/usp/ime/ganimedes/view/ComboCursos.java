package br.usp.ime.ganimedes.view;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import br.usp.ime.ganimedes.dao.DaoCurso;
import br.usp.ime.ganimedes.mb.MbAnuncio;
import br.usp.ime.ganimedes.model.Curso;
import br.usp.ime.ganimedes.model.ENivelCurso;
import br.usp.ime.ganimedes.model.ERegime;

@ManagedBean(name = "comboCursos")
@RequestScoped
public class ComboCursos {

	@EJB
	DaoCurso daoCurso;

	public List<SelectItem> getCursos() {
		List<SelectItem> itens = new ArrayList<SelectItem>();

		FacesContext fc = FacesContext.getCurrentInstance();
		MbAnuncio mbAnuncio = (MbAnuncio) fc.getApplication().getVariableResolver().resolveVariable(fc, "mbAnuncio");

		try {
			if (!mbAnuncio.getTela().getSelRegimeTrabalho().getValue().equals(ERegime.ESTAGIO)) {

				for (Curso c : daoCurso.buscarCursos()) {
					itens.add(new SelectItem(c, c.getNivel().getValor() + " - " + c.getNome()));

				}
			} else {
				for (Curso c : daoCurso.buscarCursos()) {

					if (c.getNivel().equals(ENivelCurso.GRADUACAO)) {
						itens.add(new SelectItem(c, c.getNivel().getValor() + " - " + c.getNome()));
					}

				}

			}
		}

		catch (Exception e) {
			for (Curso c : daoCurso.buscarCursos()) {

				if (c.getNivel().equals(ENivelCurso.GRADUACAO)) {
					itens.add(new SelectItem(c, c.getNivel().getValor() + " - " + c.getNome()));
				}

			}

		}

		return itens;

	}

}
