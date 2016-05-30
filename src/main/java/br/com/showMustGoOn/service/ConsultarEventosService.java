package br.com.showMustGoOn.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.showMustGoOn.DTO.EventosDTO;
import br.com.showMustGoOn.model.Evento;
import br.com.showMustGoOn.repository.EventoRepository;

public class ConsultarEventosService implements Serializable {

	private static final long serialVersionUID = 4856696488191044237L;
	@Inject
	EventoRepository eventoRepository;

	public List<Evento> pesquisar(EventosDTO eventosDTO) {
		return eventoRepository.pesquisar(eventosDTO);
	}

	public Evento obterEvento(Integer codEvento) {
		return eventoRepository.porId(codEvento);
	}

}
