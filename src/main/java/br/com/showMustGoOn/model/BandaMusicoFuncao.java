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

import br.com.showMustGoOn.converter.BaseEntity;

@Entity
@Table(name="banda_musico_funcao")
public class BandaMusicoFuncao implements Serializable, BaseEntity {

	private static final long serialVersionUID = 8539201667340069520L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_banda_musico_funcao")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="cod_musico",nullable=false)
	private Musico musico;
	
	@ManyToOne
	@JoinColumn(name="cod_banda",nullable=false)
	private Banda banda;
	
	@ManyToOne
	@JoinColumn(name="cod_funcao",nullable=false)
	private Funcao funcao;
	

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Banda getBanda() {
		return banda;
	}

	public void setBanda(Banda banda) {
		this.banda = banda;
	}

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

}
