package br.com.showMustGoOn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name="musico")
@Entity
public class Musico implements Serializable{

	private static final long serialVersionUID = -5720713929816252592L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_musico")
	private Integer codMusico;
	
	@Column(name="nome",nullable=false)
	private String nome;
	
	@Column(name="sobrenome",nullable=false)
	private String sobrenome;
	
	@Column(name="email",nullable=false)
	private String email;
	
	@Column(name="senha",nullable=false)
	private String senha;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_nascimento",nullable=false)
	private Date dataNascimento;
	
	//TODO mapear enum sexo
	
	@Column(name="telefone1")
	private String telefone1;
	
	@Column(name="telefone2")
	private String telefone2;
	
	@Column(name="pais",nullable=false)
	private String pais;
	
	@Column(name="estado",nullable=false)
	private String estado;
	
	@Column(name="cidade",nullable=false)
	private String cidade;
	
	@Column(name="bairro",nullable=false)
	private String bairro;
	
	@Column(name="rua",nullable=false)
	private String rua;
	
	@Column(name="complemento")
	private String complemento;
	
	
	/////////////////////////////////////////////////
	///////////////GETTERS AND SETTERS///////////////
	/////////////////////////////////////////////////
	
	public Integer getCodMusico() {
		return codMusico;
	}
	public void setCodMusico(Integer codMusico) {
		this.codMusico = codMusico;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getTelefone1() {
		return telefone1;
	}
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}
	public String getTelefone2() {
		return telefone2;
	}
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	
	
	
	

}
