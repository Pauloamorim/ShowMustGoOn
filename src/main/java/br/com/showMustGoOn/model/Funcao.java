package br.com.showMustGoOn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.showMustGoOn.converter.BaseEntity;

@Entity
@Table(name="funcao")
public class Funcao implements Serializable,BaseEntity {

	private static final long serialVersionUID = -4100044195470268370L;
	
	@Id
	@Column(name="cod_funcao",insertable=false,updatable=false)
	private Integer codFuncao;
	
	@Column(name="descricao_funcao",insertable=false,updatable=false)
	private String descricaoFuncao;

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codFuncao == null) ? 0 : codFuncao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcao other = (Funcao) obj;
		if (codFuncao == null) {
			if (other.codFuncao != null)
				return false;
		} else if (!codFuncao.equals(other.codFuncao))
			return false;
		return true;
	}
	
	@Override
	public Integer getId() {
		return codFuncao;
	}

	public Integer getCodFuncao() {
		return codFuncao;
	}

	public void setCodFuncao(Integer codFuncao) {
		this.codFuncao = codFuncao;
	}

	public String getDescricaoFuncao() {
		return descricaoFuncao;
	}

	public void setDescricaoFuncao(String descricaoFuncao) {
		this.descricaoFuncao = descricaoFuncao;
	}
	
	
	

	

}
