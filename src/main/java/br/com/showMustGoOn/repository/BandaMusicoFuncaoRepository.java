package br.com.showMustGoOn.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.showMustGoOn.model.BandaMusicoFuncao;

public class BandaMusicoFuncaoRepository implements Serializable {

	private static final long serialVersionUID = -5971308754073799207L;

	@Inject
	private EntityManager manager;

	public BandaMusicoFuncao porId(Long id) {
		return manager.find(BandaMusicoFuncao.class, id);
	}


	public void salvar(BandaMusicoFuncao bandaMusicoFuncao) {
		manager.merge(bandaMusicoFuncao);
	}


	public void salvar(List<BandaMusicoFuncao> listBMF) {
		for (final BandaMusicoFuncao bandaMusicoFuncao : listBMF) {
			salvar(bandaMusicoFuncao);
		}

	}


}
