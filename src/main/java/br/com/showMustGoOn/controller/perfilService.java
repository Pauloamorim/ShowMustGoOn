package br.com.showMustGoOn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.HibernateException;
import org.omg.CORBA.RepositoryIdHelper;

import br.com.showMustGoOn.model.Funcao;
import br.com.showMustGoOn.model.FuncaoMusico;
import br.com.showMustGoOn.model.Musico;
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
	
	public Musico obterMusico(String email){
		return musicosRepository.obterMusicoPorEmail(email,null);
	}

	@Transacional
	public void salvarMusico(Musico musico, List<Funcao> listaFuncoes) throws Exception {
		try{
			validarEmailExistente(musico.getEmail(),musico.getCodMusico());
			musicosRepository.salvar(musico);
			repositoryFuncoesMusico.excluirTodos(repositoryFuncoesMusico.listarFuncoesMusico(musico.getCodMusico()));
			repositoryFuncoesMusico.salvar(criarListaFuncoesMusico(listaFuncoes,musico));
			
		}catch(HibernateException he){
			throw new Exception(he.getMessage());
		}
	}

	private List<FuncaoMusico> criarListaFuncoesMusico(List<Funcao> listaFuncoes, Musico musico) {
		List<FuncaoMusico> lista = new ArrayList<FuncaoMusico>();
		FuncaoMusico fm = null;
		for (Funcao funcao : listaFuncoes) {
			fm = new FuncaoMusico();
			fm.setMusico(musico);
			fm.setFuncao(funcao);
			lista.add(fm);
		}
		return lista;
	}

	private void validarEmailExistente(String email, Integer codMusico) {
		if(musicosRepository.obterMusicoPorEmail(email,codMusico) != null){
			throw new HibernateException("O email informado já esta cadastrado! Informe outro email.");
		}
	}
	
	public List<Funcao> listarFuncoes() {
		return repositoryFuncoes.todas();
	}

	public List<Funcao> carregarFuncoesMusico(Integer codMusico) {
		return repositoryFuncoesMusico.carregarFuncoesMusico(codMusico);
	}

	

}
