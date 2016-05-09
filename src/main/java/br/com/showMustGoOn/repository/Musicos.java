package br.com.showMustGoOn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.showMustGoOn.model.Musico;

public class Musicos implements Serializable {

	private static final long serialVersionUID = -5971308754073799207L;
	
	@Inject
	private EntityManager manager;
	
	public Musico porId(Long id) {
		return manager.find(Musico.class, id);
	}
	
	public List<Musico> todas() {
		return manager.createQuery("from Musico", Musico.class).getResultList();
	}

}
