package br.com.showMustGoOn.DTO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import br.com.showMustGoOn.model.Funcao;
import br.com.showMustGoOn.model.Musico;

public class MusicoFuncaoDTO implements Serializable {

	private static final long serialVersionUID = -3722369536711018086L;
	private Musico musico;
	private List<Funcao> listaFuncoes;
	
	@Transient
	public String getFuncoesFormatadas(){
		String retorno = StringUtils.EMPTY;
		if(getListaFuncoes() == null || getListaFuncoes().isEmpty()){
			return retorno;
		}
		for (Funcao funcao : getListaFuncoes()) {
			retorno = retorno.concat(funcao.getDescricaoFuncao().concat(", "));
		}
		retorno = StringUtils.removeEnd(retorno, ", ");
		return retorno;
	}
	public MusicoFuncaoDTO(Musico musico,List<Funcao> listaFuncoes) {
		this.musico = musico;
		this.listaFuncoes = listaFuncoes;
	}
	
	/////////////////////////////////////////////////
	///////////////GETTERS AND SETTERS///////////////
	/////////////////////////////////////////////////
	
	public Musico getMusico() {
		return musico;
	}
	public void setMusico(Musico musico) {
		this.musico = musico;
	}
	public List<Funcao> getListaFuncoes() {
		return listaFuncoes;
	}
	public void setListaFuncoes(List<Funcao> listaFuncoes) {
		this.listaFuncoes = listaFuncoes;
	}
	
	

}
