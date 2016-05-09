package br.com.showMustGoOn.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.showMustGoOn.service.ConsultarMusicosService;

@Named
@ViewScoped
public class ConsultarMusicosController implements Serializable {
	
	
	private static final long serialVersionUID = -7013843804976849864L;
	@Inject
	private ConsultarMusicosService servico;
	private String teste;
	
	
	public String getTeste() {
		return teste;
	}


	public void setTeste(String teste) {
		this.teste = teste;
	}


	@PostConstruct
	public void init(){
		servico.listar();
	}

	
}
