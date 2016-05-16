package br.com.showMustGoOn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.showMustGoOn.model.Estado;
import br.com.showMustGoOn.model.Funcao;

public class FuncoesRepository implements Serializable {

	private static final long serialVersionUID = -6907158422573489350L;
	@Inject
	private EntityManager manager;
	
	public Estado porId(Long id) {
		return manager.find(Estado.class, id);
	}
	
	public List<Funcao> todas() {
		return manager.createQuery("from Funcao", Funcao.class).getResultList();
	}
	
}
