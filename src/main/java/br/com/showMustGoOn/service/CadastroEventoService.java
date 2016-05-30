package br.com.showMustGoOn.service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

import br.com.showMustGoOn.model.Cidade;
import br.com.showMustGoOn.model.Estado;
import br.com.showMustGoOn.model.Evento;
import br.com.showMustGoOn.model.Musico;
import br.com.showMustGoOn.repository.BandaRepository;
import br.com.showMustGoOn.repository.CidadesRepository;
import br.com.showMustGoOn.repository.Estados;
import br.com.showMustGoOn.repository.EventoRepository;
import br.com.showMustGoOn.repository.Musicos;
import br.com.showMustGoOn.util.Transacional;

public class CadastroEventoService implements Serializable {

	private static final long serialVersionUID = 7041540501129452386L;
	@Inject
	private Musicos musicosRepository;
	@Inject
	private Estados estadosRepository;
	@Inject
	private CidadesRepository cidadeRepository;
	@Inject
	private BandaRepository bandaRepository;
	@Inject
	private EventoRepository eventoRepository;

	public List<Estado> listarEstados() {
		return estadosRepository.todas();
	}

	public List<Cidade> listarCidadesPorEstado(Integer codEstado, String cidade) {
		return cidadeRepository.listarCidadesPorEstado(codEstado, cidade);
	}

	public Musico obterMusicoPorEmail(String email) {
		return musicosRepository.obterMusicoPorEmail(email, null);
	}

	@Transacional
	public void salvarEvento(Evento evento) throws Exception {
		if (evento.getData().before(new Date())) {
			throw new Exception("A data do evento não pode ser inferior a data atual.");
		}
		if (evento.getId() != null) {
			eventoRepository.alterar(evento);
		} else {
			eventoRepository.salvar(evento);
		}

	}

	public String colocarFormatoHora(String hora) throws ParseException {
		final SimpleDateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		df.setTimeZone(TimeZone.getTimeZone("BRT"));
		final Date date = df.parse(hora);
		final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(date);
	}

	public Evento obterEvento(Integer codEvento) {
		return eventoRepository.porId(codEvento);
	}

}
