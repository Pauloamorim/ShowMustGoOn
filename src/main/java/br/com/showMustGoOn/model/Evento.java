package br.com.showMustGoOn.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import br.com.showMustGoOn.converter.BaseEntity;

@Entity
@Table(name = "evento")
public class Evento implements Serializable, BaseEntity {

	private static final long serialVersionUID = -8469109513792027072L;
	@Id
	@Column(name = "cod_evento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nome")
	private String nome;

	@ManyToOne
	@JoinColumn(name = "estado")
	private Estado estado;

	@ManyToOne
	@JoinColumn(name = "cidade", nullable = false)
	private Cidade cidade;

	@Temporal(TemporalType.DATE)
	@Column(name = "data")
	private Date data;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "hora_inicio")
	private Date horaInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "hora_fim")
	private Date horaFim;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "endereco")
	private String endereco;

	@Column(name = "img_evento")
	private byte[] imagemEvento;

	@ManyToOne
	@JoinColumn(name = "cod_musico_responsavel", nullable = false)
	private Musico musicoResponsavel;

	@Column(name = "tel_contato")
	private String telefoneContato;

	@Transient
	public String getDataFormatada() {
		final SimpleDateFormat sdt = new SimpleDateFormat("dd/MM/yyyy");
		return sdt.format(getData());
	}

	@Transient
	public String getHoraInicioFormatada() {
		final SimpleDateFormat sdt = new SimpleDateFormat("HH:mm");
		return sdt.format(getHoraInicio());
	}

	@Transient
	public String getHoraFimFormatada() {
		if (getHoraFim() == null) {
			return StringUtils.EMPTY;
		}
		final SimpleDateFormat sdt = new SimpleDateFormat("HH:mm");
		return sdt.format(getHoraFim());
	}

	/////////////////////////////////////////////////
	/////////////// GETTERS AND SETTERS///////////////
	/////////////////////////////////////////////////

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public byte[] getImagemEvento() {
		return imagemEvento;
	}

	public void setImagemEvento(byte[] imagemEvento) {
		this.imagemEvento = imagemEvento;
	}

	public Musico getMusicoResponsavel() {
		return musicoResponsavel;
	}

	public void setMusicoResponsavel(Musico musicoResponsavel) {
		this.musicoResponsavel = musicoResponsavel;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

}
