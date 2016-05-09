package br.com.showMustGoOn.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.showMustGoOn.model.Musico;
import br.com.showMustGoOn.repository.Musicos;

public class ConsultarMusicosService implements Serializable {

	private static final long serialVersionUID = -258261831807595909L;
	@Inject
	private Musicos repositoryMusicos;
	
	
	public List<Musico> listar(){
		return repositoryMusicos.todas();
	}
	
}
