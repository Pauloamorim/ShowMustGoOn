package br.com.showMustGoOn.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.showMustGoOn.DTO.MusicoFuncaoDTO;
import br.com.showMustGoOn.model.Banda;
import br.com.showMustGoOn.model.BandaMusicoFuncao;
import br.com.showMustGoOn.model.Cidade;
import br.com.showMustGoOn.model.Estado;
import br.com.showMustGoOn.model.Funcao;
import br.com.showMustGoOn.model.Musico;
import br.com.showMustGoOn.repository.BandaMusicoFuncaoRepository;
import br.com.showMustGoOn.repository.BandaRepository;
import br.com.showMustGoOn.repository.CidadesRepository;
import br.com.showMustGoOn.repository.Estados;
import br.com.showMustGoOn.repository.FuncoesRepository;
import br.com.showMustGoOn.repository.Musicos;
import br.com.showMustGoOn.util.Transacional;

public class CadastroBandaService implements Serializable {

	private static final long serialVersionUID = 7041540501129452386L;
	@Inject
	private Musicos musicosRepository;
	@Inject
	private Estados estadosRepository;
	@Inject
	private CidadesRepository cidadeRepository;
	@Inject
	private FuncoesRepository funcoesRepository;
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

	public List<Funcao> listarFuncoes() {
		return funcoesRepository.todas();
	}

	@Transacional
	public void salvarBanda(Banda banda, List<MusicoFuncaoDTO> musicosFuncoes) throws Exception {
		validarNomeDuplicado(banda.getNome());
		bandaRepository.salvar(banda);

		final List<BandaMusicoFuncao> listBMF = new ArrayList<BandaMusicoFuncao>();
		//cria registros bandaMusicoFuncao
		BandaMusicoFuncao bmf;
		for (final MusicoFuncaoDTO musicoFuncaoDTO : musicosFuncoes) {
			for (final Funcao func : musicoFuncaoDTO.getListaFuncoes()) {
				bmf = new BandaMusicoFuncao();
				bmf.setBanda(banda);
				bmf.setMusico(musicoFuncaoDTO.getMusico());
				bmf.setFuncao(func);
				listBMF.add(bmf);
			}
		}
		bandaMusicoFuncaoRepository.salvar(listBMF);
	}

	private void validarNomeDuplicado(String nome) throws Exception {
		if(bandaRepository.obterBandaPorNome(nome) != null){
			throw new Exception("Já existe uma banda com este nome.");
		}
	}


}
