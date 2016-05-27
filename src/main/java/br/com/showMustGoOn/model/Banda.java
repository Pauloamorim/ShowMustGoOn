package br.com.showMustGoOn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.showMustGoOn.converter.BaseEntity;

@Entity
@Table(name="banda")
public class Banda implements Serializable,BaseEntity {

	private static final long serialVersionUID = -8469109513792027072L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_banda")
	private Integer codBanda;
	
	@Column(name="nome",nullable=false)
	private String nome;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_fundacao",nullable=false)
	private Date dataFundacao;
	
	@Column(name="facebook")
	private String facebook;
	
	@Column(name="twitter")
	private String twitter;
	
	@Column(name="email_contato")
	private String emailContato;
	
	@Column(name="tel_contato")
	private String telefoneContato;
	
	@Column(name="imagem")
	private byte[] imagem;
	
	@Column(name="descricao_banda")
	private String descricaoBanda;
	
	@ManyToOne
	@JoinColumn(name="estado", nullable=false)
	private Estado estado;
	
	@ManyToOne
	@JoinColumn(name="cidade",nullable=false)
	private Cidade cidade;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codBanda == null) ? 0 : codBanda.hashCode());
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
		Banda other = (Banda) obj;
		if (codBanda == null) {
			if (other.codBanda != null)
				return false;
		} else if (!codBanda.equals(other.codBanda))
			return false;
		return true;
	}
	
	/////////////////////////////////////////////////
	///////////////GETTERS AND SETTERS///////////////
	/////////////////////////////////////////////////

	@Override
	public Integer getId() {
		return codBanda;
	}

	public Integer getCodBanda() {
		return codBanda;
	}

	public void setCodBanda(Integer codBanda) {
		this.codBanda = codBanda;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getEmailContato() {
		return emailContato;
	}

	public void setEmailContato(String emailContato) {
		this.emailContato = emailContato;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getDescricaoBanda() {
		return descricaoBanda;
	}

	public void setDescricaoBanda(String descricaoBanda) {
		this.descricaoBanda = descricaoBanda;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}



	
	

}
