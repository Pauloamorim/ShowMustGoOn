package br.com.showMustGoOn.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.showMustGoOn.model.Musico;
import br.com.showMustGoOn.service.ConsultarMusicosService;

@Named
@ViewScoped
public class ConsultarMusicosController implements Serializable {
	
	
	private static final long serialVersionUID = -7013843804976849864L;
	@Inject
	private ConsultarMusicosService servico;
	
	private List<Musico> listaMusicos;

	@PostConstruct
	public void init(){
		listaMusicos = servico.listar();
	}

	public List<Musico> getListaMusicos() {
		return listaMusicos;
	}
	
	

	
}
