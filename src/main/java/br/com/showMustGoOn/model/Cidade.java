package br.com.showMustGoOn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.showMustGoOn.converter.BaseEntity;

@Entity
@Table(name="cidade")
public class Cidade implements Serializable,BaseEntity {

	private static final long serialVersionUID = -8469109513792027072L;
	@Id
	@Column(name="id",insertable=false,updatable=false)
	private Integer id;
	
	@Column(name="nome",insertable=false,updatable=false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="estado")
	private Estado estado;
	
	/////////////////////////////////////////////////
	///////////////GETTERS AND SETTERS///////////////
	/////////////////////////////////////////////////

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return nome;
	}
	
	

}
