package br.usp.ime.ganimedes.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public abstract class Dao<T> {

	private Class<T> persistentClass;

	@PersistenceContext(unitName = "ganimedes")
	protected EntityManager em;

	public boolean delete(T o) {

		try {
			em.remove(em.merge(o));
			em.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return em.createQuery("SELECT T FROM " + persistentClass.getSimpleName() + " T").getResultList();
	}

	public T findById(Integer id) {
		T o = em.find(this.persistentClass, id);
		return o;
	}

	public Object persist(T o) {

		try {
			o = em.merge(o);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return o;
	}

	public Object update(T o) {

		try {
			o = em.merge(o);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return o;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

}