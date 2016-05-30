package br.com.showMustGoOn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.com.showMustGoOn.model.Banda;
import br.com.showMustGoOn.model.BandaMusicoFuncao;
import br.com.showMustGoOn.model.Evento;
import br.com.showMustGoOn.model.Funcao;
import br.com.showMustGoOn.model.Musico;
import br.com.showMustGoOn.service.perfilService;

@Named
@ViewScoped
public class perfilController extends BaseController implements Serializable {

	private static final long serialVersionUID = 6791589589135467083L;

	@Inject
	private perfilService perfilservice;

	private Musico musico;
	private List<Funcao> listaFuncoes;
	private List<Funcao> listaFuncoesSelecionadas;
	private List<Banda> listaBandas;
	private Banda bandaExibir;
	private Evento eventoExibir;
	private UploadedFile imagemMusico;
	private List<BandaMusicoFuncao> musicosBanda;
	private List<Evento> listaEventos;

	@PostConstruct
	public void init() {
		final FacesContext fc = FacesContext.getCurrentInstance();
		final HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		setMusico(perfilservice.obterMusico((String) session.getAttribute("emailLogado")));
		if (getMusico() != null) {
			setListaFuncoesSelecionadas(perfilservice.carregarFuncoesMusico(getMusico().getCodMusico()));
			setListaFuncoes(perfilservice.listarFuncoes());
			setListaEventos(perfilservice.listarEventosPorMusico(getMusico().getCodMusico()));
			setListaBandas(perfilservice.listarBandasMusico(getMusico().getCodMusico()));
			final String mensagemSucessoCadastroBanda = (String) fc.getExternalContext().getFlash()
					.get("msgSucessoBanda");
			if (StringUtils.isNotBlank(mensagemSucessoCadastroBanda)) {
				adicionarMensagem(mensagemSucessoCadastroBanda, FacesMessage.SEVERITY_INFO);
			}
		}
	}

	public String logoff() {
		final FacesContext fc = FacesContext.getCurrentInstance();
		final HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);

		session.removeAttribute("logado");
		return "index.xhtml?faces-redirect=true";
	}

	public void removerBanda() {
		final Integer codBanda = Integer.valueOf(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codBanda"));
		perfilservice.removerBanda(codBanda);
		setListaBandas(perfilservice.listarBandasMusico(getMusico().getCodMusico()));
		adicionarMensagem("Sua Banda foi removida!", FacesMessage.SEVERITY_INFO);
	}

	public void removerEvento() {
		final Integer codEvento = Integer.valueOf(
				FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codEvento"));
		perfilservice.removerEvento(codEvento);
		setListaEventos(perfilservice.listarEventosPorMusico(getMusico().getCodMusico()));
		adicionarMensagem("Seu Evento foi removido!", FacesMessage.SEVERITY_INFO);
	}

	public void fileUpload(FileUploadEvent event) throws IOException {
		try {
			getMusico().setImagem(event.getFile().getContents());
			setImagemMusico(event.getFile());
			salvar();

		} catch (final Exception ex) {
			ex.printStackTrace();
		}
	}

	public void salvar() {
		try {
			perfilservice.salvarMusico(getMusico());
			adicionarMensagem("Cadastro alterado com sucesso!", FacesMessage.SEVERITY_INFO);
		} catch (final Exception e) {
			adicionarMensagem(e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}

	}

	public void atualizarQualificacoes() {
		perfilservice.atualizarQualificacoes(musico, listaFuncoesSelecionadas);
		adicionarMensagem("Cadastro alterado com sucesso!", FacesMessage.SEVERITY_INFO);
	}

	public void setarCodBandaExibicao() {
		final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		final Integer codBanda = Integer.valueOf(externalContext.getRequestParameterMap().get("codBanda"));
		setBandaExibir(perfilservice.obterBanda(codBanda));
		setMusicosBanda(perfilservice.obterMusicosBanda(codBanda));
	}

	public void setarCodEventoExibicao() {
		final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		final Integer codEvento = Integer.valueOf(externalContext.getRequestParameterMap().get("codEvento"));
		setEventoExibir(perfilservice.obterEvento(codEvento));
	}

	public Musico getMusico() {
		return musico;
	}

	public void setMusico(Musico musico) {
		this.musico = musico;
	}

	public List<Funcao> getListaFuncoes() {
		return listaFuncoes;
	}

	public void setListaFuncoes(List<Funcao> listaFuncoes) {
		this.listaFuncoes = listaFuncoes;
	}

	public List<Funcao> getListaFuncoesSelecionadas() {
		return listaFuncoesSelecionadas;
	}

	public void setListaFuncoesSelecionadas(List<Funcao> listaFuncoesSelecionadas) {
		this.listaFuncoesSelecionadas = listaFuncoesSelecionadas;
	}

	public List<Banda> getListaBandas() {
		return listaBandas;
	}

	public void setListaBandas(List<Banda> listaBandas) {
		this.listaBandas = listaBandas;
	}

	public Banda getBandaExibir() {
		return bandaExibir;
	}

	public void setBandaExibir(Banda bandaExibir) {
		this.bandaExibir = bandaExibir;
	}

	public UploadedFile getImagemMusico() {
		return imagemMusico;
	}

	public void setImagemMusico(UploadedFile imagemMusico) {
		this.imagemMusico = imagemMusico;
	}

	public List<BandaMusicoFuncao> getMusicosBanda() {
		return musicosBanda;
	}

	public void setMusicosBanda(List<BandaMusicoFuncao> musicosBanda) {
		this.musicosBanda = musicosBanda;
	}

	public List<Evento> getListaEventos() {
		return listaEventos;
	}

	public void setListaEventos(List<Evento> listaEventos) {
		this.listaEventos = listaEventos;
	}

	public Evento getEventoExibir() {
		return eventoExibir;
	}

	public void setEventoExibir(Evento eventoExibir) {
		this.eventoExibir = eventoExibir;
	}

}
