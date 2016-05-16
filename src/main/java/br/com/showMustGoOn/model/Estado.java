package br.com.showMustGoOn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.showMustGoOn.converter.BaseEntity;

@Table(name="estado")
@Entity
public class Estado implements Serializable,BaseEntity {

	private static final long serialVersionUID = 5602510365652825034L;
	@Id
	@Column(name="id",insertable=false,updatable=false)
	private Integer codEstado;
	
	@Column(name="nome",insertable=false,updatable=false)
	private String nome;
	
	@Column(name="uf",insertable=false,updatable=false)
	private String uf;
	

	@Override
	public Integer getId() {
		return getCodEstado();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codEstado == null) ? 0 : codEstado.hashCode());
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
		Estado other = (Estado) obj;
		if (codEstado == null) {
			if (other.codEstado != null)
				return false;
		} else if (!codEstado.equals(other.codEstado))
			return false;
		return true;
	}

	
	
	/////////////////////////////////////////////////
	///////////////GETTERS AND SETTERS///////////////
	/////////////////////////////////////////////////
	
	public Integer getCodEstado() {
		return codEstado;
	}
	public void setCodEstado(Integer codEstado) {
		this.codEstado = codEstado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
