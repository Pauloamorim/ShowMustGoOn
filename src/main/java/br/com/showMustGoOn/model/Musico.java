package br.com.showMustGoOn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import br.com.showMustGoOn.converter.BaseEntity;
import br.com.showMustGoOn.enums.SexoEnum;
import br.com.showMustGoOn.enums.converter.SexoEnumConverter;

@Table(name = "musico")
@Entity
public class Musico implements Serializable, BaseEntity {

	private static final long serialVersionUID = -5720713929816252592L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_musico")
	private Integer codMusico;

	@Column(name = "nome", nullable = false)
	private String nome;

	@Column(name = "sobrenome", nullable = false)
	private String sobrenome;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "senha", nullable = false)
	private String senha;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento", nullable = false)
	private Date dataNascimento;

	@Convert(converter = SexoEnumConverter.class)
	@Column(name = "sexo", nullable = false)
	private SexoEnum sexo;

	@Column(name = "telefone1")
	private String telefone1;

	@Column(name = "telefone2")
	private String telefone2;

	@ManyToOne
	@JoinColumn(name = "estado", nullable = false)
	private Estado estado;

	@ManyToOne
	@JoinColumn(name = "cidade", nullable = false)
	private Cidade cidade;

	@Column(name = "bairro", nullable = false)
	private String bairro;

	@Column(name = "rua", nullable = false)
	private String rua;

	@Column(name = "complemento")
	private String complemento;

	@Column(name = "imagem")
	private byte[] imagem;

	@Transient
	private String confirmarSenha;

	@Transient
	public String getNomeSobrenomeEstadoCidadeMusico() {
		if (getEstado() == null || getCidade() == null) {
			return StringUtils.EMPTY;
		}
		return getNome().concat(" ").concat(getSobrenome()).concat(" - ").concat(getCidade().getNome()).concat(" - ")
				.concat(getEstado().getNome());
	}

	@Transient
	public String getNomeSobrenome() {
		return getNome().concat(" ").concat(getSobrenome());
	}

	/////////////////////////////////////////////////
	/////////////// GETTERS AND SETTERS///////////////
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

	public SexoEnum getSexo() {
		return sexo;
	}

	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	@Override
	public Integer getId() {
		return getCodMusico();
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

}
