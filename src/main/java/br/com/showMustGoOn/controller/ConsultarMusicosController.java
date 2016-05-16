package br.com.showMustGoOn.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.showMustGoOn.DTO.MusicoDTO;
import br.com.showMustGoOn.enums.SexoEnum;
import br.com.showMustGoOn.model.Cidade;
import br.com.showMustGoOn.model.Estado;
import br.com.showMustGoOn.model.Funcao;
import br.com.showMustGoOn.model.Musico;
import br.com.showMustGoOn.service.ConsultarMusicosService;

@Named
@ViewScoped
public class ConsultarMusicosController implements Serializable {
	
	
	private static final long serialVersionUID = -7013843804976849864L;
	@Inject
	private ConsultarMusicosService servico;
	
	private List<Estado> listaEstados;
	private List<Musico> listaMusicos;
	private SexoEnum[] listaSexo = SexoEnum.values();
	private List<Funcao> listaFuncoes;
	private MusicoDTO dto;

	@PostConstruct
	public void init(){
		setListaEstados(servico.listarEstados());
		setListaMusicos(servico.listar());
		setListaFuncoes(servico.listarFuncoes());
		setDto(new MusicoDTO());
	}

	public List<Musico> getListaMusicos() {
		return listaMusicos;
	}
	
	public List<Cidade> listarCidades(String cidade){
		return servico.listarCidadesPorEstado(getDto().getEstado().getCodEstado(),cidade);
	}
	
	public void pesquisar(){
		
		setListaMusicos(null);
		
		if(getListaMusicos() == null || getListaMusicos().isEmpty()){
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN, "Nenhum Registro encontrado", ""));
		}
		
		getDto();
	}
	
	public MusicoDTO getDto() {
		return dto;
	}

	public void setDto(MusicoDTO dto) {
		this.dto = dto;
	}

	public List<Estado> getListaEstados() {
		return listaEstados;
	}

	public void setListaEstados(List<Estado> listaEstados) {
		this.listaEstados = listaEstados;
	}

	public SexoEnum[] getListaSexo() {
		return listaSexo;
	}

	public List<Funcao> getListaFuncoes() {
		return listaFuncoes;
	}

	public void setListaMusicos(List<Musico> listaMusicos) {
		this.listaMusicos = listaMusicos;
	}

	public void setListaFuncoes(List<Funcao> listaFuncoes) {
		this.listaFuncoes = listaFuncoes;
	}

}
