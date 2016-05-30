package br.com.showMustGoOn.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.showMustGoOn.DTO.MusicoDTO;
import br.com.showMustGoOn.model.Cidade;
import br.com.showMustGoOn.model.Estado;
import br.com.showMustGoOn.model.Funcao;
import br.com.showMustGoOn.model.Musico;
import br.com.showMustGoOn.repository.CidadesRepository;
import br.com.showMustGoOn.repository.Estados;
import br.com.showMustGoOn.repository.FuncaoMusicoRepository;
import br.com.showMustGoOn.repository.FuncoesRepository;
import br.com.showMustGoOn.repository.Musicos;

public class ConsultarMusicosService implements Serializable {

	private static final long serialVersionUID = -258261831807595909L;

	@Inject
	private Musicos repositoryMusicos;
	@Inject
	private Estados repositoryEstados;
	@Inject
	private CidadesRepository repositoryCidades;
	@Inject
	private FuncoesRepository repositoryFuncoes;
	@Inject
	private FuncaoMusicoRepository repositoryFuncaoMusicoRepository;

	public List<Musico> listarMusicos(MusicoDTO musicoDTO) {
		return repositoryFuncaoMusicoRepository.listarMusicos(musicoDTO);
	}

	public List<Estado> listarEstados() {
		return repositoryEstados.todas();
	}

	public List<Cidade> listarCidadesPorEstado(Integer codEstado, String cidade) {
		return repositoryCidades.listarCidadesPorEstado(codEstado, cidade);
	}

	public List<Funcao> listarFuncoes() {
		return repositoryFuncoes.todas();
	}

	public Musico obterMusico(Integer codMusico) {
		return repositoryMusicos.porId(codMusico);
	}

	public List<Funcao> listarFuncoesPorMusico(Integer codMusico) {
		return repositoryFuncaoMusicoRepository.carregarFuncoesMusico(codMusico);
	}

}
