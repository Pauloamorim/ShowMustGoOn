package br.com.showMustGoOn.controller;

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

import br.com.showMustGoOn.model.Banda;
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

	@PostConstruct
	public void init() {
		final FacesContext fc = FacesContext.getCurrentInstance();
		final HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		setMusico(perfilservice.obterMusico((String) session.getAttribute("emailLogado")));
		if (getMusico() != null) {
			setListaFuncoesSelecionadas(perfilservice.carregarFuncoesMusico(getMusico().getCodMusico()));
			setListaFuncoes(perfilservice.listarFuncoes());
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

}
