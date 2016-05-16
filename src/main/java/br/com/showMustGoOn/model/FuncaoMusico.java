package br.com.showMustGoOn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="funcao_musico")
public class FuncaoMusico implements Serializable{

	private static final long serialVersionUID = 1497581004128295560L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_funcao_musico")
	private Integer codFuncaoMusico;
	
	@ManyToOne
	@JoinColumn(name="cod_funcao")
	private Funcao funcao;
	
	@ManyToOne
	@JoinColumn(name="cod_musico")
	private Musico musico;
	
	
	/////////////////////////////////////////////////
	///////////////GETTERS AND SETTERS///////////////
	/////////////////////////////////////////////////

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public Musico getMusico() {
		return musico;
	}

	public void setMusico(Musico musico) {
		this.musico = musico;
	}

	public Integer getCodFuncaoMusico() {
		return codFuncaoMusico;
	}

	public void setCodFuncaoMusico(Integer codFuncaoMusico) {
		this.codFuncaoMusico = codFuncaoMusico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codFuncaoMusico == null) ? 0 : codFuncaoMusico.hashCode());
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
		FuncaoMusico other = (FuncaoMusico) obj;
		if (codFuncaoMusico == null) {
			if (other.codFuncaoMusico != null)
				return false;
		} else if (!codFuncaoMusico.equals(other.codFuncaoMusico))
			return false;
		return true;
	}
	

	
}
