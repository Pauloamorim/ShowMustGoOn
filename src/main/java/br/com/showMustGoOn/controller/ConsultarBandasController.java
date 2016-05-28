package br.com.showMustGoOn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.showMustGoOn.DTO.BandaDTO;
import br.com.showMustGoOn.model.Banda;
import br.com.showMustGoOn.model.Cidade;
import br.com.showMustGoOn.model.Estado;
import br.com.showMustGoOn.model.Musico;
import br.com.showMustGoOn.service.ConsultarBandaService;

@Named
@ViewScoped
public class ConsultarBandasController extends BaseController implements Serializable {

	private static final long serialVersionUID = -7013843804976849864L;
	@Inject
	private ConsultarBandaService servico;

	private List<Estado> listaEstados;
	private BandaDTO dto;
	private List<Banda> listaBanda;
	private Banda bandaExibir;

	@PostConstruct
	public void init() {
		setListaEstados(servico.listarEstados());
		setDto(new BandaDTO());
		setListaBanda(new ArrayList<Banda>());
	}

	public List<Cidade> listarCidades(String cidade) {
		return servico.listarCidadesPorEstado(getDto().getEstado().getCodEstado(), cidade);
	}

	public void pesquisar() {
		if (!verificarNenhumFiltroPreenchido()) {
			adicionarMensagem("Preencha ao menos um filtro para a consulta.", FacesMessage.SEVERITY_ERROR);
		} else {
			setListaBanda(servico.pesquisar(getDto()));
			if (getListaBanda() == null || getListaBanda().isEmpty()) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Nenhum Registro encontrado", ""));
			}
		}
	}

	private boolean verificarNenhumFiltroPreenchido() {

		Boolean filtroPreenchido = false;
		if (StringUtils.isNotBlank(getDto().getNome())) {
			filtroPreenchido = true;
		}
		if (getDto().getEstado() != null) {
			filtroPreenchido = true;
		}
		if (getDto().getCidade() != null) {
			filtroPreenchido = true;
		}
		if (getDto().getMusico() != null) {
			filtroPreenchido = true;
		}

		return filtroPreenchido;
	}

	public List<Musico> listarMusico(String nome) {
		return servico.listarMusicosPorNome(nome);
	}

	public void limparGridResultados() {
		setListaBanda(new ArrayList<Banda>());
	}

	public void limparFiltros() {
		setDto(new BandaDTO());
	}

	public void setarCodBandaExibicao() {
		final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		final Integer codBanda = Integer.valueOf(externalContext.getRequestParameterMap().get("codBanda"));
		setBandaExibir(servico.obterBanda(codBanda));
	}

	/////////////////////////////////////////////////
	/////////////// GETTERS AND SETTERS///////////////
	/////////////////////////////////////////////////

	public List<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public BandaDTO getDto() {
		return dto;
	}

	public void setDto(BandaDTO dto) {
		this.dto = dto;
	}

	public List<Banda> getListaBanda() {
		return listaBanda;
	}

	public void setListaBanda(List<Banda> listaBanda) {
		this.listaBanda = listaBanda;
	}

	public Banda getBandaExibir() {
		return bandaExibir;
	}

	public void setBandaExibir(Banda bandaExibir) {
		this.bandaExibir = bandaExibir;
	}

}
