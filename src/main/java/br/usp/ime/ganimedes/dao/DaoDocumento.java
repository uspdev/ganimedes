package br.usp.ime.ganimedes.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.usp.ime.ganimedes.model.Documento;
import br.usp.ime.ganimedes.model.Estagio;

@Stateless
public class DaoDocumento extends Dao<Documento> {
	
	@EJB
	DaoEstagio daoEstagio;

	public DaoDocumento() {
		super.setPersistentClass(Documento.class);
	}

	public List<Documento> buscarDocumentosAtivosPeriodo(Date dtaini, Date dtafim) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		List<Documento> documentos = new ArrayList<Documento>();

		if (dtaini.equals(dtafim)) {
			String q = "SELECT D FROM Documento D WHERE :data BETWEEN D.dtaini AND D.dtafim";
			Query query = em.createQuery(q);
			query.setParameter("data", dtaini);
			documentos = query.getResultList();

		} else {
			String q = "SELECT D FROM Documento D WHERE D.dtaini >= :dtaini AND D.dtafim <= :dtafim";
			Query query = em.createQuery(q);
			query.setParameter("dtaini", dtaini);
			query.setParameter("dtafim", dtafim);
			documentos = query.getResultList();
		}

		for (Documento documento : documentos) {
			Estagio e = daoEstagio.buscarEstagio(documento.getEstagio().getId());
			documento.setEstagio(e);

		}

		return documentos;

	}
}
