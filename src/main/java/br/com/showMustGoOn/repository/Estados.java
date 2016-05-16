package br.com.showMustGoOn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.com.showMustGoOn.model.Estado;
import br.com.showMustGoOn.model.Musico;

public class Estados implements Serializable {

	private static final long serialVersionUID = -5971308754073799207L;
	
	@Inject
	private EntityManager manager;
	
	public Estado porId(Long id) {
		return manager.find(Estado.class, id);
	}
	
	public List<Estado> todas() {
		return manager.createQuery("from Estado", Estado.class).getResultList();
	}

}
