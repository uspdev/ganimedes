package br.usp.ime.ganimedes.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.usp.ime.ganimedes.ejb.DaoReplicadoInterface;
import br.usp.ime.ganimedes.model.Contato;
import br.usp.ime.ganimedes.model.Convenio;
import br.usp.ime.ganimedes.model.Empresa;

@Stateless
public class DaoEmpresa extends Dao<Empresa> {

	@EJB
	DaoReplicadoInterface daoReplicado;

	@EJB
	DaoEstagio daoEstagio;

	public DaoEmpresa() {
		super.setPersistentClass(Empresa.class);
	}

	public List<Empresa> buscarEmpresas() {
		List<Empresa> empresas = new ArrayList<Empresa>();

		String q = "SELECT E FROM Empresa E ORDER BY E.nome ASC";
		Query query = em.createQuery(q);

		empresas = query.getResultList();

		return empresas;

	}

	public List<Empresa> buscarEmpresaPorNome(String criterio) {
		List<Empresa> empresas = new ArrayList<Empresa>();

		String q = "SELECT E FROM Empresa E WHERE E.nome like :criterio";
		Query query = em.createQuery(q);
		query.setParameter("criterio", "%" + criterio + "%");
		empresas = query.getResultList();

		return empresas;
	}

	public List<Empresa> buscarEmpresaPorCnpj(String criterio) {
		List<Empresa> empresas = new ArrayList<Empresa>();

		String q = "SELECT E FROM Empresa E WHERE E.cnpj =:criterio";
		Query query = em.createQuery(q);
		query.setParameter("criterio", criterio);
		empresas = query.getResultList();

		for (Empresa empresa : empresas) {
			empresa.setConvenios(this.buscarConvenios(empresa));
		}

		for (Empresa empresa : empresas) {
			empresa.setContatos(this.buscarContatos(empresa));
		}
		
		return empresas;

	}

	public List<Empresa> buscarEmpresasConveniadas(Date dta) {
		List<Convenio> convenios = new ArrayList<Convenio>();

		String q = " SELECT C FROM Convenio C WHERE :dta BETWEEN C.dtaini AND C.dtafim";
		Query query = em.createQuery(q);
		query.setParameter("dta", dta);

		convenios = query.getResultList();

		List<Empresa> empresas = new ArrayList<Empresa>();

		for (Convenio convenio : convenios) {
			empresas.add(convenio.getEmpresa());
		}

		for (Empresa empresa : empresas) {
			empresa.setConvenios(this.buscarConvenios(empresa));
		}

		return empresas;

	}

	public List<Empresa> buscarEmpresasConveniadasProximoEncerramento() {
		List<Convenio> convenios = new ArrayList<Convenio>();

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.add(Calendar.MONTH, 2);

		Date df = cal.getTime();
		Date hoje = new Date();

		String q = " SELECT C FROM Convenio C WHERE  C.dtafim BETWEEN :hoje AND :d";
		Query query = em.createQuery(q);
		query.setParameter("d", df);
		query.setParameter("hoje", hoje);

		convenios = query.getResultList();

		List<Empresa> empresas = new ArrayList<Empresa>();

		for (Convenio convenio : convenios) {
			empresas.add(convenio.getEmpresa());
		}

		for (Empresa empresa : empresas) {
			empresa.setConvenios(this.buscarConvenios(empresa));
		}

		return empresas;

	}

	public List<Contato> buscarContatos(Empresa empresa) {
		List<Contato> contatos = new ArrayList<Contato>();
		String q = "SELECT C FROM Contato C WHERE C.empresa = :empresa";
		Query query = em.createQuery(q);
		query.setParameter("empresa", empresa);
		contatos = query.getResultList();

		return contatos;

	}

	public List<Convenio> buscarConvenios(Empresa empresa) {
		List<Convenio> convenios = new ArrayList<Convenio>();
		String q = "SELECT C FROM Convenio C WHERE C.empresa = :empresa";
		Query query = em.createQuery(q);
		query.setParameter("empresa", empresa);
		convenios = query.getResultList();

		return convenios;

	}

}
