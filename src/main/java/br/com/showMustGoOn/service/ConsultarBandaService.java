package br.com.showMustGoOn.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.showMustGoOn.DTO.BandaDTO;
import br.com.showMustGoOn.model.Banda;
import br.com.showMustGoOn.model.Cidade;
import br.com.showMustGoOn.model.Estado;
import br.com.showMustGoOn.model.Musico;
import br.com.showMustGoOn.repository.BandaMusicoFuncaoRepository;
import br.com.showMustGoOn.repository.BandaRepository;
import br.com.showMustGoOn.repository.CidadesRepository;
import br.com.showMustGoOn.repository.Estados;
import br.com.showMustGoOn.repository.Musicos;

public class ConsultarBandaService implements Serializable {

	private static final long serialVersionUID = -9202039823140922991L;
	@Inject
	private Musicos musicosRepository;
	@Inject
	private Estados estadosRepository;
	@Inject
	private CidadesRepository cidadeRepository;
	@Inject
	private BandaRepository bandaRepository;
	@Inject
	private BandaMusicoFuncaoRepository bandaMusicoFuncaoRepository;

	public List<Estado> listarEstados() {
		return estadosRepository.todas();
	}

	public List<Cidade> listarCidadesPorEstado(Integer codEstado, String cidade) {
		return cidadeRepository.listarCidadesPorEstado(codEstado, cidade);
	}

	public List<Musico> listarMusicosPorNome(String nome) {
		return musicosRepository.listarMusicosPorNome(nome);
	}

	public Banda obterBanda(Integer valueOf) {
		return bandaRepository.porId(valueOf);
	}

	public List<Banda> pesquisar(BandaDTO dto) {
		return bandaMusicoFuncaoRepository.pesquisarBandas(dto);
	}

}
