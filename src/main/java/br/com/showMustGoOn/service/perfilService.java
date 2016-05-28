package br.com.showMustGoOn.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.HibernateException;

import br.com.showMustGoOn.model.Banda;
import br.com.showMustGoOn.model.Funcao;
import br.com.showMustGoOn.model.FuncaoMusico;
import br.com.showMustGoOn.model.Musico;
import br.com.showMustGoOn.repository.BandaMusicoFuncaoRepository;
import br.com.showMustGoOn.repository.BandaRepository;
import br.com.showMustGoOn.repository.FuncaoMusicoRepository;
import br.com.showMustGoOn.repository.FuncoesRepository;
import br.com.showMustGoOn.repository.Musicos;
import br.com.showMustGoOn.util.Transacional;

public class perfilService implements Serializable {

	private static final long serialVersionUID = -6502991900228819566L;
	@Inject
	private Musicos musicosRepository;
	@Inject
	private FuncoesRepository repositoryFuncoes;
	@Inject
	private FuncaoMusicoRepository repositoryFuncoesMusico;
	@Inject
	private BandaMusicoFuncaoRepository bandaMusicoFuncaoRepository;
	@Inject
	private BandaRepository bandaRepository;

	public Musico obterMusico(String email) {
		return musicosRepository.obterMusicoPorEmail(email, null);
	}

	@Transacional
	public void salvarMusico(Musico musico) throws Exception {
		try {
			validarEmailExistente(musico.getEmail(), musico.getCodMusico());
			musicosRepository.salvar(musico);

		} catch (final HibernateException he) {
			throw new Exception(he.getMessage());
		}
	}

	@Transacional
	public void atualizarQualificacoes(Musico musico, List<Funcao> listaFuncoes) {
		repositoryFuncoesMusico.excluirTodos(repositoryFuncoesMusico.listarFuncoesMusico(musico.getCodMusico()));
		repositoryFuncoesMusico.salvar(criarListaFuncoesMusico(listaFuncoes, musico));
	}

	private List<FuncaoMusico> criarListaFuncoesMusico(List<Funcao> listaFuncoes, Musico musico) {
		final List<FuncaoMusico> lista = new ArrayList<FuncaoMusico>();
		FuncaoMusico fm = null;
		for (final Funcao funcao : listaFuncoes) {
			fm = new FuncaoMusico();
			fm.setMusico(musico);
			fm.setFuncao(funcao);
			lista.add(fm);
		}
		return lista;
	}

	private void validarEmailExistente(String email, Integer codMusico) {
		if (musicosRepository.obterMusicoPorEmail(email, codMusico) != null) {
			throw new HibernateException("O email informado já esta cadastrado! Informe outro email.");
		}
	}

	public List<Funcao> listarFuncoes() {
		return repositoryFuncoes.todas();
	}

	public List<Funcao> carregarFuncoesMusico(Integer codMusico) {
		return repositoryFuncoesMusico.carregarFuncoesMusico(codMusico);
	}

	public List<Banda> listarBandasMusico(Integer codMusico) {
		return bandaMusicoFuncaoRepository.listarBandasMusico(codMusico);
	}

	public byte[] obterImagemBanda(Integer codBanda) {
		return bandaRepository.obterImagemBanda(codBanda);
	}

	@Transacional
	public void removerBanda(Integer codBanda) {
		bandaMusicoFuncaoRepository.removeAll(bandaMusicoFuncaoRepository.listarPorCodBanda(codBanda));
		bandaRepository.remove(bandaRepository.porId(codBanda));
	}

	public Banda obterBanda(Integer codBanda) {
		return bandaRepository.porId(codBanda);
	}

}
