package br.com.showMustGoOn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.showMustGoOn.DTO.EventosDTO;
import br.com.showMustGoOn.model.Evento;
import br.com.showMustGoOn.service.ConsultarEventosService;

@Named
@ViewScoped
public class ConsultarEventosController extends BaseController implements Serializable {

	private static final long serialVersionUID = -7013843804976849864L;
	private EventosDTO dto;
	private List<Evento> listaEventos = new ArrayList<Evento>();
	private Evento eventoExibir;

	@Inject
	ConsultarEventosService consultarEventosService;

	@PostConstruct
	public void init() {
		setDto(new EventosDTO());
	}

	public void pesquisar() {
		setListaEventos(consultarEventosService.pesquisar(getDto()));
		if (getListaEventos().isEmpty()) {
			adicionarMensagem("Nenhum registro encontrado", FacesMessage.SEVERITY_WARN);
		}
	}

	public void limparGridResultados() {
		setListaEventos(new ArrayList<Evento>());
	}

	public void limparFiltros() {
		setDto(new EventosDTO());
	}

	public void setarCodEventoExibicao() {
		final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		final Integer codEvento = Integer.valueOf(externalContext.getRequestParameterMap().get("codEvento"));
		setEventoExibir(consultarEventosService.obterEvento(codEvento));
	}

	/////////////////////////////////////////////////
	/////////////// GETTERS AND SETTERS///////////////
	/////////////////////////////////////////////////
	public EventosDTO getDto() {
		return dto;
	}

	public void setDto(EventosDTO dto) {
		this.dto = dto;
	}

	public List<Evento> getListaEventos() {
		return listaEventos;
	}

	public void setListaEventos(List<Evento> listaEventos) {
		this.listaEventos = listaEventos;
	}

	public Evento getEventoExibir() {
		return eventoExibir;
	}

	public void setEventoExibir(Evento eventoExibir) {
		this.eventoExibir = eventoExibir;
	}

}
