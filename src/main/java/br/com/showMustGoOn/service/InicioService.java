package br.com.showMustGoOn.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.HibernateException;

import br.com.showMustGoOn.model.Cidade;
import br.com.showMustGoOn.model.Estado;
import br.com.showMustGoOn.model.Musico;
import br.com.showMustGoOn.repository.CidadesRepository;
import br.com.showMustGoOn.repository.Estados;
import br.com.showMustGoOn.repository.Musicos;
import br.com.showMustGoOn.util.Transacional;

public class InicioService implements Serializable {

	private static final long serialVersionUID = 7041540501129452386L;
	@Inject 
	private Musicos musicosRepository;
	@Inject
	private Estados estadosRepository;
	@Inject
	private CidadesRepository cidadeRepository;
	
	public Boolean verificarLogin(String email, String senha){
		return musicosRepository.verificarLogin(email,senha);
	}

	public List<Estado> listarEstados() {
		return estadosRepository.todas();
	}

	public List<Cidade> listarCidadesPorEstado(Integer codEstado, String cidade) {
		return cidadeRepository.listarCidadesPorEstado(codEstado, cidade);
	}

	@Transacional
	public void salvarMusico(Musico musico) throws Exception {
		try{
			validarEmailExistente(musico.getEmail());
			musicosRepository.salvar(musico);
		}catch(HibernateException he){
			throw new Exception(he.getMessage());
		}
	}

	private void validarEmailExistente(String email) {
		if(musicosRepository.obterMusicoPorEmail(email) != null){
			throw new HibernateException("O email informado já esta cadastrado! Informe outro email.");
		}
	}

}
