package br.com.showMustGoOn.DTO;

import java.io.Serializable;
import java.util.List;

import br.com.showMustGoOn.enums.SexoEnum;
import br.com.showMustGoOn.model.Cidade;
import br.com.showMustGoOn.model.Estado;
import br.com.showMustGoOn.model.Funcao;

public class MusicoDTO implements Serializable {

	private static final long serialVersionUID = 3640979799126713503L;
	private String nome;
	private Cidade cidade;
	private Estado estado;
	private SexoEnum sexo;
	private List<Funcao> listaFuncoes;
	
	/////////////////////////////////////////////////
	///////////////GETTERS AND SETTERS///////////////
	/////////////////////////////////////////////////
	
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade codCidade) {
		this.cidade = codCidade;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado codEstado) {
		this.estado = codEstado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public SexoEnum getSexo() {
		return sexo;
	}
	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}
	public List<Funcao> getListaFuncoes() {
		return listaFuncoes;
	}
	public void setListaFuncoes(List<Funcao> listaFuncoes) {
		this.listaFuncoes = listaFuncoes;
	}
	
	

}
